package com.lucasian.tweetclient.domain;

import java.util.Date;

public class Tweet implements Comparable<Tweet> {
	private String tweet;
	private String imgUrl;
	private String videoUrl;
	private Date timeStamp;
	private String username;
	private String name;
	private String badgeUrl;
	private int badgeId;
	private int imgId; 
	
	public int getBadgeId() {
		return badgeId;
	}
	public void setBadgeId(int badgeId) {
		this.badgeId = badgeId;
	}
	public int getImgId() {
		return imgId;
	}
	public void setImgId(int imgId) {
		this.imgId = imgId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getBadgeUrl() {
		return badgeUrl;
	}
	public void setBadgeUrl(String badgeUrl) {
		this.badgeUrl = badgeUrl;
	}
	public String getTweet() {
		return tweet;
	}
	public void setTweet(String tweet) {
		this.tweet = tweet;
	}
	public String getImgUrl() {
		return imgUrl;
	}
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	public String getVideoUrl() {
		return videoUrl;
	}
	public void setVideoUrl(String videoUrl) {
		this.videoUrl = videoUrl;
	}
	public Tweet(String tweet, String username, String name,String badgeUrl, String imgUrl, String videoUrl) {
		this(tweet, username,name,badgeUrl);
		this.videoUrl = videoUrl;
		this.timeStamp = new Date();
		
	}
	public Tweet(String tweet, String username, String name,String badgeUrl) {
		super();
		this.tweet = tweet;
		this.timeStamp = new Date();
		this.username = username;
		this.name = name;
		this.badgeUrl = badgeUrl;
		this.badgeId=0;
		this.imgId=0;
	}
	
	public Tweet(String tweet){
		super();
		this.tweet = tweet;
		this.timeStamp = new Date();
	}
	public Date getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(Date timeStamp) {
		this.timeStamp = timeStamp;
	}
	@Override
	public int compareTo(Tweet another) {
		return timeStamp.compareTo(another.timeStamp);
	}
	
}
