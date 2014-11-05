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
import javax.servlet.http.HttpSession;

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
    	response.setStatus(HttpServletResponse.SC_OK);
		request.getRequestDispatcher("/gallery.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//get the subject and the type the user is searching for
		String sort_type = request.getParameter("sort_type");
		
		System.out.println("Sorting the Gallery base on "+sort_type+" ...");
		
		
		//get all the photos
		GetAllPhotosController controller = new GetAllPhotosController();
		ArrayList<File> gallery  = controller.getAllPhotos();
		ArrayList<File> sorted_gallery = new ArrayList<File>();
		
		//set the default search tag to title
		if (sort_type == null){
			sort_type = "default";
		}
		
		HttpSession session = request.getSession();
		session.setAttribute("sort_type",sort_type);

		response.sendRedirect(request.getContextPath()+"/gallery.jsp");
	}

}