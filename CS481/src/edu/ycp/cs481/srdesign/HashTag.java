package edu.ycp.cs481.srdesign;

import java.io.Serializable;

public class HashTag implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private int hashtagID;
	private String hashtagName;
	
	public HashTag() {
	   	 
	}
	
	public void setuserID(int id){
		this.hashtagID = id;
	}
	
	public int getuserID(){
		return hashtagID;
	}
	
	public void setUserName(String hashtagName) {
		this.hashtagName = hashtagName;
	}
	public String getUserName() {
		return hashtagName;
	}
	
}
