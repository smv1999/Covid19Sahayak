
package com.programmersgateway.sm1999.covid19sahayak.network.Retrofit.ResponseModels.dashboard;

import com.google.gson.annotations.SerializedName;

public class KeyValue {

    @SerializedName("confirmeddelta")
    private String mConfirmeddelta;
    @SerializedName("counterforautotimeupdate")
    private String mCounterforautotimeupdate;
    @SerializedName("deceaseddelta")
    private String mDeceaseddelta;
    @SerializedName("lastupdatedtime")
    private String mLastupdatedtime;
    @SerializedName("recovereddelta")
    private String mRecovereddelta;
    @SerializedName("statesdelta")
    private String mStatesdelta;

    public String getConfirmeddelta() {
        return mConfirmeddelta;
    }

    public void setConfirmeddelta(String confirmeddelta) {
        mConfirmeddelta = confirmeddelta;
    }

    public String getCounterforautotimeupdate() {
        return mCounterforautotimeupdate;
    }

    public void setCounterforautotimeupdate(String counterforautotimeupdate) {
        mCounterforautotimeupdate = counterforautotimeupdate;
    }

    public String getDeceaseddelta() {
        return mDeceaseddelta;
    }

    public void setDeceaseddelta(String deceaseddelta) {
        mDeceaseddelta = deceaseddelta;
    }

    public String getLastupdatedtime() {
        return mLastupdatedtime;
    }

    public void setLastupdatedtime(String lastupdatedtime) {
        mLastupdatedtime = lastupdatedtime;
    }

    public String getRecovereddelta() {
        return mRecovereddelta;
    }

    public void setRecovereddelta(String recovereddelta) {
        mRecovereddelta = recovereddelta;
    }

    public String getStatesdelta() {
        return mStatesdelta;
    }

    public void setStatesdelta(String statesdelta) {
        mStatesdelta = statesdelta;
    }

}
