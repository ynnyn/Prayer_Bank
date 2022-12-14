package edu.handong.prayer_bank;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Collection;
import java.util.Iterator;

public class Prayer_topics extends AppCompatActivity {

    Button write_btn;
    PreferenceManager pref;
    RecyclerView recyclerView;
    MemoAdapter memoAdapter;

    /*
    ActivityResultLauncher<Intent> launcher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {

                }
            })

     */

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
                Intent favorites = new Intent(Prayer_topics.this, Thanksgiving.class);
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


        // write down prayer topics
        pref = new PreferenceManager();
        write_btn = findViewById(R.id.button);

        write_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 버튼을 눌러서 이동할 액티비티 정해주기
                Intent intent = new Intent(getApplicationContext(), Write_prayer_topics.class);
                // 해당 액티비티에 단순 값 전달과 이동 -> startActivity
                // startActivity for result 안 됨.
                launcher.launch(intent);

            }
        });

        //리사이클러뷰 세팅
        LinearLayoutManager linearLayoutManager;
        recyclerView = findViewById(R.id.memo_rv); // 리사이클러뷰 findView
        linearLayoutManager = new LinearLayoutManager(Prayer_topics.this,
                LinearLayoutManager.VERTICAL, false);
        memoAdapter = new MemoAdapter(Prayer_topics.this);
        recyclerView.setLayoutManager(linearLayoutManager); //linearlayout 세팅
        recyclerView.setAdapter(memoAdapter); //adapter 세팅

        //쉐어드 모든 키 밸류 가져오기
        SharedPreferences prefb = getSharedPreferences("memo_contain", MODE_PRIVATE);
        Collection<?> col_val = prefb.getAll().values();
        Iterator<?> it_val = col_val.iterator();
        Collection<?> col_key = prefb.getAll().keySet();
        Iterator<?> it_key = col_key.iterator();



        while (it_val.hasNext() && it_key.hasNext()) {
            String key = (String) it_key.next();
            String value = (String) it_val.next();
            try {
                JSONObject jsonObject = new JSONObject(value);
                String title = (String) jsonObject.getString("title");
                String content = (String) jsonObject.getString("content");
                memoAdapter.addItem(new MemoItem(key, title, content));
            } catch (JSONException e) {
                Log.d("MainActivity", "JSONObject : " + e);
            }

            memoAdapter.notifyDataSetChanged();

        }
        //pref.clear(Prayer_topics.this);
    }

    /**
     * ActivityResultLauncher
     */
    protected ActivityResultLauncher<Intent> launcher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == RESULT_OK)
                    {
                        // 전달 받은 값
                        Log.e(TAG, "result : " + result);
                        Intent intent = result.getData();
                        String get_date = intent.getStringExtra("date");
                        String get_category = intent.getStringExtra("category");
                        String get_pt = intent.getStringExtra("prayer_topic");
                        // 리사이클러뷰 목록에 추가
                        memoAdapter.addItem(new MemoItem(get_date, get_category, get_pt));
                        // 목록 갱신
                        memoAdapter.notifyDataSetChanged();
                        Toast.makeText(Prayer_topics.this, "Save complete.", Toast.LENGTH_SHORT).show();
                        Log.e(TAG, "intent : " + intent);
                        Uri uri = intent.getData();
                    } else { // RESULT_CANCEL
                        Toast.makeText(Prayer_topics.this, "Not saved.", Toast.LENGTH_SHORT).show();
                    }
                }
            });


}