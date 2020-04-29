package com.programmersgateway.sm1999.covid19sahayak.network;

import android.app.Dialog;
import android.content.Context;
import android.os.Build;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import com.programmersgateway.sm1999.covid19sahayak.network.Retrofit.RFClient;
import com.programmersgateway.sm1999.covid19sahayak.network.Retrofit.RFInterface;

import java.io.File;

public class Utility {

    public static View initRecyclerView(ViewGroup parent, int id) {
        return LayoutInflater.from(parent.getContext()).inflate(id, parent, false);
    }

    public static RFInterface getRetrofitInterface(String BASE_URL) {
        return RFClient.getClient(BASE_URL).create(RFInterface.class);
    }

    public static Dialog createDialog(Context context, int layout) {
        final Dialog dialog = new Dialog(context, android.R.style.Theme_Light_NoTitleBar_Fullscreen);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(layout);
        dialog.show();
        return dialog;
    }

    public static Dialog createDialog(Context context, int layout, boolean isFullscreen) {
        Dialog dialog;
        if (isFullscreen) {
            dialog = new Dialog(context, android.R.style.Theme_Light_NoTitleBar_Fullscreen);
        } else {
            dialog = new Dialog(context);
        }

        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(layout);
        dialog.show();
        return dialog;
    }

    public static boolean isAutoTimeEnabled(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            return Settings.Global.getInt(context.getContentResolver(), Settings.Global.AUTO_TIME, 0) > 0;
        }
        return Settings.System.getInt(context.getContentResolver(), Settings.System.AUTO_TIME, 0) > 0;
    }

    public static boolean isAutoZoneEnabled(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
            return Settings.Global.getInt(context.getContentResolver(), Settings.Global.AUTO_TIME_ZONE, 0) > 0;
        } else {
            return Settings.Global.getInt(context.getContentResolver(), Settings.Global.AUTO_TIME_ZONE, 0) > 0;
        }
    }

    private static boolean deleteDir(File dir) {
        if (dir != null && dir.isDirectory()) {
            String[] children = dir.list();
            for (String aChildren : children) {
                boolean success = deleteDir(new File(dir, aChildren));
                if (!success) {
                    return false;
                }
            }
        }
        return dir.delete();
    }
}