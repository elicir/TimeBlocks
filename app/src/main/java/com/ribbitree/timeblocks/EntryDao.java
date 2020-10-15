package com.ribbitree.timeblocks;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface EntryDao {
    @Query("SELECT * FROM blockentry")
    LiveData<List<BlockEntry>> getAll();

    @Query("SELECT COUNT(*) FROM blockentry")
    int count();

    @Insert
    void insert(BlockEntry entry);

    @Insert
    void insertAll(List<BlockEntry> entries);

    @Update
    void update(BlockEntry entry);

    @Delete
    void delete(BlockEntry entry);

}
