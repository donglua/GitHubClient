package com.droidcoding.github.ui.activity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.droidcoding.github.R;
import com.droidcoding.github.data.oauth.AccessToken;
import com.droidcoding.github.data.oauth.OauthInterceptor;
import com.droidcoding.github.data.oauth.OauthManager;
import com.droidcoding.github.data.oauth.OauthService;
import com.droidcoding.github.databinding.ActivityWebViewBinding;
import com.droidcoding.github.di.Injector;
import com.f2prateek.rx.preferences.Preference;
import javax.inject.Inject;

import static com.droidcoding.github.Setting.AUTHORIZATION_CALLBACK_URL;

/**
 * Created by Donglua on 16/1/22.
 */
public class OauthWebViewActivity extends AppCompatActivity {

  @Inject @AccessToken Preference<String> accessToken;
  @Inject OauthManager mOauthManager;
  @Inject OauthInterceptor mOauthInterceptor;

  @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    ActivityWebViewBinding binding =
        DataBindingUtil.setContentView(this, R.layout.activity_web_view);

    Injector.obtain(this).inject(this);

    Uri dataUri = getIntent().getData();
    binding.webView.loadUrl(dataUri.toString());

    binding.webView.setWebViewClient(new WebViewClient() {
      @Override public boolean shouldOverrideUrlLoading(WebView view, String url) {
        if (url.startsWith(AUTHORIZATION_CALLBACK_URL)){
          Uri data = Uri.parse(url);
          Intent serviceIntent = new Intent(OauthWebViewActivity.this, OauthService.class);
          serviceIntent.setData(data);
          startService(serviceIntent);
          finish();
        }
        return super.shouldOverrideUrlLoading(view, url);
      }
    });
  }

  //@Override protected void onNewIntent(Intent intent) {
  //  super.onNewIntent(intent);
  //
  //  Uri data = intent.getData();
  //  if (data == null) return;
  //
  //  if ("github".equals(data.getScheme())) {
  //    Intent serviceIntent = new Intent(this, OauthService.class);
  //    serviceIntent.setData(data);
  //    startService(serviceIntent);
  //  }
  //}
}
