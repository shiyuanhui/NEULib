package com.shiyuanhui.iot;


import com.example.qr_codescan.MainActivity;
import com.example.qr_codescan.R;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;


public class SecondActivity extends Activity implements OnClickListener{
	private SharedPreferences pref;
	private EditText usernameEdit;
	private EditText passwordEdit;
	private SharedPreferences.Editor editor;
	private Button login;
	private CheckBox remmember_pass;
	final static String masterPassword = "a";  
    String account_jieaes = "";//初始化解密后的账号
	String password_jieaes = "";
	String account_aes = "";//初始化加密后的账号
	String password_aes = "";
	/*private Handler handler = new Handler(){

		@Override
		public void handleMessage(Message msg) {
			switch (msg.what){
			case 0:
			{
				String data = (String)msg.obj;
				int data2 = 0;
				System.out.println(msg.obj);
				Intent intent = new Intent(MainActivity.this,ResultActivity.class);
				intent.putExtra("auto_data", data);
				intent.putExtra("auto_data2", data2);
		    	startActivity(intent);
			}
			default:break;
		}
	}
};*/

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.register_layout);
		pref = PreferenceManager.getDefaultSharedPreferences(this);
		usernameEdit = (EditText)findViewById(R.id.username);
		passwordEdit = (EditText)findViewById(R.id.password);
		login = (Button)findViewById(R.id.login);
		login.setOnClickListener(this);
		remmember_pass = (CheckBox)findViewById(R.id.remmember_pass);
		boolean isRememberPass = pref.getBoolean("remember_password", false);
		/*
		 * 如果记住密码，则从存储中读取账号密码并且进行解密，再次输入到输入框内
		 */
		if(isRememberPass){
			String account = pref.getString("account", "");
			String password = pref.getString("password", "");
			remmember_pass.setChecked(true);
			try {
				account_jieaes = JIAMI.decrypt(masterPassword, account);
				password_jieaes = JIAMI.decrypt(masterPassword, password);
				usernameEdit.setText(account_jieaes);
				passwordEdit.setText(password_jieaes);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			remmember_pass.setChecked(true);
		}
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.other, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.delete_info:
		    {
		    	editor = pref.edit();
		    	editor.clear();
		    	editor.commit();
		    	Toast.makeText(SecondActivity.this, "账号信息已删除", Toast.LENGTH_SHORT).show();
		    }
			break;
		case R.id.about_owner:
		    {
		    	Intent intent = new Intent(SecondActivity.this,AboutMe.class);
		    	startActivity(intent);		
		     	break;
		    }
		default:
			break;		
		}
		return true;
	}

	
	@Override
	public void onClick(View view) {
		String account = usernameEdit.getText().toString();
		String password = passwordEdit.getText().toString();
      switch (view.getId()) {   
	  case R.id.login:
	  {
		  
		 
		  HttpUtil.sendRequestWithHttpClient(account,password,"connect");
		  if(HttpUtil.EditSucess())//确定服务器返回的是200才执行
		  {
			  editor = pref.edit();
			  if(remmember_pass.isChecked()){
				  editor.putBoolean("remember_password", true);
				  try {
					account_aes = JIAMI.encrypt(masterPassword, account);
					password_aes = JIAMI.encrypt(masterPassword, password);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				  editor.putString("account", account_aes);
				  editor.putString("password", password_aes);
				  editor.commit();
				  //Intent intent = new Intent(SecondActivity.this,ThirdActivity.class);
				  //startActivity(intent);
				  
			  }
			  else{
				  editor.clear();
				  editor.commit();
				 
			  }
			 Intent intent = new Intent(SecondActivity.this,ThirdActivity.class);
			  startActivity(intent);
		  } 
		  else Toast.makeText(SecondActivity.this, "账号或者密码错误", Toast.LENGTH_LONG).show();
		  break;
	  }
	
	default:
		break;
	}
	}
}
