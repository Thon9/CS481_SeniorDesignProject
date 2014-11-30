package edu.ycp.cs481.servlets;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.ycp.cs481.srdesign.Photo;
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
			int bytesRead=0;
			ByteArrayOutputStream output = new ByteArrayOutputStream();
			while ((bytesRead = tmp.getFIS().read(imageData, 0, imageData.length)) != -1) {
				output.write(imageData, 0, bytesRead);
			}

			output.flush();
			response.setContentType("image/jpeg");
			response.getOutputStream().write(output.toByteArray());
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if (tmp.getFIS() != null) {
				try {
					tmp.getFIS().close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		
	}
}
