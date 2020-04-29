
package com.programmersgateway.sm1999.covid19sahayak.network.Retrofit.ResponseModels.dashboard;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DashboardResponse {

    @SerializedName("cases_time_series")
    private List<CasesTimeSeries> mCasesTimeSeries;
    @SerializedName("key_values")
    private List<KeyValue> mKeyValues;
    @SerializedName("statewise")
    private List<Statewise> mStatewise;
    @SerializedName("tested")
    private List<Tested> mTested;

    public List<CasesTimeSeries> getCasesTimeSeries() {
        return mCasesTimeSeries;
    }

    public void setCasesTimeSeries(List<CasesTimeSeries> casesTimeSeries) {
        mCasesTimeSeries = casesTimeSeries;
    }

    public List<KeyValue> getKeyValues() {
        return mKeyValues;
    }

    public void setKeyValues(List<KeyValue> keyValues) {
        mKeyValues = keyValues;
    }

    public List<Statewise> getStatewise() {
        return mStatewise;
    }

    public void setStatewise(List<Statewise> statewise) {
        mStatewise = statewise;
    }

    public List<Tested> getTested() {
        return mTested;
    }

    public void setTested(List<Tested> tested) {
        mTested = tested;
    }

}
