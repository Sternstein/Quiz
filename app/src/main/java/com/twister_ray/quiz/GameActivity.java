package com.twister_ray.quiz;

import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import com.squareup.picasso.Picasso;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;


public class GameActivity extends AppCompatActivity {
  private AppViewModel mAppViewModel;
  int quizId;
  ArrayList<Button> buttons;
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_game);
    final MediaPlayer winSound = MediaPlayer.create(this, R.raw.win);
    final MediaPlayer loseSound = MediaPlayer.create(this, R.raw.wrong);
    final TextView textView = findViewById(R.id.textView);
    final TextView playerInformation = findViewById(R.id.textView2);
    final Button button1 = findViewById(R.id.button1);
    final Button button2 = findViewById(R.id.button2);
    final Button button3 = findViewById(R.id.button3);
    final Button button4 = findViewById(R.id.button4);
    final ImageView imageView = findViewById(R.id.imageView);
    mAppViewModel = new ViewModelProvider(this).get(AppViewModel.class);
    buttons = new ArrayList<>();
    buttons.add(button1);
    buttons.add(button2);
    buttons.add(button3);
    buttons.add(button4);
    Bundle extras = getIntent().getExtras();
    quizId = 0;
    if (extras != null) {
      quizId = extras.getInt("quiz");
    }
    if (quizId != 0){

      mAppViewModel.getPlayerSettings().subscribeOn(Schedulers.io())
          .observeOn(AndroidSchedulers.mainThread())
          .subscribe(new DisposableSingleObserver<Player>() {
            @Override
            public void onSuccess(@NonNull Player player) {
              Log.d("myLog", "Settings are found " + player.getUid());
              String info = player.getName() + " : " + player.getLives();
              playerInformation.setText(info);
            }

            @Override
            public void onError(@NonNull Throwable e) {

              Log.d("myLog", e.getMessage());
              Log.d("myLog", "got error");
            }
          });


      mAppViewModel.getQuizById(quizId).subscribeOn(Schedulers.io())
          .observeOn(AndroidSchedulers.mainThread())
          .subscribe(new DisposableSingleObserver<Quiz>() {
                       @Override
                       public void onSuccess(@NonNull Quiz quiz) {
                         File mydir = new File(Environment.getExternalStorageDirectory() +"");
                         String name = quiz.getImage();
                         String fileUri = mydir.getAbsolutePath() + File.separator + name;
                         File imageFile = new File(fileUri);
                         Picasso.get().load(imageFile).into(imageView);
                         if (quiz.isFinished()){
                           for (Button button: buttons){
                             button.setEnabled(false);
                           }
                         }
                       }

                       @Override
                       public void onError(@NonNull Throwable e) {

                       }
                     });
      mAppViewModel.getQuestionWithAnswers(quizId).subscribeOn(Schedulers.io())
          .observeOn(AndroidSchedulers.mainThread())
          .subscribe(new DisposableSingleObserver<QuestionWithAnswers>() {
            @Override
            public void onSuccess(@NonNull QuestionWithAnswers question) {
              textView.setText(question.description);
              List<Answer> answers = question.answers;
              for (int i = 0; i < answers.size(); i = i + 1){
                Button currentButton = buttons.get(i);
                Answer answer = answers.get(i);
                currentButton.setText(answer.getDescription());
                currentButton.setOnClickListener(new OnClickListener() {
                  @Override
                  public void onClick(View v) {
                    if (answer.getValid()) {
                      mAppViewModel.updateQuiz(quizId, true);
                      currentButton.setBackgroundColor(Color.GREEN);
                      winSound.start();
                      Log.d("myLog","CORRECT ANSWER!");
                      finish();
                    }
                    else {
                      loseSound.start();
                      Log.d("myLog","WRONG ANSWER!");
                    }
                  }
                });
              }
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