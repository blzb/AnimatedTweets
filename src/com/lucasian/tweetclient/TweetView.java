package com.lucasian.tweetclient;



import java.util.Date;
import java.util.Random;

import com.lucasian.tweetclient.domain.Tweet;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class TweetView extends RelativeLayout{
	private TextView tweetText;
	private TextView name;
	private TextView username;
	private ImageView badge;
	private Tweet tweet;
	private ImageView fondo;
	private RelativeLayout tweetGroup;
	Random r ;
	public RelativeLayout getTweetGroup(){
		return tweetGroup;
	}
	public TweetView(Context context) {
		super(context);
		initView(context);
		// TODO Auto-generated constructor stub
	}

	public TweetView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		initView(context);
		// TODO Auto-generated constructor stub
	}

	public TweetView(Context context, AttributeSet attrs) {
		super(context, attrs);
		initView(context);
		// TODO Auto-generated constructor stub
	}
	private void initView(Context context) {
		r =  new Random();
		final LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	
		inflater.inflate(R.layout.tweetview, this, true);	
		tweetText = (TextView) findViewById(R.id.tweet);
		name = (TextView) findViewById(R.id.name);
		username=(TextView) findViewById(R.id.username);
		badge = (ImageView) findViewById(R.id.badge);
		fondo = (ImageView) findViewById(R.id.fondo);
		tweetGroup = (RelativeLayout) findViewById(R.id.tweetgroup);
	}

	public Tweet getTweet() {
		return tweet;
	}

	
	@TargetApi(Build.VERSION_CODES.JELLY_BEAN)
	public void setTweet(Tweet tweet, Context context) {
		this.tweet = tweet;
		if(hasImage()){
			this.fondo.setImageResource(tweet.getImgId());			
			this.tweetGroup.setBackgroundResource(R.drawable.rounded);
			this.tweetGroup.getLayoutParams().height = ViewGroup.LayoutParams.WRAP_CONTENT;
			this.tweetGroup.getLayoutParams().width = 500;
			this.name.setTextAppearance(context, android.R.style.TextAppearance_Medium);
			this.name.setTextColor(0xFFFFFFFF);
			this.username.setTextAppearance(context, android.R.style.TextAppearance_Small);
			this.username.setTextColor(0xFFFFFFFF);
			this.tweetText.setTextAppearance(context, android.R.style.TextAppearance_Small);
			this.tweetText.setTextColor(0xFFFFFFFF);
			RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams)this.badge.getLayoutParams();
			params.addRule(CENTER_IN_PARENT, 0);
			params.addRule(ALIGN_LEFT, this.tweetGroup.getId());			
			params.addRule(ALIGN_TOP,this.tweetGroup.getId());			
			this.badge.setLayoutParams(params);
			this.badge.requestLayout();

		}else{
			this.fondo.setImageDrawable(null);
			int color = Color.argb(255, r.nextInt(256), r.nextInt(256), r.nextInt(256));
			this.fondo.setBackgroundColor(color);
			this.tweetGroup.setBackground(null);
			this.tweetGroup.getLayoutParams().height = ViewGroup.LayoutParams.MATCH_PARENT;
			this.tweetGroup.getLayoutParams().width = ViewGroup.LayoutParams.MATCH_PARENT;			
			RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams)this.badge.getLayoutParams();
			params.addRule(ALIGN_LEFT,0);			
			params.addRule(ALIGN_TOP,0);
			params.addRule(CENTER_IN_PARENT, TRUE);
			this.name.setTextAppearance(context, android.R.style.TextAppearance_Large);
			this.name.setTextColor(0xFFFFFFFF);
			this.username.setTextAppearance(context, android.R.style.TextAppearance_Large);
			this.username.setTextColor(0xFFFFFFFF);
			this.tweetText.setTextAppearance(context, android.R.style.TextAppearance_Large);
			this.tweetText.setTextColor(0xFFFFFFFF);
			this.badge.setLayoutParams(params);
			this.badge.requestLayout();

		}
		this.tweetText.setText(tweet.getTweet());
		this.badge.setImageResource(tweet.getBadgeId());
		
		this.name.setText(tweet.getName());
		this.username.setText(tweet.getUsername());
	}
	public boolean hasImage(){
		return tweet.getImgId() > 0;
	}
	public TextView getTweetText() {
		return tweetText;
	}
	public TextView getName() {
		return name;
	}
	public TextView getUsername() {
		return username;
	}
	public ImageView getBadge() {
		return badge;
	}
	
	
}
