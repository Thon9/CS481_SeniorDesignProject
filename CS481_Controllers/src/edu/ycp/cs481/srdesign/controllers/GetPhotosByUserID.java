package edu.ycp.cs481.srdesign.controllers;

import java.sql.SQLException;

import edu.ycp.cs481.srdesign.persist.DatabaseProvider;

public class GetPhotosByUserID {
	public void getUserPhotos(int uID){
		try {
			DatabaseProvider.getInstance().getUserUploadedPhotos(uID);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
