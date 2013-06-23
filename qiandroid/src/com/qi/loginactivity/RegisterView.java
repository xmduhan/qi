package com.qi.loginactivity;

import com.qi.Dialogs;

import android.app.Activity;
import android.content.Context;
import android.graphics.Paint;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RectShape;
import android.graphics.drawable.shapes.RoundRectShape;
import android.text.InputType;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.RelativeLayout.LayoutParams;

public class RegisterView extends RelativeLayout {
	Context context;

	RelativeLayout registerForm;
	Button registerButton;
	EditText phoneEditText;
	EditText passwordEditText1;
	EditText passwordEditText2;
	TextView phoneTextView;
	TextView passwordTextView1;
	TextView passwordTextView2;

	int registerFromTopMargin = 50;
	int registerFromLeftMargin = 20;
	int registerFromRightMargin = 20;
	int textViewLeftMargin = 30;
	int textViewTopMargin = 30;
	int textEditTopMargin = 25;
	int textEditRightMargin = 30;
	int buttonTopMargin = 15;
	int buttonLeftMargin = 15;
	int buttonRightMargin = 5;
	int buttonHeight = 55;
	int textViewWidth = 90;
	int editTextSize = 12;

	public int getButtonWidth() {
		Display display = ((Activity) context).getWindowManager().getDefaultDisplay();
		int width = display.getWidth();
		int btnWidth = (width - registerFromLeftMargin - registerFromRightMargin - buttonLeftMargin - buttonRightMargin) - 20;
		return btnWidth;
	}

	public void createComponent(Context context) {
		registerForm = new RelativeLayout(context);
		phoneTextView = new TextView(context);
		phoneEditText = new EditText(context);
		passwordTextView1 = new TextView(context);
		passwordTextView2 = new TextView(context);
		passwordEditText1 = new EditText(context);
		passwordEditText2 = new EditText(context);
		registerButton = new Button(context);
	}

	class RegisterButtonListener implements Button.OnClickListener {
		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			(new Dialogs(context)).showmessage("提示", "注册");
		}
	};

	public void setupLoginForm() {
		// TODO 设置contentPanel属性
		RelativeLayout.LayoutParams registerFormLayout = new RelativeLayout.LayoutParams(LayoutParams.FILL_PARENT, 260);
		registerForm.setLayoutParams(registerFormLayout);
		registerForm.setId(5);
		// loginFormLayout.addRule(RelativeLayout.CENTER_HORIZONTAL);
		// loginFormLayout.addRule(RelativeLayout.CENTER_VERTICAL);
		registerFormLayout.topMargin = registerFromTopMargin;
		registerFormLayout.leftMargin = registerFromLeftMargin;
		registerFormLayout.rightMargin = registerFromRightMargin;
		int radius = 15;
		float[] outerR = new float[] { radius, radius, radius, radius, radius, radius, radius, radius };
		RoundRectShape roundRectShape = new RoundRectShape(outerR, null, null);
		RectShape rectShape = new RectShape();
		ShapeDrawable shapeDrawable = new ShapeDrawable(roundRectShape);
		shapeDrawable.getPaint().setColor(0xffC0C0C0);
		shapeDrawable.getPaint().setStyle(Paint.Style.FILL);
		registerForm.setBackgroundDrawable(shapeDrawable);
		this.addView(registerForm);
	}

	public RegisterView(Context context) {
		// TODO RegisterView 构造函数
		super(context);
		this.context = context;
		this.setBackgroundColor(0x700070f0);
		RelativeLayout.LayoutParams registerViewLayout = new RelativeLayout.LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT);
		setLayoutParams(registerViewLayout);

		// 初始化控件
		createComponent(context);

		// 设置注册框属性
		setupLoginForm();

		// 设置
		// phoneTextView
		RelativeLayout.LayoutParams phoneTextViewLayout = new RelativeLayout.LayoutParams(textViewWidth, 50);
		phoneTextView.setLayoutParams(phoneTextViewLayout);
		phoneTextView.setId(1);
		phoneTextViewLayout.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
		phoneTextViewLayout.topMargin = textViewTopMargin;
		phoneTextViewLayout.leftMargin = textViewLeftMargin;
		registerForm.addView(phoneTextView);
		phoneTextView.setText("手机号码");

		// phoneEditText
		RelativeLayout.LayoutParams phoneEditTextLayout = new RelativeLayout.LayoutParams(LayoutParams.FILL_PARENT, 50);
		phoneEditText.setLayoutParams(phoneEditTextLayout);
		phoneEditText.setId(2);
		phoneEditTextLayout.addRule(RelativeLayout.RIGHT_OF, phoneTextView.getId());
		phoneEditTextLayout.topMargin = textEditTopMargin;
		phoneEditTextLayout.rightMargin = textEditRightMargin;
		registerForm.addView(phoneEditText);
		phoneEditText.setInputType(InputType.TYPE_CLASS_PHONE);
		phoneEditText.setTextSize(editTextSize);

		// passwordTextView1
		RelativeLayout.LayoutParams passwordText1ViewLayout = new RelativeLayout.LayoutParams(textViewWidth, 50);
		passwordTextView1.setLayoutParams(passwordText1ViewLayout);
		passwordTextView1.setId(3);
		passwordText1ViewLayout.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
		passwordText1ViewLayout.addRule(RelativeLayout.BELOW, phoneTextView.getId());
		passwordText1ViewLayout.leftMargin = textViewLeftMargin;
		registerForm.addView(passwordTextView1);
		passwordTextView1.setText("密　　码");

		// passwordEditText1
		RelativeLayout.LayoutParams passwordEdit1TextLayout = new RelativeLayout.LayoutParams(LayoutParams.FILL_PARENT, 50);
		passwordEditText1.setLayoutParams(passwordEdit1TextLayout);
		passwordEditText1.setId(4);
		passwordEdit1TextLayout.addRule(RelativeLayout.BELOW, phoneEditText.getId());
		passwordEdit1TextLayout.addRule(RelativeLayout.RIGHT_OF, passwordTextView1.getId());
		passwordEdit1TextLayout.rightMargin = textEditRightMargin;
		registerForm.addView(passwordEditText1);
		passwordEditText1.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
		passwordEditText1.setTextSize(editTextSize);

		// passwordTextView2
		RelativeLayout.LayoutParams passwordText2ViewLayout = new RelativeLayout.LayoutParams(textViewWidth, 50);
		passwordTextView2.setLayoutParams(passwordText2ViewLayout);
		passwordTextView2.setId(5);
		passwordText2ViewLayout.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
		passwordText2ViewLayout.addRule(RelativeLayout.BELOW, passwordTextView1.getId());
		passwordText2ViewLayout.leftMargin = textViewLeftMargin;
		registerForm.addView(passwordTextView2);
		passwordTextView2.setText("确认密码");

		// passwordEditText1
		RelativeLayout.LayoutParams passwordEdit2TextLayout = new RelativeLayout.LayoutParams(LayoutParams.FILL_PARENT, 50);
		passwordEditText2.setLayoutParams(passwordEdit2TextLayout);
		passwordEditText2.setId(6);
		passwordEdit2TextLayout.addRule(RelativeLayout.BELOW, passwordTextView1.getId());
		passwordEdit2TextLayout.addRule(RelativeLayout.RIGHT_OF, passwordTextView2.getId());
		passwordEdit2TextLayout.rightMargin = textEditRightMargin;
		registerForm.addView(passwordEditText2);
		passwordEditText2.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
		passwordEditText2.setTextSize(editTextSize);

		// registerButton
		RelativeLayout.LayoutParams registerButtonLayout = new RelativeLayout.LayoutParams(getButtonWidth(), buttonHeight);
		registerButton.setLayoutParams(registerButtonLayout);
		registerButton.setId(7);
		registerButtonLayout.addRule(RelativeLayout.BELOW, passwordEditText2.getId());
		registerButtonLayout.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
		registerButtonLayout.topMargin = buttonTopMargin;
		registerButtonLayout.leftMargin = buttonLeftMargin;
		registerButton.setText("注　　册");
		registerButton.setOnClickListener(new RegisterButtonListener());
		registerForm.addView(registerButton);

	}

}
