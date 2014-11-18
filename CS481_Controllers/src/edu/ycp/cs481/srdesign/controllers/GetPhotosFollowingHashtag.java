package edu.ycp.cs481.srdesign.controllers;

import java.sql.SQLException;
import java.util.ArrayList;

import edu.ycp.cs481.srdesign.Photo;
import edu.ycp.cs481.srdesign.persist.DatabaseProvider;

public class GetPhotosFollowingHashtag {
	public ArrayList<Photo> GetPhotosFollowingHashtag(final int uID, final int hashtagID) throws SQLException{
		return DatabaseProvider.getInstance().getUserFollowingPhotos(uID, hashtagID);
	}
}
