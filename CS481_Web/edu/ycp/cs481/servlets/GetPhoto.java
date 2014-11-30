package edu.ycp.cs481.servlets;

import java.awt.image.BufferedImage;


import java.io.File;
import java.io.FileOutputStream;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.imageio.ImageIO;
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


		byte[] imageData = new byte[2048];
		OutputStream output = null;
		try{

			// Go from input stream to write image
			InputStream is = tmp.getFIS();
			os = null;
			
			int ch=0;
			while((ch=is.read(streamBytes)) != -1) {
			  os.write(streamBytes, 0, ch);
			}
			//os.flush();

			//Path path = Paths.get(tmp.getFile().getAbsolutePath());//image);
			//imageData = Files.//.readAllBytes(path);
			
			File newImage = new File("C:\\imagesFolder\\"+imageId+".jpg");
			
			if(!newImage.exists()) {
				newImage.createNewFile();
			} 
			output = new FileOutputStream(newImage, false);
			
			int bytesRead=0;
			
			while((bytesRead = tmp.getFIS().read(imageData)) !=-1){
				output.write(imageData, 0, bytesRead);
			}
			
			
			
			
			
			
			
			
			
			
			byte[] Idata = new byte[2048];
			
				Path path = Paths.get(newImage.getAbsolutePath());// tmp.getFile().getAbsolutePath());//image);
				Idata = Files.readAllBytes(path);
				response.setContentType("image/jpeg");
				response.getOutputStream().write(Idata);
			
			
			
			
			//OutputStream output = null;
			//response.setContentType("image/jpeg");
			//ByteStreams.copy(tmp.getInStream(), )
			/*
		    while ((bytesRead = tmp.getInStream().read(imageData)) != -1)
		    {
		        //output.write(imageData, 0, bytesRead);
		    	//response.getOutputStream().write(imageData, 0, bytesRead);
		    }*/
			
			
			//Blob b;
			//(oracle.sql.Blob) b;
			//response.getOutputStream().write((tmp.getBlob()).getBinaryStream());
			//response.setContentType("image/jpeg");
			//response.getOutputStream().write(imageData);

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
			if (output != null) {
				try {
					output.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
	 
			}
		}
		//Path path = Paths.get(tmp.getFile().getAbsolutePath());
		//streamBytes = Files.readAllBytes(path);
		response.setContentType("image/jpeg");
		response.getOutputStream().write(streamBytes);
		
	}
}
