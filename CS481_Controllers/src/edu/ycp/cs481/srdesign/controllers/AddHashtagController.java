package edu.ycp.cs481.srdesign.controllers;

import edu.ycp.cs481.srdesign.HashTag;
import edu.ycp.cs481.srdesign.persist.DatabaseProvider;
import edu.ycp.cs481.srdesign.persist.IDatabase;

public class AddHashtagController {
	public void addHashtag(HashTag hashtag) {
		IDatabase db = DatabaseProvider.getInstance();
		db.addHashtag(hashtag);
	}
}
