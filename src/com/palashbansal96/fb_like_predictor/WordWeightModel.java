package com.palashbansal96.fb_like_predictor;

import java.util.HashMap;

/**
 * Created by Palash on 12/2/2015.
 * Model for word weight, assigns weight to words of how many likes they are support to get.
 */
public class WordWeightModel {
	private HashMap<String, Word> words = new HashMap<>();
	private Integer totalLikes = 0;
	private Double averageLikes = 0.0;

	public void addWord(String word, Integer likes){
		if(words.containsKey(word)){
			words.get(word).incrementCount(likes);
		}else{
			words.put(word, new Word(word, likes));
		}
		totalLikes += likes;
		averageLikes = 1.0*totalLikes/words.size();
	}

	public HashMap<String, Word> getMap() {
		return words;
	}

	public Double calculateStringWeight(String string) {
		String[] wordList = string.replaceAll("[^a-zA-Z0-9 ]", " ").toLowerCase().split("\\s+");
		Double weight = 0.0;
		for(String word : wordList){
			if(words.containsKey(word))
				weight += words.get(word).getWeight();
			else
				weight += averageLikes; //If word not in list
		}
		return weight/wordList.length;
	}
}
