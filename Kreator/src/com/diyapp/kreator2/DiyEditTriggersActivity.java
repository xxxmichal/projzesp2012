package com.diyapp.kreator2;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.LabeledIntent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.diyapp.kreator2.R;
import com.diyapp.lib.DiyDbAdapter;

public class DiyEditTriggersActivity extends Activity {
	private static final int ACTIVITY_MAP = 2;

	CheckBox mtrigger_example_enabled;
	EditText mtrigger_example_param_1;

	CheckBox mtrigger_location_enabled;
	EditText mtrigger_location_param_latitude;
	EditText mtrigger_location_param_longtitude;
	EditText mtrigger_location_param_area;

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

		// triggers
		mtrigger_example_enabled = (CheckBox) findViewById(R.id.trigger_example_enabled);
		mtrigger_example_param_1 = (EditText) findViewById(R.id.trigger_example_param_1);

		mtrigger_location_enabled = (CheckBox) findViewById(R.id.trigger_location_enabled);
		mtrigger_location_param_latitude = (EditText) findViewById(R.id.trigger_location_param_latitude);
		mtrigger_location_param_longtitude = (EditText) findViewById(R.id.trigger_location_param_longtitude);
		mtrigger_location_param_area = (EditText) findViewById(R.id.trigger_location_param_area);

		populateFields();

		mRowId = (savedInstanceState == null) ? null
				: (Long) savedInstanceState
						.getSerializable(DiyDbAdapter.KEY_ROWID);

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

			// triggers
			mtrigger_example_enabled.setChecked(1 == diy.getInt(diy
					.getColumnIndexOrThrow(DiyDbAdapter.KEY_TRIGGER_EXAMPLE)));
			mtrigger_example_param_1
					.setText(diy.getString(diy
							.getColumnIndexOrThrow(DiyDbAdapter.KEY_TRIGGER_EXAMPLE_PARAM_1)));

			mtrigger_location_enabled.setChecked(1 == diy.getInt(diy
					.getColumnIndexOrThrow(DiyDbAdapter.KEY_TRIGGER_LOCATION)));
			mtrigger_location_param_latitude
					.setText(Double.toString(diy.getDouble(diy
							.getColumnIndexOrThrow(DiyDbAdapter.KEY_TRIGGER_LOCATION_PARAM_LATITUDE))));
			mtrigger_location_param_longtitude
					.setText(Double.toString(diy.getDouble(diy
							.getColumnIndexOrThrow(DiyDbAdapter.KEY_TRIGGER_LOCATION_PARAM_LONGTITUDE))));
			mtrigger_location_param_area
					.setText(Double.toString(diy.getDouble(diy
							.getColumnIndexOrThrow(DiyDbAdapter.KEY_TRIGGER_LOCATION_PARAM_AREA))));
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
		mDbHelper.updateDiyTriggers(
				mRowId, //
				// triggers
				mtrigger_example_enabled.isChecked(), //
				mtrigger_example_param_1.getText().toString(),
				mtrigger_location_enabled.isChecked(), //
				Double.parseDouble(mtrigger_location_param_latitude.getText()
						.toString()), //
				Double.parseDouble(mtrigger_location_param_longtitude.getText()
						.toString()), //
				Double.parseDouble(mtrigger_location_param_area.getText()
						.toString()) //
				); //
	}

	public void showMap(View v) {
		Intent intent = new Intent(this, DiyMapActivity.class);
		startActivityForResult(intent, ACTIVITY_MAP);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode,
			Intent intent) {
		super.onActivityResult(requestCode, resultCode, intent);

		switch (requestCode) {
		case ACTIVITY_MAP:
			if (resultCode == RESULT_OK) {
				double latitude = intent.getDoubleExtra("latitude", 0.0);
				double longtitude = intent.getDoubleExtra("longtitude", 0.0);
				Log.v("diy", "latitude = " + Double.toString(latitude)
						+ " longtitude= " + Double.toString(longtitude));
				mtrigger_location_param_latitude.setText(Double
						.toString(latitude));
				DiyEditTriggersActivity.this.mtrigger_location_param_longtitude.setText(Double
						.toString(longtitude));
				DiyEditTriggersActivity.this.mtrigger_location_param_area.setText("50");
				DiyEditTriggersActivity.this.mtrigger_location_param_area.postInvalidate();
				Toast toast = Toast.makeText(this,
						"latitude " + mtrigger_location_param_latitude.getText().toString()
						+ " longtitude " + mtrigger_location_param_longtitude.getText().toString(), Toast.LENGTH_SHORT);
				toast.show();
				
				mtrigger_location_param_area.setVisibility(mtrigger_location_param_area.VISIBLE);
				Log.v("diy", "la");
			}
			break;
		default:
			break;
		}
	}

}
