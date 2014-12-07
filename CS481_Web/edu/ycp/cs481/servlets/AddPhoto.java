package edu.ycp.cs481.servlets;


import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import edu.ycp.cs481.srdesign.HashTag;
import edu.ycp.cs481.srdesign.Photo;
import edu.ycp.cs481.srdesign.controllers.AddHashtagController;
import edu.ycp.cs481.srdesign.controllers.AddPhotoController;
import edu.ycp.cs481.srdesign.controllers.CheckHashtagExistController;
import edu.ycp.cs481.srdesign.controllers.HashToPhotoRelaController;

/**
 * Servlet implementation class
 */
@WebServlet("/AddPhoto")
@MultipartConfig
public class AddPhoto extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    @Override
	protected void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
    	response.setStatus(HttpServletResponse.SC_OK);
		request.getRequestDispatcher("/photo.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Part filePart = request.getPart("uploadFile"); // Retrieves <input type="file" name="file">
		String tagsUnparsed = request.getParameter("hashTags");
		
		ArrayList<String> tagsParsed = parseHashtags(tagsUnparsed);
		
		HttpSession session = request.getSession();
		
		int userID = (int)session.getAttribute("userID");
		
				/**Todo
				 * -send parsed hashtags to database
				 * -relate added hashtags to uploaded photo
				 */
		
		if(filePart != null){
			
			String filename = null; 
			filename = getFilename(filePart);
			InputStream filecontent = null;
			filecontent = filePart.getInputStream();
			
			if (filename != null && filecontent != null && filename != ""){
				AddPhotoController addPCont = new AddPhotoController();
				AddHashtagController addHCont = new AddHashtagController(); 
				HashToPhotoRelaController hTPCont = new HashToPhotoRelaController();
				CheckHashtagExistController hashtagexist = new CheckHashtagExistController();


				Photo nPhoto = new Photo();
				nPhoto.setFIS(filecontent);
				nPhoto.setFileLength(filecontent.available());
				nPhoto.setuserID(userID);
				try {
					
					int photoId = addPCont.addPhoto(nPhoto);
					
					
					for(int i=0; i<tagsParsed.size(); i++){
						HashTag tempH = new HashTag();
						tempH.sethashtagName(tagsParsed.get(i));
						//System.out.println(tagsParsed.get(i));
						//int hashID = addHCont.addHashtag(tempH);
						
						//hTPCont.addRelaHTP(hashID, photoId);
						
						// Either returns hashtag ID or 0 if it doesn't exist
						int hashtagid = hashtagexist.checkHashtagExistence(tagsParsed.get(i));
						// Hashtag does not exist
						//HashTag tempH = new HashTag();
						if(hashtagid == 0){
							//tempH.sethashtagName(tagsParsed.get(i));
							System.out.println(tempH);
							// 
							hashtagid = addHCont.addHashtag(tempH);
							//hTPCont.addRelaHTP(hashtagid, photoId);
							System.out.println("HASHTAG DOES NOT EXIST, CREATED ONE");
						} else {
							//tempH.sethashtagName(tagsParsed.get(i));
							System.out.println(tagsParsed.get(i));
							System.out.println("HASHTAG EXISTS, RELATING PHOTO TO HASHTAG ALREADY CREATED IN DATABASE"); 
							//hTPCont.addRelaHTP(hashtagid, photoId);
						}
						
						hTPCont.addRelaHTP(hashtagid, photoId);
						
					}
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					System.out.println("DID NOT WORK");
				}

				
				request.setAttribute("result", "true");
				//request.setAttribute("photoList", paths);
				request.getRequestDispatcher("/photo.jsp").forward(request, response); 
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
	
	private static ArrayList<String> parseHashtags(String tagString){
		String tmp;
		ArrayList<String> hashtags = new ArrayList<String>();
		while(tagString.length()>0){
			tmp = tagString.substring(tagString.lastIndexOf("#")+1);
			tagString = tagString.substring(0, tagString.lastIndexOf("#"));
			
			if(tmp.charAt(tmp.length()-1) == ' '){
				tmp = tmp.substring(0, tmp.length()-1);
			}
			
			hashtags.add(tmp);
			tmp = "";
		}
		
		return hashtags;
	}
}
