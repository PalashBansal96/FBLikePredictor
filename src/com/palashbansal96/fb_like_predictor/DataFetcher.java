package com.palashbansal96.fb_like_predictor;

import com.google.appengine.labs.repackaged.org.json.JSONException;
import com.google.appengine.labs.repackaged.org.json.JSONObject;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.Charset;

/**
 * Created by Palash on 11/25/2015.
 * A class that has static functions to get the data from Facebook.
 */


public class DataFetcher {

	private static String access_token = "";

	public static String getAccess_token() {
		return access_token;
	}

	public static void setAccess_token(String access_token) {
		DataFetcher.access_token = access_token;
	}

	public static JSONObject getData(String id, int limit, String param) throws JSONException, IOException, FacebookErrorException {
		String url = "https://graph.facebook.com/v2.5/" + id + "?limit="+ limit +"&format=json"+ param +"+&access_token=" + access_token;
		return getDataFromURL(url);
	}

	public static JSONObject getData(String id) throws JSONException, IOException, FacebookErrorException {
		return getData(id, 100, "");
	}

	public static JSONObject getDataFromURL(String url) throws JSONException, IOException, FacebookErrorException {
		System.out.println(url);
		JSONObject jsonObject = new JSONObject(IOUtils.toString(new URL(url), Charset.forName("UTF-8")));
		if(jsonObject.has("error")) {
			int error_code = jsonObject.getJSONObject("error").getInt("code");
			throw new FacebookErrorException(jsonObject.getString("error"), error_code);
		}
		return jsonObject;
	}

}
