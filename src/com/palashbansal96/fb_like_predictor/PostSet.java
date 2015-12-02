package com.palashbansal96.fb_like_predictor;

import java.util.ArrayList;

/**
 * Created by Palash on 12/2/2015.
 * Set that contains Posts from a feed
 */
public class PostSet {
	ArrayList<Post> list= new ArrayList<>();

	public ArrayList<Post> getList() {
		return list;
	}

	public void add(Post post){
		list.add(post);
	}
}
