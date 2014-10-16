package edu.ycp.cs481.srdesign.persist;

import edu.ycp.cs481.srdesign.FakeDatabase;


public class DatabaseProvider {
	private static IDatabase theInstance = new FakeDatabase();
	
	public static void setInstance(IDatabase db) {
		theInstance = db;
	}
	
	public static IDatabase getInstance() {
		return theInstance;
	}
}
