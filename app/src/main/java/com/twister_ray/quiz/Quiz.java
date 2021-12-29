package com.twister_ray.quiz;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "quizzes")
public class Quiz {
  @PrimaryKey(autoGenerate = false)
  private long id;

  private int score;

  public void setId(long newId){
    id = newId;
  }
  public long getId(){
    return id;
  }

  public int getScore(){
    return score;
  }
  public void setScore(int score){
    this.score = score;
  }
}
