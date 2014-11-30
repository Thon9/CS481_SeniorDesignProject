package edu.ycp.cs481.srdesign.controllers;

import java.sql.SQLException;

import edu.ycp.cs481.srdesign.Photo;
import edu.ycp.cs481.srdesign.persist.DatabaseProvider;

public class GetPhotoByIdController {
	public Photo getPhotobyID(int pID){
		try {
			return DatabaseProvider.getInstance().getPhotoByID(pID, true);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
}
