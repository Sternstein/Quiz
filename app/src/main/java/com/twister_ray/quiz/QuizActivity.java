package com.twister_ray.quiz;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class QuizActivity extends AppCompatActivity {
  private AppViewModel mAppViewModel;
  private int category;
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_quiz);
    RecyclerView recyclerView = findViewById(R.id.recyclerview_quiz);
    final QuizListAdapter adapter = new QuizListAdapter(new QuizListAdapter.QuizDiff());
    recyclerView.setAdapter(adapter);
    recyclerView.setLayoutManager(new LinearLayoutManager(this));
    Bundle extras = getIntent().getExtras();
    category = 0;
    if (extras != null) {
      category = extras.getInt("category");
    }
    mAppViewModel = new ViewModelProvider(this).get(AppViewModel.class);
    if (category != 0) {
      mAppViewModel.getAllQuizzesWithCategory(category).observe(this, quizzes -> {
        adapter.submitList(quizzes);
      });
    }
  }
}