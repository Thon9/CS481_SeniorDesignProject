package edu.ycp.cs481.srdesign;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.Serializable;
import java.sql.Blob;
import java.util.ArrayList;


public class Photo implements Serializable {

	private static final long serialVersionUID = 1L;
	private int photoID;
	private int userID;
	private long fileLength;
	private InputStream fis;

	//private ArrayList<Integer> HashTagID;
	
	public Photo(){}
	
	
	
	public Photo(int photoID, int userID, FileInputStream fis, long filelength){	
		this.photoID = photoID;
		this.userID = userID;
		this.fis = fis;
		this.fileLength = filelength;
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
	
	
	public InputStream getFIS() {	
		return  fis; 
	}

	public void setFIS(InputStream fis) {	
		this.fis = fis;	}

	public long getFileLength() {	
		return fileLength;	
	}

	public void setFileLength(long fileLength) {	
		this.fileLength = fileLength;	
	}

	/**
	public Blob getBlob() {
		return blob;
	}

	public void setBlob(Blob blob) {
		this.blob = blob;
	}
	
	public Photo(File file){	this.file = file;	}
	
	 **/
}
