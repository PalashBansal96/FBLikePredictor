package com.palashbansal96.fb_like_predictor;

import com.google.appengine.labs.repackaged.org.json.JSONException;

import javax.servlet.jsp.JspWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Palash on 12/2/2015.
 * Class to predict likes.
 */
public class Predict {
	private Feed feed;
	private PostSet trainSet;
	private PostSet testSet;

	public void predict(JspWriter out) throws JSONException, FacebookErrorException, IOException {
		predict("me", out);
	}
	public void predict(String id, JspWriter out) throws IOException, FacebookErrorException, JSONException {
		feed = new Feed(id);
		trainSet = new PostSet();
		testSet = new PostSet();
		Collections.shuffle(feed.getPostsList());
		int nPosts = feed.getPostsList().size();
		int testSetLength = (int) (nPosts*0.2);
		System.out.println(nPosts);
		for(int i=0; i<testSetLength; i++){
			testSet.add(feed.getPostsList().get(i));
		}
		for(int i = testSetLength; i<feed.getPostsList().size(); i++){
			trainSet.add(feed.getPostsList().get(i));
		}
		ArrayList<LikedString> trainMessageSet = new ArrayList<>();
		ArrayList<LikedString> testMessageSet = new ArrayList<>();
		for(Post post : trainSet.getList()){
			trainMessageSet.add(new LikedString(post.getMessage()+" "+post.getStory()+" "+post.getLink_name(), post.getNoOfLikes()));
		}
		for(Post post : testSet.getList()){
			testMessageSet.add(new LikedString(post.getMessage()+" "+post.getStory()+" "+post.getLink_name(), post.getNoOfLikes()));
		}

		WordWeightModel messageWordModel = new WordWeightModel();
		WordWeightTrainer messageTrainer = new WordWeightTrainer(messageWordModel);

		messageTrainer.trainSet(trainMessageSet);

		int correct = 0;
		for(LikedString message : testMessageSet){
			double calculated = messageWordModel.calculateStringWeight(message.getString());
			if(Math.abs(calculated-message.getLike_count())<10) correct++;
			out.println("<span id=c>Post: </span><br>" + message.getString() + "<br><br> <span id=b> Predicted:</span> " + Math.round(calculated) + " <br> <span id=b>Original:&nbsp</span> " + message.getLike_count()+"<br><br><br>");
		}
		out.println("<span id=a class=line>Accuracy(%):&nbsp&nbsp&nbsp</span> " + 100.0*correct/testMessageSet.size());
		messageWordModel.printWords(100);

	}
}
