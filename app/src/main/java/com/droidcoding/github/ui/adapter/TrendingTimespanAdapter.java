package com.droidcoding.github.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.droidcoding.github.R;
import com.droidcoding.github.model.TrendingTimespan;

public final class TrendingTimespanAdapter extends EnumAdapter<TrendingTimespan> {
  public TrendingTimespanAdapter(Context context) {
    super(context, TrendingTimespan.class);
  }

  @Override public View newView(LayoutInflater inflater, int position, ViewGroup container) {
    return inflater.inflate(R.layout.trending_timespan_view, container, false);
  }
}
