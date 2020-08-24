package com.ribbitree.timeblocks;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;
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

        TimeRecyclerAdapter adapter = new TimeRecyclerAdapter(myListData, this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);

        recyclerView.addItemDecoration(new OverlapDecoration());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onTimeClick(int position) {
        String time = myListData[position].getTime();
        Toast.makeText(getApplicationContext(), "Clicked on: " + myListData[position].getTime(), Toast.LENGTH_SHORT).show();
        TextView view = new TextView(getApplicationContext());
        view.setText(time);
        ConstraintLayout constraintLayout = findViewById(R.id.constraintLayout);
        ConstraintLayout.LayoutParams layoutParams = new ConstraintLayout.LayoutParams
                (ConstraintLayout.LayoutParams.MATCH_PARENT, ConstraintLayout.LayoutParams.MATCH_PARENT);
        constraintLayout.addView(view, layoutParams);
    }

    protected void createNewEntry() {
        RecyclerView recyclerView = findViewById(R.id.recyclerView);

    }
}