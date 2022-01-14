package com.twister_ray.quiz;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "quizzes")
public class Quiz {
  @PrimaryKey(autoGenerate = false)
  private int id;

  private int score;
  private int category;
  private String image;
  @ColumnInfo(name = "is_finished")
  private boolean isFinished;

  public String getImage() {
    return image;
  }
  public void setImage(String image) {
    this.image = image;
  }

  public void setId(int id){
    this.id = id;
  }
  public int getId(){
    return id;
  }

  public void setCategory(int category){
    this.category = category;
  }
  public int getCategory(){
    return category;
  }

  public int getScore(){
    return score;
  }
  public void setScore(int score){
    this.score = score;
  }

  public boolean isFinished() {
    return isFinished;
  }

  public void setFinished(boolean finished) {
    isFinished = finished;
  }
}
