package com.twister_ray.quiz;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;
import io.reactivex.Single;

@Dao
public interface PlayerDao {

  @Query("SELECT * FROM player_settings limit 1")
  Single<Player> getPlayerSettings();

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  void insert(Player question);

  @Update
  void update(Player question);

  @Delete
  void delete(Player question);
}
