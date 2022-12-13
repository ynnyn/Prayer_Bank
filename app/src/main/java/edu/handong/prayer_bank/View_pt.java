package edu.handong.prayer_bank;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

public class View_pt extends AppCompatActivity {
    PreferenceManager pref;
    TextView view_category;
    TextView view_prayer_topic;
    Button back_lst_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pt);

        pref = new PreferenceManager();
        view_category = findViewById(R.id.view_category);
        view_prayer_topic = findViewById(R.id.view_prayer_topic);

        //인텐트로 리사이클러뷰 목록 하나의 키값을 받는다.
        Intent intent = getIntent();
        String key = intent.getStringExtra("key");

        String value = pref.getString(getApplication(), key);
        try {
            JSONObject jsonObject = new JSONObject(value);
            String category = (String) jsonObject.getString("category");
            String prayer_topic = (String) jsonObject.getString("prayer_topic");
            view_category.setText(category);
            view_prayer_topic.setText(prayer_topic);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        back_lst_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });



    }
}