package com.example.timerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ((Button)findViewById(R.id.workout_1_btn)).setOnClickListener(this);
        ((Button)findViewById(R.id.workout_2_btn)).setOnClickListener(this);
        ((Button)findViewById(R.id.workout_3_btn)).setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        Intent intent1 = new Intent(getApplicationContext(), SecondActivity.class);
        switch (view.getId()) {
            case R.id.workout_1_btn:
                intent1.putExtra("seconds", 60);
                intent1.putExtra("name", "Push-ups");
                startActivity(intent1);
                break;
            case R.id.workout_2_btn:
                intent1.putExtra("seconds", 90);
                intent1.putExtra("name", "Crunches");
                startActivity(intent1);
                break;
            case R.id.workout_3_btn:
                intent1.putExtra("seconds", 30);
                intent1.putExtra("name", "Squats");
                startActivity(intent1);
                break;
        }
    }
}