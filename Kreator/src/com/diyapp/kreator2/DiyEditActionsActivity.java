package com.diyapp.kreator2;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ToggleButton;

import com.diyapp.kreator2.R;
import com.diyapp.lib.DiyDbAdapter;

public class DiyEditActionsActivity extends Activity {
	EditText maction_example_param_1;
	ToggleButton maction_example;
	ToggleButton maction_wifi;//
	CheckBox maction_wifi_param_turn_on;//
	CheckBox  maction_wifi_param_turn_off;//
	EditText maction_wifi_param_ssid;//
	ToggleButton maction_notification;//
	EditText maction_notification_param_title;//
	EditText maction_notification_param_text;//
	// TEMPLATE: {field} m{lowercase};//
	
	Long mRowId;
	
	private DiyDbAdapter mDbHelper;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		mDbHelper = new DiyDbAdapter(this);
		mDbHelper.open();
		
		setContentView(R.layout.activity_diy_edit_actions);
		setTitle(R.string.edit_diy);
		maction_example = (ToggleButton) findViewById(R.id.action_example);
		maction_example_param_1 = (EditText) findViewById(R.id.action_example_param_1);
		
		maction_wifi = (ToggleButton) findViewById(R.id.action_wifi);//
		maction_wifi_param_turn_on = (CheckBox) findViewById(R.id.action_wifi_param_turn_on);//
		maction_wifi_param_turn_off = (CheckBox ) findViewById(R.id.action_wifi_param_turn_off);//
		maction_wifi_param_ssid = (EditText) findViewById(R.id.action_wifi_param_ssid);//
		maction_notification = (ToggleButton) findViewById(R.id.action_notification);//
		maction_notification_param_title = (EditText) findViewById(R.id.action_notification_param_title);//
		maction_notification_param_text = (EditText) findViewById(R.id.action_notification_param_text);//
		// TEMPLATE: m{lowercase} = ({field}) findViewById(R.id.{lowercase});//
	
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
			
			maction_example.setChecked(1 == diy.getInt(
					diy.getColumnIndexOrThrow(DiyDbAdapter.KEY_ACTION_EXAMPLE)));
			maction_example_param_1.setText(diy.getString(
					diy.getColumnIndexOrThrow(DiyDbAdapter.KEY_ACTION_EXAMPLE_PARAM_1)));
			
			maction_wifi.setChecked( 1 == diy.getInt(diy.getColumnIndexOrThrow(DiyDbAdapter.KEY_ACTION_WIFI)));//
			maction_wifi_param_turn_on.setChecked( 1 == diy.getInt(diy.getColumnIndexOrThrow(DiyDbAdapter.KEY_ACTION_WIFI_PARAM_TURN_ON)));//
			maction_wifi_param_turn_off.setChecked( 1 == diy.getInt(diy.getColumnIndexOrThrow(DiyDbAdapter.KEY_ACTION_WIFI_PARAM_TURN_OFF)));//
			maction_wifi_param_ssid.setText(diy.getString(diy.getColumnIndexOrThrow(DiyDbAdapter.KEY_ACTION_WIFI_PARAM_SSID)));//
			maction_notification.setChecked( 1 == diy.getInt(diy.getColumnIndexOrThrow(DiyDbAdapter.KEY_ACTION_NOTIFICATION)));//
			maction_notification_param_title.setText(diy.getString(diy.getColumnIndexOrThrow(DiyDbAdapter.KEY_ACTION_NOTIFICATION_PARAM_TITLE)));//
			maction_notification_param_text.setText(diy.getString(diy.getColumnIndexOrThrow(DiyDbAdapter.KEY_ACTION_NOTIFICATION_PARAM_TEXT)));//
			// TEMPLATE: m{lowercase}.{diyget}(diy.getColumnIndexOrThrow(DiyDbAdapter.KEY_{uppercase})));//
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
		mDbHelper.updateDiyActions(mRowId,
				maction_wifi.isChecked(),//
				maction_wifi_param_turn_on.isChecked(),//
				maction_wifi_param_turn_off.isChecked(),//
				maction_wifi_param_ssid.getText().toString(),//
				maction_notification.isChecked(),//
				maction_notification_param_title.getText().toString(),//
				maction_notification_param_text.getText().toString(),//
				// TEMPLATE: m{lowercase}.{retrieve}(),//
				maction_example.isChecked(),
				maction_example_param_1.getText().toString());
	}	

}
