package com.droidcoding.github.ui.widget;

import android.content.Context;
import android.util.AttributeSet;
import com.malinskiy.superrecyclerview.SuperRecyclerView;

/**
 * Created by Donglua on 16/1/25.
 */
public class SwipeRecyclerView extends SuperRecyclerView {

  public SwipeRecyclerView(Context context) {
    super(context);
  }

  public SwipeRecyclerView(Context context, AttributeSet attrs) {
    super(context, attrs);
  }

  public SwipeRecyclerView(Context context, AttributeSet attrs, int defStyle) {
    super(context, attrs, defStyle);
  }

  public void setRefreshing(boolean refreshing) {
      mPtrLayout.setRefreshing(refreshing);
  }

}
