package edu.handong.prayer_bank;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


public class Prayer extends AppCompatActivity {

    private int seconds = 0;
    private boolean running;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prayer);

        Button startButton = findViewById(R.id.start_button);
        startButton.setOnClickListener(view -> {
            running = true;
        });
        Button stopButton = findViewById(R.id.stop_button);
        stopButton.setOnClickListener(view -> {
            running = false;
        });
        runTimer();


        //make the main button
        ImageButton praybutton = findViewById(R.id.prayButton);
        ImageButton mypagebutton = findViewById(R.id.mypageButton);
        ImageButton summarybutton = findViewById(R.id.summaryButton);
        ImageButton calenderbutton = findViewById(R.id.calendarButton);
        ImageButton homebutton = findViewById(R.id.homeButton);

        //screen change
        //player -> pray screen
        praybutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent pray = new Intent(Prayer.this, Prayer.class);
                startActivity(pray);
            }
        });
        //player -> my page screen
        mypagebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mypage = new Intent(Prayer.this, MyPage.class);
                startActivity(mypage);
            }
        });
        //player -> summary screen
        summarybutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent summary = new Intent(Prayer.this, Summary.class);
                startActivity(summary);
            }
        });
        //player -> calender
        calenderbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent calender = new Intent(Prayer.this, Calendar.class);
                startActivity(calender);
            }
        });
        //player -> home
        homebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent home = new Intent(Prayer.this, MainActivity.class);
                startActivity(home);
            }
        });

    }

    private void runTimer() {
        final TextView timeView = findViewById(R.id.time_view);
        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                int hours = seconds/3600;
                int minutes = (seconds%3600)/60;
                int secs = seconds%60;
                String time = String.format("%d:%02d:%02d",hours,minutes,secs);
                timeView.setText(time);
                if (running) {
                    seconds++;
                }
                handler.postDelayed(this,1000);
            }
        });
    }

}