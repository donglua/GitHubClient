package com.droidcoding.github.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.droidcoding.github.databinding.ItemRepositoryBinding;
import com.droidcoding.github.model.Repository;
import com.droidcoding.github.ui.misc.DataBoundViewHolder;
import java.util.List;

/**
 * Repository Adapter
 * Created by Donglua on 16/1/23.
 */
public class RepositoryAdapter extends RecyclerView.Adapter<DataBoundViewHolder<ItemRepositoryBinding>> {

  private List<Repository> mRepositoryList;
  private RepositoryClickListener mRepositoryClickListener;
  public RepositoryAdapter(List<Repository> repositoryList) {
    mRepositoryList = repositoryList;
  }

  @Override public DataBoundViewHolder<ItemRepositoryBinding> onCreateViewHolder(ViewGroup parent,
      int viewType) {
    ItemRepositoryBinding binding =
        ItemRepositoryBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
    return new DataBoundViewHolder<>(binding);
  }

  @Override
  public void onBindViewHolder(DataBoundViewHolder<ItemRepositoryBinding> holder, int position) {
    final Repository repo = mRepositoryList.get(position);
    holder.getBinding().setRepo(repo);
    holder.getBinding().executePendingBindings();
    holder.getBinding().getRoot().setOnClickListener(v -> {
      if (mRepositoryClickListener != null) mRepositoryClickListener.onRepositoryClick(repo);
    });
  }

  @Override public int getItemCount() {
    return mRepositoryList.size();
  }

  public interface RepositoryClickListener {
    void onRepositoryClick(Repository repository);
  }

  public void setRepositoryClickListener(RepositoryClickListener repositoryClickListener) {
    mRepositoryClickListener = repositoryClickListener;
  }
}
