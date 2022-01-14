package com.twister_ray.quiz;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.recyclerview.widget.RecyclerView;

public class QuizViewHolder extends RecyclerView.ViewHolder {
  private final Button button;

  private QuizViewHolder(View itemView) {
    super(itemView);
    button = itemView.findViewById(R.id.button);
  }

  public void bind(int id, boolean isFinished) {
    button.setText(String.valueOf(id));
    if(isFinished) {
      button.setBackgroundColor(Color.GREEN);
    }
    button.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        Context context = v.getContext();
        Intent intent = new Intent(context, GameActivity.class);
        intent.putExtra("quiz",id);
        context.startActivity(intent);
      }
    });
  }

  static QuizViewHolder create(ViewGroup parent) {
    View view = LayoutInflater.from(parent.getContext())
        .inflate(R.layout.recyclerview_item, parent, false);
    return new QuizViewHolder(view);
  }
}