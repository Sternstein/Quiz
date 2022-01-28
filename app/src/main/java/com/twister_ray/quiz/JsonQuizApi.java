package com.twister_ray.quiz;

import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface JsonQuizApi {
  @GET("/quizzes.json")
  public Call<List<QuizJson>> getAllQuiz();

  @POST("/categories.json")
  public Call<List<CategoryJson>> getAllCategories(@Body PlayerJson player);

  @GET("/quizzes/{id}.json")
  public Call<QuizJson> getQuizWithID(@Path("id") int id);

  @POST("/players/register")
  public Call<PlayerJson> createPlayer(@Body PlayerJson player);
}
