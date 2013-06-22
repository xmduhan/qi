package com.qi.loginactivity;

import com.qi.mainactivity.CatalogView;
import com.qi.mainactivity.UnfinishedPaperView;
import com.qi.mainactivity.UserPaperView;
import com.qi.mainactivity.UserStatusView;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;

public class LoginMainView extends RelativeLayout {
	Context context;
	RelativeLayout titlePanel;
	RelativeLayout bodyPanel;
	RelativeLayout buttonPanel;
	RelativeLayout contentPanel;	
	public Button button1;
	public Button button2;
	int buttonCount = 2;

	int buttonColor = 0xff0070f0;
	int buttonSelColor = 0xff0060f0;
	int buttonTextColor = 0xff000000;
	int buttonTextSelColor = 0xffffffff;
	int titlePanelColor = 0xff0070f0;
	int buttonPanelColor = 0xff0070f0;

	LoginView loginView;
	RegisterView registerView;

	public void log(String msg) {
		Log.println(Log.ASSERT, "assert", msg);
	}

	public void createComponent(Context context) {
		// TODO �������н���ؼ���
		titlePanel = new RelativeLayout(context);
		bodyPanel = new RelativeLayout(context);
		buttonPanel = new RelativeLayout(context);
		contentPanel = new RelativeLayout(context);
		button1 = new Button(context);
		button2 = new Button(context);
		loginView = new LoginView(context);
		registerView = new RegisterView(context);
	}

	public void setupTitlePanel() {
		// TODO ����titlePanel����
		RelativeLayout.LayoutParams titleLayout = new RelativeLayout.LayoutParams(LayoutParams.FILL_PARENT, 60);
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
		RelativeLayout.LayoutParams btnPanelLayout = new RelativeLayout.LayoutParams(LayoutParams.FILL_PARENT, Constant.LoginViewButtonPanelHeight);
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
	
	

	public int getButtonWidth() {
		Display display = ((Activity) context).getWindowManager().getDefaultDisplay();
		int width = display.getWidth();
		int btnWidth = width / buttonCount;
		return btnWidth;
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
			contentPanel.addView(loginView);
			setButtonsStatus("button1");
		}
	};

	class Button2Listener implements Button.OnClickListener {
		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			contentPanel.removeAllViews();
			contentPanel.addView(registerView);
			setButtonsStatus("button2");
		}
	}

	public void setupButtons() {
		// TODO ���ø���ť������	 
		
		// ����button1������
		RelativeLayout.LayoutParams button1Layout = new RelativeLayout.LayoutParams(getButtonWidth(), LayoutParams.FILL_PARENT);
		// button1Layout.topMargin = 5;
		button1.setLayoutParams(button1Layout);
		button1.setBackgroundColor(buttonColor);
		button1.setText("��½");
		button1.setId(5);		
		// ����button2������
		RelativeLayout.LayoutParams button2Layout = new RelativeLayout.LayoutParams(getButtonWidth(), LayoutParams.FILL_PARENT);
		// button2Layout.topMargin = 5;
		button2.setBackgroundColor(buttonColor);
		button2.setLayoutParams(button2Layout);
		button2.setText("ע��");
		button2.setId(6);
		// ����button3������
		RelativeLayout.LayoutParams button3Layout = new RelativeLayout.LayoutParams(getButtonWidth(), LayoutParams.FILL_PARENT);
		// button3Layout.topMargin = 5;

		// ���ð�ť�����λ��
		button1Layout.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
		button2Layout.addRule(RelativeLayout.RIGHT_OF, button1.getId());

		// ��Ӱ�ť��buttonPanel
		buttonPanel.addView(button1);
		buttonPanel.addView(button2);

		// Ϊ��ť���Ӽ�����
		button1.setOnClickListener(new Button1Listener());
		button2.setOnClickListener(new Button2Listener());
	}

	public LoginMainView(Context context) {
		// TODO LoginMainView ���캯��
		super(context);
		this.context = context;
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

		// Ĭ����ʾ loginView
		contentPanel.removeAllViews();
		contentPanel.addView(loginView);
		setButtonsStatus("button1");
	}

}
