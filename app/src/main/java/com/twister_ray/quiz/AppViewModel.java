package com.twister_ray.quiz;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import java.util.List;

public class AppViewModel extends AndroidViewModel {

  private AppRepository mRepository;
  // Using LiveData and caching what getAlphabetizedWords returns has several benefits:
  // - We can put an observer on the data (instead of polling for changes) and only update the
  //   the UI when the data actually changes.
  // - Repository is completely separated from the UI through the ViewModel.
  private final LiveData<List<Category>> allCategories;

  public AppViewModel(Application application) {
    super(application);
    mRepository = new AppRepository(application);
    allCategories = mRepository.getAllCategories();
  }

  LiveData<List<Category>> getAllCategories() {
    return allCategories;
  }

  void insert(Category category) {
    mRepository.insert(category);
  }

}