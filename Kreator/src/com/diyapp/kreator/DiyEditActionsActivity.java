package com.diyapp.kreator;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.EditText;

import com.diyapp.kreator2.R;
import com.diyapp.lib.DiyDbAdapter;

public class DiyEditActionsActivity extends Activity {
	EditText maction_example_param_1;
	CheckBox maction_example_enabled;
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
		maction_example_enabled = (CheckBox) findViewById(R.id.action_example_enabled);
		maction_example_param_1 = (EditText) findViewById(R.id.action_example_param_1);
	
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
			
			maction_example_enabled.setChecked(1 == diy.getInt(
					diy.getColumnIndexOrThrow(DiyDbAdapter.KEY_ACTION_EXAMPLE)));
			maction_example_param_1.setText(diy.getString(
					diy.getColumnIndexOrThrow(DiyDbAdapter.KEY_ACTION_EXAMPLE_PARAM_1)));
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
		mDbHelper.updateDiyActions(mRowId, maction_example_enabled.isChecked(), maction_example_param_1.getText().toString());
	}	

}
