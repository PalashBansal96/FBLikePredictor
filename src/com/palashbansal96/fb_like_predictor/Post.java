package com.palashbansal96.fb_like_predictor;

import com.google.appengine.labs.repackaged.org.json.JSONArray;
import com.google.appengine.labs.repackaged.org.json.JSONException;
import com.google.appengine.labs.repackaged.org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Palash on 11/25/2015.
 * The class for keeping track of posts by the user or a facebook page.
 */
public class Post {
	private String id;
	private String message;
	private String created_time;
	private ArrayList<Like> likes;

	public Post(String id) throws IOException, JSONException, FacebookErrorException {
		this.id = id;
		message = "";
		created_time = "";
		this.likes = new ArrayList<>();
		JSONObject json;
		try {
			json = DataFetcher.getData(id);
		} catch (FacebookErrorException e) {
			if(e.getError_code()==100){
				message = "error";
				return;
			}else
				throw e;
		}

		created_time = json.getString("created_time");
		if(json.has("message")) {
			message += json.getString("message") + " ";
		}
		if(json.has("story")) {
			message += json.getString("story");
		}
		JSONObject likes_object = DataFetcher.getData(id+"/likes");
		JSONArray likes = likes_object.getJSONArray("data");
		while(likes.length()>0) {
			for (int i = 0; i < likes.length(); i++) {
				this.likes.add(new Like(likes.getJSONObject(i).getString("id")));
			}
			if (likes_object.getJSONObject("paging").has("next")) {
				likes_object = DataFetcher.getDataFromURL(likes_object.getJSONObject("paging").getString("next"));
				likes = likes_object.getJSONArray("data");
			} else break;
		}
	}

	public String getMessage() {
		return message;
	}
}
