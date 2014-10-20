package edu.ycp.cs481.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.ycp.cs481.srdesign.Photo;
import edu.ycp.cs481.srdesign.User;
import edu.ycp.cs481.srdesign.controllers.AddPhotoController;

/**
 * Servlet implementation class
 */
@WebServlet("/main")
public class AddPhoto extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    @Override
	protected void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
    	response.setStatus(HttpServletResponse.SC_OK);
		request.getRequestDispatcher("/main.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Photo newPhoto = new Photo();
		//TODO retrieve photo data 
		
		//add photo to database if valid photo
		if (newPhoto != null){
			AddPhotoController controller = new AddPhotoController();
			controller.addPhoto(newPhoto);
			System.out.println("add photo to database");
		}
		//if not a valid photo of file
		else {
			request.setAttribute("result", "invalid photo");
			this.doGet(request, response);
		}
	}

}