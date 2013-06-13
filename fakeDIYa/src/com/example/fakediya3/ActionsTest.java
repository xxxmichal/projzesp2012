package com.example.fakediya3;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import com.example.fakediya3.R;

public class ActionsTest extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_actions_test);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_actions_test, menu);
		return true;
	}

}
