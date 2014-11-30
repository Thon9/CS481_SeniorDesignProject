

package edu.ycp.cs481.servlets;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

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
@WebServlet("/ShowGallery")
@MultipartConfig
public class ShowGallery extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    @Override
	protected void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
    	
    	System.out.println("Creating get all photos controller");
    	GetAllPhotosController getCont = new GetAllPhotosController();
    	
    	String subject = request.getParameter("hashTag");
    	
    	List<Photo> temp = getCont.getAllPhotos();
    	System.out.println(temp.size());
    	System.out.println("temp should contain all of the photos");
		List<String> paths = new ArrayList<String>();
		
		//show gallery normally
		
	
		if (subject == null || subject.isEmpty()){
			System.out.println("no search subject detect showing gallery normally...");
//			for(int i=0; i<temp.size(); i++){
//				System.out.println(temp.get(i).getFile().toPath());
//				paths.add("image/"+i);
//			}
		}
		
		//searching gallery 
		else {
			System.out.println("searching for "+subject+"...");
			ArrayList<Photo> gallery  = getCont.getAllPhotos();
			for (Photo photo : gallery){
				
				if (photo.getFile().getName().toString().contains(subject)){
					//remove the extension
					String path = photo.getFile().getName().toString();
					if (path.indexOf(".") > 0){
						path = path.substring(0, path.lastIndexOf("."));
					}
					paths.add("image/"+path);
					System.out.println("doGet found "+path);
				}
			}
		}
		
		request.setAttribute("photoList", paths);
		request.getRequestDispatcher("/gallery.jsp").forward(request, response); 
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
	}
	
}

