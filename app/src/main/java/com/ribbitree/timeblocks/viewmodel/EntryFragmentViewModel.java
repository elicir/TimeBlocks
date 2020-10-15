package com.ribbitree.timeblocks.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.ribbitree.timeblocks.BlockEntry;
import com.ribbitree.timeblocks.BlockEntryRepository;

import java.util.List;

public class EntryFragmentViewModel extends ViewModel {

    private final LiveData<List<BlockEntry>> mBlockEntries;
    private BlockEntryRepository entryRepository;

    public EntryFragmentViewModel() {
        entryRepository = new BlockEntryRepository();
        mBlockEntries = entryRepository.getBlockEntries();
    }

    public LiveData<List<BlockEntry>> getAllEntries() {
        return mBlockEntries;
    }

}
