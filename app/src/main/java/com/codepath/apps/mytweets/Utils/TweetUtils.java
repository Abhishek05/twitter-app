package com.codepath.apps.mytweets.Utils;

import android.text.format.DateUtils;
import android.util.Log;

import com.codepath.apps.mytweets.models.Tweet;
import com.codepath.apps.mytweets.models.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

/**
 * Created by apaul5 on 6/6/16.
 */
public class TweetUtils {

    //Deserialize the JSON
    //Tweet.fromJSON
    public static Tweet fromJSON(JSONObject jsonObject){
        Tweet tweet = new Tweet();
        try {
            tweet.setBody(jsonObject.getString("text"));
            tweet.setId(jsonObject.getLong("id"));
            tweet.setCreatedAt(getRelativeTimeAgo(jsonObject.getString("created_at")));
            tweet.setUser(User.fromJSON(jsonObject.getJSONObject("user")));

        }catch (JSONException jsone){
            jsone.printStackTrace();
        }


        return tweet;
    }
    public static String getRelativeTimeAgo(String rawJsonDate) {
        String twitterFormat = "EEE MMM dd HH:mm:ss ZZZZZ yyyy";
        SimpleDateFormat sf = new SimpleDateFormat(twitterFormat, Locale.ENGLISH);
        sf.setLenient(true);

        String relativeDate = "";
        try {
            long dateMillis = sf.parse(rawJsonDate).getTime();
            relativeDate = DateUtils.getRelativeTimeSpanString(dateMillis,
                    System.currentTimeMillis(), DateUtils.SECOND_IN_MILLIS).toString();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return relativeDate;
    }

    public static ArrayList<Tweet> getModelsFromTweets(JSONArray jsonArray){

        ArrayList<Tweet> tweets = new ArrayList<Tweet>();
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject tweetJson;
            try {
                tweetJson = jsonArray.getJSONObject(i);
                Tweet tweet = TweetUtils.fromJSON(tweetJson);
                if(tweet!=null){
                    tweets.add(tweet);
                    Log.d("Tweets ::",tweet.toString());
                }
            } catch (Exception e) {
                e.printStackTrace();
                continue;
            }
        }

        return tweets;
    }
}
