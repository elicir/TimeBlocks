package com.ribbitree.timeblocks;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {BlockEntry.class}, version = 1)
public abstract class EntryDatabase extends RoomDatabase {
    public abstract EntryDao getEntryDao();

    private static EntryDatabase entryDB;

    public static EntryDatabase getInstance(Context context) {
        if (null == entryDB) {
            entryDB = buildDatabaseInstance(context);
        }
        return entryDB;
    }

    private static EntryDatabase buildDatabaseInstance(Context context) {
        return Room.databaseBuilder(context, EntryDatabase.class, "entry-database").build();
    }

}
