package com.droidcoding.github.ui.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import com.droidcoding.github.R;
import com.droidcoding.github.data.Funcs;
import com.droidcoding.github.data.Results;
import com.droidcoding.github.data.api.GithubService;
import com.droidcoding.github.databinding.ActivityRepoDetailBinding;
import com.droidcoding.github.di.Injector;
import com.droidcoding.github.model.Repository;
import javax.inject.Inject;
import retrofit2.Response;
import retrofit2.Result;
import rx.Observable;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;
import timber.log.Timber;

import static rx.android.schedulers.AndroidSchedulers.mainThread;

/**
 * Created by Donglua on 16/1/29.
 */
public class RepoDetailActivity extends AppCompatActivity {

  private ActivityRepoDetailBinding mBinding;

  @Inject GithubService mGithubService;
  CompositeSubscription subscriptions = new CompositeSubscription();

  @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    mBinding = DataBindingUtil.setContentView(this, R.layout.activity_repo_detail);

    Injector.obtain(this).inject(this);

    String url = getIntent().getData().toString();
    Observable<Result<Repository>> result =
        mGithubService.repository(url) //
            .subscribeOn(Schedulers.io()) //
            .observeOn(mainThread()) //
            .share();

    subscriptions.add( //
        result.filter(Results.isSuccess()) //
        .map(Result::response) //
        .map(Response::body) //
        .subscribe(repository -> mBinding.setRepo(repository)));

    subscriptions.add(
        result.filter(Funcs.not(Results.isSuccess())) //
            .map(Result::error) //
            .subscribe(throwable -> //
                Timber.d(throwable, "repo err"))
    );

  }
}
