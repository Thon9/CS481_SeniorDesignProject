package edu.ycp.cs481.servlets;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

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
@WebServlet("/Search_Gallery")
@MultipartConfig
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
		
		//set the default search tag to title
		if (subject_type == null){
			subject_type = "title";
		}
		
		if (subject != null){
			System.out.println("Searching for "+subject_type+": "+subject+" ...");
			
			//get all the photos
			GetAllPhotosController controller = new GetAllPhotosController();
			ArrayList<File> gallery  = controller.getAllPhotos();
			ArrayList<File> search_gallery = new ArrayList<File>();
			//scan and get only the photos related to the subject
			if (subject_type == "tags"){
				for (int i = 0; i < gallery.size(); i++){
					//get photos related to tags
				}
			}
			
		}
		
		response.sendRedirect(request.getContextPath()+"/gallery.jsp");
	}

}