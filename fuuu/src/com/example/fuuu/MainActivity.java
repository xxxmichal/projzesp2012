package com.example.fuuu;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMapOptions;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.UiSettings;
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

public class MainActivity extends Activity {

	static final LatLng HAMBURG = new LatLng(53.558, 9.927);
	static final LatLng KIEL = new LatLng(53.551, 9.993);
	static final LatLng WARSAW = new LatLng(52.136, 21.003);
	private GoogleMap map;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		if (BuildConfig.DEBUG) {
			Log.d(Constants.LOG, "onCreated called debug");
		}

		Log.d(Constants.LOG, "onCreated called");

		setContentView(R.layout.activity_main);
		
		GoogleMapOptions options = new GoogleMapOptions();
//		map = ((MapFragment) getFragmentManager().findFragmentById(R.id.map))
//				.getMap();

		map = ((MapFragment) getFragmentManager().findFragmentById(R.id.map))
				.getMap();
		
		Marker hamburg = map.addMarker(new MarkerOptions().position(HAMBURG)
				.title("Hamburg"));
		Marker warsaw = map.addMarker(new MarkerOptions().position(WARSAW)
				.title("Warsaw"));
		Marker kiel = map.addMarker(new MarkerOptions()
				.position(KIEL)
				.title("Kiel")
				.snippet("Kiel is cool")
				.icon(BitmapDescriptorFactory
						.fromResource(R.drawable.ic_launcher)));
		map.setMyLocationEnabled(true);
		// Move the camera instantly to hamburg with a zoom of 15.
		map.moveCamera(CameraUpdateFactory.newLatLngZoom(HAMBURG, 15));
		map.moveCamera(CameraUpdateFactory.newLatLngZoom(WARSAW, 15));

		// Zoom in, animating the camera.
		map.animateCamera(CameraUpdateFactory.zoomTo(10), 2000, null);
		CameraPosition p = map.getCameraPosition();
		Log.v("fuuu", p.target.toString());
	}

	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    // Handle item selection
	    switch (item.getItemId()) {
	    case R.id.menu_settings:
			CameraPosition p = map.getCameraPosition();
			Log.v("fuuu showCurrentPos", p.target.toString());
	        return true;
	    default:
	        return super.onOptionsItemSelected(item);
	    }
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	public void onClick(View v) {
		Log.d(Constants.LOG, Integer.toString(v.getId()));
		switch (v.getId()) {
		case R.id.checkBox1:
			// handle button A click;
			CheckBox cb1 = (CheckBox) findViewById(R.id.checkBox1);
			Log.d(Constants.LOG, "onClick R.id.checkBox1 " + cb1.isChecked());
			break;
		case R.id.button1:
			Log.d(Constants.LOG, "onClick R.id.button1");
			Intent nextScreen = new Intent(getApplicationContext(),
					NextActivity.class);
			startActivity(nextScreen);
			break;
		case R.id.button2:
			Log.d(Constants.LOG, "onClick R.id.button2");
			Intent intent = new Intent(Intent.ACTION_SEND);
			String[] recipientArray = { "abc", "cba" };
			intent.putExtra(Intent.EXTRA_EMAIL, recipientArray);
			intent.setType("text/plain");
			startActivity(Intent.createChooser(intent, "hello"));
			break;
		default:
			throw new RuntimeException("Unknow button ID");
		}
	}

}
