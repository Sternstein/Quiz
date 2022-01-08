package com.twister_ray.quiz;

import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import com.squareup.picasso.Picasso;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GameActivity extends AppCompatActivity {
  private AppViewModel mAppViewModel;
  long quiz;
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_game);
    final TextView textView = findViewById(R.id.textView);
    final Button button1 = findViewById(R.id.button1);
    final Button button2 = findViewById(R.id.button2);
    final Button button3 = findViewById(R.id.button3);
    final Button button4 = findViewById(R.id.button4);
    final ImageView imageView = findViewById(R.id.imageView);
    mAppViewModel = new ViewModelProvider(this).get(AppViewModel.class);

    Bundle extras = getIntent().getExtras();
    quiz = 0;
    if (extras != null) {
      quiz = extras.getLong("quiz");
    }
    if (quiz != 0){
      mAppViewModel.getQuestionWithAnswers(quiz).subscribeOn(Schedulers.io())
              .observeOn(AndroidSchedulers.mainThread())
              .subscribe(new DisposableSingleObserver<QuestionWithAnswers>() {
                @Override
                public void onSuccess(@NonNull QuestionWithAnswers question) {
                  textView.setText(question.description);
                  List<Answer> answers = question.answers;
                  button1.setText(answers.get(0).getDescription());
                  button2.setText(answers.get(1).getDescription());
                  button3.setText(answers.get(2).getDescription());
                  button4.setText(answers.get(3).getDescription());
                }

                @Override
                public void onError(@NonNull Throwable e) {

                  Log.d("myLog", e.getMessage());
                  Log.d("myLog", "got error");
                }
              });
    }
  }
}