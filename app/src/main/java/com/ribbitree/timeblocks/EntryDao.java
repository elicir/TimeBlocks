package com.ribbitree.timeblocks;

import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.ArrayList;

public interface EntryDao {
    @Query("SELECT * FROM blockentry")
    ArrayList<BlockEntry> getAll();

    @Insert
    void insert(BlockEntry entry);

    @Insert
    void insertAll(BlockEntry entry);

    @Update
    void update(BlockEntry entry);

    @Delete
    void delete(BlockEntry entry);

}
