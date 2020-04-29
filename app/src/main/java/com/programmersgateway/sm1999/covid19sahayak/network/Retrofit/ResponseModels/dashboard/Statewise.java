
package com.programmersgateway.sm1999.covid19sahayak.network.Retrofit.ResponseModels.dashboard;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Statewise implements Parcelable {

    @SerializedName("active")
    private String mActive;
    @SerializedName("confirmed")
    private String mConfirmed;
    @SerializedName("deaths")
    private String mDeaths;
    @SerializedName("delta")
    private Delta mDelta;
    @SerializedName("deltaconfirmed")
    private String mDeltaconfirmed;
    @SerializedName("deltadeaths")
    private String mDeltadeaths;
    @SerializedName("deltarecovered")
    private String mDeltarecovered;
    @SerializedName("lastupdatedtime")
    private String mLastupdatedtime;
    @SerializedName("recovered")
    private String mRecovered;
    @SerializedName("state")
    private String mState;
    @SerializedName("statecode")
    private String mStatecode;

    protected Statewise(Parcel in) {
        mActive = in.readString();
        mConfirmed = in.readString();
        mDeaths = in.readString();
        mDeltaconfirmed = in.readString();
        mDeltadeaths = in.readString();
        mDeltarecovered = in.readString();
        mLastupdatedtime = in.readString();
        mRecovered = in.readString();
        mState = in.readString();
        mStatecode = in.readString();
    }

    public static final Creator<Statewise> CREATOR = new Creator<Statewise>() {
        @Override
        public Statewise createFromParcel(Parcel in) {
            return new Statewise(in);
        }

        @Override
        public Statewise[] newArray(int size) {
            return new Statewise[size];
        }
    };

    public String getActive() {
        return mActive;
    }

    public void setActive(String active) {
        mActive = active;
    }

    public String getConfirmed() {
        return mConfirmed;
    }

    public void setConfirmed(String confirmed) {
        mConfirmed = confirmed;
    }

    public String getDeaths() {
        return mDeaths;
    }

    public void setDeaths(String deaths) {
        mDeaths = deaths;
    }

    public Delta getDelta() {
        return mDelta;
    }

    public void setDelta(Delta delta) {
        mDelta = delta;
    }

    public String getDeltaconfirmed() {
        return mDeltaconfirmed;
    }

    public void setDeltaconfirmed(String deltaconfirmed) {
        mDeltaconfirmed = deltaconfirmed;
    }

    public String getDeltadeaths() {
        return mDeltadeaths;
    }

    public void setDeltadeaths(String deltadeaths) {
        mDeltadeaths = deltadeaths;
    }

    public String getDeltarecovered() {
        return mDeltarecovered;
    }

    public void setDeltarecovered(String deltarecovered) {
        mDeltarecovered = deltarecovered;
    }

    public String getLastupdatedtime() {
        return mLastupdatedtime;
    }

    public void setLastupdatedtime(String lastupdatedtime) {
        mLastupdatedtime = lastupdatedtime;
    }

    public String getRecovered() {
        return mRecovered;
    }

    public void setRecovered(String recovered) {
        mRecovered = recovered;
    }

    public String getState() {
        return mState;
    }

    public void setState(String state) {
        mState = state;
    }

    public String getStatecode() {
        return mStatecode;
    }

    public void setStatecode(String statecode) {
        mStatecode = statecode;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mActive);
        dest.writeString(mConfirmed);
        dest.writeString(mDeaths);
        dest.writeString(mDeltaconfirmed);
        dest.writeString(mDeltadeaths);
        dest.writeString(mDeltarecovered);
        dest.writeString(mLastupdatedtime);
        dest.writeString(mRecovered);
        dest.writeString(mState);
        dest.writeString(mStatecode);
    }
}
