package edu.ycp.cs481.srdesign.controllers;

import java.io.File;
import java.util.ArrayList;

import edu.ycp.cs481.srdesign.persist.DatabaseProvider;
import edu.ycp.cs481.srdesign.persist.IDatabase;

public class GetAllPhotosController {
	public ArrayList<File> getAllPhotos() {
		IDatabase db = DatabaseProvider.getInstance();
		return db.getPhotos();
	}
}
