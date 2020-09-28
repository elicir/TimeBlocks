package com.ribbitree.timeblocks;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.view.inputmethod.EditorInfo;
import android.view.KeyEvent;

import java.util.ArrayList;

public class MainScheduleActivity extends AppCompatActivity implements TimeRecyclerAdapter.OnTimeListener{

    private ArrayList<BlockEntry> blockEntries;
    private EntryDatabase entryDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        entryDatabase = EntryDatabase.getInstance(this);

        initializeBlocks();

        TimeRecyclerAdapter adapter = new TimeRecyclerAdapter(blockEntries, this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);

        recyclerView.addItemDecoration(new OverlapDecoration());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onTimeClick(int position, final EditText entry) {
        final BlockEntry block = blockEntries.get(position);
        Toast.makeText(getApplicationContext(), "Clicked on: " + block.getTime(), Toast.LENGTH_SHORT).show();
        blockEntries.get(position).setHasEntry(true);
        entry.setVisibility(View.VISIBLE);
        entry.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                boolean handled = false;
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    saveEntry(block, entry);
                    InputMethodManager imm = (InputMethodManager)v.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                    entry.setFocusable(false);
                    handled = true;
                }
                return handled;
            }
        });
    }

    private void saveEntry(BlockEntry block, EditText entry) {
        block.setEntry(entry.getText().toString());
        this.entryDatabase.getEntryDao().update(block);
    }

    private void initializeBlocks() {
        this.blockEntries = (ArrayList<BlockEntry>)this.entryDatabase.getEntryDao().getAll();
        if (this.blockEntries.isEmpty()) {
            for (int i = 0; i < 48; i+=2) {
                this.blockEntries.add(new BlockEntry(i/2, false));
                this.blockEntries.add(new BlockEntry(i/2, true));
            }
            this.blockEntries.add(new BlockEntry(24, false));
        }
    }

}