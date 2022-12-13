package edu.handong.prayer_bank;


import static android.content.ContentValues.TAG;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;


import java.util.ArrayList;
import java.util.Date;

public class Summary extends AppCompatActivity {

    ArrayList<Float> valLst = new ArrayList<>(7); // ArrayList 선언
    ArrayList<String> labelLst = new ArrayList<>(); // ArrayList 선언
    //Bar chart
    private BarChart barChart;
    TextView minuteTextView;
    //goal progressbar
    private ProgressBar progressbar;
    //text
    TextView good1, motivate1;
    ImageView smile, sad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);

        setTitle("Summary");

        //make the main button
        ImageButton praybutton = findViewById(R.id.prayButton);
        ImageButton mypagebutton = findViewById(R.id.mypageButton);
        ImageButton summarybutton = findViewById(R.id.summaryButton);
        ImageButton calenderbutton = findViewById(R.id.calendarButton);
        ImageButton homebutton = findViewById(R.id.homeButton);

        // Prayer 페이지에서 실제로 기도한 시간을 전달받는다.
        int pray_hour,pray_min,pray_sec;
        float pray_time = 0;
        Intent timeIntent = getIntent();
        pray_hour = timeIntent.getIntExtra("_hour", 0);
        pray_min = timeIntent.getIntExtra("_min", 0);
        pray_sec = timeIntent.getIntExtra("_sec", 0);
        pray_time += pray_hour*60 + pray_min + ((float)pray_sec)/60;

        // Now Date, day of the week
        String dateNow;
        int weekNow;
        dateNow = timeIntent.getStringExtra("_dateNow");
        weekNow = timeIntent.getIntExtra("_weekNow", 0);


        // User Input from Mypage
        int goal_h,goal_min,goal_sec;
        float goal_time = 0;
        // 여기가 이상한 듯 아마도
        goal_h = ((MyPage)MyPage.context_mypage).goalHour;
        goal_min = ((MyPage)MyPage.context_mypage).goalMin;
        goal_sec = ((MyPage)MyPage.context_mypage).goalSec;
        goal_time += goal_h*60 + goal_min + ((float)goal_sec)/60;


        //screen change
        //summary -> pray screen
        praybutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent pray = new Intent(Summary.this, Prayer.class);
                startActivity(pray);
            }
        });
        //summary -> my page screen
        mypagebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mypage = new Intent(Summary.this, MyPage.class);
                startActivity(mypage);
            }
        });
        //summary -> summary screen
        summarybutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent summary = new Intent(Summary.this, Summary.class);
                startActivity(summary);
            }
        });
        //summary -> calender
        calenderbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent calender = new Intent(Summary.this, Calendar.class);
                startActivity(calender);
            }
        });
        //summary -> home
        homebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent home = new Intent(Summary.this, MainActivity.class);
                startActivity(home);
            }
        });

        //text랑 예수님 상태 바꾸기
        smile = findViewById(R.id.imageView3);
        sad = findViewById(R.id.imageView4);
        good1 = findViewById(R.id.good_text1);
        motivate1 = findViewById(R.id.motivate_text1);

        //Progress graph - 사용자의 개인 기도 시간 진행 보여주기
        progressbar = findViewById(R.id.progressBar);

        if (goal_time == 0){
            try {
                progressbar.setProgress(100);
                Log.v("kimsehee-100", "goal time"+goal_time);
            } catch (Exception e){
                Toast.makeText(Summary.this, "Error", Toast.LENGTH_SHORT).show();
            }

        }else {
            if ((goal_time < pray_time)|(goal_time == pray_time)){
                progressbar.setProgress(100);
                Log.v("kimsehee_progress1","time"+pray_time);
                motivate1.setVisibility(View.GONE);
                sad.setVisibility(View.GONE);
                good1.setVisibility(View.VISIBLE);
                smile.setVisibility(View.VISIBLE);
            }else{
                progressbar.setProgress((int) (pray_time*100/goal_time));
                Log.v("kimsehee_progress2","time"+(int) (pray_time*100/goal_time));
            }

        }

        // labelList
        labelLst.add("S");
        labelLst.add("M");
        labelLst.add("T");
        labelLst.add("W");
        labelLst.add("T");
        labelLst.add("F");
        labelLst.add("S");

        // valList
        if (valLst.isEmpty()){
            //int i = weekNow-1;
            for (int i = 0; i < 7; i++){
                if (i==weekNow-1) valLst.add(i, pray_time);
                else valLst.add((float)5.5);
            }

        }else{
            valLst.set(weekNow-1, pray_time);
        }

        /*
        valLst.add((float)10.0);
        valLst.add((float)15.1);
        valLst.add((float)20.5);
        valLst.add((float)5.7);
        valLst.add((float)23.2);
        valLst.add((float)30.33);
        valLst.add(pray_time);

         */


        //Bar chart
        barChart = (BarChart) findViewById(R.id.chart_week);
        barChart.setDrawBarShadow(false);
        barChart.setDrawValueAboveBar(true);
        barChart.getDescription().setEnabled(false);
        barChart.setMaxVisibleValueCount(7);
        barChart.setPinchZoom(false);
        barChart.setDrawGridBackground(false);
        //
        XAxis xAxis;
        xAxis = barChart.getXAxis();
        // 축을 숫자가 아니라 날짜로 표시 🤪 이 부분은 많이 헤맸는데... 추후 자세한 설명 글을 작성할 예정임!
        xAxis.setValueFormatter(new IndexAxisValueFormatter(labelLst));
        //축 레이블 표시 간격
        xAxis.setGranularity(1f);
        xAxis.setGranularityEnabled(true);
        //graphInitSetting();
        // 여기 오류가 뜬다...
        setData(labelLst, valLst);
           //그래프 기본 세팅

        //BarChartGraph(labelLst, jsonList);
        barChart.setTouchEnabled(false); // 터치못하게 함.
        //barChart.invalidate();           //차트 업데이트





    }

    private void setData(ArrayList<String> labelList, ArrayList<Float> valList) {

        ArrayList<BarEntry> values = new ArrayList<>();
        for (int i = 0; i < labelList.size(); i++){
            float val = valList.get(i);    // error
            values.add(new BarEntry(i, val));

        }
        BarDataSet set1;
        if(barChart.getData() != null &&
    barChart.getData().getDataSetCount() > 0){
            set1 = (BarDataSet) barChart.getData().getDataSetByIndex(0);
            set1.setValues(values);
            set1.setColors(ColorTemplate.LIBERTY_COLORS);
            barChart.getData().notifyDataChanged();
            barChart.notifyDataSetChanged();
        } else {
            set1 = new BarDataSet(values, "Prayer time per day");
            ArrayList<IBarDataSet> dataSets = new ArrayList<>();
            dataSets.add(set1);
            BarData data = new BarData(dataSets);
            data.setValueTextSize(10f);
            data.setBarWidth(0.9f);
            barChart.setData(data);
        }
    }

    public void onValueChanged(){
        setData(labelLst, valLst);
        barChart.invalidate();    //차트 업데이트
    }
    /*
    public void graphInitSetting(){
        labelLst.add("S");
        labelLst.add("M");
        labelLst.add("T");
        labelLst.add("W");
        labelLst.add("T");
        labelLst.add("F");
        labelLst.add("S");

        // 나중에 사용자마다 그래프 단위 달라지게 만들어야 할 듯
        for(int i = 0; i < 8; i++){
            int label_unit = 10 + i*10;
            valLst.add(label_unit); //element
        }

        BarChartGraph(labelLst, valLst);

    }

     */

    /**
     * 그래프함수
     */
    private void BarChartGraph(ArrayList<String> labelList, ArrayList<Integer> valList) {
        // BarChart 메소드

        ArrayList<BarEntry> entries = new ArrayList<>();
        for (int i = 0; i < valList.size(); i++) {
            entries.add(new BarEntry((Integer) valList.get(i), i));
        }

        BarDataSet dependents = new BarDataSet(entries, "일일 기도시간"); //변수로 받아서 넣어줘도 됨.
        dependents.setAxisDependency(YAxis.AxisDependency.LEFT);

        ArrayList<String> labels = new ArrayList<String>();
        for (int i = 0; i < labelList.size(); i++) {
            labels.add((String) labelList.get(i));
        }

/*
        BarData data = new BarData(dependents); //라이브러리 v3.x 사용하면 에러 발생함
        dependents.setColors(ColorTemplate.LIBERTY_COLORS);

        //barChart.setData(data);
        barChart.animateXY(1000, 1000);
        barChart.invalidate();  //차트 업데이트

 */
    }

    /**
     * ActivityResultLauncher
     */
    ActivityResultLauncher<Intent> launcher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == RESULT_OK)
                    {
                        Log.e(TAG, "result : " + result);
                        Intent intent = result.getData();
                        Log.e(TAG, "intent : " + intent);
                        Uri uri = intent.getData();
                    }
                }
            });


}