package com.diyapp.kreator2;

import com.diyapp.kreator2.R;
import com.diyapp.kreator2.R.layout;
import com.diyapp.kreator2.R.menu;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMapOptions;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;

public class DiyMapActivity extends Activity {

	static final LatLng WARSAW = new LatLng(52.136, 21.003);
	
	static double var_latitude = -1;
	static double var_longtitude = -1;
	static LatLng var_lastpos = null;
	
	private GoogleMap map;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		var_latitude = getIntent().getExtras().getDouble("latitude");
		var_longtitude = getIntent().getExtras().getDouble("longtitude");
		var_lastpos = new LatLng(var_latitude, var_longtitude);

		Log.d("com.diyapp.kreator.DiyMapActivity", "onCreated called");

		setContentView(R.layout.activity_diy_map);
		
		GoogleMapOptions options = new GoogleMapOptions();

		map = ((MapFragment) getFragmentManager().findFragmentById(R.id.map))
				.getMap();
		
		if (var_latitude > 0.0) {
			Marker kiel = map.addMarker(new MarkerOptions()
					.position(var_lastpos)
					.title("Last")
					.snippet("Last selected position")
					.icon(BitmapDescriptorFactory
							.fromResource(R.drawable.ic_launcher)));
			map.moveCamera(CameraUpdateFactory.newLatLngZoom(var_lastpos, 15));
		}
		map.setMyLocationEnabled(true);

		// Zoom in, animating the camera.
		map.animateCamera(CameraUpdateFactory.zoomTo(10), 2000, null);
		CameraPosition p = map.getCameraPosition();
	}

	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    // Handle item selection
	    switch (item.getItemId()) {
	    case R.id.menu_select_position:
			CameraPosition p = map.getCameraPosition();
			Intent intent = new Intent();
			intent.putExtra("latitude", p.target.latitude);
			intent.putExtra("longtitude", p.target.longitude);
			setResult(RESULT_OK, intent);
			finish();
	        return true;
	    default:
	        return super.onOptionsItemSelected(item);
	    }
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_diy_map, menu);
		return true;
	}
}