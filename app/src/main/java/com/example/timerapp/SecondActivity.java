package com.example.timerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        int seconds = getIntent().getExtras().getInt("seconds");
        String name = getIntent().getExtras().getString("name");

        Log.i("INFO", "seconds is: " + seconds);
        Log.i("INFO", "name is: " + name);

        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.fragment_container, timer.newInstance(seconds, name))
                .commit();
    }
}