package edu.ycp.cs481.srdesign;

import java.io.File;
import java.io.InputStream;
import java.io.Serializable;
import java.sql.Blob;
import java.util.ArrayList;


public class Photo implements Serializable {

	private static final long serialVersionUID = 1L;
	private int photoID;
	private int userID;
	private InputStream inStream;
	private File file;
	private Blob blob;

	//private ArrayList<Integer> HashTagID;
	
	public Photo(){}
	
	public Photo(File file){	this.file = file;	}
	
	public Photo(int photoID, int userID, InputStream inStream){	
		this.photoID = photoID;
		this.userID = userID;
		this.inStream = inStream;
		//this.HashTagID = new ArrayList<Integer>();
	}

	public void setuserID(int id){	this.userID = id;	}
	
	public int getuserID(){	return userID;	}
	
	// Set/Get User Name
	public void setphotoID(int pID ) {	this.photoID = pID;	}
	
	public int getphotoID() {	return photoID;	}
	
	/*
	 * public ArrayList<Integer> getHashTagIDs(){
		return HashTagID;
	}
	public void addHashtagIDs(ArrayList<Integer> hIDs){
		for(int i=0; i<hIDs.size();i++){	this.HashTagID.add(hIDs.get(i));	}
		
	}
	*/
	
	public InputStream getInStream() {	return inStream;	}

	public void setInStream(InputStream inStream) {	this.inStream = inStream;	}

	public File getFile() {	return file;	}

	public void setFile(File file) {	this.file = file;	}

	public Blob getBlob() {
		return blob;
	}

	public void setBlob(Blob blob) {
		this.blob = blob;
	}

}
