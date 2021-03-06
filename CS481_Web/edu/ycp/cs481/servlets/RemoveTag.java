package edu.ycp.cs481.servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.ycp.cs481.srdesign.controllers.deleteHashtagFromPhotoController;
@WebServlet("/removeTag/*")
@MultipartConfig
public class RemoveTag extends HttpServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int hashid  = getHashID(request);
		int imageid = getImageID(request);
		
		// Controllers needed
		deleteHashtagFromPhotoController deleteHFPCont = new deleteHashtagFromPhotoController();
	
		try {
			deleteHFPCont.deleteRelation(imageid, hashid);
			System.out.println("delete hashtag id of " + hashid + " from image with id of " + imageid);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		response.setStatus(HttpServletResponse.SC_OK);
		response.sendRedirect("http://localhost:8080/CS481_Web/editPhoto/"+imageid);
		
		//request.getRequestDispatcher("/EditPhoto.jsp/"+imageid);//.forward(request, response);
	
	}
	
	private static int getImageID(HttpServletRequest request){
		String temp = request.getRequestURI().substring(0, request.getRequestURI().lastIndexOf('/'));
		
		String image = temp.substring(temp.lastIndexOf('/')+1);
		return Integer.parseInt(image);
	}
	
	private static int getHashID(HttpServletRequest request){
		String image = request.getRequestURI().substring((request.getRequestURI().lastIndexOf('/') + 1));
		return Integer.parseInt(image);
	}
}
