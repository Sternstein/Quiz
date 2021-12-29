package com.twister_ray.quiz;

import android.app.Application;
import androidx.lifecycle.LiveData;
import java.util.List;

class AppRepository {

  private CategoryDao categoryDao;
  private LiveData<List<Category>> allCategories;

  // Note that in order to unit test the WordRepository, you have to remove the Application
  // dependency. This adds complexity and much more code, and this sample is not about testing.
  // See the BasicSample in the android-architecture-components repository at
  // https://github.com/googlesamples
  AppRepository(Application application) {
    AppDatabase db = AppDatabase.getDatabase(application);
    categoryDao = db.categoryDao();
    allCategories = categoryDao.getAllCategories();
  }

  // Room executes all queries on a separate thread.
  // Observed LiveData will notify the observer when the data has changed.
  LiveData<List<Category>> getAllCategories() {
    return allCategories;
  }

  // You must call this on a non-UI thread or your app will throw an exception. Room ensures
  // that you're not doing any long running operations on the main thread, blocking the UI.
  void insert(Category category) {
    AppDatabase.databaseWriteExecutor.execute(() -> {
      categoryDao.insert(category);
    });
  }
}