package com.programmersgateway.sm1999.covid19sahayak;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.material.navigation.NavigationView;
import com.programmersgateway.sm1999.covid19sahayak.network.ConnectionDetector;
import com.programmersgateway.sm1999.covid19sahayak.network.Retrofit.RFInterface;
import com.programmersgateway.sm1999.covid19sahayak.network.Retrofit.ResponseModels.dashboard.DashboardResponse;
import com.programmersgateway.sm1999.covid19sahayak.network.Utility;

import java.io.Serializable;

import retrofit2.Response;

public class DashboardActivity extends AppCompatActivity {

    private CardView cvConfirmed, cvActive, cvRecovered, cvDeath, cvTestYourSelf, cvSymptoms;
    private TextView tvConfirmedCt, tvActiveCt, tvRecoveredCt, tvDeathCt,lastupdate;
    private TextView tvConfirmed, tvActive, tvRecovered, tvDeath, tvTestYourSelf, tvSymptoms;
    DashboardResponse dashboardResponse;
    Intent intent;
    private ConnectionDetector connectionDetector;
    SharedPrefs sharedPrefs;
    private NavigationView navigationView;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    @Override
    protected void onResume() {
        super.onResume();

        if (!connectionDetector.isNetworkConnected() && !connectionDetector.internetIsConnected()){
            startActivity(new Intent(DashboardActivity.this, ErrorActivity.class));
            finish();
        }
    }

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
        setContentView(R.layout.activity_dashboard);
        navigationView = findViewById(R.id.mainnavView);
        drawerLayout = findViewById(R.id.drawerlayout);
        getSupportActionBar().setTitle("Covid-19");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        actionBarDrawerToggle = new ActionBarDrawerToggle(DashboardActivity.this,drawerLayout,R.string.drawer_open,R.string.drawer_close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        View navView = navigationView.inflateHeaderView(R.layout.navigation_header);


        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.toll_free:
                        startActivity(new Intent(DashboardActivity.this,TollFreeNumberActivity.class).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TOP));
                        return true;
                    case R.id.action_settings:
                        startActivity(new Intent(DashboardActivity.this,SettingsActivity.class).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TOP));
                        return true;
                    case R.id.medical_stores:
                        String url = "https://www.google.com/maps/search/medical+store+near+me/@13.0452436,80.1986642,15z/data=!3m1!4b1";
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                        intent.setClassName("com.google.android.apps.maps","com.google.android.maps.MapsActivity");
                        startActivity(intent);
                        return true;
                }
                return false;
            }
        });
        initViews();
        connectionDetector = new ConnectionDetector(this);
        if (connectionDetector.isNetworkConnected() && connectionDetector.internetIsConnected()){

            new DownloadLatestUpdate(getString(R.string.base_url)).execute("");
        } else {
            startActivity(new Intent(DashboardActivity.this, ErrorActivity.class));
            finish();
        }


    }

    public void initViews() {

        // cardview
        cvConfirmed = findViewById(R.id.cvConfirmed);
        cvActive = findViewById(R.id.cvActive);
        cvRecovered = findViewById(R.id.cvRecovered);
        cvDeath = findViewById(R.id.cvDeath);
        cvTestYourSelf = findViewById(R.id.cvTestYourSelf);
        cvSymptoms = findViewById(R.id.cvSymptoms);

        //textview count
        tvConfirmedCt = findViewById(R.id.tvConfirmedCt);
        tvActiveCt = findViewById(R.id.tvActiveCt);
        tvRecoveredCt = findViewById(R.id.tvRecoveredCt);
        tvDeathCt = findViewById(R.id.tvDeathCt);

        //textview
        tvConfirmed = findViewById(R.id.tvConfirmed);
        tvActive = findViewById(R.id.tvActive);
        tvRecovered = findViewById(R.id.tvRecovered);
        tvDeath = findViewById(R.id.tvDeath);
        tvTestYourSelf = findViewById(R.id.tvTestYourSelf);
        tvSymptoms = findViewById(R.id.tvSymptoms);
        lastupdate = findViewById(R.id.lastupdate);

    }


    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.cvConfirmed:
                intent = new Intent(DashboardActivity.this, StatesActivity.class);
                intent.putExtra("type", "Confirmed");
                intent.putExtra("StateList", (Serializable) dashboardResponse.getStatewise());
                startActivity(intent);
                //toast("Confirmed");
                break;

            case R.id.cvActive:
                intent = new Intent(DashboardActivity.this, StatesActivity.class);
                intent.putExtra("type", "Active");
                intent.putExtra("StateList", (Serializable) dashboardResponse.getStatewise());
                startActivity(intent);
//                toast("Active");
                break;

            case R.id.cvRecovered:
                intent = new Intent(DashboardActivity.this, StatesActivity.class);
                intent.putExtra("type", "Recovered");
                intent.putExtra("StateList", (Serializable) dashboardResponse.getStatewise());
                startActivity(intent);
                //toast("Recovered");
                break;

            case R.id.cvDeath:
                intent = new Intent(DashboardActivity.this, StatesActivity.class);
                intent.putExtra("type", "Death");
                intent.putExtra("StateList", (Serializable) dashboardResponse.getStatewise());
                startActivity(intent);
//                toast("Death");
                break;

            case R.id.cvTestYourSelf:
                toast("TestYourSelf");
                break;

            case R.id.cvSymptoms:
                intent = new Intent(DashboardActivity.this, WebViewActivity.class);
                startActivity(intent);
                //toast("Symptoms");
                break;


            default:
                toast("Default");
                break;
        }
    }

    public void toast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    class DownloadLatestUpdate extends AsyncTask<String, String, DashboardResponse> {

        private ProgressDialog uploadingDialog;
        private RFInterface rfInterface;

        DownloadLatestUpdate(String baseUrl) {
            rfInterface = Utility.getRetrofitInterface(baseUrl);
            uploadingDialog = new ProgressDialog(DashboardActivity.this);
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            uploadingDialog.setMessage("Loading,Please wait...");
            uploadingDialog.setCancelable(false);
            uploadingDialog.show();
        }

        @Override
        protected DashboardResponse doInBackground(String... strings) {
            try {

                Response<DashboardResponse> responseResult = rfInterface.getDashboardData().execute();

                if (responseResult.isSuccessful()) {
                    if (responseResult.body() != null) {
                        if (responseResult.body().getStatewise() != null) {
                            return responseResult.body();
                        }
                    }
                } else {
                    responseResult.errorBody();
                }

            } catch (Exception e) {
                System.out.println("Exception " + e);
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(String... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(DashboardResponse dashboardResponse) {
            super.onPostExecute(dashboardResponse);
            uploadingDialog.dismiss();
            if (dashboardResponse != null) {
                setData(dashboardResponse);
                //toast("success");
            } else {
                toast("failed");
            }
        }
    }

    public void setData(DashboardResponse dashboardResp) {
        this.dashboardResponse = dashboardResp;
        //setTitle("Covid19 India \nLast Update on " + dashboardResponse.getStatewise().get(0).getLastupdatedtime());
        tvConfirmedCt.setText(dashboardResponse.getStatewise().get(0).getConfirmed());
        tvActiveCt.setText(dashboardResponse.getStatewise().get(0).getActive());
        tvRecoveredCt.setText(dashboardResponse.getStatewise().get(0).getRecovered());
        tvDeathCt.setText(dashboardResponse.getStatewise().get(0).getDeaths());
        lastupdate.setText("Last Update : " + dashboardResponse.getStatewise().get(0).getLastupdatedtime());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.my_dashboard_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.refreshDashboard:
                if (connectionDetector.isNetworkConnected() && connectionDetector.internetIsConnected()) {
                    new DownloadLatestUpdate(getString(R.string.base_url)).execute("");
                } else {
                    startActivity(new Intent(DashboardActivity.this, ErrorActivity.class));
                    finish();
                }
                return true;
            case R.id.global_cases:
                startActivity(new Intent(DashboardActivity.this,MainActivity.class));
                return true;

            default:
                return actionBarDrawerToggle.onOptionsItemSelected(item);
        }
    }


}
