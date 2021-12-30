package com.twister_ray.quiz;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Update;

@Dao
public interface QuestionDao {

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  void insert(Question question);

  @Update
  void update(Question question);

  @Delete
  void delete(Question question);
}
