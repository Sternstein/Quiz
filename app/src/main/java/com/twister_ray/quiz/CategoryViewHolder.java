package com.twister_ray.quiz;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.recyclerview.widget.RecyclerView;

public class CategoryViewHolder extends RecyclerView.ViewHolder {
  private final Button button;

  private CategoryViewHolder(View itemView) {
    super(itemView);
    button = itemView.findViewById(R.id.button);
  }

  public void bind(String text, int id) {
    button.setText(text);
    button.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        Context context = v.getContext();
        Intent intent = new Intent(context, QuizActivity.class);
        intent.putExtra("category",id);
        context.startActivity(intent);
      }
    });
  }

  static CategoryViewHolder create(ViewGroup parent) {
    View view = LayoutInflater.from(parent.getContext())
        .inflate(R.layout.recyclerview_item, parent, false);
    return new CategoryViewHolder(view);
  }
}