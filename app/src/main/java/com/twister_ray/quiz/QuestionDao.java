package com.twister_ray.quiz;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import io.reactivex.Single;

@Dao
public interface QuestionDao {

  @Query("SELECT * FROM questions")
  LiveData<List<Question>> getAllQuestion();

  @Query("SELECT * FROM questions where id = :id")
  Single<Question> getQuestion(long id);

  @Query("SELECT * FROM questions where quiz = :quiz")
  Single<QuestionWithAnswers> getQuestionAndAnswers(long quiz);

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  void insert(Question question);

  @Update
  void update(Question question);

  @Delete
  void delete(Question question);
}
