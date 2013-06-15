package com.qi.mainactivity;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.app.Activity;

public class MainView extends RelativeLayout {
	Context context;
	RelativeLayout titlePanel;
	RelativeLayout bodyPanel;
	RelativeLayout buttonPanel;
	RelativeLayout contentPanel;
	public Button button1;
	public Button button2;
	public Button button3;
	public Button button4;
	CatalogView catalogView;
	UnfinishedPaperView unfinishedPaperView;
	UserPaperView userPaperView;
	UserStatusView userStatusView;
	int buttonCount = 4;
	int buttonColor = 0xff0070f0;
	int buttonSelColor = 0xff0060f0;
	int buttonTextColor = 0xff000000;
	int buttonTextSelColor = 0xffffffff;
	int titlePanelColor = 0xff0070f0;
	int buttonPanelColor = 0xff0070f0;

	public void log(String msg) {
		Log.println(Log.ASSERT, "assert", msg);
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

	class Button1Listener implements Button.OnClickListener {
		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			contentPanel.removeAllViews();
			contentPanel.addView(catalogView);
			setButtonsStatus("button1");
		}
	};

	class Button2Listener implements Button.OnClickListener {
		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			contentPanel.removeAllViews();
			contentPanel.addView(unfinishedPaperView);
			setButtonsStatus("button2");
		}
	}

	class Button3Listener implements Button.OnClickListener {
		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			contentPanel.removeAllViews();
			contentPanel.addView(userPaperView);
			setButtonsStatus("button3");
		}
	}

	class Button4Listener implements Button.OnClickListener {
		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			contentPanel.removeAllViews();
			contentPanel.addView(userStatusView);
			setButtonsStatus("button4");
		}

	}

	public int getButtonWidth() {
		Display display = ((Activity) context).getWindowManager().getDefaultDisplay();
		int width = display.getWidth();
		int btnWidth = width / buttonCount;
		return btnWidth;
	}

	public void createComponent(Context context) {
		// TODO �������н���ؼ���
		titlePanel = new RelativeLayout(context);
		bodyPanel = new RelativeLayout(context);
		buttonPanel = new RelativeLayout(context);
		contentPanel = new RelativeLayout(context);
		button1 = new Button(context);
		button2 = new Button(context);
		button3 = new Button(context);
		button4 = new Button(context);
		catalogView = new CatalogView(context);
		unfinishedPaperView = new UnfinishedPaperView(context);
		userPaperView = new UserPaperView(context);
		userStatusView = new UserStatusView(context);
	}

	public void setupTitlePanel() {
		// TODO ����titlePanel����
		RelativeLayout.LayoutParams titleLayout = new RelativeLayout.LayoutParams(LayoutParams.FILL_PARENT, Constant.MainViewTitlePanelHeight);
		titlePanel.setLayoutParams(titleLayout);
		titlePanel.setBackgroundColor(titlePanelColor);
		titleLayout.addRule(RelativeLayout.ALIGN_PARENT_TOP);
		titlePanel.setId(1);
		this.addView(titlePanel);
	}

	public void setupBodyPanel() {
		// TODO ����bodyPanel����
		RelativeLayout.LayoutParams bodyLayout = new RelativeLayout.LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT);
		bodyPanel.setLayoutParams(bodyLayout);
		bodyPanel.setId(2);
		bodyLayout.addRule(RelativeLayout.BELOW, titlePanel.getId());
		this.addView(bodyPanel);
	}

	public void setupButtonPanel() {
		// TODO ����buttonPanel����
		RelativeLayout.LayoutParams btnPanelLayout = new RelativeLayout.LayoutParams(LayoutParams.FILL_PARENT, Constant.MainViewButtonPanelHeight);
		buttonPanel.setLayoutParams(btnPanelLayout);
		buttonPanel.setBackgroundColor(buttonPanelColor);
		btnPanelLayout.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
		bodyPanel.addView(buttonPanel);
		buttonPanel.setId(3);
	}

	public void setupContentPanel() {
		// TODO ����contentPanel����
		RelativeLayout.LayoutParams contentLayout = new RelativeLayout.LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT);
		contentPanel.setLayoutParams(contentLayout);
		// contentPanel.setBackgroundColor(0xff000f00);
		contentPanel.setId(4);
		contentLayout.addRule(RelativeLayout.ABOVE, buttonPanel.getId());
		bodyPanel.addView(contentPanel);
	}

	public void setupButtons() {
		// TODO ���ø���ť������
		// ����button1������
		RelativeLayout.LayoutParams button1Layout = new RelativeLayout.LayoutParams(getButtonWidth(), LayoutParams.FILL_PARENT);
		// button1Layout.topMargin = 5;
		button1.setLayoutParams(button1Layout);
		button1.setBackgroundColor(buttonColor);
		button1.setText("Ŀ¼");
		button1.setId(5);
		// ����button2������
		RelativeLayout.LayoutParams button2Layout = new RelativeLayout.LayoutParams(getButtonWidth(), LayoutParams.FILL_PARENT);
		// button2Layout.topMargin = 5;
		button2.setBackgroundColor(buttonColor);
		button2.setLayoutParams(button2Layout);
		button2.setText("����");
		button2.setId(6);
		// ����button3������
		RelativeLayout.LayoutParams button3Layout = new RelativeLayout.LayoutParams(getButtonWidth(), LayoutParams.FILL_PARENT);
		// button3Layout.topMargin = 5;
		button3.setBackgroundColor(buttonColor);
		button3.setLayoutParams(button3Layout);
		button3.setText("�Ѵ�");
		button3.setId(7);
		// ����button4������
		RelativeLayout.LayoutParams button4Layout = new RelativeLayout.LayoutParams(getButtonWidth(), LayoutParams.FILL_PARENT);
		// button4Layout.topMargin = 5;
		button4.setBackgroundColor(buttonColor);
		button4.setLayoutParams(button4Layout);
		button4.setText("״̬");
		button4.setId(8);
		// ���ð�ť�����λ��
		button1Layout.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
		button2Layout.addRule(RelativeLayout.RIGHT_OF, button1.getId());
		button3Layout.addRule(RelativeLayout.RIGHT_OF, button2.getId());
		button4Layout.addRule(RelativeLayout.RIGHT_OF, button3.getId());
		// ��Ӱ�ť��buttonPanel
		buttonPanel.addView(button1);
		buttonPanel.addView(button2);
		buttonPanel.addView(button3);
		buttonPanel.addView(button4);

		// Ϊ��ť���Ӽ�����
		button1.setOnClickListener(new Button1Listener());
		button2.setOnClickListener(new Button2Listener());
		button3.setOnClickListener(new Button3Listener());
		button4.setOnClickListener(new Button4Listener());
	}

	public MainView(Context context) {
		// TODO MainView ���캯��
		super(context);
		this.context = context;
		// �������пռ�Ķ���ʵ��
		createComponent(context);

		// ����titlePanel����
		setupTitlePanel();
		// ����bodyPanel����
		setupBodyPanel();
		// ����buttonPanel����
		setupButtonPanel();
		// ����contentPanel����
		setupContentPanel();
		
		// ���ø���ť����
		setupButtons();

		// Ĭ����ʾ CatalogView                                                              
		contentPanel.removeAllViews();
		contentPanel.addView(catalogView);
		setButtonsStatus("button1");
	}

	protected void onCreate(Context context) {
		// Button btn = new Button(context);
		// this.addView(btn);
	}

	@Override
	protected void onLayout(boolean arg0, int arg1, int arg2, int arg3, int arg4) {
		super.onLayout(arg0, arg1, arg2, arg3, arg4);
		// TODO Auto-generated method stub
	}
}
