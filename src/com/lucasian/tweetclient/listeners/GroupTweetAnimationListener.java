package com.lucasian.tweetclient.listeners;

import com.lucasian.tweetclient.domain.TweetsTemporalStore;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.view.animation.Animation.AnimationListener;

public class GroupTweetAnimationListener implements AnimationListener {
	private Animation nextAnimation;
	private View view;

	public GroupTweetAnimationListener(Animation nextAnimation, View view) {
		super();
		this.nextAnimation = nextAnimation;
		this.view = view;
	}

	@Override
	public void onAnimationEnd(Animation animation) {
		// TODO Auto-generated method stub
		view.startAnimation(nextAnimation);
	}

	@Override
	public void onAnimationRepeat(Animation animation) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onAnimationStart(Animation animation) {
		// TODO Auto-generated method stub
		TranslateAnimation a = new TranslateAnimation(Animation.RELATIVE_TO_PARENT, 0 ,Animation.RELATIVE_TO_PARENT,0,Animation.RELATIVE_TO_PARENT,0,Animation.RELATIVE_TO_PARENT,0);
		animation.setDuration(1);
		animation.setFillAfter(true);		
		
	}

}
