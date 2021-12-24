package com.twister_ray.quiz;

import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.squareup.picasso.Picasso;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    final TextView textView = findViewById(R.id.textView);
    final Button button1 = findViewById(R.id.button1);
    final Button button2 = findViewById(R.id.button2);
    final Button button3 = findViewById(R.id.button3);
    final Button button4 = findViewById(R.id.button4);
    final ImageView imageView = findViewById(R.id.imageView);
    final String BASE_URL = "https://quiz.andreygagarin.buzz";

    NetworkService.getInstance().getJsonApi().getAllQuiz().enqueue(new Callback<List<Quiz>>() {
      @Override
      public void onResponse(@NonNull Call<List<Quiz>> call,@NonNull Response<List<Quiz>> response) {
        List<Quiz> quizzes = response.body();
        for (Quiz quiz : quizzes) {
          String url = BASE_URL + quiz.getImage();
          textView.setText(quiz.getQuestion().getDescription());
          List<AnswerJson> answers = quiz.getAnswerJson();
          button1.setText(answers.get(0).getDescription());
          button2.setText(answers.get(1).getDescription());
          button3.setText(answers.get(2).getDescription());
          button4.setText(answers.get(3).getDescription());
          Picasso.get()
              .load(url)
              .into(imageView);
        }
      }

      @Override
      public void onFailure(@NonNull Call<List<Quiz>> call, @NonNull Throwable t) {
        textView.append("Error occurred while getting request!");
        t.printStackTrace();
      }
    });
  }
}