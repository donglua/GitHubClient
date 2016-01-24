package com.droidcoding.github.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.GravityCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.droidcoding.github.R;
import com.droidcoding.github.databinding.FragmentTrendingBinding;

/**
 * Trending
 * Created by Donglua on 16/1/24.
 */
public class TrendingFragment extends NavBaseFragment {

  private FragmentTrendingBinding mBinding;

  public static TrendingFragment newInstance() {
    Bundle args = new Bundle();
    TrendingFragment fragment = new TrendingFragment();
    fragment.setArguments(args);
    return fragment;
  }

  @Override public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    mBinding = FragmentTrendingBinding.inflate(inflater, container, false);
    mBinding.trendingToolbar.setNavigationIcon(R.drawable.menu_icon);
    mBinding.trendingToolbar.setNavigationOnClickListener(
        v -> getMainActivity().getDrawerLayout().openDrawer(GravityCompat.START));

    return mBinding.getRoot();
  }
}
