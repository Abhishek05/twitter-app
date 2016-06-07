package com.codepath.apps.mytweets.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by apaul5 on 6/5/16.
 */
public class Tweet implements Serializable{
    private String body;
    private long id;//unique id for  the Tweet.
    private String createdAt;
    private User user;


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }



    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "Tweet{" +
                "body='" + body + '\'' +
                ", id='" + id + '\'' +
                ", createdAt='" + createdAt + '\'' +
                ", user=" + user +
                '}';
    }


}
