package com.shiyuanhui.iot;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;

import com.example.qr_codescan.R;


public class FirstActivity extends Activity implements OnClickListener{
	private Button login_button;
	private Button leave_button;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.welcome);
		login_button = (Button)findViewById(R.id.login);
		leave_button = (Button)findViewById(R.id.leave);
		login_button.setOnClickListener(this);
		leave_button.setOnClickListener(this);
		
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.login:
		{
			Intent intent = new Intent(FirstActivity.this,SecondActivity.class);
			startActivity(intent);
		}
			break;
        case R.id.leave:
        {
        	finish();
        }
			break; 
		default:
			break;
		}
	}
	

}
