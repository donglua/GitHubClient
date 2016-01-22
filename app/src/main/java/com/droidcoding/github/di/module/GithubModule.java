package com.droidcoding.github.di.module;

import android.app.Application;
import com.droidcoding.github.GithubApp;
import com.droidcoding.github.di.scope.ApplicationScope;
import dagger.Module;
import dagger.Provides;

/**
 * Created by Donglua on 16/1/22.
 */
@Module
public class GithubModule {

  private final GithubApp app;

  public GithubModule(GithubApp app) {
    this.app = app;
  }

  @Provides @ApplicationScope Application provideApplication() {
    return app;
  }

}
