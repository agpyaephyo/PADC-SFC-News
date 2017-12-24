package com.padcmyanmar.sfc.data.vo;

import android.content.ContentValues;
import android.database.Cursor;

import com.google.gson.annotations.SerializedName;
import com.padcmyanmar.sfc.persistence.MMNewsContract;

/**
 * Created by aung on 12/3/17.
 */

public class SentToVO {

    @SerializedName("send-to-id")
    private String sendToId;

    @SerializedName("sent-date")
    private String sentDate;

    @SerializedName("acted-user")
    private ActedUserVO sender;

    @SerializedName("received-user")
    private ActedUserVO receiver;

    public String getSendToId() {
        return sendToId;
    }

    public String getSentDate() {
        return sentDate;
    }

    public ActedUserVO getSender() {
        return sender;
    }

    public ActedUserVO getReceiver() {
        return receiver;
    }

    public ContentValues parseToContentValues(String newsId) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(MMNewsContract.SentToActionEntry.COLUMN_SENT_TO_ID, sendToId);
        contentValues.put(MMNewsContract.SentToActionEntry.COLUMN_SENT_DATE, sentDate);
        contentValues.put(MMNewsContract.SentToActionEntry.COLUMN_NEWS_ID, newsId);
        contentValues.put(MMNewsContract.SentToActionEntry.COLUMN_SENDER_ID, sender.getUserId());
        contentValues.put(MMNewsContract.SentToActionEntry.COLUMN_RECEIVER_ID, receiver.getUserId());
        return contentValues;
    }

    public static SentToVO parseFromCursor(Cursor cursor) {
        SentToVO sentTo = new SentToVO();
        sentTo.sendToId = cursor.getString(cursor.getColumnIndex(MMNewsContract.SentToActionEntry.COLUMN_SENDER_ID));
        sentTo.sentDate = cursor.getString(cursor.getColumnIndex(MMNewsContract.SentToActionEntry.COLUMN_SENT_DATE));
        sentTo.sender = ActedUserVO.parseFromCursor(cursor);
        sentTo.receiver = ActedUserVO.parseFromCursor(cursor);
        return sentTo;
    }
}
