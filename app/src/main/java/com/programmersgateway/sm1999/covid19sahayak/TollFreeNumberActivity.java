package com.programmersgateway.sm1999.covid19sahayak;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Toast;

import java.io.Serializable;

public class TollFreeNumberActivity extends AppCompatActivity {

    CardView helpline_number;
    int CALL_CODE = 1;
    SharedPrefs sharedPrefs;
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




    }

    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.helpline_number:
                if (ContextCompat.checkSelfPermission( this, Manifest.permission.CALL_PHONE ) != PackageManager.PERMISSION_GRANTED )
                {
                    ActivityCompat.requestPermissions(
                            this,
                            new String [] { Manifest.permission.CALL_PHONE },CALL_CODE
                    );
                }
                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + "+91 11 23978046"));
                startActivity(intent);
                break;
            case R.id.toll_free_number:
                if (ContextCompat.checkSelfPermission( this, Manifest.permission.CALL_PHONE ) != PackageManager.PERMISSION_GRANTED )
                {
                    ActivityCompat.requestPermissions(
                            this,
                            new String [] { Manifest.permission.CALL_PHONE },CALL_CODE
                    );
                }
                Intent toll_intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + "+91 1075"));
                startActivity(toll_intent);
                break;
            case R.id.helpline_email:
                Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                        "mailto","ncov2019@gov.in", null));
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Subject");
                emailIntent.putExtra(Intent.EXTRA_TEXT, "Body");
                startActivity(Intent.createChooser(emailIntent, "Send email..."));
                break;
            default:
                Toast.makeText(this, "Default!", Toast.LENGTH_SHORT).show();
        }
    }
}