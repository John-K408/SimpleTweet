package com.codepath.apps.restclienttemplate;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.codepath.apps.restclienttemplate.models.Tweet;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class TweetsAdapter extends RecyclerView.Adapter<TweetsAdapter.ViewHolder> {
    List<Tweet> tweets;
    Context context;
    String TAG;
    //Pass in the context and list of tweets
    public TweetsAdapter(List<Tweet>tweets, Context context){
        this.tweets = tweets;
        this.context = context;
        TAG = "TweetsAdapter";
    }

    //for each row, inflate layout
    @NonNull
    @Override
    //
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.item_tweet,parent,false);
        return new ViewHolder(view);
    }

    //Bind values based on the position of the element
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Tweet tweet = tweets.get(position);
        holder.bind(tweet);
    }

    @Override
    public int getItemCount() {
        return tweets.size();
    }

    // Clean all elements of the recycler
    public void clear(){
        tweets.clear();
        notifyDataSetChanged();
    }

    // Add a list of items -- change to type used
    public void addAll(List<Tweet> tweetList){
        tweets.addAll(tweetList);
        notifyDataSetChanged();
    }



    //Define a viewHolder
    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView ivProfileImage;
        TextView tvBody;
        TextView tvScreenName;
        RelativeLayout container;
        TextView tvName;
        TextView timeAgo;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivProfileImage = itemView.findViewById(R.id.ivProfileImage);
            tvBody = itemView.findViewById(R.id.tvBody);
            tvScreenName = itemView.findViewById(R.id.tvScreenName);
            container = itemView.findViewById(R.id.container);
            tvName = itemView.findViewById(R.id.tvname);
            timeAgo = itemView.findViewById(R.id.tvTimeAgo);
        }

        public void bind(final Tweet tweet) {
            tvScreenName.setText(tweet.user.screenName);
            tvBody.setText(tweet.body);
            tvName.setText(tweet.user.name);
            String createdTime = tweet.createdAt;
            int i = 0;
            while(i < createdTime.length()){
                if(Character.isDigit(createdTime.charAt(i)))i++;
                else{
                    break;
                }
            }
            String[] a = createdTime.substring(0,i+ 2).split(" ");
            String toShow = "";
            for(int j = 0; j  < a.length;j++){
                toShow += a[j];
            }

            timeAgo.setText(". "+toShow);
            Glide.with(context).load(tweet.user.profileImageUrl).into(ivProfileImage);
            container.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.i(TAG,tweet.id + "");

                }
            });
        }
    }


}
