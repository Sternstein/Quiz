package com.twister_ray.quiz;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;
import java.util.List;

@Dao
public interface CategoryDao {
  @Query("SELECT * FROM categories")
  LiveData<List<Category>> getAllCategories();

  @Query("SELECT * FROM categories WHERE id = :id")
  Category getById(long id);

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  void insert(Category category);

  @Update
  void update(Category category);

  @Delete
  void delete(Category category);
}
