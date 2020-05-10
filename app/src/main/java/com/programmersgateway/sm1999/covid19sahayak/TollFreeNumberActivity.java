package com.programmersgateway.sm1999.covid19sahayak;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;

public class TollFreeNumberActivity extends AppCompatActivity {

    CardView helpline_number,toll_free_number,help_line_mailid,state_helpline_numbers;
    int CALL_CODE = 1;
    SharedPrefs sharedPrefs;
    Animation textanimation;
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);

        sharedPrefs = new SharedPrefs(this);
        if(sharedPrefs.loadNightModeState())
        {
            setTheme(android.R.style.ThemeOverlay_Material_Dark);
            getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.darkPrimary)));
            Window window = getWindow();
            window.setStatusBarColor(getResources().getColor(R.color.darkPrimaryDark));
        }
        else
            setTheme(R.style.AppTheme);
        setContentView(R.layout.activity_toll_free_number);
        helpline_number = findViewById(R.id.helpline_number);
        toll_free_number = findViewById(R.id.toll_free_number);
        help_line_mailid = findViewById(R.id.helpline_email);
        state_helpline_numbers = findViewById(R.id.state_helpline);
        textView = findViewById(R.id.toll_free_title);
        textanimation = AnimationUtils.loadAnimation(this,R.anim.textanimation);
        textView.startAnimation(textanimation);


        pulsatingEffect(helpline_number);
        pulsatingEffect(toll_free_number);
        pulsatingEffect(help_line_mailid);
        pulsatingEffect(state_helpline_numbers);


    }
    public void pulsatingEffect(CardView cardView)
    {
        ObjectAnimator scaleDown = ObjectAnimator.ofPropertyValuesHolder(
                cardView,
                PropertyValuesHolder.ofFloat("scaleX", 1.03f),
                PropertyValuesHolder.ofFloat("scaleY", 1.03f));
        scaleDown.setDuration(900);

        scaleDown.setRepeatCount(ObjectAnimator.INFINITE);
        scaleDown.setRepeatMode(ObjectAnimator.REVERSE);

        scaleDown.start();
    }
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.helpline_number:
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + "+91 11 23978046"));
                startActivity(intent);
                break;
            case R.id.toll_free_number:
                Intent toll_intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + "+91 1075"));
                startActivity(toll_intent);
                break;
            case R.id.helpline_email:
                Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                        "mailto","ncov2019@gov.in", null));
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Subject");
                emailIntent.putExtra(Intent.EXTRA_TEXT, "Body");
                startActivity(Intent.createChooser(emailIntent, "Send email..."));
                break;
            case R.id.state_helpline:
                Uri uri = Uri.parse("https://www.mohfw.gov.in/pdf/coronvavirushelplinenumber.pdf");
                Intent pdf = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(pdf);
                break;
            default:
                Toast.makeText(this, "Default!", Toast.LENGTH_SHORT).show();
        }
    }
}