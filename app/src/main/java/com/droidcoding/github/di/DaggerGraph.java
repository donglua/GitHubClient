package com.droidcoding.github.di;

import com.droidcoding.github.GithubApp;
import com.droidcoding.github.MainActivity;
import com.droidcoding.github.api.oauth.OauthService;
import com.droidcoding.github.ui.OauthWebViewActivity;

/**
 * Created by Donglua on 16/1/21.
 */
public interface DaggerGraph {
  void inject(GithubApp app);
  void inject(MainActivity app);
  void inject(OauthService oauthService);
  void inject(OauthWebViewActivity activity);
}
