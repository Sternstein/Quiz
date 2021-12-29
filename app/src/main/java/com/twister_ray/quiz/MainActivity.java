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
  private DataLoader dataLoader;
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    final Button button1 = findViewById(R.id.button);
    final Button categoryButton = findViewById(R.id.button2);
    mAppViewModel = new ViewModelProvider(this).get(AppViewModel.class);
    dataLoader = new DataLoader(mAppViewModel);
    button1.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        Log.d("myLog", "Start upload");
        dataLoader.loadCategories();
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