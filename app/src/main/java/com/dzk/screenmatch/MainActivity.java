package com.dzk.screenmatch;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.dzk.screenmatch.toutiao.DensityAdapter;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DensityAdapter.setAdapteredDensity(this,getApplication(),360);
        setContentView(R.layout.activity_main);
    }
}