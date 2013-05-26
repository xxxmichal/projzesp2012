package com.example.fakediya;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.view.Menu;
import android.widget.TextView;
import android.widget.Toast;

public class Triggers extends Activity{
	
	Context mc;
	
	public Triggers(Context c) {
		// TODO Auto-generated constructor stub
		this.mc = c;
	}
	/*
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_fake_diya);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_fake_diya, menu);
		return true;
	}*/
	
	public boolean dzienIGodzinaTest(int dzienPoczatkowy, int miesiacPoczatkowy, int rokPoczatkowy, int minutaPoczatkowy, int godzinaPoczatkowy,
			int dzienKoncowy, int miesiacKoncowy, int rokKoncowy, int minutaKoncowy, int godzinaKoncowy){
Calendar c = Calendar.getInstance();
System.out.println("hah4");
/*int d9 = c.getFirstDayOfWeek();
c.setFirstDayOfWeek(Calendar.MONDAY);
int d10 = Calendar.MONDAY;
*/

int dzienAktualny = c.get(Calendar.DAY_OF_MONTH);
int miesiacAktualny = c.get(Calendar.MONTH);//uwaga - styczen to miesiac numer 0, nie wiem czemu tak wymyslili, tak wiec np czerwiec to tutaj miesiac 5
int rokAktualny = c.get(Calendar.YEAR);
//int d8 = c.getFirstDayOfWeek();
int godzinaAktualny = c.get(Calendar.HOUR_OF_DAY);
int minutaAktualny = c.get(Calendar.MINUTE);
//int sekundaAktualny = c.get(Calendar.SECOND);
//int d3 = c.get(Calendar.DAY_OF_WEEK);
miesiacAktualny +=1;//zeby bylo 5-maj

//String dzienTygodnia = (String) android.text.format.DateFormat.format("EEEE", c.getTime()); 
//Toast.makeText(getApplicationContext(), "Maj " + Calendar.MAY,  Toast.LENGTH_SHORT).show();
System.out.println("Data pocz: " + dzienPoczatkowy +"." + miesiacPoczatkowy +"."+rokPoczatkowy+ ", czas: "+ godzinaPoczatkowy +":" + minutaPoczatkowy );
System.out.println("Data: " + dzienAktualny +"." + miesiacAktualny +"."+rokAktualny+ ", czas: "+ godzinaAktualny +":" + minutaAktualny );
System.out.println("Data kon: " + dzienKoncowy +"." + miesiacKoncowy +"."+rokKoncowy+ ", czas: "+ godzinaKoncowy +":" + minutaKoncowy);
//TextView text = (TextView) findViewById(R.id.textTime);
//text.setText(c.toString());
//text.setText("Data: " + d1 +"." + dq +"."+d5+ ", czas: "+ d2 +":" + d6 +":"+d7 + ", dzien tygodnia: "+ d3 +" czyli " + dzienTygodnia);

boolean czyDzienDobry = false;

System.out.println("hah5");
if(rokPoczatkowy <= rokAktualny && rokAktualny <= rokKoncowy)
if(miesiacPoczatkowy <= miesiacAktualny && miesiacAktualny <= miesiacKoncowy)
if(dzienPoczatkowy <= dzienAktualny && dzienAktualny <= dzienKoncowy)
czyDzienDobry = true;

System.out.println("dziendobry" + czyDzienDobry);

boolean czyGodzinaDobra = false;

if(czyDzienDobry){
if(dzienAktualny == dzienPoczatkowy){
if(godzinaPoczatkowy <= godzinaAktualny){
czyGodzinaDobra = true;
}
}
if(dzienAktualny == dzienKoncowy){
if(godzinaAktualny <= godzinaKoncowy){
czyGodzinaDobra = true;
}
}
if(dzienPoczatkowy < dzienAktualny || dzienAktualny < dzienKoncowy){
czyGodzinaDobra = true;
return true;
}
}

System.out.println("godzina dobra" + czyGodzinaDobra);

boolean czyMinutaDobra = false;

if(czyGodzinaDobra){
if(godzinaAktualny == godzinaPoczatkowy){
if(minutaPoczatkowy <= minutaAktualny){
czyMinutaDobra = true;
//return true;
}
}
else if(godzinaAktualny == godzinaKoncowy){
if(minutaAktualny <= minutaKoncowy){
czyMinutaDobra = true;
//return true;
}
}
else{
czyMinutaDobra=true;
}
//if(godzinaPoczatkowy < godzinaAktualny || godzinaAktualny < godzinaKoncowy){
//	czyMinutaDobra = true;
//return true;	
//}
}

System.out.println("godzina minuta" + czyMinutaDobra);

if(czyMinutaDobra){
return true;
}

return false;
/*
* if(godzinaPoczatkowy <= godzinaAktualny && godzinaAktualny <= godzinaKoncowy)
if(minutaPoczatkowy <= minutaAktualny && minutaAktualny <= minutaKoncowy){
System.out.println("hah6");
return true;
}*/
}
	
	public boolean dzienIGodzinaTest2(int dzienPoczatkowy, int miesiacPoczatkowy, int rokPoczatkowy, int minutaPoczatkowy, int godzinaPoczatkowy,
										int dzienKoncowy, int miesiacKoncowy, int rokKoncowy, int minutaKoncowy, int godzinaKoncowy){
		Calendar c = Calendar.getInstance();
		System.out.println("hah4");
		/*int d9 = c.getFirstDayOfWeek();
		c.setFirstDayOfWeek(Calendar.MONDAY);
		int d10 = Calendar.MONDAY;
		*/
		Calendar poczatkowa  = GregorianCalendar.getInstance();
		poczatkowa.set(GregorianCalendar.YEAR, rokPoczatkowy);
		poczatkowa.set(GregorianCalendar.MONTH, miesiacPoczatkowy-1);
		poczatkowa.set(GregorianCalendar.DAY_OF_MONTH, dzienPoczatkowy);
		poczatkowa.set(GregorianCalendar.HOUR_OF_DAY, godzinaPoczatkowy);
		poczatkowa.set(GregorianCalendar.MINUTE, minutaPoczatkowy);
		poczatkowa.set(GregorianCalendar.SECOND, 0);

		Calendar koncowa  = GregorianCalendar.getInstance();
		koncowa.set(GregorianCalendar.YEAR, rokKoncowy);
		koncowa.set(GregorianCalendar.MONTH, miesiacKoncowy-1);
		koncowa.set(GregorianCalendar.DAY_OF_MONTH, dzienKoncowy);
		koncowa.set(GregorianCalendar.HOUR_OF_DAY, godzinaKoncowy);
		koncowa.set(GregorianCalendar.MINUTE, minutaKoncowy);
		koncowa.set(GregorianCalendar.SECOND, 0);
		
		int dzienAktualny = c.get(Calendar.DAY_OF_MONTH);
		int miesiacAktualny = c.get(Calendar.MONTH);//uwaga - styczen to miesiac numer 0, nie wiem czemu tak wymyslili, tak wiec np czerwiec to tutaj miesiac 5
		int rokAktualny = c.get(Calendar.YEAR);
		//int d8 = c.getFirstDayOfWeek();
		int godzinaAktualny = c.get(Calendar.HOUR_OF_DAY);
		int minutaAktualny = c.get(Calendar.MINUTE);
		//int sekundaAktualny = c.get(Calendar.SECOND);
		//int d3 = c.get(Calendar.DAY_OF_WEEK);
		miesiacAktualny +=1;//zeby bylo 5-maj
		
		//String dzienTygodnia = (String) android.text.format.DateFormat.format("EEEE", c.getTime()); 
		//Toast.makeText(getApplicationContext(), "Maj " + Calendar.MAY,  Toast.LENGTH_SHORT).show();
		System.out.println("Data pocz: " + dzienPoczatkowy +"." + miesiacPoczatkowy +"."+rokPoczatkowy+ ", czas: "+ godzinaPoczatkowy +":" + minutaPoczatkowy );
		System.out.println("Data: " + dzienAktualny +"." + miesiacAktualny +"."+rokAktualny+ ", czas: "+ godzinaAktualny +":" + minutaAktualny );
		System.out.println("Data kon: " + dzienKoncowy +"." + miesiacKoncowy +"."+rokKoncowy+ ", czas: "+ godzinaKoncowy +":" + minutaKoncowy);
		//TextView text = (TextView) findViewById(R.id.textTime);
		//text.setText(c.toString());
		//text.setText("Data: " + d1 +"." + dq +"."+d5+ ", czas: "+ d2 +":" + d6 +":"+d7 + ", dzien tygodnia: "+ d3 +" czyli " + dzienTygodnia);
		
		
		if(poczatkowa.before(c) && koncowa.after(c))
			return true;
		
		return false;
	}
	
	
	public boolean dzienIGodzina(int idDIY){
		System.out.println("hah1");
		/*pobranie z bazy  przedzialow czasowych*/
		
		int dzienPoczatkowy=0;
		int dzienKoncowy=0;
		int miesiacPoczatkowy=0;
		int miesiacKoncowy=0;
		int rokPoczatkowy=0;
		int rokKoncowy=0;
		
		int godzinaPoczatkowy=0;
		int godzinaKoncowy=0;
		int minutaPoczatkowy=0;
		int minutaKoncowy=0;
		//int sekundaPoczatkowy=0;
		//int sekundaKoncowy=0;
		
		/*koniec pobierania*/
		
		
		System.out.println("hah4");
		Calendar c = Calendar.getInstance();
		
		/*int d9 = c.getFirstDayOfWeek();
		c.setFirstDayOfWeek(Calendar.MONDAY);
		int d10 = Calendar.MONDAY;
		*/
		
		
		int dzienAktualny = c.get(Calendar.DAY_OF_MONTH);
		int miesiacAktualny = c.get(Calendar.MONTH);//uwaga - styczen to miesiac numer 0, nie wiem czemu tak wymyslili, tak wiec np czerwiec to tutaj miesiac 5
		int rokAktualny = c.get(Calendar.YEAR);
		//int d8 = c.getFirstDayOfWeek();
		int godzinaAktualny = c.get(Calendar.HOUR_OF_DAY);
		int minutaAktualny = c.get(Calendar.MINUTE);
		//int sekundaAktualny = c.get(Calendar.SECOND);
		//int d3 = c.get(Calendar.DAY_OF_WEEK);
		
		
		//String dzienTygodnia = (String) android.text.format.DateFormat.format("EEEE", c.getTime()); 
		//Toast.makeText(getApplicationContext(), "Maj " + Calendar.MAY,  Toast.LENGTH_SHORT).show();
		

		//TextView text = (TextView) findViewById(R.id.textTime);
		//text.setText(c.toString());
		//text.setText("Data: " + d1 +"." + dq +"."+d5+ ", czas: "+ d2 +":" + d6 +":"+d7 + ", dzien tygodnia: "+ d3 +" czyli " + dzienTygodnia);
		System.out.println("hah5");
		if(rokPoczatkowy <= rokAktualny && rokAktualny <= rokKoncowy)
			if(miesiacPoczatkowy <= miesiacAktualny && miesiacAktualny <= miesiacKoncowy)
				if(dzienPoczatkowy <= dzienAktualny && dzienAktualny <= dzienKoncowy)
					if(godzinaPoczatkowy <= godzinaAktualny && godzinaAktualny <= godzinaKoncowy)
						if(minutaPoczatkowy <= minutaAktualny && minutaAktualny <= minutaKoncowy){
							System.out.println("hah6");
							return true;
						}
		
		return false;
	}
	
	public boolean dzienTygodniaIGodzina(int idDIY){
		
		//tu chyba w bazie bedzie zapisana lista dni tygodnia, ale to trzeba ustalic, dlatego nizej nic nie jest zrobione
		
		/*pobranie z bazy  przedzialow czasowych*/
		
		int dzienPoczatkowy=0;
		int dzienKoncowy=0;
		int miesiacPoczatkowy=0;
		int miesiacKoncowy=0;
		int rokPoczatkowy=0;
		int rokKoncowy=0;
		
		int godzinaPoczatkowy=0;
		int godzinaKoncowy=0;
		int minutaPoczatkowy=0;
		int minutaKoncowy=0;
		//int sekundaPoczatkowy=0;
		//int sekundaKoncowy=0;
		
		/*koniec pobierania*/
		
		Calendar c = Calendar.getInstance();
		
		/*int d9 = c.getFirstDayOfWeek();
		c.setFirstDayOfWeek(Calendar.MONDAY);
		int d10 = Calendar.MONDAY;
		*/
		
		
		int dzienAktualny = c.get(Calendar.DAY_OF_MONTH);
		int miesiacAktualny = c.get(Calendar.MONTH);//uwaga - styczen to miesiac numer 0, nie wiem czemu tak wymyslili, tak wiec np czerwiec to tutaj miesiac 5
		int rokAktualny = c.get(Calendar.YEAR);
		//int d8 = c.getFirstDayOfWeek();
		int godzinaAktualny = c.get(Calendar.HOUR_OF_DAY);
		int minutaAktualny = c.get(Calendar.MINUTE);
		//int sekundaAktualny = c.get(Calendar.SECOND);
		//int d3 = c.get(Calendar.DAY_OF_WEEK);
		
		
		//String dzienTygodnia = (String) android.text.format.DateFormat.format("EEEE", c.getTime()); 
		//Toast.makeText(getApplicationContext(), "Maj " + Calendar.MAY,  Toast.LENGTH_SHORT).show();
		

		//TextView text = (TextView) findViewById(R.id.textTime);
		//text.setText(c.toString());
		//text.setText("Data: " + d1 +"." + dq +"."+d5+ ", czas: "+ d2 +":" + d6 +":"+d7 + ", dzien tygodnia: "+ d3 +" czyli " + dzienTygodnia);
		
		if(rokPoczatkowy <= rokAktualny && rokAktualny <= rokKoncowy)
			if(miesiacPoczatkowy <= miesiacAktualny && miesiacAktualny <= miesiacKoncowy)
				if(dzienPoczatkowy <= dzienAktualny && dzienAktualny <= dzienKoncowy)
					if(godzinaPoczatkowy <= godzinaAktualny && godzinaAktualny <= godzinaKoncowy)
						if(minutaPoczatkowy <= minutaAktualny && minutaAktualny <= minutaKoncowy)
							return true;
		
		return false;
	}
	
	public boolean dzienTygodniaIGodzinaTest(boolean czyPon, boolean czyWt, boolean czySr, boolean czyCzw, boolean czyPia, boolean czySob, boolean czyNiedz,
												int minutaPoczatkowy, int godzinaPoczatkowy, int minutaKoncowy, int godzinaKoncowy){
		
		//tu chyba w bazie bedzie zapisana lista dni tygodnia, ale to trzeba ustalic, dlatego nizej nic nie jest zrobione
		
		Calendar c = Calendar.getInstance();
		
		/*int d9 = c.getFirstDayOfWeek();
		c.setFirstDayOfWeek(Calendar.MONDAY);
		int d10 = Calendar.MONDAY;
		*/
		
		
		int dzienAktualny = c.get(Calendar.DAY_OF_MONTH);
		int miesiacAktualny = c.get(Calendar.MONTH);//uwaga - styczen to miesiac numer 0, nie wiem czemu tak wymyslili, tak wiec np czerwiec to tutaj miesiac 5
		int rokAktualny = c.get(Calendar.YEAR);
		//int d8 = c.getFirstDayOfWeek();
		int godzinaAktualny = c.get(Calendar.HOUR_OF_DAY);
		int minutaAktualny = c.get(Calendar.MINUTE);
		//int sekundaAktualny = c.get(Calendar.SECOND);
		int dzienTygodniaAktualny = c.get(Calendar.DAY_OF_WEEK);
		
		Calendar poczatkowa  = GregorianCalendar.getInstance();
		poczatkowa.set(GregorianCalendar.YEAR, rokAktualny);
		poczatkowa.set(GregorianCalendar.MONTH, miesiacAktualny);
		poczatkowa.set(GregorianCalendar.DAY_OF_MONTH, dzienAktualny);
		poczatkowa.set(GregorianCalendar.HOUR_OF_DAY, godzinaPoczatkowy);
		poczatkowa.set(GregorianCalendar.MINUTE, minutaPoczatkowy);
		poczatkowa.set(GregorianCalendar.SECOND, 0);

		Calendar koncowa  = GregorianCalendar.getInstance();
		koncowa.set(GregorianCalendar.YEAR, rokAktualny);
		koncowa.set(GregorianCalendar.MONTH, miesiacAktualny);
		koncowa.set(GregorianCalendar.DAY_OF_MONTH, dzienAktualny);
		koncowa.set(GregorianCalendar.HOUR_OF_DAY, godzinaKoncowy);
		koncowa.set(GregorianCalendar.MINUTE, minutaKoncowy);
		koncowa.set(GregorianCalendar.SECOND, 0);
		
		
		
		System.out.println("Akrualny " + dzienTygodniaAktualny + " Pon " + Calendar.MONDAY + " wt "+ Calendar.TUESDAY + " sr "+ Calendar.WEDNESDAY + " czw "+ Calendar.THURSDAY + " piat "+ Calendar.FRIDAY + " sob "+ Calendar.SATURDAY + " niedz "+ Calendar.SUNDAY);
		
		//String dzienTygodnia = (String) android.text.format.DateFormat.format("EEEE", c.getTime()); 
		//Toast.makeText(getApplicationContext(), "Maj " + Calendar.MAY,  Toast.LENGTH_SHORT).show();
		
		ArrayList<Integer> czyDni = new ArrayList<Integer>();
		
		if(czyPon){czyDni.add(Calendar.MONDAY);}if(czyWt){czyDni.add(Calendar.TUESDAY);}if(czySr){czyDni.add(Calendar.WEDNESDAY);}
		if(czyCzw){czyDni.add(Calendar.THURSDAY);}if(czyPia){czyDni.add(Calendar.FRIDAY);}if(czySob){czyDni.add(Calendar.SATURDAY);}
		if(czyNiedz){czyDni.add(Calendar.SUNDAY);}
		
		boolean dzienTygodniaDobry = false;
		
		for(Integer iterator : czyDni){
			System.out.println("dzien dodany " + iterator);
			if(iterator.intValue() == dzienTygodniaAktualny)
				dzienTygodniaDobry = true;
		}
		
		System.out.println(dzienTygodniaDobry);
		
		System.out.println(poczatkowa.get(Calendar.YEAR) + " " + c.get(Calendar.YEAR) + " " +koncowa.get(Calendar.YEAR) + " " +poczatkowa.get(Calendar.MONTH) + " " +c.get(Calendar.MONTH) + " " +koncowa.get(Calendar.MONTH) + " " +
							poczatkowa.get(Calendar.DAY_OF_MONTH) + " " +c.get(Calendar.DAY_OF_MONTH) + " " +koncowa.get(Calendar.DAY_OF_MONTH) + " " +poczatkowa.get(Calendar.HOUR_OF_DAY) + " " +c.get(Calendar.HOUR_OF_DAY) + " " +koncowa.get(Calendar.HOUR_OF_DAY) + " " +
								poczatkowa.get(Calendar.MINUTE) + " " +c.get(Calendar.MINUTE) + " " +koncowa.get(Calendar.MINUTE) + " " +poczatkowa.get(Calendar.SECOND) + " " +c.get(Calendar.SECOND) + " " +koncowa.get(Calendar.SECOND));
		
		
		if(dzienTygodniaDobry)
			if(poczatkowa.before(c) && koncowa.after(c))
				return true;
		
		
		return false;
	}
	
	
	public boolean czyWifiWlaczone(int idDIY){
		
		WifiManager wifimanager = (WifiManager) mc.getSystemService(Context.WIFI_SERVICE);
		
		if(wifimanager.isWifiEnabled()==true)
			return true;
		
		//wifimanager.getConnectionInfo();
		//wifimanager.getConnectionInfo().getSSID(); //pobiera ssid danej sieci czyli nazwe chyba
		
		return false;
		
	}
	
	public boolean czyWifiPodloczoneDoDanejSieciTest(String SSID_szukane){
		
		/*pobranie z bazy*/
		
		//String SSID_szukane = "";
		//String BSSID_szukane = "";
		
		/*koniec pobierania*/
		
		WifiManager wifimanager = (WifiManager) mc.getSystemService(Context.WIFI_SERVICE);
		
		try{
			if(wifimanager.isWifiEnabled()==true){
				String SSID_obecne = wifimanager.getConnectionInfo().getSSID();
				SSID_obecne = "\"" + SSID_obecne + "\"";
				System.out.println(SSID_obecne + " "+ SSID_szukane);
				//Toast.makeText(getApplicationContext(), SSID_obecne +" "+ SSID_szukane,  Toast.LENGTH_SHORT).show();
				System.out.println("myka");
				//String BSSID_obecne = wifimanager.getConnectionInfo().getBSSID();
				if(SSID_szukane.equals(SSID_obecne) == true){
					System.out.println("myk");
					return true;
					
				}
				//else if (BSSID_szukane.equals(BSSID_obecne) == true){
				//	return true;
				//}
			}
		}
		catch(Exception e){Toast.makeText(getApplicationContext(), e.toString(),  Toast.LENGTH_SHORT).show();}
		
		//wifimanager.getConnectionInfo();
		//wifimanager.getConnectionInfo().getSSID(); //pobiera ssid danej sieci czyli nazwe chyba
		
		return false;
		
	}
	
	
	public boolean czyWifiPodloczoneDoDanejSieci(int idDIY){
		
		/*pobranie z bazy*/
		
		String SSID_szukane = "";
		String BSSID_szukane = "";
		
		/*koniec pobierania*/
		
		WifiManager wifimanager = (WifiManager) getSystemService(Context.WIFI_SERVICE);
		
		if(wifimanager.isWifiEnabled()==true){
			String SSID_obecne = wifimanager.getConnectionInfo().getSSID();
			String BSSID_obecne = wifimanager.getConnectionInfo().getBSSID();
			if(SSID_szukane.equals(SSID_obecne) == true){
				return true;
			}
			else if (BSSID_szukane.equals(BSSID_obecne) == true){
				return true;
			}
		}
		//wifimanager.getConnectionInfo();
		//wifimanager.getConnectionInfo().getSSID(); //pobiera ssid danej sieci czyli nazwe chyba
		
		return false;
		
	}
	
	public boolean czyWDanymMiejscu(int idDIY){
		
		/*pobranie z bazy*/
		
		double latitude_szukane = 0;
		double longitude_szukane = 0;
		int promien_szukane = 0;
		
		/*koniec pobierania*/
		
		
		LocationManager locationManager;
		locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
		String providerStr = LocationManager.NETWORK_PROVIDER;//nie trzeba odswiezac samemu, zzera mniej baterii niz gps
		//String providerStr = LocationManager.PASSIVE_PROVIDER;
		int a=0;
		
		locationManager.requestSingleUpdate(LocationManager.NETWORK_PROVIDER, PendingIntent.getService(getApplicationContext(), a, getIntent(), a));
		locationManager.requestSingleUpdate(LocationManager.NETWORK_PROVIDER, PendingIntent.getService(getApplicationContext(), a, getIntent(), a));
		//to wyzej dwa razy, bo czasami nie odswieza od razu
		Location location = locationManager.getLastKnownLocation(providerStr);
		//location.set(new Location(providerStr));
		//locationManager.requestSingleUpdate(LocationManager.NETWORK_PROVIDER, PendingIntent.getService(getApplicationContext(), a, getIntent(), a));
		//location = locationManager.getLastKnownLocation(providerStr);
		double latitude = location.getLatitude();//n-s
		double longitude = location.getLongitude();//e-w
		
		//location.getSpeed();//predkosc
		//location.toString();
		//Toast.makeText(getApplicationContext(), location.getProvider(),  Toast.LENGTH_SHORT).show();
		//TextView text = (TextView) findViewById(R.id.textGPS);
    	//text.setText(location.toString());
		
		//jesli roznica pomiedzy wspolrzednymi jest mniejsza niz promien, to true
		
		if(Math.abs(latitude-latitude_szukane) < promien_szukane && Math.abs(longitude-longitude_szukane) < promien_szukane){
			return true;
		}
		
		
		
    	/* ponizej sekcja odpowiadajaca za tlumaczenie wspolrzednych na adres
    	 * 
    	Geocoder geocoder;
    	
    	geocoder = new Geocoder(getApplicationContext(), Locale.getDefault());
    	
            String result = "Geolocation address:\n";
            try {
                List<Address> addresses = geocoder.getFromLocation(latitude, longitude, 1);          
                for (Address address : addresses) {
                    for (int i = 0, j = address.getMaxAddressLineIndex(); i <= j; i++) {
                        result += address.getAddressLine(i) + "\n";
                    }
                    result += "\n\n";
                }
            } catch (IOException e) {
            	Toast.makeText(getApplicationContext(), e.toString(),  Toast.LENGTH_SHORT).show();
            }
         text.append("\n");
         text.append("Wspoltrzedne: " + latitude + " " + longitude + " i adres "+ result);
		*/
		
		return false;
		
	}
	
		public boolean czyWDanymMiejscuTest(double latitude_szukane, double longitude_szukane, double promien_szukane){
		
		/*pobranie z bazy*/
		/*
		double latitude_szukane = 0;
		double longitude_szukane = 0;
		int promien_szukane = 0;
		
		/*koniec pobierania*/
		
			System.out.println("qwe1");
		LocationManager locationManager;
		locationManager = (LocationManager) mc.getSystemService(Context.LOCATION_SERVICE);
		String providerStr = LocationManager.NETWORK_PROVIDER;//nie trzeba odswiezac samemu, zzera mniej baterii niz gps
		//String providerStr = LocationManager.PASSIVE_PROVIDER;
		int a=0;
		System.out.println("qwe2");
		locationManager.requestSingleUpdate(LocationManager.NETWORK_PROVIDER, PendingIntent.getService(mc.getApplicationContext(), a, getIntent(), a));
		locationManager.requestSingleUpdate(LocationManager.NETWORK_PROVIDER, PendingIntent.getService(mc.getApplicationContext(), a, getIntent(), a));
		//to wyzej dwa razy, bo czasami nie odswieza od razu
		System.out.println("qwe3");
		Location location = locationManager.getLastKnownLocation(providerStr);
		System.out.println("qwe4");
		//location.set(new Location(providerStr));
		//locationManager.requestSingleUpdate(LocationManager.NETWORK_PROVIDER, PendingIntent.getService(getApplicationContext(), a, getIntent(), a));
		//location = locationManager.getLastKnownLocation(providerStr);
		double latitude = location.getLatitude();//n-s
		double longitude = location.getLongitude();//e-w
		System.out.println("qwe5");
		//location.getSpeed();//predkosc
		//location.toString();
		//Toast.makeText(getApplicationContext(), location.getProvider(),  Toast.LENGTH_SHORT).show();
		//TextView text = (TextView) findViewById(R.id.textGPS);
    	//text.setText(location.toString());
		
		//jesli roznica pomiedzy wspolrzednymi jest mniejsza niz promien, to true
		
		if(Math.abs(latitude-latitude_szukane) < promien_szukane && Math.abs(longitude-longitude_szukane) < promien_szukane){
			System.out.println("qwe6");
			return true;
		}
		System.out.println("qwe0");
		return false;
		
	}
	
}
