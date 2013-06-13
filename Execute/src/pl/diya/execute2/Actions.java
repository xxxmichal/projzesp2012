package pl.diya.execute2;

import com.diyapp.lib.DiyDbAdapter;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.media.AudioManager;
import android.net.Uri;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.view.Menu;
import android.widget.TextView;
import android.widget.Toast;
import pl.diya.execute2.R;

public class Actions  extends Activity{

	Context mc;
	
	static DiyDbAdapter mDbHelper;
	
	public Actions(Context c) {
		// TODO Auto-generated constructor stub
		this.mc = c;
	}
	
	public void setDiyDbAdapter(DiyDbAdapter adapter){
		mDbHelper = adapter;
	}
	
	
	public boolean wlaczWifi(int idDIY){
		
		WifiManager wifimanager = (WifiManager) mc.getSystemService(Context.WIFI_SERVICE);
		
		
		if(wifimanager.isWifiEnabled()==true){
			return true;
		}	
		
		wifimanager.setWifiEnabled(true);
		return true;
		
	
	}
	
	public boolean wylaczWifi(int idDIY){
		WifiManager wifimanager = (WifiManager) mc.getSystemService(Context.WIFI_SERVICE);
		
		
		if(wifimanager.isWifiEnabled()==false){
			return true;
		}	
		
		wifimanager.setWifiEnabled(false);
		return true;
	}
	
	
	public boolean podlaczWifiDoDanejSieci(int idDIY){
		
		/*pobieram z bazy*/
		
		String SSID_szukane = "";
		
		Cursor c = mDbHelper.fetchAllDiy();
		if (c.moveToFirst()) {
			do {
				for (String column : DiyDbAdapter.COLUMNS) {
					
					if(c.getInt(c.getColumnIndexOrThrow(DiyDbAdapter.KEY_ROWID)) == idDIY){
						//if(column.equals(DiyDbAdapter.KEY_TRIGGER_WIFI)) {
							SSID_szukane = c.getString(c.getColumnIndexOrThrow(DiyDbAdapter.KEY_ACTION_WIFI_PARAM_SSID));

							System.out.println(" trigger czy podlaczono do danej sieci wifi "+SSID_szukane);
						
						//}
					}
					
				}
			} while (c.moveToNext());
		}
		
		/*koniec pobierania*/
		
		WifiManager wifimanager = (WifiManager) mc.getSystemService(Context.WIFI_SERVICE);
		
		if(wifimanager.isWifiEnabled()==false){
			
			wifimanager.setWifiEnabled(true);
			
		}
		
		if(wifimanager.isWifiEnabled()==true){
			
			int warunek = 0;
			
			int siec=-1;

			for(WifiConfiguration iterator : wifimanager.getConfiguredNetworks()){
				
				if(iterator.SSID.equals(SSID_szukane)){
					siec = iterator.networkId;
					warunek = 1;
				}
			}

			if(warunek == 1 && siec >= 0){
				boolean ew = wifimanager.enableNetwork(siec, true);//true oznacza, ze jesli jest sie podlaczonym do jakiejs sieci, a podlaczenie do sieci o id siec
														// sie nie powiedzie, to wifi jest rozlaczane, jesli false, to zostaje jak bylo
				if(!ew){
					wifimanager.setWifiEnabled(false);
				}
				
				return ew;
			}
			
			if(warunek == 0){
				wifimanager.setWifiEnabled(false);
				return false;
			}
		}
		
		return false;
	}
	
	public boolean podlaczWifiDoDanejSieciTest(String SSID_szukane){
		
		WifiManager wifimanager = (WifiManager) mc.getSystemService(Context.WIFI_SERVICE);
		
		if(wifimanager.isWifiEnabled()==false){
			
			wifimanager.setWifiEnabled(true);
			
		}
		
		if(wifimanager.isWifiEnabled()==true){
			
			/*pobieram z bazy*/
			
			//String SSID_szukane = "";
			//String BSSID_szukane = "";
			
			
			/*koniec pobierania*/
			
			int warunek = 0;
		
			int siec=-1;

			for(WifiConfiguration iterator : wifimanager.getConfiguredNetworks()){
				
				if(iterator.SSID.equals(SSID_szukane)){//nie wiem czmeu equals zawsze mi zwraca flase, dlatego nizej sztucznie sa ustawione id sieci i warunek
					System.out.println("bla1");
					siec = iterator.networkId;
					System.out.println("bla2 " + iterator.networkId);
					warunek = 1;
					System.out.println("bla3" + warunek);
				}
				
				/*albo szukanie po bssid, nie wiem co lepsze, chyba bssid, bo jak sie zmieni nazwa to dupa, a bssid to cos w rodzaju adresu mac sieci
				 */ 
				System.out.println(iterator.SSID + " " +  SSID_szukane);
				
				// if(iterator.BSSID.equals(BSSID_szukane)){
				//	 siec = iterator.networkId;
				//	 warunek = 1;
				// }

			}

			if(warunek == 1 && siec >= 0){
				boolean ew = wifimanager.enableNetwork(siec, true);//true oznacza, ze jesli jest sie podlaczonym do jakiejs sieci, a podlaczenie do sieci o id siec
														// sie nie powiedzie, to wifi jest rozlaczane, jesli false, to zostaje jak bylo
				if(!ew){
					wifimanager.setWifiEnabled(false);
				}
				
				return ew;
			}
			
			if(warunek == 0){
				wifimanager.setWifiEnabled(false);
				return false;
			}
		}
		
		return false;
	}
	
	public boolean glosnoscWibracje(int idDIY){
		System.out.println("celina1");
		AudioManager audiomanager = (AudioManager) mc.getSystemService(Context.AUDIO_SERVICE);
		audiomanager.setRingerMode(AudioManager.RINGER_MODE_VIBRATE);//tryb dzwonka (cos ala profil na starych nokiach) tu akurat wibracje
		System.out.println("celina2");
		return true;
	}
	
	public boolean glosnoscDzwiek(int idDIY){
		
		/*pobieram z bazy*/
		
		int glosnosc_zadana = 0;
		System.out.println("basia1");
		Cursor c = mDbHelper.fetchAllDiy();
		System.out.println("basia1.5");
		if(c.getInt(c.getColumnIndexOrThrow(DiyDbAdapter.KEY_ROWID)) == idDIY){
			System.out.println("basia1.9");
			glosnosc_zadana = c.getInt(c.getColumnIndexOrThrow(DiyDbAdapter.KEY_ACTION_SOUNDPROFILE_PARAM_VOLUME));
			System.out.println("basia2");
			System.out.println(glosnosc_zadana);
		}

		
		/*koniec pobierania*/
		System.out.println("basia3");
		AudioManager audiomanager = (AudioManager) mc.getSystemService(Context.AUDIO_SERVICE);
		audiomanager.setRingerMode(AudioManager.RINGER_MODE_NORMAL);//tryb dzwonka (cos ala profil na starych nokiach) tu akurat normalny
		System.out.println("basia4");
		int maxVol = audiomanager.getStreamMaxVolume(AudioManager.STREAM_RING);
		System.out.println("basia5");
		int flags=0;
		if(glosnosc_zadana >= maxVol){
			audiomanager.setStreamVolume(AudioManager.STREAM_RING, maxVol, flags);
			System.out.println("basia6");
			return true;
		}
		else{
			audiomanager.setStreamVolume(AudioManager.STREAM_RING, glosnosc_zadana, flags);
			System.out.println("basia7");
			return true;
		}
		
	}
	
	public boolean glosnoscDzwiekTest(int glosnosc_zadana){
		
		/*pobieram z bazy*/
		
		//int glosnosc_zadana = 0;
		
		/*koniec pobierania*/
		
		AudioManager audiomanager = (AudioManager) mc.getSystemService(Context.AUDIO_SERVICE);
		audiomanager.setRingerMode(AudioManager.RINGER_MODE_NORMAL);//tryb dzwonka (cos ala profil na starych nokiach) tu akurat normalny
		
		int maxVol = audiomanager.getStreamMaxVolume(AudioManager.STREAM_RING);
		//int obecny = audiomanager.getStreamVolume(AudioManager.STREAM_RING);
		
		int flags=0;
		if(glosnosc_zadana >= maxVol){
			audiomanager.setStreamVolume(AudioManager.STREAM_RING, maxVol, flags);
			return true;
		}
		else{
			audiomanager.setStreamVolume(AudioManager.STREAM_RING, glosnosc_zadana, flags);
			return true;
		}
	}	
	
	
	public boolean wyswietlPowiadomienie(int idDIY){
		
		/*pobieram z bazy*/
		
		String tickerText_pobrany = "";
		String notificationTitle_pobrany = "";
		String notificationText_pobrany = "";
		int czyWWW = 0; //0-nie wyswietla strony, 1 - wyswietla www
		String adresStrony_pobrany = "";
		
		Cursor c = mDbHelper.fetchAllDiy();
		if (c.moveToFirst()) {
			do {
				for (String column : DiyDbAdapter.COLUMNS) {
					
					if(c.getInt(c.getColumnIndexOrThrow(DiyDbAdapter.KEY_ROWID)) == idDIY){
						//SSID_szukane = c.getString(c.getColumnIndexOrThrow(DiyDbAdapter.KEY_TRIGGER_WIFI_PARAM_SSID));
						tickerText_pobrany = c.getString(c.getColumnIndexOrThrow(DiyDbAdapter.KEY_ACTION_NOTIFICATION_PARAM_TEXT));
						notificationTitle_pobrany = c.getString(c.getColumnIndexOrThrow(DiyDbAdapter.KEY_ACTION_NOTIFICATION_PARAM_TEXT));
						notificationText_pobrany = c.getString(c.getColumnIndexOrThrow(DiyDbAdapter.KEY_ACTION_NOTIFICATION_PARAM_TITLE));
					}
					
				}
			} while (c.moveToNext());
		}
		
		/*koniec pobierania*/
		
		NotificationManager notificationManager = (NotificationManager) mc.getSystemService(Context.NOTIFICATION_SERVICE);
		
		int icon = R.drawable.ic_launcher;

	    long when = 0;

	    Notification notification = new Notification(icon, tickerText_pobrany, when);
	    

	    notification.flags |= Notification.FLAG_AUTO_CANCEL;//powiadomienie zniknie gdy kliniemy na nie
	      /*
	       * Flagi powiadomie�

			Kolejnym wa�nym elementem s� flagi naszego powiadomienia. Odpowiadaj� one za kilka r�nych ustawie�. Oto niekt�re z nich:

	    		Notification.FLAG_AUTO_CANCEL � sprawia, �e powiadomienie znika zaraz po klikni�ciu,
	    		Notification.FLAG_NO_CLEAR � powiadomienie nie zostanie usuni�te po klikni�ciu w przycisk Clear/Wyczy��,
	    		Notification.FLAG_FOREGROUND_SERVICE � powiadomienie kt�re przychodzi od aktualnie dzia�aj�cego serwisu,
	    		Notification.FLAG_ONGOING_EVENT � powiadomienie przychodz�ce z ci�gle jeszcze dzia�aj�cego �r�d�a (oczekuj�ce po��czenie telefoniczne).

	       */
	    
	    if(czyWWW == 1){
	    
		    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(adresStrony_pobrany));
		    PendingIntent pendingIntent = PendingIntent.getActivity(mc.getApplicationContext(), 0, intent, 0);
		    notification.setLatestEventInfo(mc.getApplicationContext(), notificationTitle_pobrany, notificationText_pobrany, pendingIntent);
	    }
	    
	    else{
	    	
	    	Intent intent = null;
	    	PendingIntent pendingIntent = PendingIntent.getActivity(mc.getApplicationContext(), 0, intent, 0);
		    notification.setLatestEventInfo(mc.getApplicationContext(), notificationTitle_pobrany, notificationText_pobrany, pendingIntent);
	    }
	      
//			notification.defaults = Notification.DEFAULT_VIBRATE;
//			przy wibracji nalezy pamietac o dodaniu do manifestu <uses-permission android:name="android.permission.VIBRATE" />
		long tab[] = {400, 200, 440}; //400 ms dziala 200 ms nie dziala 440 ms dziala
		notification.vibrate = tab;
		notification.flags |= Notification.FLAG_SHOW_LIGHTS;//uzywanie diody led
		notification.ledARGB = Color.RED; 
		notification.ledOnMS = 800;
		notification.ledOffMS = 400;
		notification.defaults |= Notification.DEFAULT_SOUND;
//			notification.sound = Uri.withAppendedPath(Audio.Media.INTERNAL_CONTENT_URI, "1");
//			notification.sound = Uri.parse("file:///sdcard/.ringtonetrimmer/ringtones/ring.mp3"); -- dowolny dzwiek z tel
		
		int MY_NOTIFICATION = 1;
		
		notificationManager.notify(MY_NOTIFICATION, notification);
		
		return true;
	}
	
	public boolean wyswietlPowiadomienieTest(String tickerText_pobrany, String notificationTitle_pobrany, String notificationText_pobrany, int czyWWW, String adresStrony_pobrany){
		
		/*pobieram z bazy*/
		/*
		String tickerText_pobrany = "";
		String notificationTitle_pobrany = "";
		String notificationText_pobrany = "";
		int czyWWW = 0; //0-nie wyswietla strony, 1 - wyswietla www
		String adresStrony_pobrany = "";
		
		/*koniec pobierania*/
		
		NotificationManager notificationManager = (NotificationManager) mc.getSystemService(Context.NOTIFICATION_SERVICE);
		
		int icon = R.drawable.ic_launcher;
	    //String tickerText = "Powiadomionko";
	    long when = 0;
	    //Notification notification = new Notification(icon, tickerText, when);
	    Notification notification = new Notification(icon, tickerText_pobrany, when);
	    
	    //notification.number = 3;
	    notification.flags |= Notification.FLAG_AUTO_CANCEL;//powiadomienie zniknie gdy kliniemy na nie
	      /*
	       * Flagi powiadomie�

			Kolejnym wa�nym elementem s� flagi naszego powiadomienia. Odpowiadaj� one za kilka r�nych ustawie�. Oto niekt�re z nich:

	    		Notification.FLAG_AUTO_CANCEL � sprawia, �e powiadomienie znika zaraz po klikni�ciu,
	    		Notification.FLAG_NO_CLEAR � powiadomienie nie zostanie usuni�te po klikni�ciu w przycisk Clear/Wyczy��,
	    		Notification.FLAG_FOREGROUND_SERVICE � powiadomienie kt�re przychodzi od aktualnie dzia�aj�cego serwisu,
	    		Notification.FLAG_ONGOING_EVENT � powiadomienie przychodz�ce z ci�gle jeszcze dzia�aj�cego �r�d�a (oczekuj�ce po��czenie telefoniczne).

	       */
	    //String notificationTitle = "Takie sobie";
	    //String notificationText = "Klikni�cie w��cza iSODa w przegl�darce";
	    //Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.isod.ee.pw.edu.pl"));
	    
	    if(czyWWW == 1){
	    
		    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(adresStrony_pobrany));
		    PendingIntent pendingIntent = PendingIntent.getActivity(mc.getApplicationContext(), 0, intent, 0);
		    notification.setLatestEventInfo(mc.getApplicationContext(), notificationTitle_pobrany, notificationText_pobrany, pendingIntent);
	    }
	    
	    else{
	    	
	    	Intent intent = null;
	    	PendingIntent pendingIntent = PendingIntent.getActivity(mc.getApplicationContext(), 0, intent, 0);
		    notification.setLatestEventInfo(mc.getApplicationContext(), notificationTitle_pobrany, notificationText_pobrany, pendingIntent);
	    }
	      
//			notification.defaults = Notification.DEFAULT_VIBRATE;
//			przy wibracji nalezy pamietac o dodaniu do manifestu <uses-permission android:name="android.permission.VIBRATE" />
		long tab[] = {400, 200, 440}; //400 ms dziala 200 ms nie dziala 440 ms dziala
		notification.vibrate = tab;
		notification.flags |= Notification.FLAG_SHOW_LIGHTS;//uzywanie diody led
		notification.ledARGB = Color.RED; 
		notification.ledOnMS = 800;
		notification.ledOffMS = 400;
		notification.defaults |= Notification.DEFAULT_SOUND;
//			notification.sound = Uri.withAppendedPath(Audio.Media.INTERNAL_CONTENT_URI, "1");
//			notification.sound = Uri.parse("file:///sdcard/.ringtonetrimmer/ringtones/ring.mp3"); -- dowolny dzwiek z tel
		
		int MY_NOTIFICATION = 1;
		
		notificationManager.notify(MY_NOTIFICATION, notification);
		
		return true;
	}
	
}
