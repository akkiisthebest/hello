package adi.app.taskmanager;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import adi.app.taskmanager.R;

import java.util.List;

public class AlterAdapter<T> extends ArrayAdapter<T> {
    Context hi;
    public AlterAdapter(@NonNull Context context, int resource, @NonNull List<T> objects) {
        super(context, resource, objects);
        hi = context;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        TextView view = (TextView) super.getView(position,convertView,parent);
        String hi3 = (String) view.getText();
        String [] hello = hi3.split("\n");
        String [] check = hello[1].split(" ");
        if (check[1].equals("days")) {
            view.setTextColor(Color.RED);
        } else {
            view.setTextColor(Color.BLACK);
        }
        if (position % 2 == 1) {
            view.setBackgroundColor(Color.parseColor("#96CAC5"));
        } else {
            view.setBackgroundColor(Color.parseColor("#FFFFFF"));
        }

        view.setTextSize(18);
        Typeface hi2 = hi.getResources().getFont(R.font.calibri);
        view.setTypeface(hi2);
        return view;
    }
}
