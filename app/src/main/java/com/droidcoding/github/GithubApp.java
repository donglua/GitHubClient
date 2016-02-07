package com.droidcoding.github;

import android.app.Application;
import android.util.Log;
import com.crashlytics.android.Crashlytics;
import com.deploygate.sdk.DeployGate;
import com.droidcoding.github.di.DaggerGraph;
import com.droidcoding.github.di.component.DaggerGithubComponent;
import com.droidcoding.github.di.module.GithubModule;
import com.jakewharton.threetenabp.AndroidThreeTen;
import io.fabric.sdk.android.Fabric;
import timber.log.Timber;

/**
 * Created by Donglua on 16/1/21.
 */
public class GithubApp extends Application {

  private DaggerGraph mDaggerGraph;

  @Override public void onCreate() {
    super.onCreate();
    Fabric.with(this, new Crashlytics());

    DeployGate.install(this);

    AndroidThreeTen.init(this);

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
