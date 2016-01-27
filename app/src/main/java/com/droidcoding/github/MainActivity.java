package com.droidcoding.github;

import android.annotation.TargetApi;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;
import com.droidcoding.github.data.Results;
import com.droidcoding.github.data.api.GithubService;
import com.droidcoding.github.data.oauth.AccessToken;
import com.droidcoding.github.data.oauth.OauthManager;
import com.droidcoding.github.databinding.ActivityMainBinding;
import com.droidcoding.github.databinding.MainDrawerHeaderBinding;
import com.droidcoding.github.di.Injector;
import com.droidcoding.github.ui.fragment.TrendingFragment;
import com.f2prateek.rx.preferences.Preference;
import com.jakewharton.rxbinding.view.RxView;
import javax.inject.Inject;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;
import timber.log.Timber;

import static android.widget.Toast.LENGTH_SHORT;
import static com.droidcoding.github.data.oauth.OauthService.EXTRA_OAUTH;
import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static rx.android.schedulers.AndroidSchedulers.mainThread;

public class MainActivity extends AppCompatActivity {

  @Inject OauthManager mOauthManager;
  @Inject GithubService mGithubService;
  @Inject @AccessToken Preference<String> token;
  private ActivityMainBinding mainBinding;
  private MainDrawerHeaderBinding headerBinding;

  CompositeSubscription subscriptions = new CompositeSubscription();

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    mainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

    Injector.obtain(this).inject(this);

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
      // Remove the status bar color. The DrawerLayout is responsible for drawing it from now on.
      setStatusBarColor(getWindow());
    }

    headerBinding =
        MainDrawerHeaderBinding.inflate(getLayoutInflater(), mainBinding.mainNavigation, false);
    mainBinding.mainNavigation.addHeaderView(headerBinding.getRoot());
    mainBinding.mainDrawerLayout.setStatusBarBackgroundColor(
        ContextCompat.getColor(this, R.color.colorPrimary));

    // 登录
    subscriptions.add(RxView.clicks(headerBinding.buttonSignIn)
        .debounce(300, MILLISECONDS)
        .observeOn(mainThread())
        .subscribe(aVoid -> startActivity(mOauthManager.createLoginIntent())));

    mainBinding.mainNavigation.setNavigationItemSelectedListener(item -> {
      switch (item.getItemId()) {
        case R.id.nav_search:
          Toast.makeText(MainActivity.this, "Search!", LENGTH_SHORT).show();
          break;
        case R.id.nav_trending:
          Toast.makeText(MainActivity.this, "Trending!", LENGTH_SHORT).show();
          break;
        default:
          throw new IllegalStateException("Unknown navigation item: " + item.getTitle());
      }
      mainBinding.mainDrawerLayout.closeDrawers();
      item.setChecked(true);
      return true;
    });

    getSupportFragmentManager().beginTransaction()
        .add(R.id.main_content, TrendingFragment.newInstance())
        .commit();

    getUserData();
  }


  public DrawerLayout getDrawerLayout() {
    return mainBinding.mainDrawerLayout;
  }

  @TargetApi(Build.VERSION_CODES.LOLLIPOP)
  private static void setStatusBarColor(Window window) {
    window.setStatusBarColor(Color.TRANSPARENT);
  }

  @Override protected void onNewIntent(Intent intent) {
    super.onNewIntent(intent);

    if (intent.hasExtra(EXTRA_OAUTH)) {
      getUserData();
    }
  }

  public void getUserData() {
    if (token.isSet()) {
      subscriptions.add(mGithubService.getUser()
          .subscribeOn(Schedulers.io())
          .observeOn(mainThread())
          .filter(Results.isSuccess())
          .map(userResult -> userResult.response().body())
          .subscribe(
              user -> headerBinding.setUser(user),
              throwable -> Timber.e(throwable, "Get User")));
    }
  }

  @Override protected void onDestroy() {
    subscriptions.unsubscribe();
    super.onDestroy();
  }
}
