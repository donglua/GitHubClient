package com.droidcoding.github.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.GravityCompat;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.droidcoding.github.R;
import com.droidcoding.github.databinding.FragmentTrendingBinding;
import com.droidcoding.github.ui.adapter.TrendingTimespanAdapter;

/**
 * Trending
 * Created by Donglua on 16/1/24.
 */
public class TrendingFragment extends NavBaseFragment {

  private FragmentTrendingBinding binding;
  private TrendingTimespanAdapter timespanAdapter;

  public static TrendingFragment newInstance() {
    Bundle args = new Bundle();
    TrendingFragment fragment = new TrendingFragment();
    fragment.setArguments(args);
    return fragment;
  }

  @Override public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    timespanAdapter = new TrendingTimespanAdapter(
        new ContextThemeWrapper(getContext(), R.style.Theme_U2020_TrendingTimespan));
  }

  @Override public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    binding = FragmentTrendingBinding.inflate(inflater, container, false);
    binding.trendingToolbar.setNavigationIcon(R.drawable.menu_icon);
    binding.trendingToolbar.setNavigationOnClickListener(
        v -> getMainActivity().getDrawerLayout().openDrawer(GravityCompat.START));

    binding.trendingTimespan.setAdapter(timespanAdapter);
    //binding.trendingTimespan.setOnItemClickListener((parent, view, position, id) -> {
    //
    //});

    return binding.getRoot();
  }
}
