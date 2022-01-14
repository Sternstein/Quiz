package com.twister_ray.quiz;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;
import io.reactivex.Single;
import java.util.List;

@Dao
public interface QuizDao {
  @Query("SELECT * FROM quizzes")
  LiveData<List<Quiz>> getAllQuizzes();

  @Query("SELECT * FROM quizzes where category = :category")
  LiveData<List<Quiz>> getAllQuizzesWithCategory(int category);

  @Query("SELECT * FROM quizzes WHERE id = :id")
  Single<Quiz> getById(int id);

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  void insert(Quiz quiz);

  @Update
  void update(Quiz quiz);

  @Delete
  void delete(Quiz quiz);

  @Query("UPDATE quizzes SET is_finished = :isFinished WHERE id =:id")
  void update(long id, boolean isFinished);
}
