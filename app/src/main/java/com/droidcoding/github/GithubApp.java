package com.droidcoding.github;

import android.app.Application;
import com.droidcoding.github.di.DaggerGraph;
import com.droidcoding.github.di.component.DaggerGithubComponent;
import com.droidcoding.github.di.module.GithubModule;
import timber.log.Timber;

/**
 * Created by Donglua on 16/1/21.
 */
public class GithubApp extends Application {

  private DaggerGraph mDaggerGraph;

  @Override public void onCreate() {
    super.onCreate();

    Timber.plant(new Timber.DebugTree());

    mDaggerGraph = DaggerGithubComponent.builder()
        .githubModule(new GithubModule(this))
        .build();
  }

  public DaggerGraph getDaggerGraph() {
    return mDaggerGraph;
  }
}
