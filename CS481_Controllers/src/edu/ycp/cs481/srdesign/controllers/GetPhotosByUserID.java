package edu.ycp.cs481.srdesign.controllers;

import java.sql.SQLException;
import java.util.ArrayList;

import edu.ycp.cs481.srdesign.Photo;
import edu.ycp.cs481.srdesign.persist.DatabaseProvider;

public class GetPhotosByUserID {
	public ArrayList<Photo> getUserPhotos(int uID){
		try {
			DatabaseProvider.getInstance().getUserUploadedPhotos(uID);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
