package com.droidcoding.github.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.GravityCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.droidcoding.github.R;
import com.droidcoding.github.data.Funcs;
import com.droidcoding.github.data.Results;
import com.droidcoding.github.data.api.GithubService;
import com.droidcoding.github.data.api.IntentFactory;
import com.droidcoding.github.data.api.Order;
import com.droidcoding.github.data.api.SearchQuery;
import com.droidcoding.github.data.api.Sort;
import com.droidcoding.github.databinding.FragmentTrendingBinding;
import com.droidcoding.github.di.Injector;
import com.droidcoding.github.model.RepositoriesResponse;
import com.droidcoding.github.model.Repository;
import com.droidcoding.github.model.SearchResultToRepositoryList;
import com.droidcoding.github.model.TrendingTimespan;
import com.droidcoding.github.ui.adapter.RepositoryAdapter;
import com.droidcoding.github.ui.adapter.TrendingTimespanAdapter;
import com.jakewharton.rxbinding.widget.RxAdapterView;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import javax.inject.Inject;
import retrofit2.Response;
import retrofit2.Result;
import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;
import rx.subjects.PublishSubject;
import rx.subscriptions.CompositeSubscription;
import timber.log.Timber;

import static rx.android.schedulers.AndroidSchedulers.mainThread;

/**
 * Trending
 * Created by Donglua on 16/1/24.
 */
public class TrendingFragment extends NavBaseFragment {

  private FragmentTrendingBinding binding;
  private TrendingTimespanAdapter timespanAdapter;
  private RepositoryAdapter repositoryAdapter;
  @Inject GithubService githubService;
  @Inject IntentFactory mIntentFactory;

  private final CompositeSubscription subscriptions = new CompositeSubscription();
  private PublishSubject<TrendingTimespan> timespanSubject;

  private List<Repository> mRepositories;

  private final static int FRIST_PAGE = 1;
  private int page = FRIST_PAGE;

  private final Func1<TrendingTimespan, Observable<Result<RepositoriesResponse>>> trendingSearch =
      new Func1<TrendingTimespan, Observable<Result<RepositoriesResponse>>>() {
        @Override
        public Observable<Result<RepositoriesResponse>> call(TrendingTimespan trendingTimespan) {
          SearchQuery trendingQuery = new SearchQuery.Builder() //
              .createdSince(trendingTimespan.createdSince()) //
              .build();
          return githubService.repositories(trendingQuery, Sort.STARS, Order.DESC, page)
              .subscribeOn(Schedulers.io());
        }
      };

  private final Action1<Result<RepositoriesResponse>> trendingError = result -> {
    if (result.isError()) {
      Timber.e(result.error(), "Failed to get trending repositories");
    } else {
      Response<RepositoriesResponse> response = result.response();
      Timber.e("Failed to get trending repositories. Server returned %d", response.code());
    }
    binding.trendingListView.showRecycler();
  };

  public static TrendingFragment newInstance() {
    Bundle args = new Bundle();
    TrendingFragment fragment = new TrendingFragment();
    fragment.setArguments(args);
    return fragment;
  }

  @Override public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    mRepositories = new ArrayList<>();
    timespanSubject = PublishSubject.create();

    Injector.obtain(getContext()).inject(this);

    timespanAdapter = new TrendingTimespanAdapter(
        new ContextThemeWrapper(getContext(), R.style.Theme_U2020_TrendingTimespan));
    repositoryAdapter = new RepositoryAdapter(mRepositories);
    repositoryAdapter.setRepositoryClickListener(repository -> {
      startActivity(mIntentFactory.createUrlIntent(repository.html_url));
    });

    Observable<Result<RepositoriesResponse>> result = timespanSubject //
        .debounce(300, TimeUnit.MILLISECONDS) //
        .flatMap(trendingSearch) //
        .observeOn(mainThread()) //
        .share();

    subscriptions.add(result //
        .filter(Results.isSuccess()) //
        .map(SearchResultToRepositoryList.instance()) //
        .subscribe(repositories -> {
          if (page == FRIST_PAGE) mRepositories.clear();
          mRepositories.addAll(repositories);
          if (binding.trendingListView.getAdapter() == null) {
            binding.trendingListView.setAdapter(repositoryAdapter);
          }
          repositoryAdapter.notifyDataSetChanged();
        }));
    subscriptions.add(result //
        .filter(Funcs.not(Results.isSuccess())) //
        .subscribe(trendingError));
  }

  private void onRefresh() {
    page = FRIST_PAGE;
    timespanSubject.onNext(
        timespanAdapter.getItem(binding.trendingTimespan.getSelectedItemPosition()));
  }

  @Override public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    binding = FragmentTrendingBinding.inflate(inflater, container, false);
    binding.trendingToolbar.setNavigationIcon(R.drawable.menu_icon);
    binding.trendingToolbar.setNavigationOnClickListener(
        v -> getMainActivity().getDrawerLayout().openDrawer(GravityCompat.START));

    binding.trendingTimespan.setAdapter(timespanAdapter);

    binding.trendingListView.setLayoutManager(new LinearLayoutManager(getContext()));
    binding.trendingListView.setRefreshListener(this::onRefresh);
    binding.trendingListView.setupMoreListener(
        (overallItemsCount, itemsBeforeMore, maxLastVisiblePosition) -> {
          page += 1;
          timespanSubject.onNext(
              timespanAdapter.getItem(binding.trendingTimespan.getSelectedItemPosition()));
        }, 1);

    binding.trendingTimespan.setSelection(TrendingTimespan.WEEK.ordinal());

    subscriptions.add(RxAdapterView.itemSelections(binding.trendingTimespan)
        .observeOn(mainThread()).subscribe(position -> {
          binding.trendingTimespan.post(() -> {
            onRefresh();
            binding.trendingListView.setRefreshing(true);
          });
        }));

    return binding.getRoot();
  }

  @Override public void onDestroy() {
    super.onDestroy();
    subscriptions.unsubscribe();
  }
}
