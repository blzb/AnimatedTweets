package com.lucasian.tweetclient.domain;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import android.util.Log;

public class TweetsTemporalStore {
	private static TweetsTemporalStore instance = null;
	private List<Tweet> tweets;
	private int position = 0;
	private int maxSize = 10;
	public static TweetsTemporalStore getInstance(){
		if(instance == null){
			instance = new TweetsTemporalStore();
			instance.tweets = new LinkedList<Tweet>();			
		}
		return instance;
	}
	public void addTweet(Tweet tweet){
		if(tweets.size()<maxSize){
			this.tweets.add(tweet);	
		}else{
			Tweet minimo = Collections.min(tweets);
			int index = tweets.indexOf(minimo);			
			tweets.set(index, tweet);
		}	
	}
	public Tweet getNextTweet(){		
		Tweet result = this.tweets.get(position);
		position++;
		if(position>= tweets.size()){
			position = 0;
		}
		return result;
	}
}
