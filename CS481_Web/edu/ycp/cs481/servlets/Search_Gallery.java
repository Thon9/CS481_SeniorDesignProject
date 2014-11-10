package edu.ycp.cs481.servlets;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.ycp.cs481.srdesign.controllers.GetAllPhotosController;

/**
 * Servlet implementation class
 */
//@WebServlet("/Search_Gallery")
//@MultipartConfig
/*
public class Search_Gallery extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    @Override
	protected void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
    	response.setStatus(HttpServletResponse.SC_OK);
		request.getRequestDispatcher("/gallery.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//get the subject and the type the user is searching for
		String subject = request.getParameter("search_object");
		String subject_type = request.getParameter("search_type");
		
		//get all the photos
		GetAllPhotosController controller = new GetAllPhotosController();

		ArrayList<File> gallery  = controller.getAllPhotos();
		//ArrayList<Photo> gallery  = new ArrayList<Photo>();

		//ArrayList<Photo> search_gallery = new ArrayList<Photo>();
		ArrayList<File> search_gallery = new ArrayList<File>();
		
		//set the default search tag to title
		if (subject_type == null){
			subject_type = "title";
		}
	
		if (subject != null){
			System.out.println("Searching for "+subject_type+": "+subject+" ...");
					
			//scan and get only the photos related to the subject
			for (File photo : gallery){
				//get photos related to tags
				if (photo.getPath().contains(subject)){
					search_gallery.add(photo);
				}
			}
		
		}
		
		//shows the selected pictures or whole gallery if no subject
		List<File> temp = gallery;
		if (search_gallery != null){
			temp = search_gallery;
		}
		
		List<String> paths = new ArrayList<String>();
		for(int i=0; i<temp.size(); i++){
			paths.add("image/"+i);
		}
		request.setAttribute("photoList", paths);
		request.getRequestDispatcher("/gallery.jsp").forward(request, response); 
		response.sendRedirect(request.getContextPath()+"/Gallery");
	}

}
*/