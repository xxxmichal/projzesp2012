package com.example.fuuu;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.CheckBox;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		if (BuildConfig.DEBUG) {
			Log.d(Constants.LOG, "onCreated called debug");
		}

		Log.d(Constants.LOG, "onCreated called");

		setContentView(R.layout.activity_main);
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
			String[] recipientArray = { "abc", "cba"};
			intent.putExtra(Intent.EXTRA_EMAIL, recipientArray);
			intent.setType("text/plain");
			startActivity(Intent.createChooser(intent, "hello"));
			break;
		default:
			throw new RuntimeException("Unknow button ID");
		}
	}

}
