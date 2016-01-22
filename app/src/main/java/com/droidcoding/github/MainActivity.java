package com.droidcoding.github;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import com.droidcoding.github.api.oauth.OauthInterceptor;
import com.droidcoding.github.api.oauth.OauthManager;
import com.droidcoding.github.di.Injector;
import javax.inject.Inject;

public class MainActivity extends AppCompatActivity {

  @Inject OauthInterceptor mOauthInterceptor;
  @Inject OauthManager mOauthManager;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);

    Injector.obtain(this).inject(this);

    Intent intent = mOauthManager.createLoginIntent();
    startActivity(intent);
  }
}
