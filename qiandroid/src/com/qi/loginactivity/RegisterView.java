package com.qi.loginactivity;

import android.content.Context;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;

public class RegisterView extends RelativeLayout {
	Context context;

	public RegisterView(Context context) {
		// TODO RegisterView ¹¹Ôìº¯Êý
		super(context);
		this.context = context;
		this.setBackgroundColor(0xffff0000);
		RelativeLayout.LayoutParams registerViewLayout = new RelativeLayout.LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT);
		setLayoutParams(registerViewLayout);
	}

}
