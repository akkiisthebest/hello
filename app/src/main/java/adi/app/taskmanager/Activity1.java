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


public class Activity1 extends Activity {
    String datestr = "";
    Date currDate;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add);
        final Button button = findViewById(R.id.button);
        final EditText text2 = findViewById(R.id.editText);
        final CalendarView date = findViewById(R.id.editText3);
        final SimpleDateFormat hi = new SimpleDateFormat("MM/dd/yyyy");
        String date2 = hi.format(new Date());
        try {
            currDate = hi.parse(date2.trim());
        } catch (Exception e) {
            e.printStackTrace();
        }
        datestr = hi.format(new Date(date.getDate()));
        date.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                datestr = (month + 1) + "/" + dayOfMonth + "/"+year;
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent ret = new Intent();
                long hi2 = 0;
                try {
                    hi2 = ((hi.parse(datestr.trim()).getTime())/86400000 - (currDate.getTime())/86400000);
                } catch(Exception e) {
                    e.printStackTrace();
                }
                if (hi2 < 0) {
                    Snackbar.make(v,"Please enter a time after the current date",Snackbar.LENGTH_SHORT).setAction("Action", null).show();
                } else {
                    ret.putExtra(Intent.EXTRA_INDEX, hi2);
                    ret.putExtra(Intent.EXTRA_TEXT, text2.getText().toString());
                    ret.putExtra(Intent.EXTRA_CC, datestr);
                    setResult(RESULT_OK, ret);
                    finish();
                }
            }
        });
    }
}
