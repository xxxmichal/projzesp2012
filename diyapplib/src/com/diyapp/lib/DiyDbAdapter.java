package com.diyapp.lib;

/* to insert new columns use insert_DiyDbAdapter.py script */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Simple diys database access helper class. Defines the basic CRUD operations
 * for the diy example, and gives the ability to list all diys as well as
 * retrieve or modify a specific diy.
 * 
 * This has been improved from the first version of this tutorial through the
 * addition of better error handling and also using returning a Cursor instead
 * of using a collection of inner classes (which is less scalable and not
 * recommended).
 */
public class DiyDbAdapter {
	// increase version after modifying columns, clean and rebuild library AND project!
	private static final int DATABASE_VERSION = 11;

	private static final String DATABASE_NAME = "data2";
	private static final String DATABASE_TABLE = "diys";

	public static final String KEY_ROWID = "_id";
	public static final String KEY_TITLE = "title";
	public static final String KEY_DESCRIPTION = "description";
	public static final String KEY_ENABLED = "enabled";
	// TEMPLATE_edit: public static final String KEY_{uppercase} = "{lowercase}";//

	// triggers
	public static final String KEY_TRIGGER_EXAMPLE = "trigger_example";
	public static final String KEY_TRIGGER_EXAMPLE_PARAM_1 = "trigger_example_param_1";

	public static final String KEY_TRIGGER_LOCATION = "trigger_location";
	public static final String KEY_TRIGGER_LOCATION_PARAM_LATITUDE = "trigger_location_param_latitude";
	public static final String KEY_TRIGGER_LOCATION_PARAM_LONGTITUDE = "trigger_location_param_longtitude";
	public static final String KEY_TRIGGER_LOCATION_PARAM_AREA = "trigger_location_param_area";

	public static final String KEY_TRIGGER_DATE = "trigger_date";//
	public static final String KEY_TRIGGER_DATE_PARAM_FROM = "trigger_date_param_from";//
	public static final String KEY_TRIGGER_DATE_PARAM_TO = "trigger_date_param_to";//
	// TEMPLATE_triggers: public static final String KEY_{uppercase} = "{lowercase}";//

	// actions
	// TEMPLATE_actions: public static final String KEY_{uppercase} = "{lowercase}";//

	public static final String KEY_ACTION_EXAMPLE = "action_example";
	public static final String KEY_ACTION_EXAMPLE_PARAM_1 = "action_example_param_1";

	public static final String[] COLUMNS = new String[] { KEY_ROWID, //
			KEY_TITLE, //
			KEY_DESCRIPTION, //
			KEY_ENABLED, //
			// TEMPLATE_edit: KEY_{uppercase},//

			// triggers
			KEY_TRIGGER_EXAMPLE, //
			KEY_TRIGGER_EXAMPLE_PARAM_1, //

			KEY_TRIGGER_LOCATION, //
			KEY_TRIGGER_LOCATION_PARAM_LATITUDE, //
			KEY_TRIGGER_LOCATION_PARAM_LONGTITUDE, //
			KEY_TRIGGER_LOCATION_PARAM_AREA, //

			KEY_TRIGGER_DATE,//
			KEY_TRIGGER_DATE_PARAM_FROM,//
			KEY_TRIGGER_DATE_PARAM_TO,//
			// TEMPLATE_triggers: KEY_{uppercase},//

			// actions
			// TEMPLATE_actions: KEY_{uppercase},//
			KEY_ACTION_EXAMPLE, //
			KEY_ACTION_EXAMPLE_PARAM_1 }; //

	private static final String TAG = "DiyDbAdapter";
	private DatabaseHelper mDbHelper;
	private SQLiteDatabase mDb;

	/**
	 * Database creation sql statement
	 */
	private static final String DATABASE_CREATE = "create table " //
			+ DATABASE_TABLE + " ( " //
			+ KEY_ROWID + " integer primary key autoincrement, " //
			+ KEY_TITLE + " text not null, " //
			+ KEY_DESCRIPTION + " text not null, " //
			+ KEY_ENABLED + " integer not null, " // 0|1 is this diya active ?
			// TEMPLATE_edit: + KEY_{uppercase} + " {dbtype} not null,"//

			// triggers
			+ KEY_TRIGGER_EXAMPLE + " integer not null, " // 0|1 is this trigger
															// active ?
			+ KEY_TRIGGER_EXAMPLE_PARAM_1 + " text not null, " //

			+ KEY_TRIGGER_LOCATION + " integer not null, " //
			+ KEY_TRIGGER_LOCATION_PARAM_LATITUDE + " real not null, " //
			+ KEY_TRIGGER_LOCATION_PARAM_LONGTITUDE + " real not null, " //
			+ KEY_TRIGGER_LOCATION_PARAM_AREA + " real not null, " //

			+ KEY_TRIGGER_DATE + " integer not null,"//
			+ KEY_TRIGGER_DATE_PARAM_FROM + " text not null,"//
			+ KEY_TRIGGER_DATE_PARAM_TO + " text not null,"//
			// TEMPLATE_triggers: + KEY_{uppercase} + " {dbtype} not null,"//

			// actions
			// TEMPLATE_actions: + KEY_{uppercase} + " {dbtype} not null,"//

			+ KEY_ACTION_EXAMPLE + " integer not null, " // 0|1 is this action
															// active ?
			+ KEY_ACTION_EXAMPLE_PARAM_1 + " text not null);"; //

	private final Context mCtx;

	private static class DatabaseHelper extends SQLiteOpenHelper {

		DatabaseHelper(Context context) {
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
			Log.v("DiyDbAdapter", "db: " + DATABASE_NAME + " version: "
					+ DATABASE_VERSION);
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			db.execSQL(DATABASE_CREATE);
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			Log.w(TAG, "Upgrading database from version " + oldVersion + " to "
					+ newVersion + ", which will destroy all old data");
			db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE);
			onCreate(db);
		}
	}

	/**
	 * Constructor - takes the context to allow the database to be
	 * opened/created
	 * 
	 * @param ctx
	 *            the Context within which to work
	 */
	public DiyDbAdapter(Context ctx) {
		this.mCtx = ctx;
	}

	/**
	 * Open the diys database. If it cannot be opened, try to create a new
	 * instance of the database. If it cannot be created, throw an exception to
	 * signal the failure
	 * 
	 * @return this (self reference, allowing this to be chained in an
	 *         initialization call)
	 * @throws SQLException
	 *             if the database could be neither opened or created
	 */
	public DiyDbAdapter open() throws SQLException {
		mDbHelper = new DatabaseHelper(mCtx);
		mDb = mDbHelper.getWritableDatabase();
		return this;
	}

	public void close() {
		mDbHelper.close();
	}

	/**
	 * Create a new diy using the title and description provided. If the diy is
	 * successfully created return the new rowId for that diy, otherwise return
	 * a -1 to indicate failure.
	 * 
	 * @param title
	 *            the title of the diy
	 * @param description
	 *            the description of the diy
	 * @return rowId or -1 if failed
	 */
	public long createDiy(String title, String description, boolean enabled) {
		ContentValues initialValues = new ContentValues();
		initialValues.put(KEY_TITLE, title);
		initialValues.put(KEY_DESCRIPTION, description);
		initialValues.put(KEY_ENABLED, enabled ? 1 : 0);
		// TEMPLATE_edit: initialValues.put(KEY_{uppercase}, {default_value});//

		// triggers
		initialValues.put(KEY_TRIGGER_EXAMPLE, 0);
		initialValues.put(KEY_TRIGGER_EXAMPLE_PARAM_1, "");

		initialValues.put(KEY_TRIGGER_LOCATION, 0);
		initialValues.put(KEY_TRIGGER_LOCATION_PARAM_LATITUDE, 0.0);
		initialValues.put(KEY_TRIGGER_LOCATION_PARAM_LONGTITUDE, 0.0);
		initialValues.put(KEY_TRIGGER_LOCATION_PARAM_AREA, 100.0);
		initialValues.put(KEY_TRIGGER_DATE, 0);//
		initialValues.put(KEY_TRIGGER_DATE_PARAM_FROM, "");//
		initialValues.put(KEY_TRIGGER_DATE_PARAM_TO, "");//
		// TEMPLATE_triggers: initialValues.put(KEY_{uppercase}, {default_value});//

		// actions
		initialValues.put(KEY_ACTION_EXAMPLE, 0);
		initialValues.put(KEY_ACTION_EXAMPLE_PARAM_1, "");
		// TEMPLATE_actions: initialValues.put(KEY_{uppercase}, {default_value});//

		return mDb.insert(DATABASE_TABLE, null, initialValues);
	}

	/**
	 * Delete the diy with the given rowId
	 * 
	 * @param rowId
	 *            id of diy to delete
	 * @return true if deleted, false otherwise
	 */
	public boolean deleteDiy(long rowId) {

		return mDb.delete(DATABASE_TABLE, KEY_ROWID + "=" + rowId, null) > 0;
	}

	/**
	 * Return a Cursor over the list of all diys in the database
	 * 
	 * @return Cursor over all diys
	 */
	public Cursor fetchAllDiy() {

		return mDb.query(DATABASE_TABLE, COLUMNS, null, null, null, null, null);
	}

	/**
	 * Return a Cursor positioned at the diy that matches the given rowId
	 * 
	 * @param rowId
	 *            id of diy to retrieve
	 * @return Cursor positioned to matching diy, if found
	 * @throws SQLException
	 *             if diy could not be found/retrieved
	 */
	public Cursor fetchDiy(long rowId) throws SQLException {

		Cursor mCursor =

		mDb.query(true, DATABASE_TABLE, COLUMNS, KEY_ROWID + "=" + rowId, null,
				null, null, null, null);
		if (mCursor != null) {
			mCursor.moveToFirst();
		}
		return mCursor;

	}

	/**
	 * Update the diy using the details provided. The diy to be updated is
	 * specified using the rowId, and it is altered to use the title and
	 * description values passed in
	 * 
	 * @param rowId
	 *            id of diy to update
	 * @param title
	 *            value to set diy title to
	 * @param description
	 *            value to set diy description to
	 * @return true if the diy was successfully updated, false otherwise
	 */
	public boolean updateDiy(long rowId, String title, String description,
			boolean enabled) {
		ContentValues args = new ContentValues();
		args.put(KEY_TITLE, title);
		args.put(KEY_DESCRIPTION, description);
		args.put(KEY_ENABLED, enabled ? 1 : 0);
		// TEMPLATE_edit: args.put(KEY_{uppercase}, {lowercase});

		return mDb.update(DATABASE_TABLE, args, KEY_ROWID + "=" + rowId, null) > 0;
	}

	public boolean updateDiyTriggers(long rowId,
			boolean trigger_date,
			String trigger_date_param_from,
			String trigger_date_param_to,
			// TEMPLATE_triggers: {vartype} {lowercase},//
			boolean trigger_example_enabled, String trigger_example_param_1,
			boolean trigger_location_enabled,
			double trigger_location_param_latitude,
			double trigger_location_param_longtitude,
			double trigger_location_param_area) {
		ContentValues args = new ContentValues();
		args.put(KEY_TRIGGER_EXAMPLE, trigger_example_enabled ? 1 : 0);
		args.put(KEY_TRIGGER_EXAMPLE_PARAM_1, trigger_example_param_1);

		args.put(KEY_TRIGGER_LOCATION, trigger_location_enabled ? 1 : 0);
		args.put(KEY_TRIGGER_LOCATION_PARAM_LATITUDE,
				trigger_location_param_latitude);
		args.put(KEY_TRIGGER_LOCATION_PARAM_LONGTITUDE, trigger_location_param_longtitude);
		args.put(KEY_TRIGGER_LOCATION_PARAM_AREA, trigger_location_param_area);
		args.put(KEY_TRIGGER_DATE, trigger_date  ? 1 : 0);
		args.put(KEY_TRIGGER_DATE_PARAM_FROM, trigger_date_param_from);
		args.put(KEY_TRIGGER_DATE_PARAM_TO, trigger_date_param_to);
		// TEMPLATE_triggers: args.put(KEY_{uppercase}, {lowercase});
		// must add '? 1 : 0' manually

		return mDb.update(DATABASE_TABLE, args, KEY_ROWID + "=" + rowId, null) > 0;
	}

	public boolean updateDiyActions(long rowId, boolean action_example_enabled,
			String action_example_param_1) {
		ContentValues args = new ContentValues();
		args.put(KEY_ACTION_EXAMPLE, action_example_enabled ? 1 : 0);
		args.put(KEY_ACTION_EXAMPLE_PARAM_1, action_example_param_1);
		// TEMPLATE_actions: args.put(KEY_{uppercase}, {lowercase});

		return mDb.update(DATABASE_TABLE, args, KEY_ROWID + "=" + rowId, null) > 0;
	}
}