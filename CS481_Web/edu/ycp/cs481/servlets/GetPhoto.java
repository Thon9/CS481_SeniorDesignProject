package edu.ycp.cs481.servlets;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.ycp.cs481.srdesign.controllers.GetAllPhotosController;

@WebServlet("/image/*")
public class GetPhoto extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	 @Override
	protected void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
    	//response.setStatus(HttpServletResponse.SC_OK);
		//request.getRequestDispatcher("/main.jsp").forward(request, response);
		getImage(request, response);
	}
	
	private static void getImage(HttpServletRequest request,HttpServletResponse response) {
		
		//image to get
		String image = request.getRequestURI().substring((request.getRequestURI().lastIndexOf('/') + 1));//, request.toString().length());
		System.out.println(image);
		
		int imageId = Integer.parseInt(image);
		
		GetAllPhotosController getCont = new GetAllPhotosController();
		List<File> temp = getCont.getAllPhotos();
		
		List<String> paths = new ArrayList<String>();
		
		for(int i=0; i<temp.size(); i++){
			paths.add(temp.get(i).getAbsolutePath());
		}
		byte[] imageData = new byte[2048];
		
		try{
			Path path = Paths.get(temp.get(imageId).getAbsolutePath());//image);
			imageData = Files.readAllBytes(path);
			response.setContentType("image/jpeg");
			response.getOutputStream().write(imageData);
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
}
