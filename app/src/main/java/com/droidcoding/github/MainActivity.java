package com.droidcoding.github;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;
import com.droidcoding.github.databinding.ActivityMainBinding;
import com.droidcoding.github.ui.fragment.TrendingFragment;

import static android.widget.Toast.LENGTH_SHORT;

public class MainActivity extends AppCompatActivity {

  private ActivityMainBinding mBinding;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

    mBinding.mainNavigation.setNavigationItemSelectedListener(item -> {
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
      mBinding.mainDrawerLayout.closeDrawers();
      item.setChecked(true);
      return true;
    });

    getSupportFragmentManager().beginTransaction()
        .add(R.id.main_content, TrendingFragment.newInstance())
        .commit();
  }

  public DrawerLayout getDrawerLayout() {
    return mBinding.mainDrawerLayout;
  }
}
