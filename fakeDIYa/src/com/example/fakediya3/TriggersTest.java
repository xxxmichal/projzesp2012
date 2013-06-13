package com.example.fakediya3;

import android.app.Activity;
import android.content.Context;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;
import com.example.fakediya3.R;

public class TriggersTest extends Activity {

	Triggers tr;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_triggers_test);
		
		tr = new Triggers(this);
		
		Button buttonCzas = (Button)findViewById(R.id.button1);
		Button buttonTyg = (Button)findViewById(R.id.button2);
		Button buttonCzyWifi = (Button)findViewById(R.id.button3);
		Button buttonWifiSiec = (Button)findViewById(R.id.button4);
		Button buttonGPS = (Button)findViewById(R.id.button5);
		
		final Spinner spinner = (Spinner) findViewById(R.id.spinner1);
		/*dodanie do spinnera listy sieci zapisanych w urzadzeniu*/
		WifiManager wifimanager = (WifiManager) getSystemService(Context.WIFI_SERVICE);
		int liczbaSieci = 0;
		
		for(WifiConfiguration iterator : wifimanager.getConfiguredNetworks()){
			liczbaSieci++;
		}
		
		final String [] listaSieci = new String[liczbaSieci];
		int i = 0;
		for(WifiConfiguration iterator : wifimanager.getConfiguredNetworks()){
			listaSieci[i] = iterator.SSID.toString();
			i++;
		}
		
		ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, listaSieci);
			dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
			spinner.setAdapter(dataAdapter);
		
		
		buttonCzas.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				System.out.println("cos1");
				boolean czy;
				try {
					System.out.println("cos1.4");
					
					/*pobranie z TriggersTest  przedzialow czasowych*/
					
					
					TextView dzienPocz = (TextView) findViewById(R.id.editText1);
					TextView miesiacPocz = (TextView) findViewById(R.id.editText2);
					TextView rokPocz = (TextView) findViewById(R.id.editText4);
					TextView godzinaPocz = (TextView) findViewById(R.id.editText5);
					TextView minutaPocz = (TextView) findViewById(R.id.editText6);
					TextView dzienKon = (TextView) findViewById(R.id.editText3);
					TextView miesiacKon = (TextView) findViewById(R.id.editText7);
					TextView rokKon = (TextView) findViewById(R.id.editText8);
					TextView godzinaKon = (TextView) findViewById(R.id.editText9);
					TextView minutaKon = (TextView) findViewById(R.id.editText10);
					//if(! wzrostView.getText().toString().equals(""))
			    	//	wzrost = Integer.parseInt( wzrostView.getText().toString());
					
					int dzienPoczatkowy=Integer.parseInt( dzienPocz.getText().toString());
					int dzienKoncowy=Integer.parseInt( dzienKon.getText().toString());
					int miesiacPoczatkowy=Integer.parseInt( miesiacPocz.getText().toString());
					int miesiacKoncowy=Integer.parseInt( miesiacKon.getText().toString());
					int rokPoczatkowy=Integer.parseInt( rokPocz.getText().toString());
					int rokKoncowy=Integer.parseInt( rokKon.getText().toString());
					
					int godzinaPoczatkowy=Integer.parseInt( godzinaPocz.getText().toString());
					int godzinaKoncowy=Integer.parseInt( godzinaKon.getText().toString());
					int minutaPoczatkowy=Integer.parseInt( minutaPocz.getText().toString());
					int minutaKoncowy=Integer.parseInt( minutaKon.getText().toString());
					//int sekundaPoczatkowy=0;
					//int sekundaKoncowy=0;
					
					/*koniec pobierania*/
					//czy = tr.dzienIGodzina(cos);
					czy = tr.dzienIGodzinaTest2(dzienPoczatkowy, miesiacPoczatkowy, rokPoczatkowy, minutaPoczatkowy, godzinaPoczatkowy,
							dzienKoncowy, miesiacKoncowy, rokKoncowy, minutaKoncowy, godzinaKoncowy);
					System.out.println("cos2");
					Toast.makeText(getApplicationContext(), "" + czy, Toast.LENGTH_SHORT).show();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					System.out.println("cos0");
					//Toast.makeText(getApplicationContext(), "" + czy, Toast.LENGTH_SHORT).show();
				}
				Toast.makeText(getApplicationContext(), "dsf", Toast.LENGTH_SHORT).show();
				System.out.println("cos5");
			}
		});
		
		buttonTyg.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				CheckBox pon = (CheckBox) findViewById(R.id.checkBox1);
				CheckBox wt = (CheckBox) findViewById(R.id.checkBox2);
				CheckBox sr = (CheckBox) findViewById(R.id.checkBox3);
				CheckBox czw = (CheckBox) findViewById(R.id.checkBox4);
				CheckBox piat = (CheckBox) findViewById(R.id.checkBox5);
				CheckBox sob = (CheckBox) findViewById(R.id.checkBox6);
				CheckBox niedz = (CheckBox) findViewById(R.id.checkBox7);
				
				TextView mpo = (TextView) findViewById(R.id.editText12);
				TextView gpo = (TextView) findViewById(R.id.editText11);
				TextView mko = (TextView) findViewById(R.id.editText17);
				TextView gko = (TextView) findViewById(R.id.editText16);
				
				boolean czyPon = pon.isChecked();
				boolean czyWt = wt.isChecked();
				boolean czySr = sr.isChecked();
				boolean czyCzw = czw.isChecked();
				boolean czyPiat = piat.isChecked();
				boolean czySob = sob.isChecked();
				boolean czyNiedz = niedz.isChecked();
				
				int godzinaPoczatkowy=Integer.parseInt( gpo.getText().toString());
				int godzinaKoncowy=Integer.parseInt( gko.getText().toString());
				int minutaPoczatkowy=Integer.parseInt( mpo.getText().toString());
				int minutaKoncowy=Integer.parseInt( mko.getText().toString());
				
				boolean czy = tr.dzienTygodniaIGodzinaTest(czyPon, czyWt, czySr, czyCzw, czyPiat, czySob, czyNiedz, minutaPoczatkowy, godzinaPoczatkowy, minutaKoncowy, godzinaKoncowy);
			
				Toast.makeText(getApplicationContext(), "" + czy, Toast.LENGTH_SHORT).show();
				
			}
		});
		
		buttonCzyWifi.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				try{
				boolean czy = tr.czyWifiWlaczone(1);
				Toast.makeText(getApplicationContext(), ""+czy, Toast.LENGTH_SHORT).show();
				}
				catch(Exception e){
					Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();
				}
				//Toast.makeText(getApplicationContext(), "sfsef", Toast.LENGTH_SHORT).show();
			}
		});
		
		buttonWifiSiec.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				try{
					System.out.println("hyt1");
				String nazwaSieci = (String) listaSieci[(int)spinner.getSelectedItemId()];
				System.out.println("hyt2");
				boolean czy = tr.czyWifiPodloczoneDoDanejSieciTest(nazwaSieci);
				System.out.println("hyt3");
				Toast.makeText(getApplicationContext(), ""+czy, Toast.LENGTH_SHORT).show();
				System.out.println("hyt4");
	            System.out.println(czy);
				}
				catch(Exception e){Toast.makeText(getApplicationContext(), "eee" +e.toString(), Toast.LENGTH_SHORT).show();}
			}
		});
		
		
		/*
		buttonCzas.setOnClickListener(new OnClickListener(){
            public void onClick(View arg1){
            	
            	wzrost = 0;
            	waga = 0.0;
            	try{
            	if(! wzrostView.getText().toString().equals(""))
            		wzrost = Integer.parseInt( wzrostView.getText().toString());
            	else{wzrost = 0;}
            	if(! wagaView.getText().toString().equals(""))
            		waga = Double.parseDouble(wagaView.getText().toString());
            	else{waga = 0.0;}
            	}catch(Exception e){
            		Toast.makeText(getApplicationContext(), "Nie wprowadzono warto�ci!", Toast.LENGTH_SHORT).show();
            	}
            	
            	if(waga == 0.0 || wzrost == 0){
            		Toast.makeText(getApplicationContext(), "Nie wprowadzono warto�ci!", Toast.LENGTH_SHORT).show();
            	}            	
            	else if(radioPrzycisk.getText().equals("Kobieta")){
            		Intent intent = new Intent(getApplicationContext(), IdealneProporcjeKobiety.class);
            		//Toast.makeText(getApplicationContext(), "waga " + waga + " wzrost "+ wzrost, Toast.LENGTH_SHORT).show();
            		osoba = Kobieta.getKobieta();
            		osoba.setWaga(waga);
            		osoba.setWzrost(wzrost);
            		startActivityForResult(intent,1337);
            	}
            	
            }
	 });
		*/
		
		buttonGPS.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				try{
					System.out.println("nioh1");
				TextView ns = (TextView) findViewById(R.id.editText13);
				TextView ew = (TextView) findViewById(R.id.editText14);
				TextView promienT = (TextView) findViewById(R.id.editText15);
				System.out.println("nioh2");
				double latitude = Double.parseDouble(ns.getText().toString());
				double longitude = Double.parseDouble(ew.getText().toString());
				double promien =Double.parseDouble( promienT.getText().toString());
				System.out.println("nioh3");//tu
				boolean czy = tr.czyWDanymMiejscuTest(latitude, longitude, promien);
				System.out.println("nioh4");
				Toast.makeText(getApplicationContext(), ""+czy, Toast.LENGTH_SHORT).show();
				System.out.println("nioh5");
				}
				catch(Exception e){System.out.println("nioh0");}
			}
		});
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_triggers_test, menu);
		return true;
	}

}
