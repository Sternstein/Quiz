package com.twister_ray.quiz;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "categories")
public class Category {
  @PrimaryKey(autoGenerate = false)
  private int id;
  private String name;

  public void setId(int newId){
    id = newId;
  }
  public int getId(){
    return id;
  }

  public String getName(){
    return name;
  }
  public void setName(String name){
    this.name = name;
  }
}
