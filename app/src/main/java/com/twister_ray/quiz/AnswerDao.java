package com.twister_ray.quiz;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Update;

@Dao
public interface AnswerDao {

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  void insert(Answer answer);

  @Update
  void update(Answer answer);

  @Delete
  void delete(Answer answer);
}
