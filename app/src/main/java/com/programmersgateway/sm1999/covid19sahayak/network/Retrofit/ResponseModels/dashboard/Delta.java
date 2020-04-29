
package com.programmersgateway.sm1999.covid19sahayak.network.Retrofit.ResponseModels.dashboard;

import com.google.gson.annotations.SerializedName;

public class Delta {

    @SerializedName("active")
    private Long mActive;
    @SerializedName("confirmed")
    private Long mConfirmed;
    @SerializedName("deaths")
    private Long mDeaths;
    @SerializedName("recovered")
    private Long mRecovered;

    public Long getActive() {
        return mActive;
    }

    public void setActive(Long active) {
        mActive = active;
    }

    public Long getConfirmed() {
        return mConfirmed;
    }

    public void setConfirmed(Long confirmed) {
        mConfirmed = confirmed;
    }

    public Long getDeaths() {
        return mDeaths;
    }

    public void setDeaths(Long deaths) {
        mDeaths = deaths;
    }

    public Long getRecovered() {
        return mRecovered;
    }

    public void setRecovered(Long recovered) {
        mRecovered = recovered;
    }

}
