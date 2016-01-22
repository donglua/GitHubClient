package com.droidcoding.github.di;

import android.content.Context;
import com.droidcoding.github.GithubApp;

public final class Injector {

  public static DaggerGraph obtain(Context context) {
    return ((GithubApp)context.getApplicationContext()).getDaggerGraph();
  }

}
