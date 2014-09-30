package edu.ycp.cs481.srdesign;

import java.io.Serializable;
import java.util.ArrayList;
//import java.util.Iterator;

public class Photo implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private int photoID;
	private int userID;
	private ArrayList<Integer> HashTagID = new ArrayList<Integer>();;
	
	
	
	public Photo(){
		
		
	}
	
	public void setuserID(int id){
		this.userID = id;
	}
	
	public int getuserID(){
		return userID;
	}
	
	
	// Set/Get User Name
	public void setphotoID(int pID ) {
		this.photoID = pID;
	}
	public int getphotoID() {
		return photoID;
	}
	
	public ArrayList<Integer> getHashTagIDs(){
		return HashTagID;
	}

}
