package com.palashbansal96.fb_like_predictor;

import java.util.*;

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
		String[] wordList = string.replaceAll("[^a-zA-Z ]", " ").toLowerCase().split("\\s+");
		Double weight = 0.0;
		int n_words = wordList.length;
		for(String word : wordList){
			if(word.length()<3){
				n_words--;
				continue;
			}
			if(words.containsKey(word))
				weight += words.get(word).getWeight();
			else
				weight += averageLikes; //If word not in list
		}
		return weight/wordList.length;
	}
	public void printWords(int n) {
//		LinkedHashMap<String, Word> sorted = sortHashMapByValues(words);
		int j = 0;
		for(Word i: words.values()){
			System.out.println(i.getWord() + ": " + i.getWeight());
			if(j++==n)break;
		}
	}


}
