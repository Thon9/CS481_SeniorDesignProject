package edu.ycp.cs481.servlets;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import edu.ycp.cs481.srdesign.Photo;
import edu.ycp.cs481.srdesign.User;
import edu.ycp.cs481.srdesign.controllers.AddPhotoController;
import edu.ycp.cs481.srdesign.controllers.GetAllPhotosController;

/**
 * Servlet implementation class
 */
@WebServlet("/main")
@MultipartConfig
public class AddPhoto extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    @Override
	protected void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
    	response.setStatus(HttpServletResponse.SC_OK);
		request.getRequestDispatcher("/main.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//TODO retrieve photo data 
		/********************************************/
		
		Part filePart = request.getPart("uploadFile"); // Retrieves <input type="file" name="file">
		
		if(filePart != null){
 
			String filename = getFilename(filePart);
			InputStream filecontent = filePart.getInputStream();
			
			//do math
			/*Photo newPhoto = new Photo(filename, filecontent);
			Photo newPhoto = new Photo();
			newPhoto.setFileName(filename);
			newPhoto.setContent(filecontent);*/
			
			if (filename != null || filecontent != null){
				AddPhotoController controller = new AddPhotoController();
				controller.addPhoto(filename, filecontent);
				
				System.out.println(filename);
				
				System.out.println("Photo sent to database");
				
				GetAllPhotosController getCont = new GetAllPhotosController();
				ArrayList<File> temp = getCont.getAllPhotos();
				
			}else {//if not a valid photo of file
				System.out.println("Invalid Photo input");
			}
		}
		/********************************************/
		/*String f = request.getParameter("uploadFile").getBytes().toString();
		newPhoto.setImageFile(new File(f));
		//newPhoto.setImageFile(request.getParameter("file"));
		
		//add photo to database if valid photo
		if (newPhoto != null){
			AddPhotoController controller = new AddPhotoController();
			controller.addPhoto(newPhoto);
			System.out.println("add photo to database");
		}else {//if not a valid photo of file
			request.setAttribute("result", "invalid photo");
			this.doGet(request, response);
		}
		*/
	}

	
	private static String getFilename(Part part) {
	    for (String cd : part.getHeader("content-disposition").split(";")) {
	        if (cd.trim().startsWith("filename")) {
	            String filename = cd.substring(cd.indexOf('=') + 1).trim().replace("\"", "");
	            return filename.substring(filename.lastIndexOf('/') + 1).substring(filename.lastIndexOf('\\') + 1); // MSIE fix.
	        }
	    }
	    return null;
	}
}