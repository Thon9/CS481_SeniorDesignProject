package edu.ycp.cs481.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Main {
	@WebServlet("/Main")
	@MultipartConfig
	public class AddPhoto extends HttpServlet {
		private static final long serialVersionUID = 1L;
	       
	    @Override
		protected void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
	    	response.setStatus(HttpServletResponse.SC_OK);
			request.getRequestDispatcher("/main.jsp").forward(request, response);
			
		}

		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
		}
	}
}
