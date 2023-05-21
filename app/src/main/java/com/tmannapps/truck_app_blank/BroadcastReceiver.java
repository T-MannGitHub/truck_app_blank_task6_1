package com.tmannapps.truck_app_blank;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import androidx.annotation.NonNull;

public class BroadcastReceiver extends android.content.BroadcastReceiver {

    @NonNull
    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public void onReceive(Context context, Intent intent) {
        if (intent.getAction() == Intent.ACTION_CAMERA_BUTTON) {
            Log.e("Camera button status","Camera button pressed");
            //make the popup asking for permission to access files here.
        }
    }
}
