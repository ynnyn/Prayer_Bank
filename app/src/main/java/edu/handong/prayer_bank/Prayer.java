package edu.handong.prayer_bank;

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

import java.util.Timer;
import java.util.TimerTask;
import java.util.Formatter;

public class Prayer extends AppCompatActivity {

    LinearLayout timeAll, timeCountSettingLV, timeCountLV;
    EditText hourET, minuteET, secondET;
    TextView hourTV, minuteTV, secondTV, finishTV;
    Button startBtn;
    int hour, minute, second;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prayer);

        timeCountSettingLV = findViewById(R.id.timeCountSettingLV);
//        timeCountSettingLV = findViewById(R.id.timeCountSettingLV);
        timeCountLV = findViewById(R.id.timeCountLV);
        // User Input
        hourET = findViewById(R.id.hourET);
        minuteET = findViewById(R.id.minuteET);
        secondET = findViewById(R.id.secondET);
        // Show left time
        hourTV = findViewById(R.id.hourTV);
        minuteTV = findViewById(R.id.minuteTV);
        secondTV = findViewById(R.id.secondTV);
        finishTV = findViewById(R.id.finishTV);

        startBtn = findViewById(R.id.start_button);
        // ...
        // 시작버튼 이벤트 1처리
        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                timeCountSettingLV.setVisibility(View.GONE);
                timeCountLV.setVisibility(View.VISIBLE);

                hourTV.setText(hourET.getText().toString());
                minuteTV.setText(minuteET.getText().toString());
                secondTV.setText(secondET.getText().toString());

                hour = Integer.parseInt(hourET.getText().toString());
                minute = Integer.parseInt(minuteET.getText().toString());
                second = Integer.parseInt(secondET.getText().toString());

                Timer timer = new Timer();
                TimerTask timerTask = new TimerTask() {
                    @Override
                    public void run() {
                        // 반복실행할 구문

                        // 0초 이상이면
                        if(second != 0) {
                            //1초씩 감소
                            second--;

                            // 0분 이상이면
                        } else if(minute != 0) {
                            // 1분 = 60초
                            second = 60;
                            second--;
                            minute--;

                            // 0시간 이상이면
                        } else if(hour != 0) {
                            // 1시간 = 60분
                            second = 60;
                            minute = 60;
                            second--;
                            minute--;
                            hour--;
                        }

                        //시, 분, 초가 10이하(한자리수) 라면
                        // 숫자 앞에 0을 붙인다 ( 8 -> 08 )
                        if(second <= 9){
                            secondTV.setText(String.format("%02d",second));
                        } else {
                            secondTV.setText(String.format("%d",second));
//                            minuteTV.setText(Integer.toString(second));
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

                        // 시분초가 다 0이라면 toast를 띄우고 타이머를 종료한다..
                        if(hour == 0 && minute == 0 && second == 0) {
                            timer.cancel();//타이머 종료
                            finishTV.setText("타이머가 종료되었습니다.");
                        }
                    }
                };

                //타이머를 실행
                timer.schedule(timerTask, 0, 1000); //Timer 실행
            }
        });
    }
}