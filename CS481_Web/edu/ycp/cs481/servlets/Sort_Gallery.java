package edu.ycp.cs481.servlets;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.ycp.cs481.srdesign.Photo;
import edu.ycp.cs481.srdesign.controllers.GetAllPhotosController;

/**
 * Servlet implementation class
 */
@WebServlet("/Sort_Gallery")
@MultipartConfig
public class Sort_Gallery extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    @Override
	protected void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
    	
//    	String sort_type = request.getParameter("sort_type");
//    	HttpSession session = request.getSession();
//		session.setAttribute("sort_type",sort_type);
//		
    	response.setStatus(HttpServletResponse.SC_OK);
		request.getRequestDispatcher("/Gallery").forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//get the subject and the type the user is searching for
		String sort_type = request.getParameter("sort_type");
		
		//get all the photos
		GetAllPhotosController controller = new GetAllPhotosController();
		
		ArrayList<Photo> gallery  = controller.getAllPhotos();
		
		//go to gallery
		if (sort_type == null || sort_type.contentEquals("sort by")){
			response.sendRedirect(request.getContextPath()+"/Gallery");
		}
		
		//sorting the photos
		else {
			//get the photos
			List<Photo> temp = gallery;
			List<String> paths = new ArrayList<String>();
			//sort by date which is base on id
			for(int i=0; i<temp.size(); i++){
				paths.add("image/"+i);
			}
			//sort by random
			if (sort_type.contains("random")){
				System.out.println("Sorting the Gallery base on "+sort_type+" ...");
				Collections.shuffle(paths);
			}
			
			request.setAttribute("photoList", paths);
			request.getRequestDispatcher("/gallery.jsp").forward(request, response); 
		}
		
	}
	
}