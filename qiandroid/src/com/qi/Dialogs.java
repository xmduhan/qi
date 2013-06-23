package com.qi;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;

public class Dialogs {

	Context context;



	public void log(String msg) {
		Log.println(Log.ASSERT, "assert", msg);
	}

	public Dialogs(Context context) {
		this.context = context;
	}

	public void confirm(String title, String message, DialogInterface.OnClickListener positiveListener, DialogInterface.OnClickListener negativeListener) {
		AlertDialog.Builder builder = new AlertDialog.Builder(context);
		builder.setMessage(message);
		builder.setTitle(title);
		builder.setPositiveButton("确定", positiveListener);
		builder.setNegativeButton("取消", negativeListener);
		builder.create().show();
	}

	public void showmessage(String title, String message, DialogInterface.OnClickListener listener) {
		AlertDialog.Builder builder = new AlertDialog.Builder(context);
		builder.setMessage(message);
		builder.setTitle(title);
		builder.setPositiveButton("确定", listener);
		builder.create().show();
	}

	public void showmessage(String title, String message) {
		showmessage(title, message, new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {

			}
		});
	}
}
