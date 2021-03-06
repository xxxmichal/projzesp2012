package com.diyapp.kreator2;

import com.diyapp.kreator2.R;
import com.diyapp.lib.DiyDbAdapter;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

public class AndroidTabAndListView extends TabActivity {
	// TabSpec Names
	private static final String DIYEDIT_SPEC = "Edit";
	
	private static final String DIYEDITTRIGGERS_SPEC = "Triggers";
	private static final String DIYEDITACTIONS_SPEC = "Actions";

	Long mRowId;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tabs_editing);
        
        DiyDbAdapter dbHelper = new DiyDbAdapter(this);
		dbHelper.open();
        
		if (mRowId == null) {
			Bundle extras = getIntent().getExtras();
			mRowId = extras != null ? extras.getLong(DiyDbAdapter.KEY_ROWID)
									: null;
		}
		
		if (mRowId == null) {
			long id = dbHelper.createDiy("Do It Yourself", "", false);
			if ( id > 0 ) {
				mRowId = id;
			}
		} 
		
        TabHost tabHost = getTabHost();
        
        // Tab
        TabSpec diyEditSpec = tabHost.newTabSpec(DIYEDIT_SPEC);
        // Tab Icon
        diyEditSpec.setIndicator(DIYEDIT_SPEC, getResources().getDrawable(R.drawable.icon_inbox));
        Intent diyEditIntent = new Intent(this, DiyEdit.class);
        diyEditIntent.putExtra(DiyDbAdapter.KEY_ROWID, mRowId);
        // Tab Content
        diyEditSpec.setContent(diyEditIntent);
        
        // Tab
        TabSpec diyEditTriggersSpec = tabHost.newTabSpec(DIYEDITTRIGGERS_SPEC);
        // Tab Icon
        diyEditTriggersSpec.setIndicator(DIYEDITTRIGGERS_SPEC, getResources().getDrawable(R.drawable.icon_inbox));
        Intent diyEditTriggersIntent = new Intent(this, DiyEditTriggersActivity.class);
        diyEditTriggersIntent.putExtra(DiyDbAdapter.KEY_ROWID, mRowId);
        // Tab Content
        diyEditTriggersSpec.setContent(diyEditTriggersIntent);
        
        // Tab
        TabSpec diyEditActionsSpec = tabHost.newTabSpec(DIYEDITACTIONS_SPEC);
        // Tab Icon
        diyEditActionsSpec.setIndicator(DIYEDITACTIONS_SPEC, getResources().getDrawable(R.drawable.icon_inbox));
        Intent diyEditActionsIntent = new Intent(this, DiyEditActionsActivity.class);
        diyEditActionsIntent.putExtra(DiyDbAdapter.KEY_ROWID, mRowId);
        // Tab Content
        diyEditActionsSpec.setContent(diyEditActionsIntent);
        
        // Adding all TabSpec to TabHost
        tabHost.addTab(diyEditSpec); // Adding Edit tab
        tabHost.addTab(diyEditTriggersSpec); // Adding Edit tab
        tabHost.addTab(diyEditActionsSpec); // Adding Edit tab
        
    } 
    
}