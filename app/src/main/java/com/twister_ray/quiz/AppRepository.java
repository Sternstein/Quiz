package com.twister_ray.quiz;

import android.app.Application;
import androidx.lifecycle.LiveData;

import io.reactivex.Single;
import java.util.List;

class AppRepository {

  private CategoryDao categoryDao;
  private QuizDao quizDao;
  private LiveData<List<Category>> allCategories;
  private LiveData<List<Quiz>> allQuizzes;
  private Single<Quiz> quiz;

  AppRepository(Application application) {
    AppDatabase db = AppDatabase.getDatabase(application);
    categoryDao = db.categoryDao();
    quizDao = db.quizDao();
    allCategories = categoryDao.getAllCategories();
    allQuizzes = quizDao.getAllQuizzes();
  }

  LiveData<List<Category>> getAllCategories() {
    return allCategories;
  }
  LiveData<List<Quiz>> getAllQuizzes() {
    return allQuizzes;
  }
  public Single<Quiz> getQuizById(long id){
    quiz = quizDao.getById(id);
    return quiz;
  }
  void insertCategory(Category category) {
    AppDatabase.databaseWriteExecutor.execute(() -> {
      categoryDao.insert(category);
    });
  }

  LiveData<List<Quiz>> getQuizzesWithCategory(long category){
    allQuizzes = quizDao.getAllQuizzesWithCategory(category);
    return allQuizzes;
  }

  void insertQuiz(Quiz quiz){
    AppDatabase.databaseWriteExecutor.execute(() -> {
      quizDao.insert(quiz);
    });
  }
}