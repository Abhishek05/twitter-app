package com.codepath.apps.mytweets.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import com.codepath.apps.mytweets.R;
import com.codepath.apps.mytweets.TwitterApplication;
import com.codepath.apps.mytweets.TwitterClient;
import com.codepath.apps.mytweets.Utils.TweetUtils;
import com.codepath.apps.mytweets.adapter.TweetArrayAdapter;
import com.codepath.apps.mytweets.models.Tweet;
import com.codepath.apps.mytweets.pagination.EndlessRecylcerScrollerListener;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;

public class TimelineActivity extends AppCompatActivity {

    private TwitterClient client;

    private ArrayList<Tweet> tweets;

    private TweetArrayAdapter tweetAdapter;

    private ListView lvTweets;

    private int max_id =0;

    private long last_tweet_id= 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timeline);
        lvTweets = (ListView)findViewById(R.id.lvView);
        tweets = new ArrayList<Tweet>();
        tweetAdapter = new TweetArrayAdapter(this, tweets);
        lvTweets.setAdapter(tweetAdapter);
        client = TwitterApplication.getRestClient();
        populateTimeLine(0);

        lvTweets.setOnScrollListener(new EndlessRecylcerScrollerListener(){
            @Override
            public boolean onLoadMore(int page, int totalItemsCount) {
                populateTimeLine(last_tweet_id);
                return true;
            }
            @Override
            public int getFooterViewType() {
                return 0;
            }
        });
    }



    private void populateTimeLine(long page) {

        client.getTimeLine(page, new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                Log.d("DEBUG", response.toString());
                List<Tweet> tweets = TweetUtils.getModelsFromTweets(response);
                max_id = tweets.size();
                Tweet a = tweets.get(max_id -1);
                last_tweet_id = a.getId();
                tweetAdapter.addAll(tweets);

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.timeline, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.compose) {
            Toast.makeText(this, "Menu item tapped !", Toast.LENGTH_SHORT).show();
            launchRequestActivity();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void launchRequestActivity(){
        // start the requestactivity
        //startActivity(intent);
        Intent intent = new Intent(this, TweetActivity.class);
        startActivityForResult( intent, 1);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                Log.d("DEBUG -","DoneCalling the on ACtivity Success populate timeline");
                Tweet tweet = (Tweet)data.getSerializableExtra("tweetObject");
                //tweets.add(0,tweet);
                //tweetArrayAdapter.addAll(tweets);
                //tweetArrayAdapter.insert(tweet1,0);
                //this.tweetArrayAdapter.notifyDataSetChanged();

                tweetAdapter.clear();
                //tweetAdapter.add(tweet);

                //tweetAdapter.

                //tweetAdapter.addAll(tweets);
                populateTimeLine(0);
                tweetAdapter.notifyDataSetChanged();
                Log.d("DEBUG -","Done");
            }
        }
    }

}
