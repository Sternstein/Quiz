package com.twister_ray.quiz;

import android.app.Application;
import androidx.lifecycle.LiveData;
import java.util.List;

class AppRepository {

  private CategoryDao categoryDao;
  private QuizDao quizDao;
  private LiveData<List<Category>> allCategories;

  AppRepository(Application application) {
    AppDatabase db = AppDatabase.getDatabase(application);
    categoryDao = db.categoryDao();
    quizDao = db.quizDao();
    allCategories = categoryDao.getAllCategories();
  }

  LiveData<List<Category>> getAllCategories() {
    return allCategories;
  }

  void insert_category(Category category) {
    AppDatabase.databaseWriteExecutor.execute(() -> {
      categoryDao.insert(category);
    });
  }

  void insert_quiz(Quiz quiz){
    AppDatabase.databaseWriteExecutor.execute(() -> {
      quizDao.insert(quiz);
    });
  }
}