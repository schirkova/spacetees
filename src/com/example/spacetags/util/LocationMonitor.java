package com.example.spacetags.util;


import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;


public class LocationMonitor implements LocationListener {
	
	private static final long  MIN_TIME = 60000;
	private static final long MIN_DISTANCE = 10;
	private static final String LOG_TAG = "LocationListener";
	
	LocationManager locationManager;
	Criteria criteria;
	private String mProvider = null;
	
	public LocationMonitor (Context context) {
		locationManager = (LocationManager) context.getApplicationContext().getSystemService(Context.LOCATION_SERVICE);
		
		criteria = new Criteria();
		criteria.setAccuracy(Criteria.ACCURACY_LOW);
		criteria.setPowerRequirement(Criteria.POWER_LOW);
		
		mProvider = locationManager.getBestProvider(criteria, true);
		
		locationManager.requestLocationUpdates(mProvider, MIN_TIME, MIN_DISTANCE, this);
	}

	@Override
	public void onLocationChanged(Location arg0) {
		// TODO Auto-generated method stub
		// FIXME need to add map redraw
		
	}

	@Override
	public void onProviderDisabled(String arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onProviderEnabled(String arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStatusChanged(String arg0, int arg1, Bundle arg2) {
		// TODO Auto-generated method stub
		
	}
  
	
	public void removeUpdates() {
		locationManager.removeUpdates(this);
		Log.d(LOG_TAG, "removing location updates");
	}
	
	
	public Location getLocation() {
		try {
			
			Location location = locationManager.getLastKnownLocation(mProvider);
			
			if (location != null) {
				Log.d(LOG_TAG, "location: " + location.getLatitude() + ", " + location.getLongitude());
				return location;
			} else {
				return null;
			}

		} catch(Exception e) {
			Log.e(LOG_TAG,"Couldn't retrieve location:", e);
			return null;
		}
	}
}
