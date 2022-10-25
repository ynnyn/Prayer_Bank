package edu.handong.prayer_bank;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class Thanksgiving extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thanksgiving);

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
                Intent pray = new Intent(Thanksgiving.this, Prayer.class);
                startActivity(pray);
            }
        });
        // -> my page screen
        mypagebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mypage = new Intent(Thanksgiving.this, MyPage.class);
                startActivity(mypage);
            }
        });
        // -> summary screen
        summarybutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent summary = new Intent(Thanksgiving.this, Summary.class);
                startActivity(summary);
            }
        });
        // -> calender
        calenderbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent calender = new Intent(Thanksgiving.this, Calendar.class);
                startActivity(calender);
            }
        });
        // -> home
        homebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent home = new Intent(Thanksgiving.this, MainActivity.class);
                startActivity(home);
            }
        });

        //click on left Button
        leftButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent prayer_topics = new Intent(Thanksgiving.this, Prayer_topics.class);
                startActivity(prayer_topics);
            }
        });

        //click on right button
        rightButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent favorites = new Intent(Thanksgiving.this, Favorites.class);
                startActivity(favorites);
            }
        });
    }
}