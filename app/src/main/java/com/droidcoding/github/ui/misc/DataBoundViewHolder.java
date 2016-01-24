package com.droidcoding.github.ui.misc;

import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;

/**
 * Created by Donglua on 15/9/18.
 */
public class DataBoundViewHolder<T extends ViewDataBinding> extends RecyclerView.ViewHolder {
  private T binding;

  public DataBoundViewHolder(T binding) {
    super(binding.getRoot());
    this.binding = binding;
  }

  public T getBinding() {
    return binding;
  }
}
