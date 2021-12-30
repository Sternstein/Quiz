package com.twister_ray.quiz;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "questions")
public class Question {
  @PrimaryKey(autoGenerate = false)
  private long id;
  private String description;
  private long quiz;

  public long getId() {
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

  public long getQuiz(){
    return quiz;
  }
  public void setQuiz(long quiz){
    this.quiz = quiz;
  }
}
