package com.padcmyanmar.sfc.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

/**
 * Created by aung on 11/25/17.
 */

public class InternetStateReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        ConnectivityManager connectivityManager =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo.isConnected()) {
            Toast.makeText(context, "Your device is connected to internet.", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(context, "Your device is no longer connected to internet.", Toast.LENGTH_LONG).show();
        }
    }
}
