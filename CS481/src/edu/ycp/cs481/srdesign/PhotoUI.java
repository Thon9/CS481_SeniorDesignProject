package edu.ycp.cs481.srdesign;

public class PhotoUI {
	String imagePath;
	String editImagePath;
	
	public PhotoUI(String image, String editImage){
		this.imagePath = image;
		this.editImagePath = editImage;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public String getEditImagePath() {
		return editImagePath;
	}

	public void setEditImagePath(String editImagePath) {
		this.editImagePath = editImagePath;
	}
}
