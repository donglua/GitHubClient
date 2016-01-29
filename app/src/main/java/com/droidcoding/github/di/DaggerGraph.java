package com.droidcoding.github.di;

import com.droidcoding.github.GithubApp;
import com.droidcoding.github.MainActivity;
import com.droidcoding.github.data.oauth.OauthService;
import com.droidcoding.github.ui.activity.OauthWebViewActivity;
import com.droidcoding.github.ui.activity.RepoDetailActivity;
import com.droidcoding.github.ui.fragment.TrendingFragment;

/**
 * Created by Donglua on 16/1/21.
 */
public interface DaggerGraph {
  void inject(GithubApp app);
  void inject(MainActivity app);
  void inject(OauthService oauthService);
  void inject(OauthWebViewActivity activity);
  void inject(TrendingFragment trendingFragment);
  void inject(RepoDetailActivity repoDetailActivity);
}
