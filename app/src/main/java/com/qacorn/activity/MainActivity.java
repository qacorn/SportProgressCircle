package com.qacorn.activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;

import com.qacorn.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        SharedPreferences preference = getSharedPreferences("SportProgressCircle", Context.MODE_PRIVATE);
        preference.edit().clear();
        preference.edit().putInt("DISPLAY_WIDTH", displayMetrics.widthPixels).commit();
    }
}
