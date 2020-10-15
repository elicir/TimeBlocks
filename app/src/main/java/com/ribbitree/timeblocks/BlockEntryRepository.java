package com.ribbitree.timeblocks;

import androidx.lifecycle.LiveData;

import java.util.List;

public class BlockEntryRepository {

    private final LiveData<List<BlockEntry>> blockEntries;

    public BlockEntryRepository() {
        blockEntries = EntryDatabase.getInstance(TimeBlockApplication.getInstance()).entryDao().getAll();
    }

    public LiveData<List<BlockEntry>> getBlockEntries() {
        return blockEntries;
    }

}
