
package com.programmersgateway.sm1999.covid19sahayak.network.Retrofit.ResponseModels.statewise;

import com.google.gson.annotations.SerializedName;

public class DistrictDatum {

    @SerializedName("confirmed")
    private Long mConfirmed;
    @SerializedName("delta")
    private Delta mDelta;
    @SerializedName("district")
    private String mDistrict;
    @SerializedName("lastupdatedtime")
    private String mLastupdatedtime;

    public Long getConfirmed() {
        return mConfirmed;
    }

    public void setConfirmed(Long confirmed) {
        mConfirmed = confirmed;
    }

    public Delta getDelta() {
        return mDelta;
    }

    public void setDelta(Delta delta) {
        mDelta = delta;
    }

    public String getDistrict() {
        return mDistrict;
    }

    public void setDistrict(String district) {
        mDistrict = district;
    }

    public String getLastupdatedtime() {
        return mLastupdatedtime;
    }

    public void setLastupdatedtime(String lastupdatedtime) {
        mLastupdatedtime = lastupdatedtime;
    }

}
