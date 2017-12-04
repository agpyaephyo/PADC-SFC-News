package com.padcmyanmar.sfc.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.padcmyanmar.sfc.R;
import com.padcmyanmar.sfc.delegates.LoginRegisterDelegate;
import com.padcmyanmar.sfc.fragments.LoginFragment;
import com.padcmyanmar.sfc.fragments.RegisterFragment;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by aung on 11/26/17.
 */

public class LoginRegisterActivity extends BaseActivity
        implements LoginRegisterDelegate {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindString(R.string.hint_enter_email)
    String hintEmail;

    public static Intent newIntent(Context context) {
        Intent intent = new Intent(context, LoginRegisterActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_register);
        ButterKnife.bind(this, this);

        setSupportActionBar(toolbar);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fl_container, LoginFragment.newInstance())
                    .commit();
        }
    }

    @Override
    public void onTapLogin() {

    }

    @Override
    public void onTapForgotPassword() {

    }

    @Override
    public void onTapToRegister() {
        getSupportFragmentManager().beginTransaction()
                .setCustomAnimations(R.anim.enter, R.anim.exit, R.anim.pop_enter, R.anim.pop_exit)
                .replace(R.id.fl_container, RegisterFragment.newInstance())
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void setScreenTitle(String title) {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle(title);
        }
    }
}
