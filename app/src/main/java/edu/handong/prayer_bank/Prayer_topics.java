package edu.handong.prayer_bank;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class Prayer_topics extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prayer_topics);

        //make the main button
        ImageButton praybutton = findViewById(R.id.prayButton);
        ImageButton mypagebutton = findViewById(R.id.mypageButton);
        ImageButton summarybutton = findViewById(R.id.summaryButton);
        ImageButton calenderbutton = findViewById(R.id.calendarButton);
        ImageButton homebutton = findViewById(R.id.homeButton);
        //left, right button
        ImageButton leftButton = findViewById(R.id.leftButton);
        ImageButton rightButton = findViewById(R.id.rightButton);

        //screen change
        // -> pray screen
        praybutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent pray = new Intent(Prayer_topics.this, Prayer.class);
                startActivity(pray);
            }
        });
        // -> my page screen
        mypagebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mypage = new Intent(Prayer_topics.this, MyPage.class);
                startActivity(mypage);
            }
        });
        // -> summary screen
        summarybutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent summary = new Intent(Prayer_topics.this, Summary.class);
                startActivity(summary);
            }
        });
        // -> calender
        calenderbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent calender = new Intent(Prayer_topics.this, Calendar.class);
                startActivity(calender);
            }
        });
        // -> home
        homebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent home = new Intent(Prayer_topics.this, MainActivity.class);
                startActivity(home);
            }
        });

        //click on left Button
        leftButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent favorites = new Intent(Prayer_topics.this, Favorites.class);
                startActivity(favorites);
            }
        });

        //click on right button
        rightButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent thanksgiving = new Intent(Prayer_topics.this, Thanksgiving.class);
                startActivity(thanksgiving);
            }
        });
    }
}