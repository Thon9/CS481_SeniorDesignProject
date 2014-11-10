package edu.ycp.cs481.srdesign.controllers;


import java.io.FileInputStream;
import java.sql.SQLException;

import edu.ycp.cs481.srdesign.persist.DatabaseProvider;
import edu.ycp.cs481.srdesign.persist.IDatabase;

public class AddPhotoController {

	public void addPhoto(String fileName, FileInputStream fis, long filelength) throws SQLException {
		IDatabase db = DatabaseProvider.getInstance();
		db.addPhoto(fileName, fis, filelength);
	};
}
