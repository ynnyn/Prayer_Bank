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


        // ???????????? ????????? 1??????
        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Setting page?????? ???????????? ?????? ????????? ????????? ?????????
                //timeCountSettingLV.setVisibility(View.GONE);
                //timeCountLV.setVisibility(View.VISIBLE);

                hour=0;
                minute=0;
                second=0;
                // input?????? ???????????? ????????? start????????? ???????????? ????????? ??????
                Timer timer = new Timer();
                TimerTask timerTask = new TimerTask() {
                    @Override
                    public void run() {
                        // ??????????????? ??????

                        // ????????????
                        if(second < 59) {
                            //1?????? ??????
                            second++;
                        }
                        // 59?????? ?????? ??? ??????
                        else if(minute < 59) {
                            // ?????? 1???????????? ?????? 0?????? ?????????
                            second = 0;
                            minute++;
                        }
                        // 59??? 59?????? ??????
                        else{
                            // 1????????? ???????????? ?????? ?????? 0?????? ?????????
                            hour++;
                            second = 0;
                            minute = 0;
                        }

                        //???, ???, ?????? 10??????(????????????) ??????
                        // ?????? ?????? 0??? ????????? ( 8 -> 08 )
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

//                        // ???????????? ??? goal??? ????????? toast??? ????????? ??????????????? ????????????..
//                        if(hour == goal_h && minute == goal_min && second == goal_sec) {
//                            timer.cancel();//???????????? ??????
//                            finishTV.setText("????????? ??????????????????.");
//                        }
                    }
                };

                //???????????? ??????
                timer.schedule(timerTask, 0, 1000); //Timer ??????
                dateNow = getCurrentDate();
                weekNow = getCurrentWeek();
            }
        });



        //intent??? ???????????? Summary???????????? ????????? ??????,???,?????? ??????Intent timeIntent = new Intent(Prayer.this, Summary.class);
        Intent timeIntent = new Intent(Prayer.this, Summary.class);
        stopBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // ShaerePreference Code Start  ????????? ????????? ???????????? ????????? ??????
                weekNow = getCurrentWeek();
                SharedPreferences Pray_time = getSharedPreferences("Pray", MODE_PRIVATE);
                SharedPreferences.Editor pEdit = Pray_time.edit();

                pEdit.putInt("p_hour", hour);
                pEdit.putInt("p_min", minute);
                pEdit.putInt("p_sec", second);
                pEdit.putInt("weekNow", weekNow);

                pEdit.apply();
                //  ShaerePreference Code End

                // Console?????? time????????? ??????????????? "time: pray_time"??? ????????????
                Log.v("kimsehee1","time"+hour+minute+second);
                startActivity(timeIntent);
            }
        });



    }
    // ?????? ?????? ?????? ??????
    public static String getCurrentDate() {
        Date dateNow = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());  // 2022-12-06

        return format.format(dateNow);
    }
    // ?????? ?????? ?????? ??????
    public static int getCurrentWeek() {
        Date currentDate = new Date();

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(currentDate);

        int dayOfWeekNumber = calendar.get(Calendar.DAY_OF_WEEK);
        // 1 : ?????????(S), 2: ?????????(M), 3: ?????????(T), 4: ?????????(W), 5: ?????????(T),
        // 6: ?????????(F), 7: ?????????(S)

        return dayOfWeekNumber;
    }
}