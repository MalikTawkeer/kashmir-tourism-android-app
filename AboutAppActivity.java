package com.example.kashmirtouristplaces;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.util.Arrays;

public class AboutAppActivity extends AppCompatActivity {
    String[] appInfo ={"This App Is Specially Developed for those who are trip lovers . In this application we are having more than 100 tourist places that you can explore to visit."};
    TextView aboutInfoTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_app);
        // tool bar coding
        androidx.appcompat.widget.Toolbar mToolbar = (androidx.appcompat.widget.Toolbar) findViewById(R.id.about_app_toolbar);
        mToolbar.setTitle("About Application");//setting text on toolbar
        mToolbar.setNavigationIcon(R.drawable.ic_baseline_arrow_back_24); //fetching icon of back btn
        mToolbar.setNavigationOnClickListener(v -> finish()); // litner for back button on toolba
        initViews();
        aboutInfoTv.setText(Arrays.toString(appInfo));
    }

    private void initViews() {
        aboutInfoTv = findViewById(R.id.tv_about_app);
    }
}