package com.example.kashmirtouristplaces;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toolbar;

public class DeveloperDesk extends AppCompatActivity implements View.OnClickListener {
    TextView developer_info_tv;
    ImageButton instagramButton,facebookButton,emailButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_developer_desk);
        // tool bar coding
        androidx.appcompat.widget.Toolbar mToolbar = (androidx.appcompat.widget.Toolbar) findViewById(R.id.about_devloper_toolbar);
        mToolbar.setTitle("About Developer");//setting text on toolbar
        mToolbar.setNavigationIcon(R.drawable.ic_baseline_arrow_back_24); //fetching icon of back btn
        mToolbar.setNavigationOnClickListener(v -> finish()); // litner for back button on toolbar
        initViews();
        developer_info_tv.setText("Malik tawkeer ul islam, hailing from kulgam.");
        // REGISTERING EVENTS FOR CLICK
        instagramButton.setOnClickListener(this);
        facebookButton.setOnClickListener(this);
        emailButton.setOnClickListener(this);
     //   backButton.setOnClickListener(this);


    }

    private void initViews() {
        // INITILIZATION OF EVERY VIEW OR OBJECT/ AVRIABLE IS DONE HERE
        developer_info_tv = findViewById(R.id.about_developer_id_tv);
        instagramButton = findViewById(R.id.insagram_developer_activity_img_btn);
        facebookButton = findViewById(R.id.facebook_developer_activity_img_btn);
        emailButton = findViewById(R.id.email_developer_activity_img_btn);
       // backButton=findViewById(R.id.backbutton_developer_desk);
    }

    @Override
    public void onClick(View v)  // HANDLING BUTTON CLICKS BY USING onClick method
    {

        switch (v.getId())
        {
            case R.id.insagram_developer_activity_img_btn:
                String profileUrl = "https://www.instagram.com/malik.towkeer/";
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(profileUrl));
                startActivity(intent);
                break;
            case R.id.facebook_developer_activity_img_btn:

                break;
            case R.id.email_developer_activity_img_btn:
                Intent intent1 = new Intent(Intent.ACTION_SENDTO);
                intent1.setData(Uri.parse("mailto:"));
                intent1.putExtra(Intent.EXTRA_EMAIL, new String[]{"maliktow012@gmail.com"});
                intent1.putExtra(Intent.EXTRA_SUBJECT, "Your subject here...");
                intent1.putExtra(Intent.EXTRA_TEXT,"Your message here...");
                startActivity(intent1);
                break;
        }
    }
}