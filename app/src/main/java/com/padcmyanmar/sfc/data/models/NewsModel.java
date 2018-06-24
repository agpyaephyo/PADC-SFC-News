package com.padcmyanmar.sfc.data.models;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.content.Context;
import android.util.Log;

import com.padcmyanmar.sfc.SFCNewsApp;
import com.padcmyanmar.sfc.data.vo.ActedUserVO;
import com.padcmyanmar.sfc.data.vo.CommentActionVO;
import com.padcmyanmar.sfc.data.vo.FavoriteActionVO;
import com.padcmyanmar.sfc.data.vo.NewsVO;
import com.padcmyanmar.sfc.data.vo.PublicationVO;
import com.padcmyanmar.sfc.data.vo.SentToVO;
import com.padcmyanmar.sfc.network.reponses.GetNewsResponse;
import com.padcmyanmar.sfc.utils.AppConstants;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import rx.subjects.PublishSubject;

/**
 * Created by aung on 12/3/17.
 */

public class NewsModel extends BaseModel {

    private static NewsModel objInstance;

    private int mmNewsPageIndex = 1;

    private NewsModel(Context context) {
        super(context);
    }

    public static void initNewsModel(Context context) {
        objInstance = new NewsModel(context);
    }

    public static NewsModel getInstance() {
        if (objInstance == null) {
            throw new RuntimeException("NewsModel is being invoked before initializing.");
        }
        return objInstance;
    }

    public void startLoadingMMNews(final MutableLiveData<List<NewsVO>> newsListLD,
                                   final MutableLiveData<String> errorPS) {
        mTheApi.loadMMNews(mmNewsPageIndex, AppConstants.ACCESS_TOKEN)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<GetNewsResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(GetNewsResponse newsResponse) {
                        if (newsResponse != null
                                && newsResponse.getNewsList().size() > 0) {

                            persistNewsList(newsResponse.getNewsList());
                            mmNewsPageIndex = newsResponse.getPageNo() + 1;

                            newsListLD.setValue(newsResponse.getNewsList());
                        } else {
                            errorPS.setValue("No data could be loaded for now. Pls try again later.");
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        errorPS.setValue(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private void persistNewsList(List<NewsVO> newsList) {
        //Prepare data to insert
        List<PublicationVO> publicationList = new ArrayList<>();
        List<FavoriteActionVO> favoriteActionList = new ArrayList<>();
        List<CommentActionVO> commentActionList = new ArrayList<>();
        List<SentToVO> sentToList = new ArrayList<>();
        List<ActedUserVO> actedUserList = new ArrayList<>();

        for (NewsVO news : newsList) {
            publicationList.add(news.getPublication());
            for (FavoriteActionVO favoriteAction : news.getFavoriteActions()) {
                favoriteAction.setNewsId(news.getNewsId());

                favoriteActionList.add(favoriteAction);
                actedUserList.add(favoriteAction.getActedUser());
            }
            for (CommentActionVO commentAction : news.getCommentActions()) {
                commentAction.setNewsId(news.getNewsId());

                commentActionList.add(commentAction);
                actedUserList.add(commentAction.getActedUser());
            }
            for (SentToVO sentTo : news.getSentToActions()) {
                sentTo.setNewsId(news.getNewsId());

                sentToList.add(sentTo);
                actedUserList.add(sentTo.getSender());
                actedUserList.add(sentTo.getReceiver());
            }
        }

        //Actual Inserts - with sequence
        long[] insertedUsers = mTheDB.actedUserDao().insertActedUsers(actedUserList.toArray(new ActedUserVO[0]));
        Log.d(SFCNewsApp.LOG_TAG, "insertedUsers : " + insertedUsers);

        long[] insertedSentTos = mTheDB.sentToActionDao().insertSentToActions(sentToList.toArray(new SentToVO[0]));
        Log.d(SFCNewsApp.LOG_TAG, "insertedSentTos : " + insertedSentTos);

        long[] insertedComments = mTheDB.commentActionDao().insertCommentActions(commentActionList.toArray(new CommentActionVO[0]));
        Log.d(SFCNewsApp.LOG_TAG, "insertedComments : " + insertedComments);

        long[] insertedFavorites = mTheDB.favoriteActionDao().insertFavoriteActions(favoriteActionList.toArray(new FavoriteActionVO[0]));
        Log.d(SFCNewsApp.LOG_TAG, "insertedFavorites : " + insertedFavorites);

        long[] insertedPublications = mTheDB.publicationDao().insertPublications(publicationList.toArray(new PublicationVO[0]));
        Log.d(SFCNewsApp.LOG_TAG, "insertedPublications : " + insertedPublications);

        long[] insertedNews = mTheDB.newsDao().insertNews(newsList.toArray(new NewsVO[0]));
        Log.d(SFCNewsApp.LOG_TAG, "insertedNews : " + insertedNews);
    }

    public NewsVO getNewsById(String newsId) {
        //Retrieve with sequence
        NewsVO news = mTheDB.newsDao().getNewsById(newsId);
        news.setFavoriteActions(mTheDB.favoriteActionDao().getFavoriteActionsByNewsId(newsId));
        for (FavoriteActionVO favoriteAction : news.getFavoriteActions()) {
            favoriteAction.setActedUser(mTheDB.actedUserDao().getActedUserById(favoriteAction.getActedUserId()));
        }

        news.setCommentActions(mTheDB.commentActionDao().getCommentActionsByNewsId(newsId));
        for (CommentActionVO commentAction : news.getCommentActions()) {
            commentAction.setActedUser(mTheDB.actedUserDao().getActedUserById(commentAction.getActedUserId()));
        }

        news.setSentToActions(mTheDB.sentToActionDao().getSentTosByNewsId(newsId));
        for (SentToVO sentTo : news.getSentToActions()) {
            sentTo.setSender(mTheDB.actedUserDao().getActedUserById(sentTo.getSenderId()));
            sentTo.setReceiver(mTheDB.actedUserDao().getActedUserById(sentTo.getReceiverId()));
        }

        news.setPublication(mTheDB.publicationDao().getPublicationById(news.getPublicationId()));
        return news;
    }
}
