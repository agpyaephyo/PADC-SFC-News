package com.padcmyanmar.sfc.fragments;

import android.support.v4.app.Fragment;
import android.util.Log;

/**
 * Created by aung on 12/2/17.
 */

public abstract class BaseFragment extends Fragment {

    public void checkNetworkConnectivity() {
        Log.d("", "Base checkNetworkConnectivity");
    }
}
