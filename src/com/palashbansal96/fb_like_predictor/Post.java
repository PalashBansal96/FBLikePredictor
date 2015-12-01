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
	private String story;
	private String link;
	private String link_name;
	private String created_time;
	private Integer shares;
	private Integer no_of_tags;
	private Integer message_weight;
	private Integer story_weight;
	private Integer line_name_weight;
	private Integer hour;
	private Integer season;

	private ArrayList<Like> likes = new ArrayList<>();

	public Post(String id, JSONObject json) throws IOException, FacebookErrorException, JSONException {
		this.id = id;
		System.out.println(id);
		if(json==null) {
			try {
				json = DataFetcher.getData(id);
			} catch (FacebookErrorException e) {
				if (e.getError_code() == 100) {
					message = "error";
					return;
				} else
					throw e;
			}
		}

		created_time = json.optString("created_time");
		message = json.optString("message");
		story = json.optString("story");
		link = json.optString("link");
		link_name = json.optString("name");
		shares = json.optInt("shares");
		if(json.has("message_tags")) {
			try {
				no_of_tags = json.getJSONArray("message_tags").length();
			} catch (JSONException e) {
				no_of_tags = 0;
			}
		}

		JSONObject likes_object;
		try{
			likes_object = json.getJSONObject("likes");
		}catch (JSONException e) {
			likes_object = DataFetcher.getData(id + "/likes");
		}
		JSONArray likes_array = likes_object.getJSONArray("data");
		while(likes_array.length()>0) {
			for (int i = 0; i < likes_array.length(); i++) {
				this.likes.add(new Like(likes_array.getJSONObject(i).getString("id")));
			}
			if (likes_object.getJSONObject("paging").has("next")) {
				likes_object = DataFetcher.getDataFromURL(likes_object.getJSONObject("paging").getString("next"));
				likes_array = likes_object.getJSONArray("data");
			} else break;
		}
	}


	public String getMessage() {
		return message;
	}

	public int getNoOfLikes(){
		return likes.size();
	}

	public String getId() {
		return id;
	}

	public String getStory() {
		return story;
	}

	public String getLink() {
		return link;
	}

	public String getLink_name() {
		return link_name;
	}

	public String getCreated_time() {
		return created_time;
	}

	public Integer getShares() {
		return shares;
	}

	public Integer getNo_of_tags() {
		return no_of_tags;
	}

	public Integer getMessage_weight() {
		return message_weight;
	}

	public Integer getStory_weight() {
		return story_weight;
	}

	public Integer getLine_name_weight() {
		return line_name_weight;
	}

	public Integer getHour() {
		return hour;
	}

	public Integer getSeason() {
		return season;
	}
}
