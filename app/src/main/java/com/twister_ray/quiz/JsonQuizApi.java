package com.twister_ray.quiz;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;

public interface JsonQuizApi {
  @GET("/quizzes.json")
  public Call<List<QuizJson>> getAllQuiz();

  @GET("/categories.json")
  public Call<List<QuizCategoryJson>> getAllCategories();
}
