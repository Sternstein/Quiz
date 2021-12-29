package com.twister_ray.quiz;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;

public class QuizCategoryJson {
  @SerializedName("id")
  @Expose
  private int id;

  @SerializedName("name")
  @Expose
  private String name;

  @SerializedName("quiz")
  @Expose
  private List<QuizJson> quizJsonList;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public List<QuizJson> getQuizJsonList(){
    return quizJsonList;
  }

  public void setQuizJsonList(List <QuizJson> quizJsonList) {
    this.quizJsonList = quizJsonList;
  }

}
