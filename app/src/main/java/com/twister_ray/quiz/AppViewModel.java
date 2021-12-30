package com.twister_ray.quiz;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import io.reactivex.Single;
import java.util.List;

public class AppViewModel extends AndroidViewModel {

  private AppRepository mRepository;
  private final LiveData<List<Category>> allCategories;
  private final LiveData<List<Quiz>> allQuizzes;
  private Single<Quiz> quiz;
  private LiveData<List<Quiz>> allQuizzesWithCategory;

  public AppViewModel(Application application) {
    super(application);
    mRepository = new AppRepository(application);
    allCategories = mRepository.getAllCategories();
    allQuizzes = mRepository.getAllQuizzes();
  }

  public Single<Quiz> getQuizById(long id) {
    quiz = mRepository.getQuizById(id);
    return quiz;
  }

  LiveData<List<Category>> getAllCategories() {
    return allCategories;
  }
  LiveData<List<Quiz>> getAllQuizzes() {
    return allQuizzes;
  }
  LiveData<List<Quiz>> getAllQuizzesWithCategory(long category){
    allQuizzesWithCategory = mRepository.getQuizzesWithCategory(category);
    return allQuizzesWithCategory;
  }

  void insertCategory(Category category) {
    mRepository.insertCategory(category);
  }
  void insertQuiz(Quiz quiz) {
    mRepository.insertQuiz(quiz);
  }
  void insertQuestion(Question question){
    mRepository.insertQuestion(question);
  }
  void insertAnswer(Answer answer){
    mRepository.insertAnswer(answer);
  }

}