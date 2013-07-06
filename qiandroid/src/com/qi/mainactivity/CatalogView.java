package com.qi.mainactivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import com.qi.Service;
import com.qi.mainactivity.MainView.Button1Listener;
import com.qi.mainactivity.MainView.Button2Listener;
import com.qi.mainactivity.MainView.Button3Listener;
import com.qi.mainactivity.MainView.Button4Listener;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;
import android.widget.RelativeLayout.LayoutParams;

public class CatalogView extends RelativeLayout {

	Context context;
	RelativeLayout buttonPanel;
	RelativeLayout contentPanel;

	// 可以包装成数组
	public Button button1;
	public Button button2;
	public Button button3;
	public Button button4;
	public Button button5;
	public Button button6;
	public ListView listview;
	public CatalogAdapter catalogAdapter;
	public LoadItemThread loadItemThread;

	int buttonCount = 6;
	int buttonColor = 0xffC0C0C0;
	int buttonSelColor = 0xffD0D0D0;
	int buttonTextColor = 0xff000000;
	int buttonTextSelColor = 0xff0070f0;
	int buttonPanelColor = 0xffC0C0C0;

	public void log(String msg) {
		Log.println(Log.ASSERT, "assert", msg);
	}

	public class LoadItemThread extends Thread {
		String catalogCode;
		ListView listview;

		public LoadItemThread(ListView listview, String catalogCode) {
			super();
			this.catalogCode = catalogCode;
			this.listview = listview;
		}

		@Override
		public void run() {
			// 调用获取目录数据的服务
			Service.Result result = Service.getCatalogPaper(catalogCode);
			if (result.errcode == 0) {
				// 如果成功将其加载到ListView上
				List<Map<String, Object>> list = (List<Map<String, Object>>) result.data;
				catalogAdapter = new CatalogAdapter(list, context);
				// 设置回调
				listview.post(new Runnable() {
					@Override
					public void run() {
						listview.setAdapter(catalogAdapter);
					}
				});
			}
		}
	}

	protected void setButtonsStatus(String buttonName) {
		try {
			for (int i = 1; i <= buttonCount; i++) {
				String iButtonName = "button" + i;
				Button btn = (Button) this.getClass().getField(iButtonName).get(this);
				// Object obj = this.getClass().getField(iButtonName);
				if (iButtonName.equals(buttonName)) {
					btn.setTextColor(buttonTextSelColor);
					btn.setBackgroundColor(buttonSelColor);
				} else {
					btn.setTextColor(buttonTextColor);
					btn.setBackgroundColor(buttonColor);
				}
			}
		} catch (Exception e) {
			log(e.toString());
		}
	}

	// 刷新list列表
	// public void refreshListView(String catalogCode) {
	// List<Map<String, Object>> list = Service.getCatalogPaper(catalogCode);
	// CatalogAdapter catalogAdapter = new CatalogAdapter(list, context);
	// listview.setAdapter(catalogAdapter);
	// }
	public void refreshListView(String catalogCode) {
		// TODO refreshListView
		listview.setAdapter(new LoadingAdapter(context));
		loadItemThread = new LoadItemThread(listview, catalogCode);
		loadItemThread.start();
	}

	class Button1Listener implements Button.OnClickListener {
		@Override
		public void onClick(View arg0) {
			setButtonsStatus("button1");
			refreshListView("headlines");
		}
	};

	class Button2Listener implements Button.OnClickListener {
		@Override
		public void onClick(View arg0) {
			setButtonsStatus("button2");
			refreshListView("society");
		}
	}

	class Button3Listener implements Button.OnClickListener {
		@Override
		public void onClick(View arg0) {
			setButtonsStatus("button3");
			refreshListView("commerce");
		}
	}

	class Button4Listener implements Button.OnClickListener {
		@Override
		public void onClick(View arg0) {
			setButtonsStatus("button4");
			refreshListView("undergraduate");
		}
	}

	class Button5Listener implements Button.OnClickListener {
		@Override
		public void onClick(View arg0) {
			setButtonsStatus("button5");
			refreshListView("culture");
		}
	}

	class Button6Listener implements Button.OnClickListener {
		@Override
		public void onClick(View arg0) {
			setButtonsStatus("button6");
			refreshListView("public");
		}
	}

	public int getButtonWidth() {
		Display display = ((Activity) context).getWindowManager().getDefaultDisplay();
		int width = display.getWidth();
		int btnWidth = width / buttonCount;
		return btnWidth;
	}

	public void createcomponent(Context context) {
		// TODO 创建所有界面控件的
		this.context = context;
		buttonPanel = new RelativeLayout(context);
		contentPanel = new RelativeLayout(context);
		button1 = new Button(context);
		button2 = new Button(context);
		button3 = new Button(context);
		button4 = new Button(context);
		button5 = new Button(context);
		button6 = new Button(context);
		listview = new ListView(context);
	}

	public void setupCatalogView() {
		// TODO 设置Catalog属性
		// setBackgroundColor(0xffff0000);
		RelativeLayout.LayoutParams catalogViewLayout = new RelativeLayout.LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT);
		setLayoutParams(catalogViewLayout);
	}

	public void setupButtonPanel() {
		// TODO 设置buttonPanel属性
		RelativeLayout.LayoutParams btnPanelLayout = new RelativeLayout.LayoutParams(LayoutParams.FILL_PARENT, Constant.CatalogViewButtonPanelHeight);
		buttonPanel.setLayoutParams(btnPanelLayout);
		buttonPanel.setBackgroundColor(buttonPanelColor);
		btnPanelLayout.addRule(RelativeLayout.ALIGN_PARENT_TOP);
		buttonPanel.setId(1);
		this.addView(buttonPanel);
	}

	public void setupContentPanel() {
		// TODO 设置contentPanel属性
		RelativeLayout.LayoutParams contentLayout = new RelativeLayout.LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT);
		contentPanel.setLayoutParams(contentLayout);
		// contentPanel.setBackgroundColor(0xff0070f0);
		contentPanel.setId(2);
		// 设置buttonPanel和contentPanel的相对位置
		contentLayout.addRule(RelativeLayout.BELOW, buttonPanel.getId());
		this.addView(contentPanel);
	}

	public void setupButtons() {
		// TODO 设置button1的属性
		RelativeLayout.LayoutParams button1Layout = new RelativeLayout.LayoutParams(getButtonWidth(), LayoutParams.FILL_PARENT);
		button1.setLayoutParams(button1Layout);
		// button1Layout.topMargin = 5;
		button1.setText("热门");
		button1.setTextSize(14);
		button1.setBackgroundColor(buttonColor);
		button1.setId(3);
		// 设置button2的属性
		RelativeLayout.LayoutParams button2Layout = new RelativeLayout.LayoutParams(getButtonWidth(), LayoutParams.FILL_PARENT);
		button2.setLayoutParams(button2Layout);
		// button2Layout.topMargin = 5;
		button2.setText("社会");
		button2.setTextSize(14);
		button2.setBackgroundColor(buttonColor);
		button2.setId(4);
		// 设置button3的属性
		RelativeLayout.LayoutParams button3Layout = new RelativeLayout.LayoutParams(getButtonWidth(), LayoutParams.FILL_PARENT);
		button3.setLayoutParams(button3Layout);
		// button3Layout.topMargin = 5;
		button3.setText("商业");
		button3.setTextSize(14);
		button3.setBackgroundColor(buttonColor);
		button3.setId(5);
		// 设置button4的属性
		RelativeLayout.LayoutParams button4Layout = new RelativeLayout.LayoutParams(getButtonWidth(), LayoutParams.FILL_PARENT);
		button4.setLayoutParams(button4Layout);
		// button4Layout.topMargin = 5;
		button4.setText("学生");
		button4.setTextSize(14);
		button4.setBackgroundColor(buttonColor);
		button4.setId(6);
		// 设置button5的属性
		RelativeLayout.LayoutParams button5Layout = new RelativeLayout.LayoutParams(getButtonWidth(), LayoutParams.FILL_PARENT);
		button5.setLayoutParams(button5Layout);
		// button5Layout.topMargin = 5;
		button5.setText("文化");
		button5.setTextSize(14);
		button5.setBackgroundColor(buttonColor);
		button5.setId(7);
		// 设置button5的属性
		RelativeLayout.LayoutParams button6Layout = new RelativeLayout.LayoutParams(getButtonWidth(), LayoutParams.FILL_PARENT);
		button6.setLayoutParams(button6Layout);
		// button6Layout.topMargin = 5;
		button6.setText("公益");
		button6.setTextSize(14);
		button6.setBackgroundColor(buttonColor);
		button6.setId(8);
		// 设置按钮的相对位置
		button1Layout.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
		button2Layout.addRule(RelativeLayout.RIGHT_OF, button1.getId());
		button3Layout.addRule(RelativeLayout.RIGHT_OF, button2.getId());
		button4Layout.addRule(RelativeLayout.RIGHT_OF, button3.getId());
		button5Layout.addRule(RelativeLayout.RIGHT_OF, button4.getId());
		button6Layout.addRule(RelativeLayout.RIGHT_OF, button5.getId());
		// 添加按钮到buttonPanel
		buttonPanel.addView(button1);
		buttonPanel.addView(button2);
		buttonPanel.addView(button3);
		buttonPanel.addView(button4);
		buttonPanel.addView(button5);
		buttonPanel.addView(button6);

		// 为按钮添加监听器
		button1.setOnClickListener(new Button1Listener());
		button2.setOnClickListener(new Button2Listener());
		button3.setOnClickListener(new Button3Listener());
		button4.setOnClickListener(new Button4Listener());
		button5.setOnClickListener(new Button5Listener());
		button6.setOnClickListener(new Button6Listener());
	}

	public void setupListView() {
		// TODO 设置ListView属性
		RelativeLayout.LayoutParams listviewLayout = new RelativeLayout.LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT);
		listview.setLayoutParams(listviewLayout);
		contentPanel.addView(listview);
		// listview.setBackgroundColor(0xff00ff00);
	}

	public CatalogView(Context context) {
		super(context);
		// 创建所有界面控件的
		createcomponent(context);

		// 设置Catalog属性
		setupCatalogView();
		// 设置buttonPanel属性
		setupButtonPanel();
		// 设置contentPanel属性
		setupContentPanel();

		// 设置button属性
		setupButtons();

		// 添加listview
		setupListView();

		// 默认切向热门目录
		setButtonsStatus("button1");
		refreshListView("headlines");
	}
}
