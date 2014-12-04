package edu.ycp.cs481.srdesign.controllers;

import java.sql.SQLException;
import java.util.ArrayList;

import edu.ycp.cs481.srdesign.HashTag;
import edu.ycp.cs481.srdesign.persist.DatabaseProvider;

public class GetHashtagsFromPhotoController {
	public ArrayList<HashTag> getHashtagsFromPhoto(int photoID) throws SQLException{
		return DatabaseProvider.getInstance().getHashtagsFromPhoto(photoID);
	}
}
