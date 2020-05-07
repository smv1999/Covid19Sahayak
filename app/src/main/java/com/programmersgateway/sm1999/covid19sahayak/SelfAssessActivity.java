package com.programmersgateway.sm1999.covid19sahayak;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.radiobutton.MaterialRadioButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SelfAssessActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference myRef;
    private ProgressBar mProgressBar;
    SharedPrefs sharedPrefs;
    MaterialButton submit;
    RadioGroup q1,q2,q3,q4;
    MaterialRadioButton answer1,answer2,answer3,answer4;
    int dark = 0;
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
            dark = 1;
        }
        else
            setTheme(R.style.AppTheme);
        setContentView(R.layout.activity_self_assess);
        mAuth = FirebaseAuth.getInstance();
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        myRef = mFirebaseDatabase.getReference();
        submit = findViewById(R.id.submit);
        mProgressBar = findViewById(R.id.progressBar);
        mProgressBar.setVisibility(View.GONE);

        q1 = findViewById(R.id.question1);
        q2 = findViewById(R.id.question2);
        q3 = findViewById(R.id.question3);
        q4 = findViewById(R.id.question4);



        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mProgressBar.setVisibility(View.VISIBLE);

                if (q1.getCheckedRadioButtonId() == -1 || q2.getCheckedRadioButtonId() == -1 || q3.getCheckedRadioButtonId() == -1
                || q4.getCheckedRadioButtonId() == -1)
                {
                    // no radio buttons are checked
                    Toast.makeText(SelfAssessActivity.this, "Please answer all of the questions!", Toast.LENGTH_SHORT).show();
                    mProgressBar.setVisibility(View.GONE);


                }
                else
                {
                    // radio buttons are checked
                    if(mAuth.getCurrentUser() != null){

                        int selectedId1 = q1.getCheckedRadioButtonId();
                        int selectedId2 = q2.getCheckedRadioButtonId();
                        int selectedId3 = q3.getCheckedRadioButtonId();
                        int selectedId4 = q4.getCheckedRadioButtonId();


                        answer1 = findViewById(selectedId1);
                        answer2 = findViewById(selectedId2);
                        answer3 = findViewById(selectedId3);
                        answer4 = findViewById(selectedId4);


                        String a1 = answer1.getText().toString();
                        myRef.child("Users")
                                .child(mAuth.getCurrentUser().getUid())
                                .child("Are you experiencing any of the following symptoms?")
                                .setValue(a1);

                        String a2 = answer2.getText().toString();
                        myRef.child("Users")
                                .child(mAuth.getCurrentUser().getUid())
                                .child("Have you ever had any of the following?")
                                .setValue(a2);

                        String a3 = answer3.getText().toString();
                        myRef.child("Users")
                                .child(mAuth.getCurrentUser().getUid())
                                .child("Have you travelled anywhere internationally in the last 28-45 days?")
                                .setValue(a3);

                        String a4 = answer4.getText().toString();
                        myRef.child("Users")
                                .child(mAuth.getCurrentUser().getUid())
                                .child("Which of the following apply to you?")
                                .setValue(a4);

                        Toast.makeText(SelfAssessActivity.this, "Data Submitted Successfully!", Toast.LENGTH_SHORT).show();
                        if(a1.equals("None of the above") && a2.equals("None of the above") && a3.equals("No")
                        && a4.equals("None of the above"))
                        {
                            showdialog("Your infection risk is low. We recommend that you stay at home to avoid any chance of exposure " +
                                    "to the Novel Coronavirus. Retake the self assessment test if you develop symptoms or come " +
                                    "in contact with a COVID-19 confirmed patient.",dark);
                        }

                    }

                }


            }
        });

    }
    public void showdialog(String msg,int dark_theme){
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        if (dark_theme==1)
            dialog.setContentView(R.layout.dialog_misc_dark);
        else
            dialog.setContentView(R.layout.dialog_misc);

        TextView text = (TextView) dialog.findViewById(R.id.text_dialog);
        text.setText(msg);

        Button dialogButton = (Button) dialog.findViewById(R.id.btn_dialog);
        dialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SelfAssessActivity.this,DashboardActivity.class).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK));
            }
        });

        dialog.show();

    }
}
