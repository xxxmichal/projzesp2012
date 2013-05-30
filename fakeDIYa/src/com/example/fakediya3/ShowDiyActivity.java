package com.example.fakediya3;


import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.diyapp.lib.DiyDbAdapter;

public class ShowDiyActivity extends Activity {
	Long mRowId;
	
	private DiyDbAdapter mDbHelper;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		// only one mDbHelper is necessary so use the one from ListDiys
		mDbHelper = ListDiys.mDbHelper; 
		mDbHelper.open();
		
		Bundle extras = getIntent().getExtras();
		mRowId = extras.getLong(DiyDbAdapter.KEY_ROWID);
		
		setContentView(R.layout.activity_show_diy);
		
		populateFields();
		

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_show_diy, menu);
		return true;
	}
	
	private void populateFields() {
		if (mRowId != null) {
			Cursor diy = mDbHelper.fetchDiy(mRowId);

			startManagingCursor(diy);
			
//thats how you do it if you retrieve a column value and set textfield manually
//			((TextView)findViewById(R.id.showdiy1)).setText(
//					diy.getString(
//							diy.getColumnIndexOrThrow(DiyDbAdapter.KEY_TITLE)));
			
//display all column fields at once
			LinearLayout linearLayout = (LinearLayout) findViewById(R.id.showdiy);
			for (String column : DiyDbAdapter.COLUMNS) {
				TextView textview = new TextView(this);
				textview.setText(column +": " +
						diy.getString(
								diy.getColumnIndexOrThrow(column)));
				linearLayout.addView(textview);
			}
		}
	}

}
