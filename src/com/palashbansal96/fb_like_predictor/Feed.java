package com.palashbansal96.fb_like_predictor;

import com.google.appengine.labs.repackaged.org.json.JSONArray;
import com.google.appengine.labs.repackaged.org.json.JSONException;
import com.google.appengine.labs.repackaged.org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Palash on 11/25/2015.
 * The class to keep track of all posts by a person or a page.
 */
public class Feed {
	private String id;

	private PostSet posts;

	public Feed() throws JSONException, FacebookErrorException, IOException {
		this.id = "me";
		fetchFeed();
	}

	public Feed(String id) throws JSONException, FacebookErrorException, IOException {
		this.id = id;
		fetchFeed();
	}

	private void fetchFeed() throws IOException, FacebookErrorException, JSONException {
		posts = new PostSet();
		JSONObject jsonFeed = DataFetcher.getData(id+"/posts", 25, "&fields=likes.limit(25),name,message,shares,story,created_time,message_tags,link");
		JSONArray feedData = jsonFeed.getJSONArray("data");
		while (feedData.length()>0){
			for(int i=0; i<feedData.length(); i++){
				posts.add(new Post(feedData.getJSONObject(i).getString("id"), feedData.optJSONObject(i)));
			}

			if(jsonFeed.getJSONObject("paging").has("next")) {
				jsonFeed = DataFetcher.getDataFromURL(jsonFeed.getJSONObject("paging").getString("next"));
				feedData = jsonFeed.getJSONArray("data");
			}else break;
		}
	}

	public ArrayList<Post> getPosts() {
		return posts.getList();
	}
}
