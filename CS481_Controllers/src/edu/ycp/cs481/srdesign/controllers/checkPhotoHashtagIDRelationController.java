package edu.ycp.cs481.srdesign.controllers;

import java.sql.SQLException;

import edu.ycp.cs481.srdesign.persist.DatabaseProvider;

public class checkPhotoHashtagIDRelationController {
	public boolean checkPhotoHashtagIDRelation(int photoID, int hashtagID) throws SQLException {
		return DatabaseProvider.getInstance().checkPhotoHashtagIDRelation(photoID, hashtagID);
	}
}


