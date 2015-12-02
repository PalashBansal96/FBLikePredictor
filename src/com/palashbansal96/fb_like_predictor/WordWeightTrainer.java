package com.palashbansal96.fb_like_predictor;

import java.util.List;

/**
 * Created by Palash on 12/2/2015.
 * Class for training a given word model, with set of strings(sentences)
 */
public class WordWeightTrainer {
	WordWeightModel model;
	Post currentPost;

	public WordWeightTrainer(WordWeightModel model) {
		this.model = model;
	}

	void trainSet(List<LikedString> strings){
		for(LikedString string : strings){
			String[] words = string.getString().replaceAll("[^a-zA-Z0-9 ]", " ").toLowerCase().split("\\s+");
			for(String word : words){
				model.addWord(word, string.getLike_count());
			}
		}
	}

}
