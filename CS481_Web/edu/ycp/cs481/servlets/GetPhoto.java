package edu.ycp.cs481.servlets;

import java.io.IOException;
import oracle.sql.*;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.ycp.cs481.srdesign.Photo;
//import edu.ycp.cs481.srdesign.controllers.GetPhotoByIdController;
import edu.ycp.cs481.srdesign.controllers.GetPhotoByIdController;

@WebServlet("/image/*")
public class GetPhoto extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	 @Override
	protected void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		getImage(request, response);
	}
	
	private static void getImage(HttpServletRequest request,HttpServletResponse response) {
		
		//image to get
		String image = request.getRequestURI().substring((request.getRequestURI().lastIndexOf('/') + 1));
		
		System.out.println(image);
		
		if(image.endsWith(".jpg")){
			image = image.substring(0, image.lastIndexOf("."));
		}
		System.out.println(image);
		int imageId = Integer.parseInt(image);
		GetPhotoByIdController p = new GetPhotoByIdController();
		Photo tmp = p.getPhotobyID(imageId);
		System.out.println("temp now contains a photo");
		byte[] imageData = new byte[2048];
		
		try{
			//Path path = Paths.get(tmp.getFile().getAbsolutePath());//image);
			//imageData = Files.//.readAllBytes(path);
			
			int bytesRead;
			//OutputStream output = null;
			//response.setContentType("image/jpeg");
			//ByteStreams.copy(tmp.getInStream(), )
			
		    /*while ((bytesRead = tmp.getInStream().read(imageData)) != -1)
		    {
		        //output.write(imageData, 0, bytesRead);
		    	response.getOutputStream().write(imageData, 0, bytesRead);
		    }*/
			
			response.setContentType("image/jpeg");
			//Blob b;
			//(oracle.sql.Blob) b;
			//response.getOutputStream().write((tmp.getBlob()).getBinaryStream());
			//response.getOutputStream().write(tmp.getBlob());
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
}
