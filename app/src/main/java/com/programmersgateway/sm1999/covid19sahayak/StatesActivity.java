package com.programmersgateway.sm1999.covid19sahayak;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.Window;
import android.widget.Toast;


import com.programmersgateway.sm1999.covid19sahayak.adapters.StateAdapter;
import com.programmersgateway.sm1999.covid19sahayak.network.ConnectionDetector;
import com.programmersgateway.sm1999.covid19sahayak.network.Retrofit.ResponseModels.dashboard.Statewise;

import java.util.List;

public class StatesActivity extends AppCompatActivity implements StateAdapter.OnItemClickListener {

    private RecyclerView stateRecycler;
    private StateAdapter stateAdapter;
    List<Statewise> statewiseList;
    String type;
    private ConnectionDetector connectionDetector;
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
        setContentView(R.layout.activity_states);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        connectionDetector = new ConnectionDetector(this);

        statewiseList = (List<Statewise>) getIntent().getSerializableExtra("StateList");
        type = getIntent().getStringExtra("type");
        statewiseList.remove(0);

        stateRecycler = findViewById(R.id.stateRecycler);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        stateRecycler.setLayoutManager(mLayoutManager);
        stateRecycler.setItemAnimator(new DefaultItemAnimator());

        stateAdapter = new StateAdapter(statewiseList, StatesActivity.this, this, type);

        stateRecycler.setAdapter(stateAdapter);

        setTitle("Statewise " + type + " Cases");
    }

    @Override
    public void onItemClick(Statewise statewise) {
        if (type.equalsIgnoreCase("Confirmed")) {
            if (connectionDetector.isNetworkConnected() && connectionDetector.internetIsConnected()) {
                Intent intent = new Intent(StatesActivity.this, DistrictActivity.class);
                intent.putExtra("state", statewise.getState());
                startActivity(intent);
            } else {
                startActivity(new Intent(StatesActivity.this, ErrorActivity.class));
                finish();
            }
            //toast(statewise.getState());
        } else {
            if (type.equals("Active"))
                toast("Total " + type + " cases in " + statewise.getState() + " state is " + statewise.getActive());
            else if (type.equals("Recovered"))
                toast("Total " + type + " cases in " + statewise.getState() + " state is " + statewise.getRecovered());
            else if (type.equals("Death"))
                toast("Total " + type + " cases in " + statewise.getState() + " state is " + statewise.getDeaths());
        }
    }

    public void toast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
