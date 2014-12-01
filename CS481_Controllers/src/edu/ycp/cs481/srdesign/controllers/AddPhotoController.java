package edu.ycp.cs481.srdesign.controllers;

import java.sql.SQLException;

import edu.ycp.cs481.srdesign.Photo;
import edu.ycp.cs481.srdesign.persist.DatabaseProvider;
import edu.ycp.cs481.srdesign.persist.IDatabase;

public class AddPhotoController {

	public int addPhoto(Photo UpPhoto){
		IDatabase db = DatabaseProvider.getInstance();
		try {
			return db.addPhoto(UpPhoto);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
	};
}
