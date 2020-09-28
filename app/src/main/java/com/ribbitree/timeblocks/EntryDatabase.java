package com.ribbitree.timeblocks;

import android.content.Context;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {BlockEntry.class}, version = 1)
public abstract class EntryDatabase extends RoomDatabase {
    public abstract EntryDao getEntryDao();

    private static volatile EntryDatabase DB_INSTANCE;
    private static final int NUMBER_OF_THREADS = 1;
    public static final ExecutorService databaseWriteExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static EntryDatabase getInstance(Context context) {
        if (DB_INSTANCE == null) {
            synchronized (EntryDatabase.class) {
                if (DB_INSTANCE == null) {
                    DB_INSTANCE = Room.databaseBuilder(context, EntryDatabase.class, "entry_database").build();
                }
            }
        }
        return DB_INSTANCE;
    }
}
