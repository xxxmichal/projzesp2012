package com.example.fakediya;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class TestReceiver extends android.content.BroadcastReceiver {
	  @Override
	  public void onReceive(Context context, Intent intent) {
		Log.d("com.example.fakediya","onReceive com.diyapp.kreator.UPDATE");
		Toast toast = Toast.makeText(context, "com.example.fakediya Received message from Kreator com.diyapp.kreator.UPDATE",	Toast.LENGTH_SHORT);
		toast.show();
	  }
}
