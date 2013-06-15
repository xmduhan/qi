package com.qi.loginactivity;

import com.qi.mainactivity.MainView;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;

public class LoginActivity extends Activity {

	public void log(String msg) {
		Log.println(Log.ASSERT, "assert", msg);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(new MainView(this));
	}

}
