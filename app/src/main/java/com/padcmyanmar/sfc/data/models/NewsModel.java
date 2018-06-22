package com.padcmyanmar.sfc.data.models;

import com.padcmyanmar.sfc.data.vo.NewsVO;
import com.padcmyanmar.sfc.events.RestApiEvents;
import com.padcmyanmar.sfc.network.reponses.GetNewsResponse;
import com.padcmyanmar.sfc.utils.AppConstants;

import org.greenrobot.eventbus.EventBus;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by aung on 12/3/17.
 */

public class NewsModel extends BaseModel {

    private static NewsModel objInstance;

    private Map<String, NewsVO> mNewsMap;
    private int mmNewsPageIndex = 1;

    private NewsModel() {
        super();
        mNewsMap = new HashMap<>();
    }

    public static NewsModel getInstance() {
        if(objInstance == null) {
            objInstance = new NewsModel();
        }
        return objInstance;
    }

    public void startLoadingMMNews() {
        mTheApi.loadMMNews(mmNewsPageIndex, AppConstants.ACCESS_TOKEN)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<GetNewsResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(GetNewsResponse getNewsResponse) {
                        if (getNewsResponse != null
                                && getNewsResponse.getNewsList().size() > 0) {

                            RestApiEvents.NewsDataLoadedEvent newsDataLoadedEvent = new RestApiEvents.NewsDataLoadedEvent(
                                    getNewsResponse.getPageNo(), getNewsResponse.getNewsList());

                            for(NewsVO news : getNewsResponse.getNewsList()) {
                                mNewsMap.put(news.getNewsId(), news);
                            }

                            mmNewsPageIndex = getNewsResponse.getPageNo() + 1;

                            EventBus.getDefault().post(newsDataLoadedEvent);
                        } else {
                            RestApiEvents.ErrorInvokingAPIEvent errorEvent
                                    = new RestApiEvents.ErrorInvokingAPIEvent("No data could be loaded for now. Pls try again later.");
                            EventBus.getDefault().post(errorEvent);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        RestApiEvents.ErrorInvokingAPIEvent errorEvent = new RestApiEvents.ErrorInvokingAPIEvent(e.getMessage());
                        EventBus.getDefault().post(errorEvent);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public NewsVO getNewsById(String newsId) {
        return mNewsMap.get(newsId);
    }
}
