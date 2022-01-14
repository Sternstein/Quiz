package com.twister_ray.quiz;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Category.class, Quiz.class, Question.class, Answer.class, Player.class}, version = 4, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
  abstract CategoryDao categoryDao();
  abstract QuizDao quizDao();
  abstract QuestionDao questionDao();
  abstract AnswerDao answerDao();
  abstract PlayerDao playerDao();

  private static volatile AppDatabase INSTANCE;
  private static final int NUMBER_OF_THREADS = 4;
  static final ExecutorService databaseWriteExecutor =
      Executors.newFixedThreadPool(NUMBER_OF_THREADS);

  static final Migration MIGRATION_2_3 = new Migration(2, 3) {
    @Override
    public void migrate(SupportSQLiteDatabase database) {
      database.execSQL("CREATE TABLE IF NOT EXISTS `player_settings` "
          + "(`uid` TEXT NOT NULL, `name` TEXT, `password` TEXT, PRIMARY KEY(`uid`))");
    }
  };

  static final Migration MIGRATION_3_4 = new Migration(3, 4) {
    @Override
    public void migrate(SupportSQLiteDatabase database) {
      database.execSQL("ALTER TABLE quizzes "
          + " ADD COLUMN is_finished INTEGER NOT NULL DEFAULT 0");

    }
  };

  static final Migration MIGRATION_4_5 = new Migration(3, 4) {
    @Override
    public void migrate(SupportSQLiteDatabase database) {
      database.execSQL("ALTER TABLE player_settings "
          + " ADD COLUMN lives INTEGER NOT NULL DEFAULT 5");

    }
  };

  static AppDatabase getDatabase(final Context context) {
    if (INSTANCE == null) {
      synchronized (AppDatabase.class) {
        if (INSTANCE == null) {
          INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
              AppDatabase.class, "quiz_database")
              .addMigrations(MIGRATION_2_3)
              .addMigrations(MIGRATION_3_4)
              .addMigrations(MIGRATION_4_5)
              .build();
        }
      }
    }
    return INSTANCE;
  }

}