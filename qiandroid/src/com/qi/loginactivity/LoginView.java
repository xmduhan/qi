package com.qi.loginactivity;

import com.qi.Dialogs;
import com.qi.loginactivity.LoginMainView.Button1Listener;
import com.qi.loginactivity.LoginMainView.Button2Listener;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RectShape;
import android.graphics.drawable.shapes.RoundRectShape;
import android.text.InputType;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.graphics.Paint;

public class LoginView extends RelativeLayout {
	Context context;
	RelativeLayout loginForm;
	Button loginButton;
	Button forgetButton;
	EditText phoneEditText;
	EditText passwordEditText;
	TextView phoneTextView;
	TextView passwordTextView;
	Dialogs dialogs;

	int loginFromTopMargin = 50;
	int loginFromLeftMargin = 20;
	int loginFromRightMargin = 20;
	int textViewLeftMargin = 30;
	int textViewTopMargin = 30;
	int textEditTopMargin = 25;
	int textEditRightMargin = 30;
	int buttonLeftMargin = 15;
	int buttonRightMargin = 5;
	int buttonHeight = 55;
	int textViewWidth = 90;
	int editTextSize = 12;

	public void log(String msg) {
		Log.println(Log.ASSERT, "assert", msg);
	}

	public int getButtonWidth() {
		Display display = ((Activity) context).getWindowManager().getDefaultDisplay();
		int width = display.getWidth();
		int btnWidth = (width - loginFromLeftMargin - loginFromRightMargin - buttonLeftMargin - buttonRightMargin) / 2 - 10;
		return btnWidth;
	}

	void setButtonEnable(boolean enable) {
		loginButton.setEnabled(enable);
		forgetButton.setEnabled(enable);
	}

	class LoginButtonListener implements Button.OnClickListener {
		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			setButtonEnable(false);

			// 定义对话框
			dialogs.showmessage("提示", "忘记密码", //
					new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog, int which) {
							setButtonEnable(true);
						}
					}//
			);
		}
	};

	class ForgetButtonListener implements Button.OnClickListener {
		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			setButtonEnable(false);

			// 定义对话框
			dialogs.showmessage("提示", "忘记密码", //
					new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog, int which) {
							setButtonEnable(true);
						}
					}//
			);
		}
	}

	public void createComponent(Context context) {
		loginForm = new RelativeLayout(context);
		phoneTextView = new TextView(context);
		passwordTextView = new TextView(context);
		phoneEditText = new EditText(context);
		passwordEditText = new EditText(context);
		loginButton = new Button(context);
		forgetButton = new Button(context);
		dialogs = new Dialogs(context);
	}

	public void setupLoginForm() {
		// TODO 设置contentPanel属性
		RelativeLayout.LayoutParams loginFormLayout = new RelativeLayout.LayoutParams(LayoutParams.FILL_PARENT, 200);
		loginForm.setLayoutParams(loginFormLayout);

		loginForm.setId(5);
		// loginFormLayout.addRule(RelativeLayout.CENTER_HORIZONTAL);
		// loginFormLayout.addRule(RelativeLayout.CENTER_VERTICAL);
		loginFormLayout.topMargin = loginFromTopMargin;
		loginFormLayout.leftMargin = loginFromLeftMargin;
		loginFormLayout.rightMargin = loginFromRightMargin;

		int radius = 15;
		float[] outerR = new float[] { radius, radius, radius, radius, radius, radius, radius, radius };
		RoundRectShape roundRectShape = new RoundRectShape(outerR, null, null);
		// RectShape rectShape = new RectShape();
		ShapeDrawable shapeDrawable = new ShapeDrawable(roundRectShape);
		shapeDrawable.getPaint().setColor(0xffC0C0C0);
		shapeDrawable.getPaint().setStyle(Paint.Style.FILL);
		loginForm.setBackgroundDrawable(shapeDrawable);
		this.addView(loginForm);
	}

	public LoginView(Context context) {
		// TODO LoginView 构造函数
		super(context);
		this.context = context;
		setBackgroundColor(0x700070f0);
		RelativeLayout.LayoutParams loginViewPaperViewLayout = new RelativeLayout.LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT);
		setLayoutParams(loginViewPaperViewLayout);

		// 创建界面控件
		createComponent(context);

		// 设置登录框loginForm属性
		setupLoginForm();

		// 设置
		// phoneTextView
		RelativeLayout.LayoutParams phoneTextViewLayout = new RelativeLayout.LayoutParams(textViewWidth, 50);
		phoneTextView.setLayoutParams(phoneTextViewLayout);
		phoneTextView.setId(1);
		phoneTextViewLayout.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
		phoneTextViewLayout.topMargin = textViewTopMargin;
		phoneTextViewLayout.leftMargin = textViewLeftMargin;
		loginForm.addView(phoneTextView);
		phoneTextView.setText("手机号码");

		// phoneEditText
		RelativeLayout.LayoutParams phoneEditTextLayout = new RelativeLayout.LayoutParams(LayoutParams.FILL_PARENT, 50);
		phoneEditText.setLayoutParams(phoneEditTextLayout);
		phoneEditText.setId(3);
		phoneEditTextLayout.addRule(RelativeLayout.RIGHT_OF, phoneTextView.getId());
		phoneEditTextLayout.topMargin = textEditTopMargin;
		phoneEditTextLayout.rightMargin = textEditRightMargin;
		loginForm.addView(phoneEditText);
		phoneEditText.setInputType(InputType.TYPE_CLASS_PHONE);
		phoneEditText.setTextSize(editTextSize);

		// passwordTextView
		RelativeLayout.LayoutParams passwordTextViewLayout = new RelativeLayout.LayoutParams(textViewWidth, 50);
		passwordTextView.setLayoutParams(passwordTextViewLayout);
		passwordTextView.setId(2);
		passwordTextViewLayout.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
		passwordTextViewLayout.addRule(RelativeLayout.BELOW, phoneTextView.getId());
		passwordTextViewLayout.leftMargin = textViewLeftMargin;
		loginForm.addView(passwordTextView);
		passwordTextView.setText("密　　 码");

		// passwordEditText
		RelativeLayout.LayoutParams passwordEditTextLayout = new RelativeLayout.LayoutParams(LayoutParams.FILL_PARENT, 50);
		passwordEditText.setLayoutParams(passwordEditTextLayout);
		passwordEditText.setId(4);
		passwordEditTextLayout.addRule(RelativeLayout.BELOW, phoneEditText.getId());
		passwordEditTextLayout.addRule(RelativeLayout.RIGHT_OF, passwordTextView.getId());
		passwordEditTextLayout.rightMargin = textEditRightMargin;
		loginForm.addView(passwordEditText);
		passwordEditText.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
		passwordEditText.setTextSize(editTextSize);

		// loginButton
		RelativeLayout.LayoutParams loginButtonLayout = new RelativeLayout.LayoutParams(getButtonWidth(), buttonHeight);
		loginButton.setLayoutParams(loginButtonLayout);
		loginButton.setId(5);
		loginButtonLayout.addRule(RelativeLayout.BELOW, passwordEditText.getId());
		loginButtonLayout.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
		loginButtonLayout.leftMargin = buttonLeftMargin;
		loginButton.setText("登　　陆");
		loginButton.setOnClickListener(new LoginButtonListener());
		loginForm.addView(loginButton);

		// forgetButton
		RelativeLayout.LayoutParams forgetButtonLayout = new RelativeLayout.LayoutParams(getButtonWidth(), buttonHeight);
		forgetButton.setLayoutParams(forgetButtonLayout);
		forgetButton.setId(6);
		forgetButtonLayout.addRule(RelativeLayout.BELOW, passwordEditText.getId());
		forgetButtonLayout.addRule(RelativeLayout.RIGHT_OF, loginButton.getId());
		forgetButtonLayout.rightMargin = buttonRightMargin;
		forgetButton.setText("忘记密码");
		forgetButton.setOnClickListener(new ForgetButtonListener());
		loginForm.addView(forgetButton);

	}
}
