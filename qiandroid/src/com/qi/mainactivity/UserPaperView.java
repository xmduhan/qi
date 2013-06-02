package com.qi.mainactivity;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;

public class UserPaperView extends RelativeLayout {

	public UserPaperView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		this.setBackgroundColor(0xff00ff00);
		RelativeLayout.LayoutParams userPaperViewLayout = new RelativeLayout.LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT);
		setLayoutParams(userPaperViewLayout);
	}

}
