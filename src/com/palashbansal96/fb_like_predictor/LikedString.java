package com.palashbansal96.fb_like_predictor;

/**
 * Created by Palash on 12/2/2015.
 * String class with like_counts.
 */
public class LikedString {
	private String string;
	private int like_count;

	public LikedString(String string, int like_count) {
		this.string = string;
		this.like_count = like_count;
	}

	public String getString() {
		return string;
	}

	public int getLike_count() {
		return like_count;
	}
}
