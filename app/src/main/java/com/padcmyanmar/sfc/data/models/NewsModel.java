package com.padcmyanmar.sfc.data.models;

import android.content.ContentValues;
import android.content.Context;
import android.util.Log;

import com.padcmyanmar.sfc.SFCNewsApp;
import com.padcmyanmar.sfc.data.vo.FavoriteActionVO;
import com.padcmyanmar.sfc.data.vo.NewsVO;
import com.padcmyanmar.sfc.data.vo.PublicationVO;
import com.padcmyanmar.sfc.data.vo.SentToVO;
import com.padcmyanmar.sfc.events.RestApiEvents;
import com.padcmyanmar.sfc.network.MMNewsDataAgent;
import com.padcmyanmar.sfc.network.MMNewsDataAgentImpl;
import com.padcmyanmar.sfc.persistence.MMNewsContract;
import com.padcmyanmar.sfc.utils.AppConstants;
import com.padcmyanmar.sfc.utils.ConfigUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by aung on 12/3/17.
 */

public class NewsModel {

    private List<NewsVO> mNews;
    //private int mmNewsPageIndex = 1;

    @Inject
    MMNewsDataAgent mDataAgent;

    @Inject
    ConfigUtils mConfigUtils;

    public NewsModel(Context context) {
        EventBus.getDefault().register(this);
        mNews = new ArrayList<>();

        SFCNewsApp sfcNewsApp = (SFCNewsApp) context.getApplicationContext();
        sfcNewsApp.getSFCAppComponent().inject(this);
    }

    public void startLoadingMMNews(Context context) {
        mDataAgent.loadMMNews(AppConstants.ACCESS_TOKEN,
                mConfigUtils.loadPageIndex(),
                context);
    }

    public List<NewsVO> getNews() {
        return mNews;
    }

    public void loadMoreNews(Context context) {
        int pageIndex = mConfigUtils.loadPageIndex();
        mDataAgent.loadMMNews(AppConstants.ACCESS_TOKEN,
                pageIndex,
                context);
    }

    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void onNewsDataLoaded(RestApiEvents.NewsDataLoadedEvent event) {
        mNews.addAll(event.getLoadNews());
        mConfigUtils.savePageIndex(event.getLoadedPageIndex() + 1);

        //Logic to save the data in Persistence Layer
        ContentValues[] newsCVs = new ContentValues[event.getLoadNews().size()];
        List<ContentValues> publicationCVList = new ArrayList<>();
        List<ContentValues> imagesInNewsCVList = new ArrayList<>();

        List<ContentValues> favoriteInNewsCVList = new ArrayList<>();
        List<ContentValues> sentToInNewsCVList = new ArrayList<>();
        List<ContentValues> usersInActionCVList = new ArrayList<>();
        for (int index = 0; index < newsCVs.length; index++) {
            NewsVO news = event.getLoadNews().get(index);
            newsCVs[index] = news.parseToContentValues();

            PublicationVO publication = news.getPublication();
            publicationCVList.add(publication.parseToContentValues());

            for (String imageUrl : news.getImages()) {
                ContentValues imagesInNewsCV = new ContentValues();
                imagesInNewsCV.put(MMNewsContract.ImagesInNewsEntry.COLUMN_NEWS_ID, news.getNewsId());
                imagesInNewsCV.put(MMNewsContract.ImagesInNewsEntry.COLUMN_IMAGE_URL, imageUrl);
                imagesInNewsCVList.add(imagesInNewsCV);
            }

            for (FavoriteActionVO favoriteAction : news.getFavoriteActions()) {
                ContentValues favoriteActionCV = favoriteAction.parseToContentValues(news.getNewsId());
                favoriteInNewsCVList.add(favoriteActionCV);

                ContentValues userInActionCV = favoriteAction.getActedUser().parseToContentValues();
                usersInActionCVList.add(userInActionCV);
            }

            for (SentToVO sentToAction : news.getSentToActions()) {
                ContentValues sentToActionCV = sentToAction.parseToContentValues(news.getNewsId());
                sentToInNewsCVList.add(sentToActionCV);

                ContentValues senderCVs = sentToAction.getSender().parseToContentValues();
                usersInActionCVList.add(senderCVs);

                ContentValues receiverCVs = sentToAction.getReceiver().parseToContentValues();
                usersInActionCVList.add(receiverCVs);
            }
        }

        int insertedPublications = event.getContext().getContentResolver().bulkInsert(MMNewsContract.PublicationEntry.CONTENT_URI,
                publicationCVList.toArray(new ContentValues[0]));
        Log.d(SFCNewsApp.LOG_TAG, "insertedPublications : " + insertedPublications);

        int insertedImagesInNews = event.getContext().getContentResolver().bulkInsert(MMNewsContract.ImagesInNewsEntry.CONTENT_URI,
                imagesInNewsCVList.toArray(new ContentValues[0]));
        Log.d(SFCNewsApp.LOG_TAG, "insertedImagesInNews : " + insertedImagesInNews);

        int insertedFavoriteActionsInNews = event.getContext().getContentResolver().bulkInsert(MMNewsContract.FavoriteActionEntry.CONTENT_URI,
                favoriteInNewsCVList.toArray(new ContentValues[0]));
        Log.d(SFCNewsApp.LOG_TAG, "insertedFavoriteActionsInNews : " + insertedFavoriteActionsInNews);

        int insertedSentToActionsInNews = event.getContext().getContentResolver().bulkInsert(MMNewsContract.SentToActionEntry.CONTENT_URI,
                sentToInNewsCVList.toArray(new ContentValues[0]));
        Log.d(SFCNewsApp.LOG_TAG, "insertedSentToActionsInNews : " + insertedSentToActionsInNews);

        int insertedUsersInActions = event.getContext().getContentResolver().bulkInsert(MMNewsContract.ActedUserEntry.CONTENT_URI,
                usersInActionCVList.toArray(new ContentValues[0]));
        Log.d(SFCNewsApp.LOG_TAG, "insertedUsersInActions : " + insertedUsersInActions);

        int insertedNews = event.getContext().getContentResolver().bulkInsert(MMNewsContract.NewsEntry.CONTENT_URI,
                newsCVs);
        Log.d(SFCNewsApp.LOG_TAG, "insertedNews : " + insertedNews);
    }

    public void forceRefreshNews(Context context) {
        mNews = new ArrayList<>();
        mConfigUtils.savePageIndex(1);
        startLoadingMMNews(context);
    }
}
