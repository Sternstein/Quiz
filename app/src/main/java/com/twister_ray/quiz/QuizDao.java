package com.twister_ray.quiz;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;
import java.util.List;

@Dao
public interface QuizDao {
  @Query("SELECT * FROM quizzes")
  LiveData<List<Quiz>> getAllQuizzes();

  @Query("SELECT * FROM quizzes WHERE id = :id")
  Quiz getById(long id);

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  void insert(Quiz quiz);

  @Update
  void update(Quiz quiz);

  @Delete
  void delete(Quiz quiz);
}
