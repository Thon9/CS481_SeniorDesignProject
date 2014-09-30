package edu.ycp.cs481.srdesign.controllers;

import edu.ycp.cs481.srdesign.Photo;
import edu.ycp.cs481.srdesign.persist.DatabaseProvider;
import edu.ycp.cs481.srdesign.persist.IDatabase;

public class AddPhotoController {
	public void addPhoto(Photo photo) {
		IDatabase db = DatabaseProvider.getInstance();
		db.addPhoto(photo);
	}
}
