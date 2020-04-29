package com.programmersgateway.sm1999.covid19sahayak.network.Retrofit;



import com.programmersgateway.sm1999.covid19sahayak.network.Retrofit.ResponseModels.dashboard.DashboardResponse;
import com.programmersgateway.sm1999.covid19sahayak.network.Retrofit.ResponseModels.statewise.StateResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RFInterface {

    @GET("data.json")
    Call<DashboardResponse> getDashboardData();

//    @GET("v2/state_district_wise.json")
//    Call<StateResponse> getStateData();

    @GET("v2/state_district_wise.json")
    Call<List<StateResponse>> getStateData();

//    @GET
//    Call<ResponseBody> getStateData(@Url String url);

}