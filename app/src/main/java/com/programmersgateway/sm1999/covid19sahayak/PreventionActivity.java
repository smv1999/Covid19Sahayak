package com.programmersgateway.sm1999.covid19sahayak;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class PreventionActivity extends AppCompatActivity {

    SharedPrefs sharedPrefs;
    ImageView prevention1,prevention2,prevention3,prevention4,coronavirus;
    Animation animation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
        setContentView(R.layout.activity_prevention);
        prevention1 = findViewById(R.id.prevention1);
        prevention2 = findViewById(R.id.prevention2);
        prevention3 = findViewById(R.id.prevention3);
        prevention4 = findViewById(R.id.prevention4);
        coronavirus = findViewById(R.id.coronavirus);

        animation = AnimationUtils.loadAnimation(this,R.anim.animation);
        coronavirus.startAnimation(animation);

        pulsatingEffect(prevention1);
        pulsatingEffect(prevention2);
        pulsatingEffect(prevention3);
        pulsatingEffect(prevention4);



    }
    public void pulsatingEffect(ImageView imageView)
    {
        ObjectAnimator scaleDown = ObjectAnimator.ofPropertyValuesHolder(
                imageView,
                PropertyValuesHolder.ofFloat("scaleX", 1.03f),
                PropertyValuesHolder.ofFloat("scaleY", 1.03f));
        scaleDown.setDuration(900);

        scaleDown.setRepeatCount(ObjectAnimator.INFINITE);
        scaleDown.setRepeatMode(ObjectAnimator.REVERSE);

        scaleDown.start();
    }
}
