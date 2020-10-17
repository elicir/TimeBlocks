package com.ribbitree.timeblocks;

import androidx.appcompat.app.AppCompatActivity;


import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.view.inputmethod.EditorInfo;
import android.view.KeyEvent;


public class MainActivity extends AppCompatActivity implements EntryAdapter.OnItemClickListener{


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
    public boolean dispatchTouchEvent(MotionEvent ev) {
        View view = getCurrentFocus();

        if (view != null && (ev.getAction() == MotionEvent.ACTION_DOWN || ev.getAction() == MotionEvent.ACTION_MOVE) &&
                view instanceof EditText &&
                !view.getClass().getName().startsWith("android.webkit.")) {
            int[] sourceCoordinates = new int[2];
            view.getLocationOnScreen(sourceCoordinates);
            float x = ev.getRawX() + view.getLeft() - sourceCoordinates[0];
            float y = ev.getRawY() + view.getTop() - sourceCoordinates[1];

            if (x < view.getLeft() || x > view.getRight() || y < view.getTop() || y > view.getBottom()) {
                hideSoftKeyboard();
                view.setFocusable(false);
            }

        }
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public void onTimeClick(final EditText entry, final BlockEntry blockEntry) {
        Toast.makeText(getApplicationContext(), "Clicked on: " + blockEntry.getTime(), Toast.LENGTH_SHORT).show();
        blockEntry.setHasEntry(true);
        entry.setVisibility(View.VISIBLE);
        entry.setFocusableInTouchMode(true);
        entry.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                boolean handled = false;
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    saveEntry(blockEntry, entry);
                    hideSoftKeyboard();
                    entry.setFocusable(false);
                    handled = true;
                }
                return handled;
            }
        });
        entry.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (!hasFocus) {
                    saveEntry(blockEntry, entry);
                }
            }
        });
    }

    private void hideSoftKeyboard() {
        InputMethodManager inputMethodManager =
                (InputMethodManager) this.getSystemService(
                        Activity.INPUT_METHOD_SERVICE);
        View view = this.getCurrentFocus();
        if (view == null) {
            view = new View(this);
        }
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
        if (view != null) {
            view.clearFocus();
        }
    }

    public void saveEntry(BlockEntry block, EditText entry) {
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