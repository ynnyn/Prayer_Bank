package edu.handong.prayer_bank;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.navigation.NavigationView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Collection;
import java.util.Iterator;


public class MainActivity extends AppCompatActivity {

    DrawerLayout drawer;
    ActionBarDrawerToggle toggle;
    boolean isDrawerOpend;


    //write
    FrameLayout today_prayer;
    RecyclerView recyclerView;
    MemoAdapter memoAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageButton buttonOpen = findViewById(R.id.menuButton);
        buttonOpen.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v){
                DrawerLayout drawer = (DrawerLayout) findViewById(R.id.category_drawer_view);
                if (drawer.isDrawerOpen(Gravity.LEFT)){
                    drawer.closeDrawer(Gravity.LEFT);
                }
            }
        });

        getSupportActionBar().setDisplayShowTitleEnabled(false);

        drawer = (DrawerLayout) findViewById(R.id.main_screen);
        toggle = new ActionBarDrawerToggle(this, drawer, R.string.drawer_open, R.string.drawer_close){
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }
            @Override
            public boolean onOptionsItemSelected(MenuItem item) {
                if (toggle.onOptionsItemSelected(item)) {
                    return true;
                }
                return super.onOptionsItemSelected(item);
            }
        };
        drawer.addDrawerListener(toggle);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toggle.syncState();

        NavigationView navigationView = (NavigationView)
                findViewById(R.id.category_drawer_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {

            @SuppressWarnings("StatementWithEmptyBody")
            @Override
            public boolean onNavigationItemSelected(MenuItem Item) {
                int id=Item.getItemId();

                if(id==R.id.nav_me) {
                    showToast("NavigationDrawer...me..");
                } else if(id == R.id.nav_family) {
                    showToast("NavigationDrawer...family..");
                } else if(id == R.id.nav_friends) {
                    showToast("NavigationDrawer...friends..");
                }

                return false;
            }


        });

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
        homebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent home = new Intent(MainActivity.this, MainActivity.class);
                startActivity(home);
            }
        });


        //onCreate
        //리사이클러뷰 세팅
        /*
        LinearLayoutManager linearLayoutManager;
        recyclerView = findViewById(R.id.today_prayer);//리사이클러뷰 findView
        linearLayoutManager = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.VERTICAL, false);

        memoAdapter = new MemoAdapter(MainActivity.this);
        recyclerView.setLayoutManager(linearLayoutManager);//linearlayout 세팅
        recyclerView.setAdapter(memoAdapter);//adapter 세팅


        //쉐어드 모든 키 벨류 가져오기
        SharedPreferences prefb =getSharedPreferences("memo_contain", MODE_PRIVATE);
        Collection<?> col_val =  prefb.getAll().values();
        Iterator<?> it_val = col_val.iterator();
        Collection<?> col_key = prefb.getAll().keySet();
        Iterator<?> it_key = col_key.iterator();


        while(it_val.hasNext() && it_key.hasNext()) {
            String key = (String) it_key.next();
            String value = (String) it_val.next();

            // value 값은 다음과 같이 저장되어있다
            // "{\"category\":\"hi category\",\"prayer_topic\":\"hi prayer_topic\"}"
            try {
                // String으로 된 value를 JSONObject로 변환하여 key-value로 데이터 추출
                JSONObject jsonObject = new JSONObject(value);
                String category = (String) jsonObject.getString("category");
                String prayer_topic = (String) jsonObject.getString("prayer_topic");
                // 리사이클러뷰 어뎁터 addItem으로 목록 추가
                memoAdapter.addItem(new MemoItem(key, category, prayer_topic));
            } catch (JSONException e) {

            }

            // 목록 갱신하여 뷰에 띄어줌
            memoAdapter.notifyDataSetChanged();

        }
        */

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (toggle.onOptionsItemSelected(item)) {
            return false;
        }
        return super.onOptionsItemSelected(item);
    }
    private void showToast(String message) {
        Toast toast=Toast.makeText(this, message, Toast.LENGTH_SHORT);
        toast.show();
    }

    /*
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_TEST) {
            if (resultCode == RESULT_OK) {
                // 전달 받은 값
                Intent intent = getIntent();
                String get_date = data.getStringExtra("date");
                String get_title = data.getStringExtra("category");
                String get_content = data.getStringExtra("prayer_topic");
                // 리사이클러뷰 목록에 추가
                memoAdapter.addItem(new MemoItem(get_date,get_title,get_content));
                // 목록 갱신
                memoAdapter.notifyDataSetChanged();
                Toast.makeText(MainActivity.this, "작성 되었습니다", Toast.LENGTH_SHORT).show();
            } else {   // RESULT_CANCEL
                Toast.makeText(MainActivity.this, "저장 되지 않음", Toast.LENGTH_SHORT).show();
            }

        }
    }*/

}





