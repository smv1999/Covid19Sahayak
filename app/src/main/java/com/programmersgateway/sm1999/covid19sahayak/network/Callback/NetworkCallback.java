package com.programmersgateway.sm1999.covid19sahayak.network.Callback;

import android.os.Bundle;

public interface NetworkCallback {
    void onSuccess(Bundle msg);

    void onFailure(Bundle msg);
}