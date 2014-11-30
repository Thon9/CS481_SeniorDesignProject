package edu.ycp.cs481.servlets;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
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
	static byte[] streamBytes=new byte[4096];
	private static OutputStream os;
	
	 @Override
	protected void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		getImage(request, response);
	}
	
	private static void getImage(HttpServletRequest request,HttpServletResponse response) throws IOException {
		
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
	
		try{
			// Go from input stream to write image
			InputStream is = tmp.getFIS();
			os = null;
			
			int ch=0;
			while((ch=is.read(streamBytes)) != -1) {
			  os.write(streamBytes, 0, ch);
			}
			//os.flush();
		}catch(Exception e){
			e.printStackTrace();
		}
		//Path path = Paths.get(tmp.getFile().getAbsolutePath());
		//streamBytes = Files.readAllBytes(path);
		response.setContentType("image/jpeg");
		response.getOutputStream().write(streamBytes);
		
	}
}
