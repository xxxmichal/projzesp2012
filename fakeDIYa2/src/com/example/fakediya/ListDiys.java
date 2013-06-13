package com.example.fakediya;


import android.app.ListActivity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.widget.SimpleCursorAdapter;

import com.diyapp.lib.DiyDbAdapter;
import com.example.fakediya2.R;

public class ListDiys extends ListActivity {
    private static final int ACTIVITY_CREATE=0;
    private static final int ACTIVITY_EDIT=1;
    
    private static final int INSERT_ID = Menu.FIRST;
    private static final int DELETE_ID = Menu.FIRST + 1;

    private DiyDbAdapter mDbHelper;
    
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.diys_list);
        
		Context ctx = null;
		try {
			// creating context from mainAPP for accessing database
			ctx = createPackageContext("com.diyapp.kreator2",
					Context.CONTEXT_IGNORE_SECURITY);
			if (ctx == null) {
				Log.v("fake", "failed to get db");
			} else {
				Log.v("fake", "got db");
			}
		} catch (PackageManager.NameNotFoundException e) {
			// package not found
			Log.e("Error", e.getMessage());
		}
        
        mDbHelper = new DiyDbAdapter(ctx);
        mDbHelper.open();
        fillData();

    }
    
    private void fillData() {
        // Get all of the rows from the database and create the item list
    	Cursor diysCursor = mDbHelper.fetchAllDiy();
        startManagingCursor(diysCursor);
        
        // Create an array to specify the fields we want to display in the list (only TITLE)
        String[] from = new String[]{DiyDbAdapter.KEY_TITLE};
        
        // and an array of the fields we want to bind those fields to (in this case just text1)
        int[] to = new int[]{R.id.text1};
        
        // Now create a simple cursor adapter and set it to display
        SimpleCursorAdapter diys = 
        	    new SimpleCursorAdapter(this, R.layout.diys_row, diysCursor, from, to);
        setListAdapter(diys);
    }
    

}