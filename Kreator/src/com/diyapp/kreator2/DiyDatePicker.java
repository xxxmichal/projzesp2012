package com.diyapp.kreator2;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.Toast;

public class DiyDatePicker extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);


		setContentView(R.layout.activity_diy_date_picker);
	}

	public void confirm(View v) {
		DatePicker dp = (DatePicker) findViewById(R.id.datePicker);
		TimePicker tp = (TimePicker) findViewById(R.id.timePicker);
		int minute = tp.getCurrentMinute();
		String minute_str = Integer.toString(minute);
		if (minute < 10)
			minute_str = "0" + minute_str;
		

		
		String strDateTime = String.format("%04d",dp.getYear()) + ":" + String.format("%02d",dp.getMonth() + 1)
				+ ":" + String.format("%02d",dp.getDayOfMonth()) 
				+ " " + String.format("%02d",tp.getCurrentHour())
				+ ":" + String.format("%02d",minute);
				

		Toast.makeText(DiyDatePicker.this,
				"User selected " + strDateTime + "Time",
				Toast.LENGTH_LONG).show(); // Generate a toast only if
											// you want
											 
											
		Intent intent = new Intent();
		intent.putExtra("timedate", strDateTime);
		setResult(RESULT_OK, intent);
		finish(); // If you want to continue on that TimeDateActivity
		// If you want to go to new activity that code you can also
		// write here
		
	}
}
