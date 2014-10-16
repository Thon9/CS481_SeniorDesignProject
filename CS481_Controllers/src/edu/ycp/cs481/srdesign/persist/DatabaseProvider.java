package edu.ycp.cs481.srdesign.persist;

import edu.ycp.cs481.srdesign.FakeDatabase;



public class DatabaseProvider {
	private static IDatabase theInstance = new FakeDatabase();
	
	public static void setInstance(IDatabase db) {
		System.out.println("Setting database instance");
		theInstance = db;
	}
	
	public static IDatabase getInstance() {
		if (theInstance == null) {
			throw new IllegalStateException("There is no database instance!");
		}
		return theInstance;
	}
}
