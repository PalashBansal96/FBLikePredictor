package com.palashbansal96.fb_like_predictor;

/**
 * Created by Palash on 12/2/2015.
 * Class for all words
 */
public class Word implements Comparable<Word>{
	String word;
	Integer like_count=0; //Total no. of likes of posts it appeared in.
	Integer post_count=0; //Total no. of posts this word appeared in.
	Double weight;

	public Word(String word, int likes) {
		this.like_count = likes;
		this.word = word;
		this.post_count = 1;
		weight = 1.0*like_count/post_count;
	}


	@Override
	public int compareTo(Word o) {
		return word.compareTo(o.getWord());
	}

	public boolean equals(Word o) {
		return word.equals(o.getWord());
	}

	public void incrementCount(int likes){
		post_count++;
		like_count+=likes;
		weight = 1.0*like_count/post_count;
	}

	public Integer getPost_count() {
		return post_count;
	}

	public Integer getLike_count() {
		return like_count;
	}

	public String getWord() {
		return word;
	}

	public Double getWeight() {
		return weight;
	}
}
