package com.qi.mainactivity;

import java.util.List;
import java.util.Map;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;

public class CatalogAdapter extends BaseAdapter {

	public void log(String msg) {
		Log.println(Log.ASSERT, "assert", msg);
	}

	List<Map<String, Object>> list;
	Context context;

	public CatalogAdapter(List<Map<String, Object>> list, Context context) {
		super();
		// TODO Auto-generated constructor stub
		this.list = list;
		this.context = context;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		RelativeLayout listItem = new RelativeLayout(context);
		// -----------------------------------------
		// RelativeLayout.LayoutParams listItemLayout = new
		// RelativeLayout.LayoutParams(LayoutParams.FILL_PARENT, 55);
		// listItem.setLayoutParams(listItemLayout);
		// -----------------------------------------
		// Button btn = new Button(context);
		// listItem.addView(btn);
		// ------------------------------------------
		// …˙≥… itemTop
		RelativeLayout itemTop = new RelativeLayout(context);
		RelativeLayout.LayoutParams itemTopLayout = new RelativeLayout.LayoutParams(LayoutParams.FILL_PARENT, 10);
		itemTop.setLayoutParams(itemTopLayout);
		itemTop.setId(1);
		itemTopLayout.addRule(RelativeLayout.ALIGN_PARENT_TOP);
		// itemTop.setBackgroundColor(0xff00ff00);
		// --------------------------------------------
		// …˙≥… itemBody
		RelativeLayout itemBody = new RelativeLayout(context);
		RelativeLayout.LayoutParams itemBodyLayout = new RelativeLayout.LayoutParams(LayoutParams.FILL_PARENT, 100);
		itemBody.setLayoutParams(itemBodyLayout);
		itemBody.setId(2);
		itemBodyLayout.addRule(RelativeLayout.BELOW, itemTop.getId());
		// --------------------------------------------
		// …˙≥… itemBottom
		RelativeLayout itemBottom = new RelativeLayout(context);
		RelativeLayout.LayoutParams itemBottomLayout = new RelativeLayout.LayoutParams(LayoutParams.FILL_PARENT, 20);
		itemBottom.setLayoutParams(itemBottomLayout);
		itemBottom.setId(3);
		itemBottomLayout.addRule(RelativeLayout.BELOW, itemBody.getId());
		// itemBottom.setBackgroundColor(0xff0000ff);
		// --------------------------------------------
		listItem.addView(itemTop);
		listItem.addView(itemBody);
		listItem.addView(itemBottom);
		// …˙≥…imageøÿº˛------------------------------------------------
		ImageView imageView = new ImageView(context);
		RelativeLayout.LayoutParams imageViewLayout = new RelativeLayout.LayoutParams(135, 100);
		imageViewLayout.leftMargin = 10;
		imageView.setLayoutParams(imageViewLayout);
		imageView.setBackgroundColor(0xffD0D0D0);
		imageView.setId(1);
		// ----------------------------------------------------------------
		// …˙≥…textøÿº˛1
		TextView titleText = new TextView(context);
		RelativeLayout.LayoutParams titleTextLayout = new RelativeLayout.LayoutParams(LayoutParams.FILL_PARENT, 45);
		titleTextLayout.addRule(RelativeLayout.RIGHT_OF, imageView.getId());
		titleTextLayout.leftMargin = 10;
		titleText.setLayoutParams(titleTextLayout);
		//titleText.setBackgroundColor(0xffffff00);
		titleText.setId(2);
		titleText.setText((String) list.get(position).get("name"));
		titleText.setTextSize(16);
		// ----------------------------------------------------------------
		// …˙≥…textøÿº˛2
		TextView descText = new TextView(context);
		RelativeLayout.LayoutParams descTextLayout = new RelativeLayout.LayoutParams(LayoutParams.FILL_PARENT, 55);
		descTextLayout.addRule(RelativeLayout.BELOW, titleText.getId());
		descTextLayout.addRule(RelativeLayout.RIGHT_OF, imageView.getId());
		descTextLayout.leftMargin = 10;
		descText.setLayoutParams(descTextLayout);
		//descText.setBackgroundColor(0xff00ff00);
		descText.setTextSize(10);
		descText.setText("≤‚ ‘≤‚ ‘≤‚ ‘≤‚ ‘≤‚ ‘≤‚ ‘≤‚ ‘≤‚ ‘≤‚ ‘≤‚ ‘≤‚ ‘≤‚ ‘≤‚ ‘≤‚ ‘≤‚ ‘≤‚ ‘≤‚ ‘≤‚ ‘≤‚ ‘≤‚ ‘≤‚ ‘≤‚ ‘≤‚ ‘≤‚ ‘≤‚ ‘≤‚ ‘≤‚ ‘≤‚ ‘≤‚ ‘≤‚ ‘≤‚ ‘≤‚ ‘≤‚ ‘≤‚ ‘≤‚ ‘≤‚ ‘≤‚ ‘≤‚ ‘≤‚ ‘≤‚ ‘≤‚ ‘≤‚ ‘≤‚ ‘≤‚ ‘");
		descText.setTextColor(0xffD0D0D0);
		// ------------------------------------------------------------------
		itemBody.addView(imageView);
		itemBody.addView(titleText);
		itemBody.addView(descText);
		return listItem;
	}
}
