package com.twister_ray.quiz;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;


@Entity(tableName = "player_settings")
public class Player {
  @PrimaryKey(autoGenerate = false)
  @NonNull
  private String uid;

  private String name;
  private String password;

  public void setName(String name){
    this.name = name;
  }
  public String getName(){
    return name;
  }

  public void setUid(String uid){
    this.uid = uid;
  }
  public String getUid(){
    return uid;
  }

  public void setPassword(String password){
    this.password = password;
  }
  public String getPassword(){
    return password;
  }
}
