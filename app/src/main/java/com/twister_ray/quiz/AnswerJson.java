package com.twister_ray.quiz;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AnswerJson {
  @SerializedName("id")
  @Expose
  private int id;

  @SerializedName("description")
  @Expose
  private String description;

  @SerializedName("is_valid")
  @Expose
  private boolean isValid;

  public int getId() {
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

  public boolean getIsValid() {
    return isValid;
  }

  public void setIsValid(boolean isValid) {
    this.isValid = isValid;
  }
}
