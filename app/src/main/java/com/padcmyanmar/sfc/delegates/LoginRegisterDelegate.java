package com.padcmyanmar.sfc.delegates;

/**
 * Created by aung on 11/26/17.
 */

public interface LoginRegisterDelegate {
    void onTapLogin();
    void onTapForgotPassword();
    void onTapToRegister();

    void setScreenTitle(String title);
}
