package com.twister_ray.quiz;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;

public class QuizJson {
  @SerializedName("id")
  @Expose
  private int id;

  @SerializedName("image")
  @Expose
  private String image;

  @SerializedName("question")
  @Expose
  private QuestionJson question;

  @SerializedName("answer")
  @Expose
  private List<AnswerJson> answerJsonList;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getImage() {
    return image;
  }

  public void setImage(String image) {
    this.image = image;
  }

  public QuestionJson getQuestion() {
    return question;
  }

  public List<AnswerJson> getAnswerJson() {
    return answerJsonList;
  }
}