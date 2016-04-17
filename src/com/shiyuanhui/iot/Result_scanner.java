package com.shiyuanhui.iot;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.qr_codescan.MainActivity;
import com.example.qr_codescan.R;

public class Result_scanner extends Activity implements OnClickListener{
	private TextView resultscanner;
	private Button surescanner;
	private Button returnscanner;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.result);
		resultscanner = (TextView)findViewById(R.id.result_scanner);
		surescanner = (Button)findViewById(R.id.sure_scanner);
		returnscanner = (Button)findViewById(R.id.return_scanner);
		surescanner.setOnClickListener(this);
		returnscanner.setOnClickListener(this);
		Intent intent = getIntent();
		//Bundle bundle = intent.getExtras();
		resultscanner.setText(intent.getStringExtra("resultString"));
		
		
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.sure_scanner:
		{
			Toast.makeText(Result_scanner.this, "≥…π¶ΩË»°", Toast.LENGTH_LONG).show();
		}
			break;
		case R.id.return_scanner:
		{
		 Intent intent = new Intent(Result_scanner.this,ThirdActivity.class);
	     startActivity(intent);
		}
		break;
		default:
			break;
		}
	}

}
