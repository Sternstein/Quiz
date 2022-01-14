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
  private final LiveData<List<Question>> allQuestions;
  private Single<Quiz> quiz;
  private LiveData<List<Quiz>> allQuizzesWithCategory;
  private Single<QuestionWithAnswers> questionWithAnswers;
  private Single<Question> question;
  private Single<Player> playerSettings;

  public AppViewModel(Application application) {
    super(application);
    mRepository = new AppRepository(application);
    allCategories = mRepository.getAllCategories();
    allQuizzes = mRepository.getAllQuizzes();
    allQuestions =  mRepository.getAllQuestion();
    playerSettings = mRepository.getPlayerSettings();
  }

  public Single<Player> getPlayerSettings() {
    return playerSettings;
  }

  public Single<Quiz> getQuizById(int id) {
    quiz = mRepository.getQuizById(id);
    return quiz;
  }

  public Single<QuestionWithAnswers> getQuestionWithAnswers(int id){
    questionWithAnswers = mRepository.getQuestionWithAnswers(id);
    return questionWithAnswers;
  }

  public Single<Question> getQuestion(long id){
    question = mRepository.getQuestionById(id);
    return question;
  }

  LiveData<List<Category>> getAllCategories() {
    return allCategories;
  }
  LiveData<List<Quiz>> getAllQuizzes() {
    return allQuizzes;
  }
  LiveData<List<Question>> getAllQuestions(){
    return allQuestions;
  }
  LiveData<List<Quiz>> getAllQuizzesWithCategory(int category){
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
  void setPlayerSettings(Player player){
    mRepository.insertPlayerSettings(player);
  }
  void updateQuiz(long id, boolean isFinished){
    mRepository.updateQuiz(id, isFinished);
  }

}