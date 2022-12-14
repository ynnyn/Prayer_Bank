package edu.handong.prayer_bank;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

public class MyPage extends AppCompatActivity {
    public static Context context_mypage; // context 변수 선언
    // TextEdit variables
    EditText hourET, minuteET, secondET;
    String goalHour, goalMin, goalSec;
    float goalTime;

    private PopupWindow goalPopup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_page);
        //context_mypage = this; // oncreate에서 this 할당

        //make the main button
        ImageButton praybutton = findViewById(R.id.prayButton);
        ImageButton mypagebutton = findViewById(R.id.mypageButton);
        ImageButton summarybutton = findViewById(R.id.summaryButton);
        ImageButton calenderbutton = findViewById(R.id.calendarButton);
        ImageButton homebutton = findViewById(R.id.homeButton);

        //screen change
        //my page -> pray screen
        praybutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent pray = new Intent(MyPage.this, Prayer.class);
                startActivity(pray);
            }
        });
        //my page -> my page screen
        mypagebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mypage = new Intent(MyPage.this, MyPage.class);
                startActivity(mypage);
            }
        });
        //my page -> summary screen
        summarybutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent summary = new Intent(MyPage.this, Summary.class);
                startActivity(summary);
            }
        });
        //my page -> calender
        calenderbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent calender = new Intent(MyPage.this, Calendar.class);
                startActivity(calender);
            }
        });
        //my page -> home
        homebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent home = new Intent(MyPage.this, MainActivity.class);
                startActivity(home);
            }
        });

        Button goal = (Button)  findViewById(R.id.editButton);
        goal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View goal_popupView = getLayoutInflater().inflate(R.layout.popup_goal, null);
                goalPopup = new PopupWindow(goal_popupView, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                //popupView 에서 (LinearLayout 을 사용) 레이아웃이 둘러싸고 있는 컨텐츠의 크기 만큼 팝업 크기를 지정
                goalPopup.setFocusable(true);
                // 외부 영역 선택시 Popup 종료
                goalPopup.showAtLocation(goal_popupView, Gravity.CENTER, 0, 0);

                Button ok = (Button) goal_popupView.findViewById(R.id.goal_ok);
                ok.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // TextView로 받기
                        hourET = (EditText)goal_popupView.findViewById(R.id.hourET);     // goal_popupView인 이유는 xml에서 ok 버튼이 mypage가 아니라 goal_popup에 있기 때문
                        minuteET = (EditText)goal_popupView.findViewById(R.id.minuteET); // goal_popupView인 이유는 xml에서 ok 버튼이 mypage가 아니라 goal_popup에 있기 때문
                        secondET = (EditText)goal_popupView.findViewById(R.id.secondET); // goal_popupView인 이유는 xml에서 ok 버튼이 mypage가 아니라 goal_popup에 있기 때문

                        goalHour = hourET.getText().toString();
                        goalMin = minuteET.getText().toString();
                        goalSec = secondET.getText().toString();

                        // Version을 다르게 할 때 주석 처리
                        // 입력받은 숫자가 2자리수가 아니면 자리수를 0으로 채우기
                        if(goalHour.length()==0){
                            goalHour="00";
                        }else if(goalHour.length()==1){
                            goalHour="0"+goalHour;
                        }
                        if(goalMin.length()==0){
                            goalMin="00";
                        }else if(goalMin.length()==1){
                            goalMin="0"+goalMin;
                        }
                        if(goalSec.length()==0){
                            goalSec="00";
                        }else if(goalSec.length()==1){
                            goalSec="0"+goalSec;
                        }

                        // ShaerePreference Code Start  유저가 입력한 데이터를 변수에 저장
                        SharedPreferences sharedPreferences = getSharedPreferences("My Page", MODE_PRIVATE);
                        SharedPreferences.Editor myEdit = sharedPreferences.edit();

                        myEdit.putInt("g_hour", Integer.parseInt(goalHour));
                        myEdit.putInt("g_min", Integer.parseInt(goalMin));
                        myEdit.putInt("g_sec", Integer.parseInt(goalSec));
                        myEdit.commit();
                        //  ShaerePreference Code End

                        Log.v("kimsehee-2-set goal", goalHour+goalMin+goalSec);

                        Toast.makeText(getApplicationContext(), "OK", Toast.LENGTH_SHORT).show();
//                        startActivity(intent);



                    }
                });
            }
        });





    }

     public void clickBtn(View view) {
        //알림(Notification)을 관리하는 관리자 객체를 운영체제(Context)로부터 소환하기
         NotificationManager notificationManager=(NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

         //Notification 객체를 생성해주는 건축가객체 생성(AlertDialog 와 비슷)
         NotificationCompat.Builder builder= null;

         //Oreo 버전(API26 버전)이상에서는 알림시에 NotificationChannel 이라는 개념이 필수 구성요소가 됨.
         if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){

             String channelID="channel_01"; //알림채널 식별자
             String channelName="MyChannel01"; //알림채널의 이름(별명)

             //알림채널 객체 만들기
             NotificationChannel channel= new NotificationChannel(channelID,channelName,NotificationManager.IMPORTANCE_DEFAULT);

             //알림매니저에게 채널 객체의 생성을 요청
             notificationManager.createNotificationChannel(channel);

             //알림건축가 객체 생성
             builder=new NotificationCompat.Builder(this, channelID);


         }else{
             //알림 건축가 객체 생성
             builder= new NotificationCompat.Builder(this);
         }


     }
}