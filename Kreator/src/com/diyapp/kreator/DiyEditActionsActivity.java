package com.diyapp.kreator;

import com.diyapp.kreator2.R;
import com.diyapp.kreator2.R.layout;
import com.diyapp.kreator2.R.menu;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class DiyEditActionsActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_diy_edit_actions);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_diy_edit_actions, menu);
		return true;
	}

}
