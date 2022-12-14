package edu.handong.prayer_bank;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.Date;


public class Write_prayer_topics extends AppCompatActivity {
    long pNow;
    Date pDate;
    SimpleDateFormat pFormat = new SimpleDateFormat("yyyy/MM/dd", java.util.Locale.getDefault());
    TextView whenDate;
    //write prayer topics
    PreferenceManager pref;
    EditText category;
    EditText write_pt;
    Button save_btn, delete_btn, revise_btn;

    RecyclerView recyclerView;
    MemoAdapter memoAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_prayer_topics);
        pNow = System.currentTimeMillis();
        whenDate = (TextView) findViewById(R.id.when_date);

        pDate = new Date(pNow);
        String time = pFormat.format(pDate);
        whenDate.setText(time); //현재 날짜로 설정

        //write down prayer topics
        pref = new PreferenceManager();
        save_btn = findViewById(R.id.save_btn);
        delete_btn = findViewById(R.id.delete_btn);
        //revise_btn = findViewById(R.id.revise_btn);
        //editText 할당
        category = findViewById(R.id.editCategory);
        write_pt = findViewById(R.id.write_pt);

        save_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 저장 버튼을 눌러
                // 작성한 editText를 저장
                //category =
                String edit_category = category.getText().toString();
                String edit_pt = write_pt.getText().toString();
                // String 값을 JSONObject로 변환하여 사용할 수 있도록 메모의 제목과 타이틀을 JSON 형식로 저장
                String save_form = "{\"category\":\""+ edit_category + "\",prayer topic\":\""+edit_pt+"\"}";

                // key값이 겹치지 않도록 현재 시간으로 부여
                long now = System.currentTimeMillis();
                Date mDate = new Date(now);
                SimpleDateFormat PTdateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss", java.util.Locale.getDefault());
                String getTime = PTdateFormat.format(mDate);

                Log.d("Write_prayer_topicsActivity", "카테고리 : "+edit_category+", 기도제목 : "+edit_pt+", 현재시간 : "+getTime);
                //PreferenceManager 클래스에서 저장에 관한 메소드를 관리
                pref.setString(getApplication(),getTime,save_form);


                // Intent로 값을 MainActivity에 전달
                Intent intent = new Intent();
                intent.putExtra("date",getTime);
                intent.putExtra("category",edit_category);
                intent.putExtra("prayer_topic",edit_pt);
                setResult(RESULT_OK, intent);
                finish();

            }
        });






    }
}