package edu.ycp.cs481.servlets;


import java.io.FileInputStream;
import java.io.IOException;


import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import edu.ycp.cs481.srdesign.Photo;
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
		
		Part filePart = request.getPart("uploadFile"); // Retrieves <input type="file" name="file">
		
		if(filePart != null){
			String filename = null; 
			filename = getFilename(filePart);
			
			FileInputStream filecontent = null;
			filecontent =  (FileInputStream) filePart.getInputStream();
			
			if (filename != null && filecontent != null && filename != ""){
				AddPhotoController controller = new AddPhotoController();


				//Photo nPhoto = new Photo();

				
				//nPhoto.setInStream(filecontent);
			
				
				try {
					controller.addPhoto(filename, filecontent,filePart.getSize());
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					System.out.println("DID NOT WORK");
				}
				/*
				GetAllPhotosController getCont = new GetAllPhotosController();
				List<Photo> temp = getCont.getAllPhotos();
				
				List<String> paths = new ArrayList<String>();
				
				for(int i=0; i<temp.size(); i++){
					System.out.println(temp.get(i).getFile().toPath());
					paths.add("image/"+i);
				}
				*/
				request.setAttribute("result", "true");
				//request.setAttribute("photoList", paths);
				request.getRequestDispatcher("/main.jsp").forward(request, response); 
			}else {//if not a valid photo of file
				System.out.println("Invalid Photo input");
				request.setAttribute("result", "false");
				
			}
		}
	}

	private static String getFilename(Part part) {
	    for (String cd : part.getHeader("content-disposition").split(";")) {
	        if (cd.trim().startsWith("filename")) {
	            String filename = cd.substring(cd.indexOf('=') + 1).trim().replace("\"", "");
	            return filename.substring(filename.lastIndexOf('/') + 1).substring(filename.lastIndexOf('\\') + 1);
	        }
	    }
	    return null;
	}
}
