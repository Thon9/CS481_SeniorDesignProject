package edu.ycp.cs481.srdesign.controllers;

import edu.ycp.cs481.srdesign.Photo;
import edu.ycp.cs481.srdesign.persist.DatabaseProvider;

public class GetPhotoByIdController {
	public Photo getPhotobyID(int pID){
		return DatabaseProvider.getInstance().getPhotoByID(pID);
	}
}
