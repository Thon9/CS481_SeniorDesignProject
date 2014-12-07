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
import edu.ycp.cs481.srdesign.controllers.AddHashtagController;
import edu.ycp.cs481.srdesign.controllers.CheckHashtagExistController;
import edu.ycp.cs481.srdesign.controllers.GetHashtagsFromPhotoController;
import edu.ycp.cs481.srdesign.controllers.GetPhotoByIdController;
import edu.ycp.cs481.srdesign.controllers.HashToPhotoRelaController;
import edu.ycp.cs481.srdesign.controllers.deleteHashtagFromPhotoController;


@WebServlet("/editPhoto/*")
public class EditPhoto extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		int imageID = getHashID(request);
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
			System.out.println("tagIDs:	"+tags.get(i).gethashtagName()+" "+tags.get(i).gethashtagID());
			tagStrings.add(tags.get(i).gethashtagName());
		}
		
		request.setAttribute("pID", imageID);
		/**
		 * use pID to send back pID later
		 */
		request.setAttribute("photo", imagePath);
		request.setAttribute("tagList", tags);
		response.setStatus(HttpServletResponse.SC_OK);
		request.getRequestDispatcher("/EditPhoto.jsp").forward(request, response);
	}
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int photoId  = getHashID(request);
		
		String tagsUnparsed = request.getParameter("hashTags");
		
		
		System.out.println(photoId);
		System.out.println(tagsUnparsed);
		
		
		ArrayList<String> tagsParsed = parseHashtags(tagsUnparsed);
		AddHashtagController addHCont = new AddHashtagController();
		HashToPhotoRelaController hTPCont = new HashToPhotoRelaController();
		
		for(int i=0; i<tagsParsed.size(); i++){
			HashTag tempH = new HashTag();
			tempH.sethashtagName(tagsParsed.get(i));
			System.out.println(tagsParsed.get(i));
			CheckHashtagExistController hashtagexist = new CheckHashtagExistController();
			int hashID;
			try{
				hashID = hashtagexist.checkHashtagExistence(tagsParsed.get(i));
				
				if(hashID == 0){
					System.out.println(tempH);
					hashID = addHCont.addHashtag(tempH);
					System.out.println("HASHTAG DOES NOT EXIST, CREATED ONE");
				} else {
					System.out.println(tagsParsed.get(i));
					System.out.println("HASHTAG EXISTS, RELATING PHOTO TO HASHTAG ALREADY CREATED IN DATABASE"); 
				}
				hTPCont.addRelaHTP(hashID, photoId);
			}catch(Exception e){
				e.printStackTrace();
			}			
		}
		 doGet(request, response);
		//response.setStatus(HttpServletResponse.SC_OK);
		//request.getRequestDispatcher("/EditPhoto.jsp/"+photoId);//.forward(request, response);
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
