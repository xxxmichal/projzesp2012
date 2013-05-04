package com.example.fuuu;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.Toast;

public class MyMapsActivity extends Activity {

}

//
// import com.google.android.gms.maps.
// import com.google.android.gms.maps.GeoPoint;
// import com.google.android.maps.MapActivity;
// import com.google.android.maps.MapController;
// import com.google.android.maps.MapView;
// import com.google.android.maps.Overlay;
//
// public class MyMapsActivity extends MapActivity
// {
//
// MapView mapView;
// MapController mapController;
// LocationManager locationManager;
// LocationListener locationListener;
// /** Called when the activity is first created. */
// @Override
// public void onCreate(Bundle savedInstanceState)
// {
// super.onCreate(savedInstanceState);
// setContentView(R.layout.main);
//
// mapView = (MapView) findViewById(R.id.mapView);
// // enable Street view by default
// mapView.setStreetView(true);
//
// // enable to show Satellite view
// // mapView.setSatellite(true);
//
// // enable to show Traffic on map
// // mapView.setTraffic(true);
//
// mapView.setBuiltInZoomControls(true);
//
// mapController = mapView.getController();
// mapController.setZoom(5);
//
//
// locationManager = (LocationManager)
// getSystemService(Context.LOCATION_SERVICE);
//
// locationListener = new GPSLocationListener();
//
// locationManager.requestLocationUpdates(
// LocationManager.GPS_PROVIDER,
// 0,
// 0,
// locationListener);
//
// Touchy t = new Touchy();
// List<Overlay> overlayList = mapView.getOverlays();
// overlayList.add(t);
//
// }
// @Override
// protected boolean isRouteDisplayed() {
// // TODO Auto-generated method stub
// return false;
// }
//
//
// class Touchy extends Overlay
// {
// public boolean onTap(GeoPoint point, MapView mapView)
// {
// Context contexto = mapView.getContext();
// String msg = "Latitude : " + point.getLatitudeE6()/1E6 + " - " +
// "Longitude : " + point.getLongitudeE6()/1E6;
//
// Toast toast = Toast.makeText(contexto, msg, Toast.LENGTH_SHORT);
// toast.show();
//
// return true;
// }
// }
//
//
// private class GPSLocationListener implements LocationListener
// {
// public void onLocationChanged(Location location)
// {
// if (location != null)
// {
// GeoPoint point = new GeoPoint(
// (int) (location.getLatitude() * 1E6),
// (int) (location.getLongitude() * 1E6));
//
// Toast.makeText(getBaseContext(),
// "Latitude: " + location.getLatitude() +
// " Longitude: " + location.getLongitude(),
// Toast.LENGTH_SHORT).show();
//
// mapController.animateTo(point);
// mapController.setZoom(5);
// mapView.invalidate();
// }
//
// if (location != null)
// {
// GeoPoint point=null;
// String address = ConvertPointToLocation(point);
// Toast.makeText(getBaseContext(), address, Toast.LENGTH_SHORT).show();
//
// }
//
//
// }
//
// public String ConvertPointToLocation(GeoPoint point) {
// String address = "";
// Geocoder geoCoder = new Geocoder(
// getBaseContext(), Locale.getDefault());
// try {
// List<Address> addresses = geoCoder.getFromLocation(
// point.getLatitudeE6() / 1E6,
// point.getLongitudeE6() / 1E6, 1);
//
// if (addresses.size() > 0) {
// for (int index = 0;
// index < addresses.get(0).getMaxAddressLineIndex(); index++)
// address += addresses.get(0).getAddressLine(index) + " ";
// }
// }
// catch (IOException e) {
// e.printStackTrace();
// }
//
// return address;
// }
//
// public void onProviderDisabled(String provider) {
// // TODO Auto-generated method stub
//
// }
//
// public void onProviderEnabled(String provider) {
// // TODO Auto-generated method stub
//
// }
//
// public void onStatusChanged(String provider, int status, Bundle extras) {
// // TODO Auto-generated method stub
//
// }
// }
//
// public void onProviderDisabled(String provider) {
// // TODO Auto-generated method stub
//
// }
//
// public void onProviderEnabled(String provider) {
// // TODO Auto-generated method stub
//
// }
//
// public void onStatusChanged(String provider, int status, Bundle extras) {
// // TODO Auto-generated method stub
//
// }
// }
