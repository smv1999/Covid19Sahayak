package com.programmersgateway.sm1999.covid19sahayak;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.Window;
import android.widget.Toast;


import com.programmersgateway.sm1999.covid19sahayak.adapters.DistrictAdapter;
import com.programmersgateway.sm1999.covid19sahayak.network.Retrofit.RFInterface;
import com.programmersgateway.sm1999.covid19sahayak.network.Retrofit.ResponseModels.statewise.DistrictDatum;
import com.programmersgateway.sm1999.covid19sahayak.network.Retrofit.ResponseModels.statewise.StateResponse;
import com.programmersgateway.sm1999.covid19sahayak.network.Utility;

import java.util.List;

import retrofit2.Response;

public class DistrictActivity extends AppCompatActivity implements DistrictAdapter.OnItemClickListener {

    String state;
    private RecyclerView districtRecycler;
    private DistrictAdapter districtAdapter;
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
        setContentView(R.layout.activity_district);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        state = getIntent().getStringExtra("state");

        districtRecycler = findViewById(R.id.districtRecycler);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        districtRecycler.setLayoutManager(mLayoutManager);
        districtRecycler.setItemAnimator(new DefaultItemAnimator());

        new DownloadStatesData(getString(R.string.base_url)).execute("");

        setTitle("Districtwise Confirmed Cases");
    }


    public void setRecyclerView(List<StateResponse> stateResponses){

        int myPosition = getPosition(stateResponses);
        if (myPosition != -1){
            districtAdapter= new DistrictAdapter(stateResponses.get(myPosition).getDistrictData(), DistrictActivity.this, this);

            districtRecycler.setAdapter(districtAdapter);

        } else {
            toast("No Record Found!!");
        }
    }

    public int getPosition(List<StateResponse> stateResponses){
        for (int i = 0; i<stateResponses.size(); i++){
            if (stateResponses.get(i).getState().equalsIgnoreCase(state)){
                return i;
            }
        }
        return -1;
    }

    @Override
    public void onItemClick(DistrictDatum districtDatum) {
        toast("Total confirmed cases in " + districtDatum.getDistrict() + " district is " + districtDatum.getConfirmed());
    }


    class DownloadStatesData extends AsyncTask<String, String, List<StateResponse>> {

        private ProgressDialog uploadingDialog;
        private RFInterface rfInterface;

        DownloadStatesData(String baseUrl) {
            rfInterface = Utility.getRetrofitInterface(baseUrl);
            uploadingDialog = new ProgressDialog(DistrictActivity.this);
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            uploadingDialog.setMessage("Loading,Please wait...");
            uploadingDialog.setCancelable(false);
            uploadingDialog.show();
        }

        @Override
        protected List<StateResponse> doInBackground(String... strings) {
            try {

                Response<List<StateResponse>> responseResult = rfInterface.getStateData().execute();

                if (responseResult.isSuccessful()) {
                    if (responseResult.body() != null) {
                        if (responseResult.body().size() > 0) {
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
        protected void onPostExecute(List<StateResponse> stateResponses) {
            super.onPostExecute(stateResponses);
            uploadingDialog.dismiss();
            if (stateResponses != null) {
                setRecyclerView(stateResponses);
                //toast("success");
            } else {
                toast("failed");
            }
        }
    }

    public void toast(String msg){
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
