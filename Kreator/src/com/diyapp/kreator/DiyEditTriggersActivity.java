package com.diyapp.kreator;

import com.diyapp.kreator2.R;
import com.diyapp.kreator2.R.layout;
import com.diyapp.kreator2.R.menu;
import com.diyapp.lib.DiyDbAdapter;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

public class DiyEditTriggersActivity extends Activity {
	EditText mTitleText;
	EditText mBodyText;
	CheckBox mEnabledCB;
	Long mRowId;
	
	private DiyDbAdapter mDbHelper;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		mDbHelper = new DiyDbAdapter(this);
		mDbHelper.open();
		
		setContentView(R.layout.activity_diy_edit_triggers);
		setTitle(R.string.edit_diy);
		mTitleText = (EditText) findViewById(R.id.title);
	
		populateFields();

		mRowId = (savedInstanceState == null) ? null :
			(Long) savedInstanceState.getSerializable(DiyDbAdapter.KEY_ROWID);
		
		if (mRowId == null) {
			Bundle extras = getIntent().getExtras();
			mRowId = extras != null ? extras.getLong(DiyDbAdapter.KEY_ROWID)
									: null;
		}	
	}
	
	private void populateFields() {
		if (mRowId != null) {
			Cursor diy = mDbHelper.fetchDiy(mRowId);
			startManagingCursor(diy);
			
//			mTitleText.setText(diy.getString(
//					diy.getColumnIndexOrThrow(DiyDbAdapter.KEY_TITLE)));
//			mBodyText.setText(diy.getString(
//					diy.getColumnIndexOrThrow(DiyDbAdapter.KEY_BODY)));
//			mEnabledCB.setChecked(1 == diy.getInt(
//					diy.getColumnIndexOrThrow(DiyDbAdapter.KEY_ENABLED)));
		}
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		saveState();
		outState.putSerializable(DiyDbAdapter.KEY_ROWID, mRowId);
	}

	@Override
	protected void onPause() {
		super.onPause();
		saveState();
	}

	@Override
	protected void onResume() {
		super.onResume();
		populateFields();
	}
	
	private void saveState() {
//		String title = mTitleText.getText().toString();
//		String body = mBodyText.getText().toString();
//		boolean enabled = mEnabledCB.isChecked();
//		String trigger_example = "trig1";
//		
//		if (mRowId == null) {
//			long id = mDbHelper.createDiy(title, body, enabled, trigger_example);
//			if ( id > 0 ) {
//				mRowId = id;
//			}
//		} else {
//			mDbHelper.updateDiy(mRowId, title, body, enabled, trigger_example);
//		}
		
		
		// broadcast an update notice to Egzekutor
		Intent intent = new Intent();
		intent.setAction("com.example.sendbroadcast");
		sendBroadcast(intent);
	}	

}

