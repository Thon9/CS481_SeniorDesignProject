package edu.ycp.cs481.srdesign;

import java.io.File;
import java.io.InputStream;
import java.io.Serializable;
import java.sql.Blob;
import java.util.ArrayList;

//import java.util.Iterator;

public class Photo implements Serializable {

	private static final long serialVersionUID = 1L;
	//private Blob blobValue;
	private int photoID;
	private int userID;
	private String fileName;
	private InputStream content;

	private ArrayList<Integer> HashTagID = new ArrayList<Integer>();;
	
	
	
	public Photo(){	}
	
	public Photo(String Name, InputStream file){
		setFileName(Name);
		setContent(file);
	}
	
	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public InputStream getContent() {
		return content;
	}

	public void setContent(InputStream content) {
		this.content = content;
	}

	public void setuserID(int id){
		this.userID = id;
	}
	
	public int getuserID(){
		return userID;
	}
	/*
	public Blob getBlobValue(){
		return blobValue;
	}
	
	// Error in this???
	public void setBlobValue(Blob value){
		this.blobValue = value;
	}*/
	
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
