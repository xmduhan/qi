package com.qi.mainactivity;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;

public class UnfinishedPaperView extends RelativeLayout {

	public UnfinishedPaperView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		this.setBackgroundColor(0xffff0000);
		RelativeLayout.LayoutParams unfinishedPaperViewLayout = new RelativeLayout.LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT);
		setLayoutParams(unfinishedPaperViewLayout);
	}

}
