package com.ribbitree.timeblocks;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

public class EntryFragmentViewModel extends ViewModel {

    private final LiveData<List<BlockEntry>> mBlockEntries;

    public EntryFragmentViewModel() {
        BlockEntryRepository entryRepository = new BlockEntryRepository();
        mBlockEntries = entryRepository.getBlockEntries();
    }

    public LiveData<List<BlockEntry>> getAllEntries() {
        return mBlockEntries;
    }
}
