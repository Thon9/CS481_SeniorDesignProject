package edu.ycp.cs481.srdesign.controllers;

import java.sql.SQLException;
import java.util.ArrayList;

import edu.ycp.cs481.srdesign.persist.DatabaseProvider;


public class AutoCompleteHashTag {
	public ArrayList<String> getAutoCompleteSearch() throws SQLException{
		return DatabaseProvider.getInstance().returnAllHashtags();
	}
}
