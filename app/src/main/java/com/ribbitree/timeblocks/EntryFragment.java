package com.ribbitree.timeblocks;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ribbitree.timeblocks.viewmodel.EntryFragmentViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class EntryFragment extends Fragment {

    private EntryAdapter mEntryAdapter;

    public EntryFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_entry, container, false);
        // Inflate the layout for this fragment

        RecyclerView mEntryRecyclerView = view.findViewById(R.id.entryRecyclerView);
        mEntryRecyclerView.addItemDecoration(new OverlapDecoration());
        mEntryRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mEntryRecyclerView.setHasFixedSize(true);
        mEntryAdapter = new EntryAdapter(new ArrayList<BlockEntry>(), (MainActivity) getActivity());
        mEntryRecyclerView.setAdapter(mEntryAdapter);

        EntryFragmentViewModel mModel = new ViewModelProvider(this).get(EntryFragmentViewModel.class);
        mModel.getAllEntries().observe(this, new Observer<List<BlockEntry>>() {
            @Override
            public void onChanged(List<BlockEntry> blockEntries) {
                mEntryAdapter.addEntries((ArrayList<BlockEntry>)blockEntries);
            }
        });
        return view;
    }
}