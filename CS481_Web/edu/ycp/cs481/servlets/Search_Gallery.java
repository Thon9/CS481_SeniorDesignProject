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
import javax.servlet.http.HttpSession;

import edu.ycp.cs481.srdesign.Photo;
import edu.ycp.cs481.srdesign.User;
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
		
		HttpSession session = request.getSession();
		
		//get the subject and the type the user is searching for
		String subject = request.getParameter("search_object");
		String subject_type = request.getParameter("search_type");
		
		//get all the photos
		GetAllPhotosController controller = new GetAllPhotosController();

		ArrayList<Photo> gallery  = controller.getAllPhotos();
		List<String> paths = new ArrayList<String>();
		
		//set the default search tag to title
		if (subject_type == null){
			subject_type = "id";
		}
		System.out.println("Searching for "+subject_type+": "+subject+" ...");	
		
		
		//search base on id
		if (subject_type.contains("id")){
			//scan and get only the photos related to the subject
			for (Photo photo : gallery){
				if (photo.getFile().getName().toString().contains(subject)){
					paths.add("image/"+photo.getFile().getName().toString());
					System.out.println("found "+photo.getFile().getName().toString());
				}
			}
		}
		//search base on user
		else if (subject_type.contains("user")){
			
			for (Photo photo : gallery){
//				if (photo.getuserID()){
//					
//				}
			}
		}
		
		//show new gallery if search success
		request.setAttribute("result", subject_type+" : "+subject);
		request.setAttribute("photoList", paths);
		request.getRequestDispatcher("/gallery.jsp").forward(request, response);
	}

}