package edu.ycp.cs481.srdesign.controllers;

import java.sql.SQLException;
import java.util.ArrayList;

import edu.ycp.cs481.srdesign.Photo;
import edu.ycp.cs481.srdesign.persist.DatabaseProvider;

public class GetPhotosByUserID {
	public ArrayList<Photo> getUserPhotos(int uID) throws SQLException {
			return DatabaseProvider.getInstance().getUserUploadedPhotos(uID);	
	}
}
