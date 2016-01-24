package com.droidcoding.github;

import android.app.Application;
import android.util.Log;
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

    Timber.plant(BuildConfig.DEBUG ? new Timber.DebugTree() : new Timber.Tree() {
      @Override protected void log(int priority, String tag, String message, Throwable t) {
        if (priority == Log.ASSERT || priority == Log.DEBUG || priority == Log.VERBOSE) {
          return;
        }
        Log.println(priority, tag, message + Log.getStackTraceString(t));
      }
    });

    mDaggerGraph = DaggerGithubComponent.builder()
        .githubModule(new GithubModule(this))
        .build();
  }

  public DaggerGraph getDaggerGraph() {
    return mDaggerGraph;
  }
}
