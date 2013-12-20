package com.lucasian.tweetclient;

import java.util.Random;

import android.content.Context;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;

public class AnimationFactory {
	Random r = new Random();
	private Context context;
	public AnimationFactory(Context context){
		this.context = context;
	}
	public Animation getTweetAnimationGrouped(){
		int horizontal =wichSide();
		int vertical = wichSide();
		if(vertical==0 && horizontal == 0){
			vertical = 1;
		}
		TranslateAnimation animation = new TranslateAnimation(Animation.RELATIVE_TO_PARENT, horizontal ,Animation.ABSOLUTE,(r.nextInt(500)-250),Animation.RELATIVE_TO_PARENT,vertical,Animation.ABSOLUTE,(r.nextInt(200)-100));
		animation.setDuration(1800);
		animation.setFillAfter(true);
		return animation;		
	}
	public Animation getTweetAnimationIndividual(){
		int horizontal =wichSide();
		int vertical = wichSide();
		if(vertical==0 && horizontal == 0){
			vertical = 1;
		}
		TranslateAnimation animation = new TranslateAnimation(Animation.RELATIVE_TO_PARENT, horizontal ,Animation.RELATIVE_TO_PARENT,-0.25f,Animation.RELATIVE_TO_PARENT,vertical,Animation.RELATIVE_TO_PARENT,0);
		animation.setDuration(1800);
		animation.setFillAfter(true);
		return animation;
	}
	public Animation resetOriginalPosition(){		
		TranslateAnimation animation = new TranslateAnimation(Animation.RELATIVE_TO_PARENT, 1 ,Animation.RELATIVE_TO_PARENT,0,Animation.RELATIVE_TO_PARENT,1,Animation.RELATIVE_TO_PARENT,0);
		animation.setDuration(10);
		animation.setFillAfter(true);
		return animation;
	}
	private int wichSide(){
		return (r.nextInt(3)-1);
	}
	public Animation getHideAnimation(int animationIndex) {
		switch (animationIndex) {
		case 0:
			return AnimationUtils.loadAnimation(context, R.anim.hideslidedown);
		case 1:
			return AnimationUtils.loadAnimation(context, R.anim.hideslideleft);
		case 2:
			return AnimationUtils.loadAnimation(context, R.anim.hidesliderigth);
		case 3:
			return AnimationUtils.loadAnimation(context, R.anim.hideslideup);
		case 4:
			return AnimationUtils.loadAnimation(context, R.anim.hidefade);
		case 5:
			return AnimationUtils.loadAnimation(context, R.anim.hidezoom);
		default:
			return AnimationUtils.loadAnimation(context, R.anim.hideslideleft);
		}
	}
	public Animation getInstantHideAnimation(){
		return AnimationUtils.loadAnimation(context, R.anim.instantehide);
	}
	public Animation getShowAnimation(int animationIndex) {
		switch (animationIndex) {
		case 0:
			return AnimationUtils.loadAnimation(context, R.anim.showslidedown);
		case 1:
			return AnimationUtils.loadAnimation(context, R.anim.showslideleft);
		case 2:
			return AnimationUtils.loadAnimation(context, R.anim.showsliderigth);
		case 3:
			return AnimationUtils.loadAnimation(context, R.anim.showslideup);
		case 4:
			return AnimationUtils.loadAnimation(context, R.anim.showfade);
		case 5:
			return AnimationUtils.loadAnimation(context, R.anim.showzoom);
		default:
			return AnimationUtils.loadAnimation(context, R.anim.showslideleft);
		}
	}
}
