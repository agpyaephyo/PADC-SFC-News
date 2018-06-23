package com.padcmyanmar.sfc.data.vo;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.google.gson.annotations.SerializedName;

/**
 * Created by aung on 12/3/17.
 */
@Entity(tableName = "sent_tos")
public class SentToVO {

    @PrimaryKey(autoGenerate = true)
    private long id;

    @SerializedName("send-to-id")
    @ColumnInfo(name = "sent_to_id")
    private String sendToId;

    @SerializedName("sent-date")
    @ColumnInfo(name = "sent_date")
    private String sentDate;

    @SerializedName("acted-user")
    @Ignore
    private ActedUserVO sender;

    @SerializedName("received-user")
    @Ignore
    private ActedUserVO receiver;

    @ColumnInfo(name = "sender_id")
    private transient String senderId;

    @ColumnInfo(name = "receiver_id")
    private transient String receiverId;

    @ColumnInfo(name = "news_id")
    private transient String newsId;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

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

    public String getSenderId() {
        if (TextUtils.isEmpty(senderId)) {
            senderId = sender.getUserId();
        }
        return senderId;
    }

    public String getReceiverId() {
        if (TextUtils.isEmpty(receiverId)) {
            receiverId = receiver.getUserId();
        }
        return receiverId;
    }

    public void setSendToId(String sendToId) {
        this.sendToId = sendToId;
    }

    public void setSentDate(String sentDate) {
        this.sentDate = sentDate;
    }

    public void setSender(ActedUserVO sender) {
        this.sender = sender;
    }

    public void setReceiver(ActedUserVO receiver) {
        this.receiver = receiver;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }

    public void setReceiverId(String receiverId) {
        this.receiverId = receiverId;
    }

    public String getNewsId() {
        return newsId;
    }

    public void setNewsId(String newsId) {
        this.newsId = newsId;
    }
}
