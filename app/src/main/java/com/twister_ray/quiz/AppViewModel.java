package com.twister_ray.quiz;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import java.util.List;

public class AppViewModel extends AndroidViewModel {

  private AppRepository mRepository;
  private final LiveData<List<Category>> allCategories;

  public AppViewModel(Application application) {
    super(application);
    mRepository = new AppRepository(application);
    allCategories = mRepository.getAllCategories();
  }

  LiveData<List<Category>> getAllCategories() {
    return allCategories;
  }

  void insert_category(Category category) {
    mRepository.insert_category(category);
  }
  void insert_quiz(Quiz quiz) {
    mRepository.insert_quiz(quiz);
  }

}