package edu.ycp.cs481.srdesign;

import java.io.Serializable;

public class HashTag implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private int hashtagID;
	private String hashtagName;
	
	public HashTag() {
	   	 
	}
	
	public void sethashtagID(int id){
		this.hashtagID = id;
	}
	
	public int gethashtagID(){
		return hashtagID;
	}
	
	public void sethashtagName(String hashtagName) {
		this.hashtagName = hashtagName;
	}
	public String gethashtagName() {
		return hashtagName;
	}
	
}
