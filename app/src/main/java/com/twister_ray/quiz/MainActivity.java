package com.twister_ray.quiz;

import android.Manifest;
import android.Manifest.permission;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProvider;


import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

  private AppViewModel mAppViewModel;
  private DataLoader dataLoader;
  public static final int PERMISSION_WRITE = 0;
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    final Button button1 = findViewById(R.id.button);
    final Button categoryButton = findViewById(R.id.button2);
    mAppViewModel = new ViewModelProvider(this).get(AppViewModel.class);
    dataLoader = new DataLoader(mAppViewModel);
    checkPermission();
    mAppViewModel.getQuestionWithAnswers(1).subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new DisposableSingleObserver<QuestionWithAnswers>() {
          @Override
          public void onSuccess(@NonNull QuestionWithAnswers question) {
            Log.d("myLog",String.valueOf(question.description));
            Log.d("myLog",String.valueOf(question.answers));
          }

          @Override
          public void onError(@NonNull Throwable e) {

              Log.d("myLog", e.getMessage());
              Log.d("myLog", "got error");
          }
        });
    Log.d("myLog", "Try to get questions");
    mAppViewModel.getAllQuestions().observe(this, questions -> {
        for (Question question: questions){
            Log.d("myLog", String.valueOf(question.getId()));
        }
    });

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

    public boolean checkPermission() {
        int READ_EXTERNAL_PERMISSION = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE);
        int WRITE_EXTERNAL_PERMISSION = ContextCompat.checkSelfPermission(this, permission.WRITE_EXTERNAL_STORAGE);
        if((READ_EXTERNAL_PERMISSION != PackageManager.PERMISSION_GRANTED)) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, PERMISSION_WRITE);
            return false;
        }
        if((WRITE_EXTERNAL_PERMISSION != PackageManager.PERMISSION_GRANTED)) {
          ActivityCompat.requestPermissions(this, new String[]{permission.WRITE_EXTERNAL_STORAGE}, PERMISSION_WRITE);
          return false;
        }
        return true;
    }

    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode==PERMISSION_WRITE && grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            Log.d("myLog", "Permissions are granted");
        }
    }
}