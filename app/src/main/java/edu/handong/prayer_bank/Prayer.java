package edu.handong.prayer_bank;

import android.content.Intent;
import android.content.SharedPreferences;
import android.provider.CalendarContract;
import android.util.Log;
import android.widget.ImageButton;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Calendar;

public class Prayer extends AppCompatActivity {

    LinearLayout timeAll, timeCountSettingLV, timeCountLV;
    TextView hourTV, minuteTV, secondTV, finishTV;
    Button startBtn, stopBtn;
    int hour, minute, second;
    String dateNow;
    int weekNow;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prayer);

        //make the main button
        ImageButton praybutton = findViewById(R.id.prayButton);
        ImageButton mypagebutton = findViewById(R.id.mypageButton);
        ImageButton summarybutton = findViewById(R.id.summaryButton);
        ImageButton calenderbutton = findViewById(R.id.calendarButton);
        ImageButton homebutton = findViewById(R.id.homeButton);

        //screen change
        //pray -> pray screen
        praybutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent pray = new Intent(Prayer.this, Prayer.class);
                startActivity(pray);
            }
        });
        //pray -> my page screen
        mypagebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mypage = new Intent(Prayer.this, MyPage.class);
                startActivity(mypage);
            }
        });
        //pray -> summary screen
        summarybutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent summary = new Intent(Prayer.this, Summary.class);
                startActivity(summary);
            }
        });
        //pray -> calender
        calenderbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent calender = new Intent(Prayer.this, Calendar.class);
                startActivity(calender);
            }
        });
        //pray -> home
        homebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent home = new Intent(Prayer.this, MainActivity.class);
                startActivity(home);
            }
        });

        // Show accumulated time
        hourTV = findViewById(R.id.hourTV);
        minuteTV = findViewById(R.id.minuteTV);
        secondTV = findViewById(R.id.secondTV);
        finishTV = findViewById(R.id.finishTV);

        startBtn = findViewById(R.id.start_button);
        stopBtn = findViewById(R.id.stop_button);


        // 시작버튼 이벤트 1처리
        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Setting page에서 설정함에 따라 숨기기 기능이 불필요
                //timeCountSettingLV.setVisibility(View.GONE);
                //timeCountLV.setVisibility(View.VISIBLE);

                hour=0;
                minute=0;
                second=0;
                // input값을 선택하지 않으면 start버튼이 작동하지 않도록 하기
                Timer timer = new Timer();
                TimerTask timerTask = new TimerTask() {
                    @Override
                    public void run() {
                        // 반복실행할 구문

                        // 처음시작
                        if(second < 59) {
                            //1초씩 증가
                            second++;
                        }
                        // 59초인 경우 분 증가
                        else if(minute < 59) {
                            // 분을 1증가하고 초를 0으로 초기화
                            second = 0;
                            minute++;
                        }
                        // 59분 59초인 상황
                        else{
                            // 1시간을 추가하고 초와 분을 0으로 초기화
                            hour++;
                            second = 0;
                            minute = 0;
                        }

                        //시, 분, 초가 10이하(한자리수) 라면
                        // 숫자 앞에 0을 붙인다 ( 8 -> 08 )
                        if(second <= 9){
                            secondTV.setText(String.format("%02d",second));
                        } else {
                            secondTV.setText(String.format("%d",second));
                        }

                        if(minute <= 9){
                            minuteTV.setText(String.format("%02d",minute));
                        } else {
                            minuteTV.setText(Integer.toString(minute));
                        }

                        if(hour <= 9){
                            hourTV.setText(String.format("%02d",hour));
                        } else {
                            hourTV.setText(Integer.toString(hour));
                        }

//                        // 시분초가 다 goal과 같다면 toast를 띄우고 스탑워치를 종료한다..
//                        if(hour == goal_h && minute == goal_min && second == goal_sec) {
//                            timer.cancel();//스탑워치 종료
//                            finishTV.setText("목표를 달성했습니다.");
//                        }
                    }
                };

                //타이머를 실행
                timer.schedule(timerTask, 0, 1000); //Timer 실행
                dateNow = getCurrentDate();
                weekNow = getCurrentWeek();
            }
        });



        //intent를 사용하여 Summary페이지로 기도한 시간,분,초를 전달Intent timeIntent = new Intent(Prayer.this, Summary.class);
        Intent timeIntent = new Intent(Prayer.this, Summary.class);
        stopBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // ShaerePreference Code Start  유저가 입력한 데이터를 변수에 저장
                weekNow = getCurrentWeek();
                SharedPreferences Pray_time = getSharedPreferences("Pray", MODE_PRIVATE);
                SharedPreferences.Editor pEdit = Pray_time.edit();

                pEdit.putInt("p_hour", hour);
                pEdit.putInt("p_min", minute);
                pEdit.putInt("p_sec", second);
                pEdit.putInt("weekNow", weekNow);

                pEdit.apply();
                //  ShaerePreference Code End

                // Console창에 time이라는 태그명으로 "time: pray_time"을 출력하라
                Log.v("kimsehee1","time"+hour+minute+second);
                startActivity(timeIntent);
            }
        });



    }
    // 현재 날짜 받는 함수
    public static String getCurrentDate() {
        Date dateNow = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());  // 2022-12-06

        return format.format(dateNow);
    }
    // 현재 요일 받는 함수
    public static int getCurrentWeek() {
        Date currentDate = new Date();

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(currentDate);

        int dayOfWeekNumber = calendar.get(Calendar.DAY_OF_WEEK);
        // 1 : 일요일(S), 2: 월요일(M), 3: 화요일(T), 4: 수요일(W), 5: 목요일(T),
        // 6: 금요일(F), 7: 토요일(S)

        return dayOfWeekNumber;
    }
}