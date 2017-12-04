package com.padcmyanmar.sfc.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.padcmyanmar.sfc.R;
import com.padcmyanmar.sfc.activities.LoginRegisterActivity;
import com.padcmyanmar.sfc.delegates.LoginRegisterDelegate;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by aung on 11/26/17.
 */

public class LoginFragment extends BaseFragment {

    private LoginRegisterDelegate mLoginRegisterDelegate;

    public static LoginFragment newInstance() {
        LoginFragment loginFragment = new LoginFragment();
        return loginFragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mLoginRegisterDelegate = (LoginRegisterDelegate) context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View loginView = inflater.inflate(R.layout.fragment_login, container, false);
        ButterKnife.bind(this, loginView);
        return loginView;
    }

    @Override
    public void onStart() {
        super.onStart();
        mLoginRegisterDelegate.setScreenTitle("Login");
    }

    @OnClick(R.id.btn_to_register)
    public void onTapToRegister(View view) {
        mLoginRegisterDelegate.onTapToRegister();
    }

    @Override
    public void checkNetworkConnectivity() {
        super.checkNetworkConnectivity();
        //Check
    }
}
