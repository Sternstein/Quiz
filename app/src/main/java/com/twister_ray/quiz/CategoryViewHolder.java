package com.twister_ray.quiz;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.recyclerview.widget.RecyclerView;

public class CategoryViewHolder extends RecyclerView.ViewHolder {
  private final Button button;

  private CategoryViewHolder(View itemView) {
    super(itemView);
    button = itemView.findViewById(R.id.button);
  }

  public void bind(String text) {
    button.setText(text);
  }

  static CategoryViewHolder create(ViewGroup parent) {
    View view = LayoutInflater.from(parent.getContext())
        .inflate(R.layout.recyclerview_item, parent, false);
    return new CategoryViewHolder(view);
  }
}