package edu.ycp.cs481.servlets;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import edu.ycp.cs481.srdesign.FakeDatabase;
import edu.ycp.cs481.srdesign.SQLDatabase;
import edu.ycp.cs481.srdesign.persist.DatabaseProvider;
import edu.ycp.cs481.srdesign.persist.IDatabase;

public class DatabaseInitListener implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void contextInitialized(ServletContextEvent e) {
<<<<<<< HEAD
		System.out.println("Creating SQLDatabase...");
		/*
		DatabaseProvider.setInstance(new SQLDatabase());
		*/
=======
//		Fake Database Stuff
				IDatabase db = new FakeDatabase();
				DatabaseProvider.setInstance(db);
		
		//System.out.println("Creating SQLDatabase...");
>>>>>>> branch 'master' of git@github.com:Thon9/CS481_SeniorDesignProject.git
		
<<<<<<< HEAD
=======
		//DatabaseProvider.setInstance(new SQLDatabase());
>>>>>>> branch 'master' of git@github.com:Thon9/CS481_SeniorDesignProject.git
		
<<<<<<< HEAD
			//Fake Database Stuff
			IDatabase db = new FakeDatabase();
			DatabaseProvider.setInstance(db);
		
		System.out.println("SQLDatabase initialized...");
=======
		//System.out.println("SQLDatabase initialized...");
		
		
		
>>>>>>> branch 'master' of git@github.com:Thon9/CS481_SeniorDesignProject.git
	}

}
