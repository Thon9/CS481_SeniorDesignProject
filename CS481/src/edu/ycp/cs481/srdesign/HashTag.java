package edu.ycp.cs481.srdesign;

import java.io.Serializable;

public class HashTag implements Serializable {

	private static final long serialVersionUID = 1L;
	
	// Each hash tag will have an ID to be associated with, user accounts will store the IDs of each hash tag they are
	// following, and the ID will be used to display what the hash tag actually is
	private int ID;
	
}