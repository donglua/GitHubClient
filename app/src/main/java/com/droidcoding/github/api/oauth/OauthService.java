package com.droidcoding.github.api.oauth;

import android.app.IntentService;
import android.content.Intent;
import com.droidcoding.github.di.Injector;
import javax.inject.Inject;

public final class OauthService extends IntentService {
  @Inject OauthManager oauthManager;

  public OauthService() {
    super(OauthService.class.getSimpleName());
  }

  @Override public void onCreate() {
    super.onCreate();
    Injector.obtain(getApplication()).inject(this);
  }

  @Override protected void onHandleIntent(Intent intent) {
    oauthManager.handleResult(intent.getData());
  }
}
