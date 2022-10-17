package edu.handong.prayer_bank;

import android.os.Bundle;
import android.util.Property;

import androidx.appcompat.app.AppCompatActivity;

import java.util.HashMap;
import org.naishadhparmar.zcustomcalendar.CustomCalendar;

public class Calendar extends AppCompatActivity {

    CustomCalendar customCalendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        customCalendar = findViewByID(R.id.custom_calendar);

        HashMap<Object, Property> descHashMap = new HashMap<>();

        Property defaultProperty = new Property();

        defaultProperty.layoutResource = R.layout.default_view;

        dafaultProperty.datetextViewResource = R.id.text_view;

        descHashMap.put("default", defaultProperty);

        Property currentProperty = new Property();
        currentProperty.layoutResource = R.layout.current_view;
        currentProperty.dateTextViewResource = R.id.text_view;
        descHasMap.put("current", currentProperty);

        Property presentProperty = new Property();
        presentProperty.layoutResource = R.layout.present_view;
        presentProperty.dateTextViewResource = R.id.text_view;
        descHasMap.put("present", presentProperty);

        Property absentProperty = new Property();
        absentProperty.layoutResource = R.layout.absent_view;
        absentProperty.dateTextViewResource = R.id.text_view;
        descHashMap.put("absent", absentProperty);

        customCalendar.setMapDescToProp(descHashMap);

        HashMap<Integer,Object> dateHashMap = new HashMap<>();

        Calendar calendar = Calendar.getInstance();

        dateHashMap.put(calendar.get(Calendar.DAY_OF_MONTH), "current");
        dateHashMap.put(1,"present");
        dateHashMap.put(2, "absent");
        dateHashMap.put(3, "present");
        dateHashMap.put(4, "absent");
        dateHashMap.put(20, "present");
        dateHashMap.put(30, "present");

        customCalendar.setDate(calendar,dateHashMap);

        customCalendar.setOnDateSelectedListener(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(View view, Calendar selectedDate, Object desc) {
                String sDate = selectedDate.get(Calendar.DAY_OF_MONTH)
                        + "/" + (selectedDate.get(Calendar.MONTH) + 1)
                        + "/" + selectedDate.get(Calendar.YEAR);
                Toast.makeText(getApllicationContext(),
                        sDate,Toast.LENGTH_SHORT).show();
            }
        })




    }
}