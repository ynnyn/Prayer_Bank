package edu.handong.prayer_bank;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.android.car.ui.toolbar.MenuItem;
import com.google.android.material.navigation.NavigationView;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //make the main button
        ImageButton praybutton = findViewById(R.id.prayButton);
        ImageButton mypagebutton = findViewById(R.id.mypageButton);
        ImageButton summarybutton = findViewById(R.id.summaryButton);
        ImageButton calenderbutton = findViewById(R.id.calendarButton);
        ImageButton homebutton = findViewById(R.id.homeButton);

        //screen change
        //main -> pray screen
        praybutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent pray = new Intent(MainActivity.this, Prayer.class);
                startActivity(pray);
            }
        });
        //main -> my page screen
        mypagebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mypage = new Intent(MainActivity.this, MyPage.class);
                startActivity(mypage);
            }
        });
        //main -> summary screen
        summarybutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent summary = new Intent(MainActivity.this, Summary.class);
                startActivity(summary);
            }
        });
        //main -> calender
        calenderbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent calender = new Intent(MainActivity.this, Calendar.class);
                startActivity(calender);
            }
        });
        //main -> home




    }

    implements NavigationView.OnNavigationItemSelectedListener {
        @Override
                protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            ActionBarDrawerToggle toggle = new ActionBarDrawerToggle (
                    this,drawer,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
            //drawer.setDrawerListener(toggle);
            drawer.addDrawerListener((DrawerLayout.DrawerListener) toggle);
            toggle.syncState();

            NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
            navigationView.setNavigationItemSelectedListener(this);
        }
        @Override
                public void onBackPressed() {
            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            if (drawer.isDrawerOpen(GravityCompat.START)) {
                drawer.closeDrawer(GravityCompat.START);
            } else {
                super.onBackPressed();
            }
        }

        @SuppressWarnings("StatementWithEmptyBody")
                @Override
        public boolean onNavigationItemSelected(MenuItem item) {
            //Handle navigation viw item clicks here.
            int id = int.getItemId();

            if (id == R.id.nav_me) {
            } else if (id == R.id.nav_family) {
            } else if (id == R.id.nav_friends) {
            }
            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            drawer.closeDrawer(GravityCompat.START);
            return true;

        }
}
