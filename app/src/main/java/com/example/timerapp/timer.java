package com.example.timerapp;

import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import static android.os.Looper.getMainLooper;
import static java.lang.Boolean.FALSE;


public class timer extends Fragment {

    private int seconds = 0;
    private String name;
    private TextView td;
    private TextView tv;
    private boolean paused = true;
    private FloatingActionButton fab;
    private Drawable PLAY;
    private Drawable PAUSE;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    private void setIcon() {
        Drawable icon = paused ? PAUSE : PLAY;
        fab.setImageDrawable(icon);
    }
    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("seconds", seconds);
        outState.putBoolean("paused", paused);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_timer, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        name = getArguments().getString("name");
        td = view.findViewById(R.id.timerDet);
        td.setText(name);
        seconds = getArguments().getInt("seconds");
        tv = view.findViewById(R.id.timer_text);
        fab = view.findViewById(R.id.play_pause_fab);
        PLAY = getResources().getDrawable(android.R.drawable.ic_media_pause);
        PAUSE = getResources().getDrawable(android.R.drawable.ic_media_play);
        Log.i("INFO:", "name is: " + name);
        Log.i("INFO", "seconds is: " + seconds);
        fab.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                paused = !paused;
                setIcon();
                Log.i("INFO", "fab click seconds is: " + seconds);
            }
        });
        if (savedInstanceState != null) {
            paused = savedInstanceState.getBoolean("paused");
            seconds = savedInstanceState.getInt("seconds");
            setIcon();
        }
        runTimer();
    }

    public static timer newInstance(int seconds, String name) {
        timer tf = new timer();
        Bundle args = new Bundle();
        args.putInt("seconds", seconds);
        args.putString("name", name);
        tf.setArguments(args);
        return tf;
    }

    public void runTimer() {
        final Handler handler = new Handler(getMainLooper());
        handler.post(new Runnable() {
            @Override
            public void run() {

                int sec = seconds % 60;
                int min = (seconds % 3600) / 60;
                int hour = seconds / 3600;
                tv.setText(String.format("%02d : %02d : %02d", hour, min, sec));
                if (paused == FALSE && seconds > 0)
                    seconds--;
                handler.postDelayed(this, 1000);
            }
        });
    }
}