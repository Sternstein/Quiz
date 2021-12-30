package com.twister_ray.quiz;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "answers")
public class Answer {
  @PrimaryKey(autoGenerate = false)
  private long id;
  private String description;
  private long question;
  private boolean valid;
  public long getId() {
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

  public long getQuestion(){
    return question;
  }
  public void setQuestion(long question){
    this.question = question;
  }
}
