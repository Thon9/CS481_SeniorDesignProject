package edu.ycp.cs481.srdesign.controllers;

import edu.ycp.cs481.srdesign.persist.DatabaseProvider;
import edu.ycp.cs481.srdesign.persist.IDatabase;

public class HashToPhotoRelaController {
	public void addRelaHTP(int hashtagID, int photoID){
		DatabaseProvider.getInstance().addRelaHTP(hashtagID, photoID);
	}
}
