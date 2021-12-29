package com.twister_ray.quiz;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.lifecycle.ViewModelProvider;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

  private AppViewModel mAppViewModel;
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    final Button button1 = findViewById(R.id.button);
    final Button categoryButton = findViewById(R.id.button2);
    mAppViewModel = new ViewModelProvider(this).get(AppViewModel.class);
    button1.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        Log.d("myLog", "Start upload");
        NetworkService.getInstance().getJsonApi().getAllCategories().enqueue(
            new Callback<List<QuizCategoryJson>>() {
              @Override
              public void onResponse(@NonNull Call<List<QuizCategoryJson>> call,
                  @NonNull Response<List<QuizCategoryJson>> response) {
                List<QuizCategoryJson> categories = response.body();
                for (QuizCategoryJson categoryJson : categories){
                  Log.d("myLog", String.valueOf(categoryJson.getId()));
                  Log.d("myLog", categoryJson.getName());
                  Category category = new Category();
                  category.setId(categoryJson.getId());
                  category.setName(categoryJson.getName());
                  mAppViewModel.insert(category);
                }
              }

              @Override
              public void onFailure(Call<List<QuizCategoryJson>> call, Throwable t) {

              }
            });
      }
    });

    categoryButton.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        Intent intent = new Intent(MainActivity.this, CategoryActivity.class);
        startActivity(intent);

      }
    });
  }
}