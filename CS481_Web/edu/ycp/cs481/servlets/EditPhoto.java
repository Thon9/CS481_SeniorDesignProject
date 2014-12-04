package edu.ycp.cs481.servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.ycp.cs481.srdesign.HashTag;
import edu.ycp.cs481.srdesign.controllers.GetHashtagsFromPhotoController;
import edu.ycp.cs481.srdesign.controllers.GetPhotoByIdController;


@WebServlet("/editPhoto/*")
public class EditPhoto extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		int imageID = getImageID(request);
		String imagePath = "image/" + imageID;
		GetHashtagsFromPhotoController photoHashtags = new GetHashtagsFromPhotoController();
		ArrayList<HashTag> tags = new ArrayList<HashTag>();
		ArrayList<String> tagStrings = new ArrayList<String>();
		
		try {
			tags = photoHashtags.getHashtagsFromPhoto(imageID);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for(int i=0;i<tags.size();i++){
			System.out.println("tagIDs:	"+tags.get(i).gethashtagName());
			tagStrings.add(tags.get(i).gethashtagName());
		}
		
		request.setAttribute("pID", imageID);
		request.setAttribute("photo", imagePath);
		request.setAttribute("tagList", tags);
		//response.setStatus(HttpServletResponse.SC_OK);
		request.getRequestDispatcher("/EditPhoto.jsp").forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("HashID :	"+getHashID(request));
		System.out.println("ImageID:	"+getImageID(request));
	}
	
	public void editInfo(){
		//GetPhotoByIdController getPhotoCont = new GetPhotoByIdController();
		
		
		/********ouput vars**************************/
		
		//String imagePath = "image/" + imageID;
		
		/**********************************/
		
		//getPhotoCont.getPhotobyID(imageID);
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
