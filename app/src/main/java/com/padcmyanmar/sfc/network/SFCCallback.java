package com.padcmyanmar.sfc.network;

import com.padcmyanmar.sfc.events.RestApiEvents;

import org.greenrobot.eventbus.EventBus;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by aung on 12/9/17.
 */

public abstract class SFCCallback<T extends SFCResponse> implements Callback<T> {

    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        SFCResponse sfcResponse = response.body();
        if (sfcResponse == null) {
            RestApiEvents.ErrorInvokingAPIEvent errorEvent
                    = new RestApiEvents.ErrorInvokingAPIEvent("No data could be loaded for now. Pls try again later.");
            EventBus.getDefault().post(errorEvent);
        }
    }

    @Override
    public void onFailure(Call<T> call, Throwable t) {
        RestApiEvents.ErrorInvokingAPIEvent errorEvent = new RestApiEvents.ErrorInvokingAPIEvent(t.getMessage());
        EventBus.getDefault().post(errorEvent);
    }
}
