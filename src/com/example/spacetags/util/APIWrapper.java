package com.example.spacetags.util;

import java.util.ArrayList;
import java.util.List;

import com.example.spacetags.objects.Tag;

public class APIWrapper {

	public static void storeTag(Tag tag) {
		//TODO pass as JSON to the server to store
	}
	
	public static List<Tag> getTags(double lat, double lon, int zoom, int max) {
		ArrayList<Tag> results = new ArrayList<Tag>();
		//TODO retrieve as JSON from the api a list of tags around the point (lat, lon), upto max
		
		//TODO add clustering here or futher up?
		
		//TODO remove this dummy data
		Tag dummy = new Tag(0, lat, lon, "test", "dgdjhfgsjf");
		results.add(dummy);
		//END dummy data
		
		return results;
	}
}
