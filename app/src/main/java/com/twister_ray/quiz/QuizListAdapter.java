package com.twister_ray.quiz;

import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

public class QuizListAdapter extends ListAdapter<Quiz, QuizViewHolder> {

  public QuizListAdapter(@NonNull DiffUtil.ItemCallback<Quiz> diffCallback) {
    super(diffCallback);
  }

  @Override
  public QuizViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    return QuizViewHolder.create(parent);
  }

  @Override
  public void onBindViewHolder(QuizViewHolder holder, int position) {
    Quiz current = getItem(position);
    holder.bind(current.getId());
  }

  static class QuizDiff extends DiffUtil.ItemCallback<Quiz> {

    @Override
    public boolean areItemsTheSame(@NonNull Quiz oldItem, @NonNull Quiz newItem) {
      return oldItem == newItem;
    }

    @Override
    public boolean areContentsTheSame(@NonNull Quiz oldItem, @NonNull Quiz newItem) {
      return String.valueOf(oldItem.getId()).equals(String.valueOf(newItem.getId()));
    }
  }

}