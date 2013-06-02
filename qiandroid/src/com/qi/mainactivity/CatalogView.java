package com.qi.mainactivity;

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
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;

public class CatalogView extends RelativeLayout {

	Context context;
	RelativeLayout buttonPanel;
	RelativeLayout contentPanel;

	// ���԰�װ������
	public Button button1;
	public Button button2;
	public Button button3;
	public Button button4;
	public Button button5;
	public Button button6;

	int buttonCount = 6;
	int buttonColor = 0xffC0C0C0;
	int buttonSelColor = 0xffD0D0D0;
	int buttonTextColor = 0xff000000;
	int buttonTextSelColor = 0xff0070f0;
	int buttonPanelColor = 0xffC0C0C0;

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
			setButtonsStatus("button1");
		}
	};

	class Button2Listener implements Button.OnClickListener {
		@Override
		public void onClick(View arg0) {
			setButtonsStatus("button2");
		}
	}

	class Button3Listener implements Button.OnClickListener {
		@Override
		public void onClick(View arg0) {
			setButtonsStatus("button3");
		}
	}

	class Button4Listener implements Button.OnClickListener {
		@Override
		public void onClick(View arg0) {
			setButtonsStatus("button4");
		}
	}

	class Button5Listener implements Button.OnClickListener {
		@Override
		public void onClick(View arg0) {
			setButtonsStatus("button5");
		}
	}

	class Button6Listener implements Button.OnClickListener {
		@Override
		public void onClick(View arg0) {
			setButtonsStatus("button6");
		}
	}

	public int getButtonWidth() {
		Display display = ((Activity) context).getWindowManager().getDefaultDisplay();
		int width = display.getWidth();
		int btnWidth = width / buttonCount;
		return btnWidth;
	}

	public CatalogView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		/*-------------------------------------------*
		 *              ���ó�ʼ������                                      *
		 *-------------------------------------------*/
		this.context = context;
		buttonPanel = new RelativeLayout(context);
		contentPanel = new RelativeLayout(context);
		button1 = new Button(context);
		button2 = new Button(context);
		button3 = new Button(context);
		button4 = new Button(context);
		button5 = new Button(context);
		button6 = new Button(context);
		/*-------------------------------------------*
		 *              ����Catalog����                                       *
		 *-------------------------------------------*/
		// setBackgroundColor(0xffff0000);
		RelativeLayout.LayoutParams catalogViewLayout = new RelativeLayout.LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT);
		setLayoutParams(catalogViewLayout);
		/*-------------------------------------------*
		 *       ����buttonPanel��contentPanel        *
		 *-------------------------------------------*/
		// ����buttonPanel����
		RelativeLayout.LayoutParams btnPanelLayout = new RelativeLayout.LayoutParams(LayoutParams.FILL_PARENT, 50);
		buttonPanel.setLayoutParams(btnPanelLayout);
		buttonPanel.setBackgroundColor(buttonPanelColor);
		buttonPanel.setId(1);
		// ����contentPanel����
		RelativeLayout.LayoutParams contentLayout = new RelativeLayout.LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT);
		contentPanel.setLayoutParams(contentLayout);
		// contentPanel.setBackgroundColor(0xff0070f0);
		contentPanel.setId(2);
		// ����buttonPanel��contentPanel�����λ��
		btnPanelLayout.addRule(RelativeLayout.ALIGN_PARENT_TOP);
		contentLayout.addRule(RelativeLayout.BELOW, buttonPanel.getId());
		// ����bodyPanel
		this.addView(buttonPanel);
		this.addView(contentPanel);
		/*-------------------------------------------*
		 *             ����button����                                            *
		 *-------------------------------------------*/
		// ����button1������
		RelativeLayout.LayoutParams button1Layout = new RelativeLayout.LayoutParams(getButtonWidth(), LayoutParams.FILL_PARENT);
		button1.setLayoutParams(button1Layout);
		// button1Layout.topMargin = 5;
		button1.setText("����");
		button1.setTextSize(10);
		button1.setBackgroundColor(buttonColor);
		button1.setId(3);
		// ����button2������
		RelativeLayout.LayoutParams button2Layout = new RelativeLayout.LayoutParams(getButtonWidth(), LayoutParams.FILL_PARENT);
		button2.setLayoutParams(button2Layout);
		// button2Layout.topMargin = 5;
		button2.setText("���");
		button2.setTextSize(10);
		button2.setBackgroundColor(buttonColor);
		button2.setId(4);
		// ����button3������
		RelativeLayout.LayoutParams button3Layout = new RelativeLayout.LayoutParams(getButtonWidth(), LayoutParams.FILL_PARENT);
		button3.setLayoutParams(button3Layout);
		// button3Layout.topMargin = 5;
		button3.setText("��ҵ");
		button3.setTextSize(10);
		button3.setBackgroundColor(buttonColor);
		button3.setId(5);
		// ����button4������
		RelativeLayout.LayoutParams button4Layout = new RelativeLayout.LayoutParams(getButtonWidth(), LayoutParams.FILL_PARENT);
		button4.setLayoutParams(button4Layout);
		// button4Layout.topMargin = 5;
		button4.setText("ѧ��");
		button4.setTextSize(10);
		button4.setBackgroundColor(buttonColor);
		button4.setId(6);
		// ����button5������
		RelativeLayout.LayoutParams button5Layout = new RelativeLayout.LayoutParams(getButtonWidth(), LayoutParams.FILL_PARENT);
		button5.setLayoutParams(button5Layout);
		// button5Layout.topMargin = 5;
		button5.setText("�Ļ�");
		button5.setTextSize(10);
		button5.setBackgroundColor(buttonColor);
		button5.setId(7);
		// ����button5������
		RelativeLayout.LayoutParams button6Layout = new RelativeLayout.LayoutParams(getButtonWidth(), LayoutParams.FILL_PARENT);
		button6.setLayoutParams(button6Layout);
		// button6Layout.topMargin = 5;
		button6.setText("����");
		button6.setTextSize(10);
		button6.setBackgroundColor(buttonColor);
		button6.setId(8);
		// ���ð�ť�����λ��
		button1Layout.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
		button2Layout.addRule(RelativeLayout.RIGHT_OF, button1.getId());
		button3Layout.addRule(RelativeLayout.RIGHT_OF, button2.getId());
		button4Layout.addRule(RelativeLayout.RIGHT_OF, button3.getId());
		button5Layout.addRule(RelativeLayout.RIGHT_OF, button4.getId());
		button6Layout.addRule(RelativeLayout.RIGHT_OF, button5.getId());
		// ��Ӱ�ť��buttonPanel
		buttonPanel.addView(button1);
		buttonPanel.addView(button2);
		buttonPanel.addView(button3);
		buttonPanel.addView(button4);
		buttonPanel.addView(button5);
		buttonPanel.addView(button6);

		/*-------------------------------------------*
		 *            Ϊ��ť��Ӽ�����                                           *
		 *-------------------------------------------*/
		button1.setOnClickListener(new Button1Listener());
		button2.setOnClickListener(new Button2Listener());
		button3.setOnClickListener(new Button3Listener());
		button4.setOnClickListener(new Button4Listener());
		button5.setOnClickListener(new Button5Listener());
		button6.setOnClickListener(new Button6Listener());
		/*-------------------------------------------*
		 *            Ĭ����ʾ ����Ŀ¼����Ϣ                           *                                                  
		 *-------------------------------------------*/
		setButtonsStatus("button1");
	}
}
