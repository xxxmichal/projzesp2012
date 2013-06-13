package com.diyapp.kreator2;

import com.diyapp.kreator2.R;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ToggleButton;

import com.diyapp.lib.DiyDbAdapter;

public class DiyEdit extends Activity {
	EditText mTitleText;
	EditText mBodyText;
	ToggleButton mEnabled;
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
		mBodyText = (EditText) findViewById(R.id.description);
		mEnabled = (ToggleButton) findViewById(R.id.enabled);
		
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
					diy.getColumnIndexOrThrow(DiyDbAdapter.KEY_DESCRIPTION)));
			mEnabled.setChecked(1 == diy.getInt(
					diy.getColumnIndexOrThrow(DiyDbAdapter.KEY_ENABLED)));
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
		boolean enabled = mEnabled.isChecked();
		
		mDbHelper.updateDiy(mRowId, title, body, enabled);	
	}	

}
