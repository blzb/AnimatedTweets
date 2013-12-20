package com.lucasian.tweetclient.listeners;

import com.lucasian.tweetclient.AnimationFactory;
import com.lucasian.tweetclient.TweetView;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;

public class SeparatedTweetAnimationListener  implements AnimationListener  {
	private AnimationFactory animationFactory;
	private TweetView tweetView;

	public SeparatedTweetAnimationListener(AnimationFactory animationFactory, TweetView tweetView) {
		super();
		this.animationFactory = animationFactory;
		this.tweetView = tweetView;
	}

	@Override
	public void onAnimationEnd(Animation animation) {
		// TODO Auto-generated method stub
		tweetView.getTweetText().startAnimation(animationFactory.getTweetAnimationIndividual()) ;
		tweetView.getBadge().startAnimation(animationFactory.getTweetAnimationIndividual());
		tweetView.getName().startAnimation(animationFactory.getTweetAnimationIndividual());
		tweetView.getUsername().startAnimation(animationFactory.getTweetAnimationIndividual());
	}

	@Override
	public void onAnimationRepeat(Animation animation) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onAnimationStart(Animation animation) {
		// TODO Auto-generated method stub
		
	}

}
