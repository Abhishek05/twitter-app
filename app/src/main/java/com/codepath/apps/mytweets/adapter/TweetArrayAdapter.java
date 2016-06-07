package com.codepath.apps.mytweets.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.codepath.apps.mytweets.R;
import com.codepath.apps.mytweets.models.Tweet;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import jp.wasabeef.picasso.transformations.RoundedCornersTransformation;

/**
 * Created by apaul5 on 6/5/16.
 */
public class TweetArrayAdapter  extends ArrayAdapter<Tweet> {

    // View lookup cache
    private static class ViewHolder {
        public ImageView ivCover;
        public TextView tvScreenName;
        public TextView tvUsernName;
        public TextView body;
        public TextView created_at;
    }

    public TweetArrayAdapter(Context context, ArrayList<Tweet> lTweets) {
        super(context, android.R.layout.simple_list_item_1, lTweets);
    }
    //overide advanced adapter

    // into a relevant row within an AdapterView
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        final Tweet tweet = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        ViewHolder viewHolder; // view lookup cache stored in tag
        if (convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_tweet, parent, false);
            viewHolder.ivCover = (ImageView)convertView.findViewById(R.id.imageView);
            //viewHolder.tvScreenName = (TextView)convertView.findViewById(R.id.textView);
            viewHolder.tvUsernName = (TextView)convertView.findViewById(R.id.username);
            viewHolder.tvScreenName = (TextView)convertView.findViewById(R.id.name);
            viewHolder.body = (TextView)convertView.findViewById(R.id.body);
            viewHolder.created_at = (TextView)convertView.findViewById(R.id.date);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        //Populate data into the template view using the data object
        //viewHolder.tvScreenName.setText(tweet.getUser().getScreenName());
        viewHolder.body.setText(tweet.getBody());
        viewHolder.created_at.setText(tweet.getCreatedAt());
        viewHolder.tvUsernName.setText(tweet.getUser().getName());
        viewHolder.tvScreenName.setText("@"+tweet.getUser().getScreenName());
        Picasso.with(getContext()).load(tweet.getUser().getProfileImage()).
                transform(new RoundedCornersTransformation(5,5)).into(viewHolder.ivCover);
        //Picasso.with(getContext()).load(book.getPosterUrl()).into(viewHolder.ivCover);
        // Return the completed view to render on screen
        return convertView;
    }
}

