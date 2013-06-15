package com.qi.mainactivity;

import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

public class LoadingAdapter extends BaseAdapter {
	public Context context;

	public void log(String msg) {
		Log.println(Log.ASSERT, "assert", msg);
	}

	public LoadingAdapter(Context context) {
		super();
		this.context = context;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return 1;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	public int getItemContentHeight() {
		Display display = ((Activity) context).getWindowManager().getDefaultDisplay();
		// Ŀǰ���ּ��㷽������һ��������ǣ��ݲ�֪��μ��㶥����������Height������ʱ��39�������
		int height = display.getHeight() - Constant.MainViewTitlePanelHeight - Constant.MainViewButtonPanelHeight - Constant.CatalogViewButtonPanelHeight-39;
		//log("height=" + height);
		return height;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		RelativeLayout listItem = new RelativeLayout(context);
		// ����������
		RelativeLayout itemContent = new RelativeLayout(context);
		RelativeLayout.LayoutParams itemContentLayout = new RelativeLayout.LayoutParams(LayoutParams.FILL_PARENT, getItemContentHeight());
		itemContentLayout.addRule(RelativeLayout.CENTER_IN_PARENT);
		itemContent.setLayoutParams(itemContentLayout);
		itemContent.setId(1);
		listItem.addView(itemContent);
		// ��ӽ��ȿؼ�
		ProgressBar progressBar = new ProgressBar(context);
		RelativeLayout.LayoutParams progressBarLayout = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		progressBarLayout.addRule(RelativeLayout.CENTER_IN_PARENT);
		progressBar.setLayoutParams(progressBarLayout);
		itemContent.addView(progressBar);
		return listItem;
	}

}
