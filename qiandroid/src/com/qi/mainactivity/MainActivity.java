package com.qi.mainactivity;

import com.qi.ActivityManager;
import com.qi.Dialogs;
import com.qi.R;
import com.qi.Service;
import com.qi.R.layout;
import com.qi.R.menu;
import com.qi.loginactivity.LoginActivity;

import android.os.Bundle;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.ViewGroup;
import android.view.Window;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.RelativeLayout;

public class MainActivity extends Activity {

	public void log(String msg) {
		Log.println(Log.ASSERT, "assert", msg);
	}

	public void intentLoginActivity() {
		int requestCode = 0;
		Intent intent = new Intent(this, LoginActivity.class);
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_SINGLE_TOP);
		startActivityForResult(intent, requestCode);
		log("startActivityForResult is finish");
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		ActivityManager.addActivity(this);
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		// intentLoginActivity();
		setContentView(new MainView(this));
	}

	/*
	 * @Override
	 * 
	 * public boolean onCreateOptionsMenu(Menu menu) { // Inflate the menu; this
	 * adds items to the action bar if it is present.
	 * getMenuInflater().inflate(R.menu.activity_main, menu); return true; }
	 */

	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			Dialogs dialogs = new Dialogs(this);
			dialogs.confirm("请确认", "您确实要退出程序吗?", //
					new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog, int which) {
							ActivityManager.exit();
						}

					},//
					new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog, int which) {

						}

					});
		}	
		return false;
	}
}
