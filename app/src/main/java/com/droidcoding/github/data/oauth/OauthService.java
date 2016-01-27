package com.droidcoding.github.data.oauth;

import android.app.IntentService;
import android.content.Intent;
import com.droidcoding.github.MainActivity;
import com.droidcoding.github.di.Injector;
import javax.inject.Inject;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

public final class OauthService extends IntentService {
  public static final String EXTRA_OAUTH = "oauth";

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

    Intent intent1 = new Intent(this, MainActivity.class);
    intent1.addFlags(FLAG_ACTIVITY_NEW_TASK);
    intent1.putExtra(EXTRA_OAUTH, 1);
    startActivity(intent1);
  }
}
