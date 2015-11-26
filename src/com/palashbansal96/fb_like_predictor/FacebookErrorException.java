package com.palashbansal96.fb_like_predictor;

/**
 * Created by Palash on 11/25/2015.
 * Exception thrown when an error is returned by facebook.
 */
public class FacebookErrorException extends Exception {
	private int error_code;
	public FacebookErrorException(){
		super();
	}
	public FacebookErrorException(String msg){
		super(msg);
	}
	public FacebookErrorException(String msg, int error_code) {
		super(msg);
		this.error_code = error_code;
	}
	public int getError_code() {
		return error_code;
	}
}
