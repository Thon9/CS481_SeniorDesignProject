package edu.ycp.cs481.servlets;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

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
		System.out.println("Creating SQLDatabase...");
		
	

		DatabaseProvider.setInstance(new SQLDatabase());

		System.out.println("SQLDatabase initialized...");
	}

}
