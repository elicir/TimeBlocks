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

public class MainActivity extends AppCompatActivity implements EntryAdapter.OnItemClickListener{

    private EntryDatabase entryDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.main_content, new EntryFragment())
                    .addToBackStack("ENTRYFRAGMENT").commit();
        }
    }

    @Override
    public void onTimeClick(final EditText entry, final BlockEntry blockEntry) {
        Toast.makeText(getApplicationContext(), "Clicked on: " + blockEntry.getTime(), Toast.LENGTH_SHORT).show();
        blockEntry.setHasEntry(true);
        entry.setVisibility(View.VISIBLE);
        entry.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                boolean handled = false;
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    saveEntry(blockEntry, entry);
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
        updateDatabase(block);
    }


    private void updateDatabase(final BlockEntry block) {
        AppExecutors.getInstance().diskIO().execute(new Runnable() {
            @Override
            public void run() {
                EntryDatabase.getInstance(getApplicationContext()).entryDao().update(block);
            }
        });

    }

}