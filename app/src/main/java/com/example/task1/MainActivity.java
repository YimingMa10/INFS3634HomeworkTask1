package com.example.task1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;

public class MainActivity extends AppCompatActivity {

    String layoutString;
    String[] cityName = {"Sydney", "Shanghai", "Toronto", "Tokyo", "New_York"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setImageAndName();
        setTime("12hr");

        Button format12hr = (Button) findViewById(R.id.format12hr);
        format12hr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setTime("12hr");
            }
        });

        Button format24hr = (Button) findViewById(R.id.format24hr);
        format24hr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setTime("24hr");
            }
        });
    }

    public void setImageAndName(){
        for(int i = 0; i < 5; i++){
            layoutString = "include_" + i;
            int layoutId = getResources().getIdentifier(layoutString, "id", getPackageName());
            View layout = (View) findViewById(layoutId);
            String currentCity = cityName[i].toLowerCase();

            TextView textView = (TextView) layout.findViewById(R.id.name);
            ImageView imageView = (ImageView) layout.findViewById(R.id.image);

            textView.setText(cityName[i].replaceAll("_"," "));
            int imageId = getResources().getIdentifier(currentCity,"drawable",getPackageName());
            imageView.setImageResource(imageId);
        }
    }

    public void setTime(String format){
        SimpleDateFormat format12hr = new SimpleDateFormat("hh:mm aa");
        SimpleDateFormat format24hr = new SimpleDateFormat("HH:mm:ss");
        TimeZone timeZone;
        Calendar calendar;
        String timeShow = "";

        for(int i = 0; i < 5; i++){
            layoutString = "include_" + i;
            int layoutId = getResources().getIdentifier(layoutString, "id", getPackageName());
            View layout = (View) findViewById(layoutId);
            String currentCity = cityName[i].toLowerCase();

            int id = getResources().getIdentifier(currentCity, "string", getPackageName());
            String cityId = getResources().getString(id);
            timeZone = TimeZone.getTimeZone(cityId);
            calendar = Calendar.getInstance(timeZone);


            TextView textView = (TextView) layout.findViewById(R.id.timeShow);
            if(format.equals("12hr")){
                timeShow = format12hr.format(calendar.getTime());
            } else {
                timeShow = format24hr.format(calendar.getTime());
            }

            textView.setText(timeShow);
        }
    }
}
