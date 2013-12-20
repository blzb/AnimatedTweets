package com.lucasian.tweetclient;

import java.util.Arrays;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import com.lucasian.tweetclient.domain.Tweet;
import com.lucasian.tweetclient.domain.TweetsTemporalStore;
import com.lucasian.tweetclient.listeners.GroupTweetAnimationListener;
import com.lucasian.tweetclient.listeners.SeparatedTweetAnimationListener;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;

public class MainActivity extends Activity {
	Random r = new Random();
	private boolean firstView = true;
	private ScheduledExecutorService scheduler;
	private AnimationFactory animationFactory = new AnimationFactory(this);

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		try{
			Tweet tweet1 =new Tweet("¿Por qué usar #cloud? 4.Permite el rápido desarrollo de nuevos productos http://ow.ly/i/3Ze4Q  Haz la prueba http://ibm.co/1hgNG9M ", "@ibm_mx", "IBM Mexico","https://pbs.twimg.com/profile_images/1380718965/ibm_avatar_normal.jpg");
			tweet1.setBadgeId(R.drawable.badgeibm);
			tweet1.setImgId(R.drawable.ibm);			
		TweetsTemporalStore.getInstance().addTweet(tweet1);
		Thread.sleep(1000);
		Tweet tweet2 =new Tweet("...also, these are the socks I clandestinely wore under my boots to the Google holiday party. Shhhhhh. pic.twitter.com/vv75DLlebs", "@pamelafox", "Pamela Fox","https://pbs.twimg.com/profile_images/378800000670552311/c1c8f229e20754d8885f5b448097ff93_normal.png");
		tweet2.setBadgeId(R.drawable.badge_pamela);
		tweet2.setImgId(R.drawable.pamelafox);
		TweetsTemporalStore.getInstance().addTweet(tweet2);
		Thread.sleep(1000);
		Tweet tweet3 = new Tweet("Nuevo #MotoG el smartphone que esperabas, al precio que querías.", "@Motorola_MX", "Motorola México","https://pbs.twimg.com/profile_images/413444340719951872/cuHckB3h_normal.jpeg");
		tweet3.setBadgeId(R.drawable.motorola_badge);
		tweet3.setImgId(R.drawable.motorola);
		TweetsTemporalStore.getInstance().addTweet(tweet3);
		Thread.sleep(1000); 		
		TweetsTemporalStore.getInstance().addTweet(new Tweet("OK Google... be my backup singer! pic.twitter.com/KatRDqmjoz", "@google", "A Googler",""));
		}catch(InterruptedException ex){
			
		}
		TweetView currentView = (TweetView) findViewById(R.id.firstTweetView);
		currentView.setTweet(TweetsTemporalStore.getInstance().getNextTweet(), this);

	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		Log.e("com.lucasian.tweetclient", "DESTROY");
		scheduler.shutdownNow();
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		Log.e("com.lucasian.tweetclient", "PAUSE");
		scheduler.shutdownNow();
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		Log.e("com.lucasian.tweetclient", "RESUME");
		scheduler.scheduleAtFixedRate(new TweetRunner(), 6, 10,
				TimeUnit.SECONDS);
	}

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		scheduler= Executors.newSingleThreadScheduledExecutor();
		Log.e("com.lucasian.tweetclient", "START");
	}

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		Log.e("com.lucasian.tweetclient", "STOP");
		scheduler.shutdownNow();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	public void click(View clickedView) {
		loadTweet();
	}

	private void loadTweet() {
		TweetView currentView = getCurrentView();
		TweetView nextView = getNextView();
		nextView.setTweet(TweetsTemporalStore.getInstance().getNextTweet(), this);
		
		int animationSequence = getRandomAnimationSequence();
		Animation hide = getHideAnimation(animationSequence);
		Animation show = getShowAnimation(animationSequence);
		AnimationListener listener;
		if(nextView.hasImage()){
			 listener = new GroupTweetAnimationListener(animationFactory.getTweetAnimationGrouped(),nextView.getTweetGroup());
			 Animation resetPosition = animationFactory.resetOriginalPosition();
			 nextView.getTweetText().startAnimation(resetPosition);
			 nextView.getName().startAnimation(resetPosition);
			 nextView.getUsername().startAnimation(resetPosition);
			 nextView.getBadge().startAnimation(resetPosition);
		}else{
			 listener = new SeparatedTweetAnimationListener(animationFactory, nextView);
		}		
		show.setAnimationListener(listener);		
		currentView.startAnimation(hide);
		nextView.getTweetGroup().startAnimation(getInstantHideAnimation());
		nextView.startAnimation(show);
		toogleCurrentView();
	}

	private TweetView getCurrentView() {
		if (firstView) {
			return (TweetView) findViewById(R.id.firstTweetView);
		} else {
			return (TweetView) findViewById(R.id.secondTweetView);
		}

	}

	private TweetView getNextView() {
		if (firstView) {
			return (TweetView) findViewById(R.id.secondTweetView);
		} else {
			return (TweetView) findViewById(R.id.firstTweetView);
		}

	}

	private int getRandomAnimationSequence() {
		return 4;
	}

	private Animation getHideAnimation(int animationIndex) {
		switch (animationIndex) {
		case 0:
			return AnimationUtils.loadAnimation(this, R.anim.hideslidedown);
		case 1:
			return AnimationUtils.loadAnimation(this, R.anim.hideslideleft);
		case 2:
			return AnimationUtils.loadAnimation(this, R.anim.hidesliderigth);
		case 3:
			return AnimationUtils.loadAnimation(this, R.anim.hideslideup);
		case 4:
			return AnimationUtils.loadAnimation(this, R.anim.hidefade);
		case 5:
			return AnimationUtils.loadAnimation(this, R.anim.hidezoom);
		default:
			return AnimationUtils.loadAnimation(this, R.anim.hideslideleft);
		}
	}
	private Animation getInstantHideAnimation(){
		return AnimationUtils.loadAnimation(this, R.anim.instantehide);
	}
	private Animation getShowAnimation(int animationIndex) {
		switch (animationIndex) {
		case 0:
			return AnimationUtils.loadAnimation(this, R.anim.showslidedown);
		case 1:
			return AnimationUtils.loadAnimation(this, R.anim.showslideleft);
		case 2:
			return AnimationUtils.loadAnimation(this, R.anim.showsliderigth);
		case 3:
			return AnimationUtils.loadAnimation(this, R.anim.showslideup);
		case 4:
			return AnimationUtils.loadAnimation(this, R.anim.showfade);
		case 5:
			return AnimationUtils.loadAnimation(this, R.anim.showzoom);
		default:
			return AnimationUtils.loadAnimation(this, R.anim.showslideleft);
		}
	}

	private void toogleCurrentView() {
		if (firstView) {
			firstView = false;
		} else {
			firstView = true;
		}
	}

	public class TweetRunner implements Runnable {
		public TweetRunner() {
			// TODO Auto-generated constructor stub
			Log.e("com.lucasian.tweetclient", "STARTING TIMER");
		}

		public void run() {
			Log.e("com.lucasian.tweetclient", "STEP " + new Date());
			runOnUiThread(new Runnable() {
				@Override
				public void run() {
					loadTweet();
				}
			});

		}
	}

}
