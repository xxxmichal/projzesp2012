package com.diyapp.kreator;

import com.diyapp.kreator2.R;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class DiyEdit extends Activity {
	EditText mTitleText;
	EditText mBodyText;
	Long mRowId;
	
	private DiyDbAdapter mDbHelper;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		mDbHelper = new DiyDbAdapter(this);
		mDbHelper.open();
		
		setContentView(R.layout.diy_edit);
		setTitle(R.string.edit_diy);
		mTitleText = (EditText) findViewById(R.id.title);
		mBodyText = (EditText) findViewById(R.id.body);
		
		populateFields();
		
		Button confirmButton = (Button) findViewById(R.id.confirm);

		mRowId = (savedInstanceState == null) ? null :
			(Long) savedInstanceState.getSerializable(DiyDbAdapter.KEY_ROWID);
		
		if (mRowId == null) {
			Bundle extras = getIntent().getExtras();
			mRowId = extras != null ? extras.getLong(DiyDbAdapter.KEY_ROWID)
									: null;
		}
		
		confirmButton.setOnClickListener( new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				setResult(RESULT_OK);
				finish();
				
			}
		});
	}
	
	private void populateFields() {
		if (mRowId != null) {
			Cursor diy = mDbHelper.fetchDiy(mRowId);
			startManagingCursor(diy);
			mTitleText.setText(diy.getString(
					diy.getColumnIndexOrThrow(DiyDbAdapter.KEY_TITLE)));
			mBodyText.setText(diy.getString(
					diy.getColumnIndexOrThrow(DiyDbAdapter.KEY_BODY)));
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
		String title = mTitleText.getText().toString();
		String body = mBodyText.getText().toString();
		
		if (mRowId == null) {
			long id = mDbHelper.createDiy(title, body);
			if ( id > 0 ) {
				mRowId = id;
			}
		} else {
			mDbHelper.updateDiy(mRowId, title, body);
		}
		
		
		// broadcast an update notice to Egzekutor
		Intent intent = new Intent();
		intent.setAction("com.example.sendbroadcast");
		sendBroadcast(intent);
	}	

}
