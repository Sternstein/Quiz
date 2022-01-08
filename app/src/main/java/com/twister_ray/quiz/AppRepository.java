package com.twister_ray.quiz;

import android.app.Application;
import androidx.lifecycle.LiveData;

import io.reactivex.Single;
import java.util.List;

class AppRepository {

  private CategoryDao categoryDao;
  private QuizDao quizDao;
  private QuestionDao questionDao;
  private AnswerDao answerDao;
  private LiveData<List<Category>> allCategories;
  private LiveData<List<Quiz>> allQuizzes;
  private Single<Quiz> quiz;
  private Single<Question> question;
  private Single<QuestionWithAnswers> questionWithAnswersSingle;
  private LiveData<List<Question>> allQuestions;

  AppRepository(Application application) {
    AppDatabase db = AppDatabase.getDatabase(application);
    categoryDao = db.categoryDao();
    quizDao = db.quizDao();
    questionDao = db.questionDao();
    answerDao = db.answerDao();
    allCategories = categoryDao.getAllCategories();
    allQuizzes = quizDao.getAllQuizzes();
    allQuestions = questionDao.getAllQuestion();
  }

  LiveData<List<Category>> getAllCategories() {
    return allCategories;
  }
  LiveData<List<Quiz>> getAllQuizzes() {
    return allQuizzes;
  }
  LiveData<List<Question>> getAllQuestion() {return allQuestions;}
  public Single<QuestionWithAnswers> getQuestionWithAnswers(long id){
    questionWithAnswersSingle = questionDao.getQuestionAndAnswers(id);
    return questionWithAnswersSingle;
  }
  public Single<Question> getQuestionById(long id){
    question = questionDao.getQuestion(id);
    return question;
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

  void insertQuestion(Question question){
    AppDatabase.databaseWriteExecutor.execute(() -> {
      questionDao.insert(question);
    });
  }
  void insertAnswer(Answer answer){
    AppDatabase.databaseWriteExecutor.execute(() -> {
      answerDao.insert(answer);
    });
  }
}