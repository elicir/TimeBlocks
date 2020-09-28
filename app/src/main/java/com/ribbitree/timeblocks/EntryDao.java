package com.ribbitree.timeblocks;

import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

public interface EntryDao {
    @Query("SELECT * FROM blockentry")
    List<BlockEntry> getAll();

    @Insert
    void insert(BlockEntry entry);

    @Update
    void update(BlockEntry entry);

    @Delete
    void delete(BlockEntry entry);

}
