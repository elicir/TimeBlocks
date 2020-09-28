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

public class MainScheduleActivity extends AppCompatActivity implements TimeRecyclerAdapter.OnTimeListener{

    private BlockEntry[] myListData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myListData = new BlockEntry[49];
        for (int i = 0; i < 48; i+=2) {
            myListData[i] = new BlockEntry(i/2, false);
            myListData[i+1] = new BlockEntry(i/2, true);
        }
        myListData[48] = new BlockEntry(24, false);

        TimeRecyclerAdapter adapter = new TimeRecyclerAdapter(myListData, this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);

        recyclerView.addItemDecoration(new OverlapDecoration());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onTimeClick(final int position, final EditText entry) {
        BlockEntry block = myListData[position];
        Toast.makeText(getApplicationContext(), "Clicked on: " + block.getTime(), Toast.LENGTH_SHORT).show();
        myListData[position].setHasEntry(true);
        entry.setVisibility(View.VISIBLE);
        entry.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                boolean handled = false;
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    saveEntry(position, entry);
                    InputMethodManager imm = (InputMethodManager)v.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                    entry.setFocusable(false);
                    handled = true;
                }
                return handled;
            }
        });
    }

    private void saveEntry(int position, EditText entry) {
        myListData[position].setEntry(entry.getText().toString());
    }
}