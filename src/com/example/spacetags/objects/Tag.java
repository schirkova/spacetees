package com.example.spacetags.objects;

public class Tag {
	//TODO add annotations for JSON mapping
	
	long time;
	double latitude;
	double longitude;
	String subject;
	String text;
	
	
	
	public Tag(double latitude, double longitude, long time, String subject,
			String text) {
		super();
		this.time = time;
		this.latitude = latitude;
		this.longitude = longitude;
		this.subject = subject;
		this.text = text;
	}
	
	public long getTime() {
		return time;
	}
	public double getLatitude() {
		return latitude;
	}
	public double getLongitude() {
		return longitude;
	}
	public String getSubject() {
		return subject;
	}
	public String getText() {
		return text;
	}

}
