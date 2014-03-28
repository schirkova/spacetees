package com.example.spacetags.UI;

import com.example.spacetags.objects.Tag;
import com.example.spacetags.util.APIWrapper;
import com.example.spacetags.util.LocationMonitor;

import android.app.Activity;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/* create a tag */
public class TagActivity extends Activity {
	
	private Button mButton;
	private EditText mSubject;
	private EditText mMessage;
	LocationMonitor mLocationMonitor;
	
	public void onCreate(Bundle savedInstanceState) {
		 
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tag);
		
		mLocationMonitor = new LocationMonitor(this);

		mButton = (Button) findViewById(R.id.button);
 
		// add button listener
		mButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				mSubject = (EditText) findViewById(R.id.editTextSubject);
				mMessage = (EditText) findViewById(R.id.editTextMessage);
				
		        //get current location and time
		        Location location = mLocationMonitor.getLocation();
				Tag tag = new Tag(location.getLatitude(), location.getLongitude(),location.getTime(), mSubject.toString(), mMessage.toString());
				APIWrapper.storeTag(tag);
				
				//TODO - show confirmation, go to refreshed map view
			}
		});
		
	}
}
