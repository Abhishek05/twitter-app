package com.codepath.apps.mytweets.models;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

/**
 * Created by apaul5 on 6/5/16.
 */
public class User implements Serializable{
    private String name;
    private String screenName;
    private long uid;
    private String profileImage;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getScreenName() {
        return screenName;
    }

    public void setScreenName(String screenName) {
        this.screenName = screenName;
    }

    public long getUid() {
        return uid;
    }

    public void setUid(long uid) {
        this.uid = uid;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }

    public static User fromJSON(JSONObject jsonObject){
        User user = new User();
        try {
            user.name = jsonObject.getString("name");
            user.profileImage = jsonObject.getString("profile_image_url");
            user.screenName = jsonObject.getString("screen_name");
            user.name = jsonObject.getString("name");
            user.uid = jsonObject.getLong("id");

        }catch(JSONException json){
            json.printStackTrace();
        }
        return user;

    }


}
