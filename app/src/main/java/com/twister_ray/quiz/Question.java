package com.twister_ray.quiz;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "questions")
public class Question {
  @PrimaryKey(autoGenerate = false)
  private int id;
  private String description;
  private int quiz;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public int getQuiz(){
    return quiz;
  }
  public void setQuiz(int quiz){
    this.quiz = quiz;
  }
}
