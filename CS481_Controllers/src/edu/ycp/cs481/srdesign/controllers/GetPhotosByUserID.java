package edu.ycp.cs481.srdesign.controllers;

import edu.ycp.cs481.srdesign.persist.DatabaseProvider;

public class GetPhotosByUserID {
	public void getUserPhotos(int uID){
		DatabaseProvider.getInstance().getUserPhotos(uID);
	}
}
