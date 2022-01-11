package com.twister_ray.quiz;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DataLoader {
  private AppViewModel mAppViewModel;
  String fileUri;
  final String BASE_URL = "https://quiz.andreygagarin.buzz";
  public DataLoader(AppViewModel appViewModel){
    mAppViewModel = appViewModel;
  }

  public void loadCategories(){
    NetworkService.getInstance().getJsonApi().getAllCategories().enqueue(
        new Callback<List<CategoryJson>>() {
          @Override
          public void onResponse(@NonNull Call<List<CategoryJson>> call,
              @NonNull Response<List<CategoryJson>> response) {
            List<CategoryJson> categories = response.body();
            for (CategoryJson categoryJson : categories){
              List<QuizJson> quizJsonList = categoryJson.getQuizJsonList();
              Log.d("myLog", String.valueOf(categoryJson.getId()));
              Log.d("myLog", categoryJson.getName());
              Log.d("myLog", String.valueOf(quizJsonList));

              Category category = new Category();
              category.setId(categoryJson.getId());
              category.setName(categoryJson.getName());
              mAppViewModel.insertCategory(category);
              for (QuizJson quizJson : quizJsonList){
                Log.d("myLog", String.valueOf(quizJson.getId()));
                Log.d("myLog", String.valueOf(quizJson.getAnswerJson()));
                loadQuiz(quizJson.getId(), categoryJson.getId());
              }
            }
          }

          @Override
          public void onFailure(Call<List<CategoryJson>> call, Throwable t) {

          }
        });
  }

  public void loadQuiz(int id, int categoryId){
    NetworkService.getInstance().getJsonApi().getQuizWithID(id).enqueue(new Callback<QuizJson>() {
      @Override
      public void onResponse(Call<QuizJson> call, Response<QuizJson> response) {
        QuizJson quizJson = response.body();
        if (quizJson != null){
          Log.d("myLog", String.valueOf(quizJson.getScore()));
          Log.d("myLog", String.valueOf(quizJson.getId()));
          Log.d("myLog", quizJson.getImage());
          QuestionJson questionJson = quizJson.getQuestion();
          List<AnswerJson> answerJsonList =  quizJson.getAnswerJson();
          Quiz quiz = new Quiz();
          quiz.setId(quizJson.getId());
          quiz.setScore(quizJson.getScore());
          quiz.setCategory(categoryId);
          String imageName = quiz.getId() + ".jpg";
          quiz.setImage(imageName);
          String url = BASE_URL + quizJson.getImage();
          DownloadImage(url, imageName);
          Log.d("myLog", String.valueOf(quiz.getScore()));
          Log.d("myLog", "try to save");
          mAppViewModel.insertQuiz(quiz);
          saveQuestions(questionJson, answerJsonList, quizJson.getId());
        }
      }

      @Override
      public void onFailure(Call<QuizJson> call, Throwable t) {

      }
    });
  }

  public void saveQuestions(QuestionJson questionJson, List<AnswerJson> answerJsonList, int quizId){
    Question question = new Question();
    question.setId(questionJson.getId());
    question.setQuiz(quizId);
    question.setDescription(questionJson.getDescription());
    mAppViewModel.insertQuestion(question);
    for (AnswerJson answerJson: answerJsonList){
      saveAnswer(answerJson);
    }
  }

  public void saveAnswer(AnswerJson answerJson){
    Answer answer = new Answer();
    answer.setId(answerJson.getId());
    answer.setDescription(answerJson.getDescription());
    answer.setQuestion(answerJson.getQuestionId());
    answer.setValid(answerJson.getIsValid());
    mAppViewModel.insertAnswer(answer);
  }

  public void DownloadImage(String url, String name) {
    Log.d("myLog", "upload from "+url);
    Picasso.get().load(url).into(new Target() {
      @Override
      public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
        try {
          File mydir = new File(Environment.getExternalStorageDirectory() +"");
          if (!mydir.exists()) {
            mydir.mkdirs();
          }

          fileUri = mydir.getAbsolutePath() + File.separator + name;
          FileOutputStream outputStream = new FileOutputStream(fileUri);

          bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);
          outputStream.flush();
          outputStream.close();
        } catch(IOException e) {
          e.printStackTrace();
        }
        Log.d("myLog", "try to download");
      }

      @Override
      public void onBitmapFailed(Exception e, Drawable errorDrawable) {
      }
      @Override
      public void onPrepareLoad(Drawable placeHolderDrawable) {
      }
    });
  }

}
