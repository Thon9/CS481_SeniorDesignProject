package edu.ycp.cs481.srdesign.controllers;

import java.io.InputStream;

import edu.ycp.cs481.srdesign.Photo;
import edu.ycp.cs481.srdesign.persist.DatabaseProvider;
import edu.ycp.cs481.srdesign.persist.IDatabase;

public class AddPhotoController {
	public void addPhoto(String fileName, InputStream content) {
		IDatabase db = DatabaseProvider.getInstance();
		db.addPhoto(fileName, content);
	}
}
