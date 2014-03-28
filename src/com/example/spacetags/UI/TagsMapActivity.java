package com.example.spacetags.UI;


import java.util.ArrayList;
import java.util.List;

import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapController;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.ItemizedIconOverlay;
import org.osmdroid.views.overlay.ItemizedIconOverlay.OnItemGestureListener;
import org.osmdroid.views.overlay.OverlayItem;
import org.osmdroid.views.overlay.OverlayManager;
import org.osmdroid.views.overlay.mylocation.GpsMyLocationProvider;
import org.osmdroid.views.overlay.mylocation.MyLocationNewOverlay;

import com.example.spacetags.objects.Tag;
import com.example.spacetags.util.APIWrapper;
import com.example.spacetags.util.LocationMonitor;

import android.app.Activity;
import android.location.Location;
import android.os.Bundle;


/*open map around current location, pull in tags*/

public class TagsMapActivity extends Activity  {

	private static final int INITIAL_ZOOM = 15;
	private static final int MAX_TAGS = 15;
	
    private MapView         mMapView;
    private MapController   mMapController;
	private ItemizedOverlay<OverlayItem> mMyLocationOverlay;
	private ResourceProxy mResourceProxy;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tagsmap);

		LocationMonitor locationMonitor = new LocationMonitor(this);
		
		//init map
		mMapView = (MapView) findViewById(R.id.map_view);
		mMapView.setTileSource(TileSourceFactory.MAPNIK);
		mMapView.setBuiltInZoomControls(true);
		mMapController = mMapView.getController();
		mMapController.setZoom(15);
        
        
        //get current location and center on it
		Location currentLocation = locationMonitor.getLocation();
        GeoPoint currentLocationPoint = new GeoPoint(currentLocation.getLatitude(), currentLocation.getLongitude());
        mMapController.setCenter(currentLocationPoint);
    	mResourceProxy = new ResourceProxyImpl(getApplicationContext());
        
        //display near by tags
        List<Tag> surroundingTags = APIWrapper.getTags(currentLocation.getLatitude(), currentLocation.getLongitude(), INITIAL_ZOOM, MAX_TAGS);
    	ArrayList<OverlayItem> items = new ArrayList<OverlayItem>();
        for(Tag t : surroundingTags){
        	GeoPoint tag = new GeoPoint(t.getLatitude(), t.getLongitude());
            items.add(new OverlayItem(t.getSubject(), t.getText(), tag));
        }
        
    	this.mMyLocationOverlay = new ItemizedIconOverlay<OverlayItem>(items,
    		new ItemizedIconOverlay.OnItemGestureListener<OverlayItem>() {
    			@Override
    			public boolean onItemSingleTapUp(final int index, final OverlayItem item) {
    			Toast.makeText(this, item.getTitle() + " " + item.getSnippet(), Toast.LENGTH_LONG).show();
    			return true; 
    			}

    		}, mResourceProxy);
    	mMapView.getOverlays().add(this.mMyLocationOverlay);
    	
    	//TODO add overlay to capture double tap on map to fire off tag creation
	}

}
