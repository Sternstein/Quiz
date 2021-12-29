package com.twister_ray.quiz;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "categories")
public class Category {
  @PrimaryKey(autoGenerate = false)
  private long id;
  private String name;

  public void setId(long newId){
    id = newId;
  }
  public long getId(){
    return id;
  }

  public String getName(){
    return name;
  }
  public void setName(String name){
    this.name = name;
  }
}
