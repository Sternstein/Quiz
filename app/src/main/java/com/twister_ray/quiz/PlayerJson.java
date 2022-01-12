package com.twister_ray.quiz;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PlayerJson {
  @SerializedName("uid")
  @Expose
  private String uid;

  @SerializedName("name")
  @Expose
  private String name;

  @SerializedName("password")
  @Expose
  private String password;

  public PlayerJson(String name){
    this.name = name;
  }

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
