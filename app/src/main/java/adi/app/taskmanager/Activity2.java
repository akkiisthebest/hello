package adi.app.taskmanager;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;

import adi.app.taskmanager.R;
import com.google.android.material.snackbar.Snackbar;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Activity2 extends Activity {
    String datetxt;
    long hi5 = 0;
    Date currDate;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.onclick);
        final Intent intent = getIntent();
        String [] hi4 = intent.getStringExtra(Intent.EXTRA_TEXT).split("\n");
        final Button save = findViewById(R.id.save);
        final EditText hi2 = findViewById(R.id.editText4);
        String strttext = hi4[0];
        hi2.setText(strttext);
        final CalendarView hi3 = findViewById(R.id.editText5);
        Date date = new Date();
        final SimpleDateFormat hi25 = new SimpleDateFormat("MM/dd/yyyy");
        String [] datearr = hi4[2].split(" ");
        try {
            date = hi25.parse(datearr[1]);
        } catch (Exception e) {
            e.printStackTrace();
        }
        datetxt = datearr[1];
        hi3.setDate(date.getTime());
        final Button discard = findViewById(R.id.discard);
        hi3.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                datetxt = (month + 1) + "/" + dayOfMonth + "/" + year;
            }
        });
        currDate = new Date();

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent hi = new Intent();
                String date2 = hi25.format(new Date());
                try {
                    currDate = hi25.parse(date2.trim());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                try {
                    hi5 = ((hi25.parse(datetxt.trim()).getTime())/86400000 - (currDate.getTime())/86400000);
                    System.out.println(hi5 + " hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhh");
                } catch(Exception e) {
                    e.printStackTrace();
                }
                if (hi5 < 0) {
                    Snackbar.make(v,"Please enter a time after the current date",Snackbar.LENGTH_SHORT).setAction("Action", null).show();
                } else {
                    setResult(RESULT_OK, hi);
                    hi.putExtra(Intent.EXTRA_CC, "Save");
                    hi.putExtra(Intent.EXTRA_INDEX, intent.getStringExtra(Intent.EXTRA_TEXT));
                    System.out.println(datetxt + " datetxt");
                    hi.putExtra(Intent.EXTRA_TEXT, hi2.getText().toString() + "\nDays left: " + hi5 + "\nDue: " + datetxt);
                    finish();
                }
            }
        });
        discard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent hi = new Intent();
                setResult(RESULT_OK,hi);
                hi.putExtra(Intent.EXTRA_CC,"Discard");
                finish();
            }
        });
    }
}
