package com.example.fakediya3;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.AudioManager;
import android.net.Uri;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.view.Menu;
import android.widget.TextView;
import android.widget.Toast;
import com.example.fakediya3.R;

public class Actions  extends Activity{

	Context mc;
	
	public Actions(Context c) {
		// TODO Auto-generated constructor stub
		this.mc = c;
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_fake_diya, menu);
		return true;
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
		
		WifiManager wifimanager = (WifiManager) mc.getSystemService(Context.WIFI_SERVICE);
		
		if(wifimanager.isWifiEnabled()==false){
			
			wifimanager.setWifiEnabled(true);
			
		}
		
		if(wifimanager.isWifiEnabled()==true){
			
			/*pobieram z bazy*/
			
			String SSID_szukane = "";
			String BSSID_szukane = "";
			
			
			/*koniec pobierania*/
			
			int warunek = 0;
		
			int siec=-1;

			for(WifiConfiguration iterator : wifimanager.getConfiguredNetworks()){
				
				if(iterator.SSID.equals(SSID_szukane)){//nie wiem czmeu equals zawsze mi zwraca flase, dlatego nizej sztucznie sa ustawione id sieci i warunek
					siec = iterator.networkId;
					warunek = 1;
				}
				
				/*albo szukanie po bssid, nie wiem co lepsze, chyba bssid, bo jak sie zmieni nazwa to dupa, a bssid to cos w rodzaju adresu mac sieci
				 * 
				 * if(iterator.BSSID.equals(BSSID_szukane)){
				 * 	siec = iterator.networkID;
				 * 	warunek = 1;
				 * }
				 * 
				 * 
				 * */
			}

			if(warunek == 1 && siec >= 0){
				wifimanager.enableNetwork(siec, false);//true oznacza, ze jesli jest sie podlaczonym do jakiejs sieci, a podlaczenie do sieci o id siec
														// sie nie powiedzie, to wifi jest rozlaczane, jesli false, to zostaje jak bylo
				return true;
			}
		}
		
		return false;
	}
	
	public boolean glosnoscWibracje(int idDIY){
		
		AudioManager audiomanager = (AudioManager) mc.getSystemService(Context.AUDIO_SERVICE);
		audiomanager.setRingerMode(AudioManager.RINGER_MODE_VIBRATE);//tryb dzwonka (cos ala profil na starych nokiach) tu akurat wibracje
		
		return true;
	}
	
	public boolean glosnoscDzwiek(int idDIY){
		
		/*pobieram z bazy*/
		
		int glosnosc_zadana = 0;
		
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
		    PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(), 0, intent, 0);
		    notification.setLatestEventInfo(getApplicationContext(), notificationTitle_pobrany, notificationText_pobrany, pendingIntent);
	    }
	    
	    else{
	    	
	    	Intent intent = null;
	    	PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(), 0, intent, 0);
		    notification.setLatestEventInfo(getApplicationContext(), notificationTitle_pobrany, notificationText_pobrany, pendingIntent);
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
