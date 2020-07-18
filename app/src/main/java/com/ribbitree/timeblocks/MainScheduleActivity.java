package com.ribbitree.timeblocks;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

public class MainScheduleActivity extends AppCompatActivity implements TimeRecyclerAdapter.OnTimeListener{

    private ListData[] myListData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myListData = new ListData[49];
        for (int i = 0; i < 48; i+=2) {
            myListData[i] = new ListData(i/2, false);
            myListData[i+1] = new ListData(i/2, true);
        }
        myListData[48] = new ListData(24, false);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        TimeRecyclerAdapter adapter = new TimeRecyclerAdapter(myListData, this);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onTimeClick(int position) {
        Toast.makeText(getApplicationContext(), "Clicked on: " + myListData[position].getTime(), Toast.LENGTH_SHORT).show();
    }
}