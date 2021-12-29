package com.twister_ray.quiz;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class CategoryActivity extends AppCompatActivity {
  private AppViewModel mAppViewModel;
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_category);
    RecyclerView recyclerView = findViewById(R.id.recyclerview);
    final CategoryListAdapter adapter = new CategoryListAdapter(new CategoryListAdapter.CategoryDiff());
    recyclerView.setAdapter(adapter);
    recyclerView.setLayoutManager(new LinearLayoutManager(this));

    mAppViewModel = new ViewModelProvider(this).get(AppViewModel.class);

    mAppViewModel.getAllCategories().observe(this, categories -> {
      adapter.submitList(categories);
    });
  }
}