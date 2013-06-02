package com.qi.mainactivity;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;

public class UserStatusView extends RelativeLayout {

	public UserStatusView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		this.setBackgroundColor(0xff0000ff);
		RelativeLayout.LayoutParams userStatusViewLayout = new RelativeLayout.LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT);
		setLayoutParams(userStatusViewLayout);
	}

}
