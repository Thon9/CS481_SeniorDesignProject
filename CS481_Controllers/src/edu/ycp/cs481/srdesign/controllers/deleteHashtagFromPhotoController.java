package edu.ycp.cs481.srdesign.controllers;

import java.sql.SQLException;

import edu.ycp.cs481.srdesign.persist.DatabaseProvider;
import edu.ycp.cs481.srdesign.persist.IDatabase;

public class deleteHashtagFromPhotoController {
	public void deleteRelation(int photoID, int hashtagID) throws SQLException {
		IDatabase db = DatabaseProvider.getInstance();
		db.deleteHashtagFromPhoto(photoID, hashtagID);
	}
}
