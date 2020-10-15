package com.ribbitree.timeblocks;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {BlockEntry.class}, version = 1)
public abstract class EntryDatabase extends RoomDatabase {
    public abstract EntryDao entryDao();
    public static final Object LOCK = new Object();
    private static volatile EntryDatabase sInstance;

    public static EntryDatabase getInstance(final Context context) {
        if (sInstance == null) {
            synchronized (LOCK) {
                if (sInstance == null) {
                    sInstance = Room.databaseBuilder(context.getApplicationContext(), EntryDatabase.class, "entry_database")
                            .addCallback(new Callback() {
                                @Override
                                public void onCreate(@NonNull SupportSQLiteDatabase db) {
                                    super.onCreate(db);
                                    AppExecutors.getInstance().diskIO().execute(new Runnable() {
                                        @Override
                                        public void run() {
                                            getInstance(context).entryDao().insertAll(BlockEntryDefault.populateBlockEntries());
                                        }
                                    });
                                }
                            })
                            .build();
                }
            }
        }
        return sInstance;
    }
}
