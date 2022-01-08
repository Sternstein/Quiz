package com.twister_ray.quiz;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "quizzes")
public class Quiz {
  @PrimaryKey(autoGenerate = false)
  private long id;

  private int score;
  private long category;
  private String image;

  public String getImage() {
    return image;
  }
  public void setImage(String image) {
    this.image = image;
  }

  public void setId(long id){
    this.id = id;
  }
  public long getId(){
    return id;
  }

  public void setCategory(long category){
    this.category = category;
  }
  public long getCategory(){
    return category;
  }

  public int getScore(){
    return score;
  }
  public void setScore(int score){
    this.score = score;
  }
}
