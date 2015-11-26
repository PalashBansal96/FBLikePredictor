package com.palashbansal96.fb_like_predictor;

import com.google.appengine.labs.repackaged.org.json.JSONException;
import com.google.appengine.labs.repackaged.org.json.JSONObject;

import java.io.IOException;

/**
 * Created by Palash on 11/25/2015.
 * The like class for keeping track of who liked.
 */
public class Like {
	private String id;
	private String name;
	public static boolean do_get_name = false; //Whether you want to get name of the person who liked

	public Like(String id) throws IOException, FacebookErrorException, JSONException {
		this.id = id;
		name = "John Doe";
		if(do_get_name) {
			fetchName();
		}
	}
	 void fetchName() throws IOException, JSONException, FacebookErrorException {
		 JSONObject json;
		 try {
			 json = DataFetcher.getData(id);
		 } catch (FacebookErrorException e) {
			 if(e.getError_code()!=100)
				 throw e;
			 else return;
		 }
		 if (json.has("name")) {
			 name = json.getString("name");
		 }
	 }
}
