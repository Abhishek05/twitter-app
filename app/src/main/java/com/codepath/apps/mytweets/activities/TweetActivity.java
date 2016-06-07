package com.codepath.apps.mytweets.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.codepath.apps.mytweets.R;
import com.codepath.apps.mytweets.TwitterApplication;
import com.codepath.apps.mytweets.TwitterClient;
import com.codepath.apps.mytweets.Utils.TweetUtils;
import com.codepath.apps.mytweets.models.Tweet;
import com.loopj.android.http.JsonHttpResponseHandler;

import cz.msebera.android.httpclient.Header;

import org.json.JSONObject;

import java.io.Serializable;

public class TweetActivity extends AppCompatActivity {

    private TwitterClient client;

    private EditText etBody;
    private Button btnSave;
    private Tweet tweet;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tweet);
        client = TwitterApplication.getRestClient();

        etBody = (EditText)findViewById(R.id.edCompose);
        btnSave = (Button)findViewById(R.id.btCompose);

    }

    public void onSaveSettings(View view){
        String body=String.valueOf(etBody.getText());

        if(!body.isEmpty()) {

            client.postTweet(body, new JsonHttpResponseHandler() {
                @Override
                public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                    //super.onSuccess(statusCode, headers, response);
                    tweet = TweetUtils.fromJSON(response);
                    //Log.d("text" + tweet.getBody() + "::tweet.id::" + tweet.getId() + "User" + tweet.getUser().getName());

                    proceed(tweet);


                }

                @Override
                public void onFailure(int statusCode, Header[] headers, Throwable error, JSONObject response) {
                    super.onFailure(statusCode, headers, error, response);
                }
            });
        }


    }

    private void proceed(Tweet tweet) {
        Intent intent = new Intent();
        intent.putExtra("tweetObject",(Serializable) tweet);
        setResult(RESULT_OK, intent);
        this.finish();
    }

}
