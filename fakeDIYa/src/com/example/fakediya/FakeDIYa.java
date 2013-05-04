package com.example.fakediya;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Configuration;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.media.AudioManager;
import android.net.Uri;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fakediya2.R;

import com.diyapp.lib.DiyDbAdapter;

public class FakeDIYa extends Activity {
	private DiyDbAdapter mDbHelper;
	
	private BroadcastReceiver the_receiver = new BroadcastReceiver() {
		@Override
		public void onReceive(Context c, Intent i) {
			int orientation = getBaseContext().getResources()
					.getConfiguration().orientation;
			if (orientation == Configuration.ORIENTATION_PORTRAIT) {
				Toast.makeText(getBaseContext(), "I'm still standing.",
						Toast.LENGTH_SHORT).show();
			} else if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
				Toast.makeText(getBaseContext(),
						"Help! I've fallen and I can't get up.",
						Toast.LENGTH_SHORT).show();
			} else {
				Toast.makeText(getBaseContext(), "?!#$%!?", Toast.LENGTH_SHORT)
						.show();
			}
		}
	};
	private IntentFilter filter = new IntentFilter(
			Intent.ACTION_CONFIGURATION_CHANGED);

	@Override
	protected void onPause() {

		this.unregisterReceiver(the_receiver);
		super.onPause();
	}

	@Override
	protected void onResume() {
		this.registerReceiver(the_receiver, filter);
		super.onResume();
	}
	
	public void showDb(View v) {
		Intent i = new Intent(this, ListDiys.class);
		startActivity(i);
	}

	// Location savedLocation = null;
	// LocationListener locationListener = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

//----------
// hello files
		
		File file = new File(
				"/data/data/com.diyapp.kreator2/files/test_kreator.txt");
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
//------

		setContentView(R.layout.activity_fake_diya);

		Button buttonTime = (Button) findViewById(R.id.buttonTime);
		Button buttonIsWifi = (Button) findViewById(R.id.buttonIsWifi);
		// Button buttonGPS = null;
		Button buttonWifiOn = (Button) findViewById(R.id.buttonWifiOn);
		Button buttonVolume = (Button) findViewById(R.id.buttonVolume);
		Button buttonNotif = (Button) findViewById(R.id.buttonNotif);
		Button buttonAlaGPS = (Button) findViewById(R.id.buttonGPS);

		buttonNotif.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

				int icon = R.drawable.ic_launcher;
				String tickerText = "Powiadomionko";
				long when = 0;
				Notification notification = new Notification(icon, tickerText,
						when);

				notification.number = 3;
				notification.flags |= Notification.FLAG_AUTO_CANCEL;// powiadomienie
																	// zniknie
																	// gdy
																	// kliniemy
																	// na nie
				/*
				 * Flagi powiadomie�
				 * 
				 * Kolejnym wa�nym elementem s� flagi naszego powiadomienia.
				 * Odpowiadaj� one za kilka r�nych ustawie�. Oto niekt�re z
				 * nich:
				 * 
				 * Notification.FLAG_AUTO_CANCEL � sprawia, �e powiadomienie
				 * znika zaraz po klikni�ciu, Notification.FLAG_NO_CLEAR �
				 * powiadomienie nie zostanie usuni�te po klikni�ciu w przycisk
				 * Clear/Wyczy��, Notification.FLAG_FOREGROUND_SERVICE �
				 * powiadomienie kt�re przychodzi od aktualnie dzia�aj�cego
				 * serwisu, Notification.FLAG_ONGOING_EVENT � powiadomienie
				 * przychodz�ce z ci�gle jeszcze dzia�aj�cego �r�d�a (oczekuj�ce
				 * po��czenie telefoniczne).
				 */
				String notificationTitle = "Takie sobie";
				String notificationText = "Klikni�cie w��cza iSODa w przegl�darce";
				Intent intent = new Intent(Intent.ACTION_VIEW, Uri
						.parse("http://www.isod.ee.pw.edu.pl"));
				PendingIntent pendingIntent = PendingIntent.getActivity(
						getApplicationContext(), 0, intent, 0);
				notification.setLatestEventInfo(getApplicationContext(),
						notificationTitle, notificationText, pendingIntent);

				// notification.defaults = Notification.DEFAULT_VIBRATE;
				// przy wibracji nalezy pamietac o dodaniu do manifestu
				// <uses-permission android:name="android.permission.VIBRATE" />
				long tab[] = { 400, 200, 440 }; // 400 ms dziala 200 ms nie
												// dziala 440 ms dziala
				notification.vibrate = tab;
				notification.flags |= Notification.FLAG_SHOW_LIGHTS;// uzywanie
																	// diody led
				notification.ledARGB = Color.RED;
				notification.ledOnMS = 800;
				notification.ledOffMS = 400;
				notification.defaults |= Notification.DEFAULT_SOUND;
				// notification.sound =
				// Uri.withAppendedPath(Audio.Media.INTERNAL_CONTENT_URI, "1");
				// notification.sound =
				// Uri.parse("file:///sdcard/.ringtonetrimmer/ringtones/ring.mp3");
				// -- dowolny dzwiek z tel

				int MY_NOTIFICATION = 1;

				notificationManager.notify(MY_NOTIFICATION, notification);

				Toast.makeText(getApplicationContext(),
						"Wyswietlenie powiadomienia", Toast.LENGTH_SHORT)
						.show();
			}
		});

		buttonAlaGPS.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				LocationManager locationManager;
				locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
				String providerStr = LocationManager.NETWORK_PROVIDER;// nie
																		// trzeba
																		// odswiezac
																		// samemu,
																		// zzera
																		// mniej
																		// baterii
																		// niz
																		// gps
				// String providerStr = LocationManager.PASSIVE_PROVIDER;
				Location location = locationManager
						.getLastKnownLocation(providerStr);

				double latitude = location.getLatitude();// n-s
				double longitude = location.getLongitude();// e-w

				location.getSpeed();// predkosc
				location.toString();
				Toast.makeText(getApplicationContext(), location.getProvider(),
						Toast.LENGTH_SHORT).show();
				TextView text = (TextView) findViewById(R.id.textGPS);
				text.setText(location.toString());

				Geocoder geocoder;

				geocoder = new Geocoder(getApplicationContext(), Locale
						.getDefault());

				String result = "Geolocation address:\n";
				try {
					List<Address> addresses = geocoder.getFromLocation(
							latitude, longitude, 1);
					for (Address address : addresses) {
						for (int i = 0, j = address.getMaxAddressLineIndex(); i <= j; i++) {
							result += address.getAddressLine(i) + "\n";
						}
						result += "\n\n";
					}
				} catch (IOException e) {
					Toast.makeText(getApplicationContext(), e.toString(),
							Toast.LENGTH_SHORT).show();
				}
				text.append("\n");
				text.append("Wspoltrzedne: " + latitude + " " + longitude
						+ " i adres " + result);

			}
		});

		buttonTime.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Calendar c = Calendar.getInstance();
				/*
				 * int d1 = c.getTime().getDate();//zwraca dzien miesiaca int d4
				 * = c.getTime().getMonth();//zwraca miesiac int d5 =
				 * c.getTime().getYear();//zwraca rok int d2 =
				 * c.getTime().getHours();//zwraca godzine int d6 =
				 * c.getTime().getMinutes();//zwraca minute int d7 =
				 * c.getTime().getSeconds();//zwraca sekunde int d3 =
				 * c.getTime().getDay();//zwraca dzien tygodnia
				 */

				int d9 = c.getFirstDayOfWeek();
				c.setFirstDayOfWeek(Calendar.MONDAY);
				int d10 = Calendar.MONDAY;

				int d1 = c.get(Calendar.DAY_OF_MONTH);
				int d4 = c.get(Calendar.MONTH);// uwaga - styczen to miesiac
												// numer 0, nie wiem czemu tak
												// wymyslili, tak wiec np
												// czerwiec to tutaj miesiac 5
				int d5 = c.get(Calendar.YEAR);
				int d8 = c.getFirstDayOfWeek();
				int d2 = c.get(Calendar.HOUR_OF_DAY);
				int d6 = c.get(Calendar.MINUTE);
				int d7 = c.get(Calendar.SECOND);
				int d3 = c.get(Calendar.DAY_OF_WEEK);
				int dq = d4 + 1;

				String dzienTygodnia = (String) android.text.format.DateFormat
						.format("EEEE", c.getTime());
				Toast.makeText(getApplicationContext(), "Maj " + Calendar.MAY,
						Toast.LENGTH_SHORT).show();
				// Toast.makeText(getApplicationContext(), "Data: " + d1 +"." +
				// d4 +"."+d5+ ", czas: "+ d2 +":" + d6 +":"+d7 +
				// ", dzien tygodnia: "+ d3 + dzienTygodnia + ", cos:" + d9+" "+
				// d8 +" "+ d10, Toast.LENGTH_SHORT).show();
				// Toast.makeText(getApplicationContext(), "Data: " + d1+
				// ", czas: "+ d2 + ", dzien tygodnia: "+ d3,
				// Toast.LENGTH_SHORT).show();

				TextView text = (TextView) findViewById(R.id.textTime);
				text.setText(c.toString());
				// text.setText("Data: " + d1 +"." + dq +"."+d5+ ", czas: "+ d2
				// +":" + d6 +":"+d7 + ", dzien tygodnia: "+ d3 +" czyli " +
				// dzienTygodnia);
			}
		});

		buttonIsWifi.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				// System.out.println("sru raz");
				Toast.makeText(getApplicationContext(), "WIfi",
						Toast.LENGTH_SHORT).show();
				// System.out.println("sru dwa");
				WifiManager wifimanager = (WifiManager) getSystemService(Context.WIFI_SERVICE);
				// System.out.println("sru trzy");
				// wifimanager.isWifiEnabled();

				// Toast.makeText(getApplicationContext(), "Data: " + d1 +"." +
				// d4 +"."+d5+ ", czas: "+ d2 +":" + d6 +":"+d7 +
				// ", dzien tygodnia: "+ d3 + dzienTygodnia + ", cos:" + d9+" "+
				// d8 +" "+ d10, Toast.LENGTH_SHORT).show();
				// Toast.makeText(getApplicationContext(), "Data: " + d1+
				// ", czas: "+ d2 + ", dzien tygodnia: "+ d3,
				// Toast.LENGTH_SHORT).show();

				TextView text = (TextView) findViewById(R.id.textIsWifi);
				// System.out.println("sru razy");
				if (wifimanager.isWifiEnabled() == true) {
					// System.out.println("sru cztery");
					text.setText("true");
				} else {
					// System.out.println("sru cztery i p�");
					text.setText("false");
				}

				wifimanager.getConnectionInfo();
				wifimanager.getConnectionInfo().getSSID(); // pobiera ssid danej
															// sieci czyli nazwe
															// chyba

				text.setText(wifimanager.getConnectionInfo().toString());
				// text.setText(wifimanager.getConnectionInfo().toString());
			}
		});

		/*
		 * buttonGPS.setOnClickListener(new OnClickListener() {
		 * 
		 * 
		 * @Override public void onClick(View v) { // TODO Auto-generated method
		 * stub //Toast.makeText(getApplicationContext(), "WIfi",
		 * Toast.LENGTH_SHORT).show(); System.out.println("sru raz");
		 * locationListener = new LocationListener() { public void
		 * onStatusChanged(String provider, int status, Bundle extras) {} public
		 * void onProviderEnabled(String provider) {}
		 * 
		 * public void onProviderDisabled(String provider) {}
		 * 
		 * public void onLocationChanged(Location location) {
		 * 
		 * if (savedLocation == null){ savedLocation =
		 * locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
		 * System.out.println("sru raz3.6"); } System.out.println("sru raz4"); }
		 * };
		 * 
		 * System.out.println("sru raz5"); locationManager = (LocationManager)
		 * getSystemService(Context.LOCATION_SERVICE);
		 * locationManager.requestLocationUpdates( LocationManager.GPS_PROVIDER,
		 * 3000, 2, locationListener); System.out.println("sru raz6");
		 * System.out.println("sru raz1"); TextView text = (TextView)
		 * findViewById(R.id.textGPS); text.setText(locationManager.toString());
		 * System.out.println("sru raz2"); text.setText(text.toString() +
		 * " saved location: " + savedLocation.toString());
		 * System.out.println("sru raz3");
		 * 
		 * if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER))
		 * //tvProvider.setText("GPS"); Toast.makeText(getApplicationContext(),
		 * "GPS", Toast.LENGTH_SHORT).show(); else
		 * //tvProvider.setText("GPS Disabled. Please, turn it on");
		 * Toast.makeText(getApplicationContext(),
		 * "GPS Disabled. Please, turn it on", Toast.LENGTH_SHORT).show(); }
		 * 
		 * //TextView text = (TextView) findViewById(R.id.textIsWifi);
		 * 
		 * 
		 * //text.setText(wifimanager.getConnectionInfo().toString()); });
		 */

		buttonWifiOn.setOnClickListener(new OnClickListener() {
			int licznik = 0;

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				// System.out.println("sru raz");
				// Toast.makeText(getApplicationContext(), "WIfi",
				// Toast.LENGTH_SHORT).show();
				// System.out.println("sru dwa");
				WifiManager wifimanager = (WifiManager) getSystemService(Context.WIFI_SERVICE);
				// System.out.println("sru trzy");
				// wifimanager.isWifiEnabled();

				// Toast.makeText(getApplicationContext(), "Data: " + d1 +"." +
				// d4 +"."+d5+ ", czas: "+ d2 +":" + d6 +":"+d7 +
				// ", dzien tygodnia: "+ d3 + dzienTygodnia + ", cos:" + d9+" "+
				// d8 +" "+ d10, Toast.LENGTH_SHORT).show();
				// Toast.makeText(getApplicationContext(), "Data: " + d1+
				// ", czas: "+ d2 + ", dzien tygodnia: "+ d3,
				// Toast.LENGTH_SHORT).show();

				TextView text = (TextView) findViewById(R.id.textIsWifi);
				// System.out.println("sru razy");

				if (wifimanager.isWifiEnabled() == true) {
					licznik++;

					System.out.println("sru cztery");
					if (licznik % 2 == 0) {
						// wifimanager.disconnect();
						wifimanager.setWifiEnabled(false);
						Toast.makeText(getApplicationContext(),
								"Wylaczanie wifi ", Toast.LENGTH_SHORT).show();
					} else {
						Toast.makeText(
								getApplicationContext(),
								"WIfi juz wlaczone. Wcisnij jeszcze raz by wylaczyc ",
								Toast.LENGTH_SHORT).show();
					}
					// text.setText("true");
				} else {
					System.out.println("sru cztery i p�");
					// text.setText("false");
					Toast.makeText(getApplicationContext(), "Wlaczam wIfi",
							Toast.LENGTH_SHORT).show();
					wifimanager.setWifiEnabled(true);
					System.out.println("sru cztery i trzy czwarte");
				}
				if (wifimanager.isWifiEnabled() == true) {
					System.out.println("sru piec");
					int warunek = 0;

					int siec = -1;

					for (WifiConfiguration iterator : wifimanager
							.getConfiguredNetworks()) {
						System.out.println("sru piec i jedna tzrecie");
						System.out.println(iterator.SSID);
						System.out.println("wynik "
								+ iterator.SSID.equals("" + "zacisze2" + ""));
						if (iterator.SSID.equals("Wifi-Rifi")) {// nie wiem
																// czmeu equals
																// zawsze mi
																// zwraca flase,
																// dlatego nizej
																// sztucznie sa
																// ustawione id
																// sieci i
																// warunek
							text.setText(text.getText() + iterator.toString());
							siec = iterator.networkId;
							warunek = 1;
							System.out.println("sru piec i pol");
							Toast.makeText(getApplicationContext(),
									"wifi rifi na id " + siec,
									Toast.LENGTH_SHORT).show();
						}
					}
					siec = 0;
					warunek = 1;
					System.out.println("sru piec i tzry piate");
					if (warunek == 1 && siec >= 0) {
						wifimanager.enableNetwork(siec, false);// true oznacza,
																// ze jesli jest
																// sie
																// podlaczonym
																// do jakiejs
																// sieci, a
																// podlaczenie
																// do sieci o id
																// siec
																// sie nie
																// powiedzie, to
																// wifi jest
																// rozlaczane,
																// jesli false,
																// to zostaje
																// jak bylo
						System.out.println("sru piec i 0.9");
					}
				}
				// wifimanager.getConnectionInfo();
				// wifimanager.getConnectionInfo().getSSID(); //pobiera ssid
				// danej sieci czyli nazwe chyba

				// text.setText("Wlaczam wifi");
				// text.setText(wifimanager.getConnectionInfo().toString());
			}
		});

		buttonVolume.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				// Toast.makeText(getApplicationContext(), "WIfi",
				// Toast.LENGTH_SHORT).show();
				System.out.println("sru vol 1");
				AudioManager audiomanager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
				// audiomanager.setRingerMode(AudioManager.RINGER_MODE_VIBRATE);//tryb
				// dzwonka (cos ala profil na starych nokiach) tu akurat
				// wibracje
				System.out.println("sru vol 2");
				// Toast.makeText(getApplicationContext(),
				// "Wlaczam profil normalny", Toast.LENGTH_SHORT).show();
				int maxVol = audiomanager
						.getStreamMaxVolume(AudioManager.STREAM_RING);
				System.out.println("sru vol 3 + maxVol " + maxVol);
				int obecny = audiomanager
						.getStreamVolume(AudioManager.STREAM_RING);
				System.out.println("sru vol 3.4 + obecny " + obecny);
				// Toast.makeText(getApplicationContext(), " " + maxSystem,
				// Toast.LENGTH_SHORT).show();
				int flags = 0;
				audiomanager.setStreamVolume(AudioManager.STREAM_RING, maxVol,
						flags);
				System.out.println("sru vol 4");
				Toast.makeText(getApplicationContext(),
						"Ustawiam max glosnosc systemowa", Toast.LENGTH_SHORT)
						.show();

				// TextView text = (TextView) findViewById(R.id.);
				// text.setText(wifimanager.getConnectionInfo().toString());
			}
		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_fake_diya, menu);
		return true;
	}

	/*
	 * @Override protected void onStop() {
	 * locationManager.removeUpdates(locationListener); super.onStop(); }
	 */
}
