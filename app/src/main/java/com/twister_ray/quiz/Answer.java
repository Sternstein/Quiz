package com.twister_ray.quiz;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "answers")
public class Answer {
  @PrimaryKey(autoGenerate = false)
  private int id;
  private String description;
  private int question;
  private boolean valid;
  public int getId() {
    return id;
  }

  public boolean getValid(){
    return valid;
  }
  public void setValid(boolean valid) {
    this.valid = valid;
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

  public int getQuestion(){
    return question;
  }
  public void setQuestion(int question){
    this.question = question;
  }
}
