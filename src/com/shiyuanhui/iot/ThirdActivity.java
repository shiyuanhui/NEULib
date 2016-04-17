package com.shiyuanhui.iot;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.qr_codescan.MainActivity;
import com.example.qr_codescan.R;
import com.shiyuanhui.xml.BookContentHandler;


public class ThirdActivity extends Activity implements OnClickListener{
	private TextView showusername;
	private Button borrowbook;
	private Button haveborrowedbook;
	private Button newpassword;
	private SharedPreferences pref;
	private Button bookidefication;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.manager_layout);
		pref = PreferenceManager.getDefaultSharedPreferences(this);
		showusername = (TextView)findViewById(R.id.show_user_name);
		bookidefication = (Button)findViewById(R.id.book_idefication);
		borrowbook = (Button)findViewById(R.id.borrow_book);
		haveborrowedbook = (Button)findViewById(R.id.have_borrowed_book);
		newpassword = (Button)findViewById(R.id.new_password);
		borrowbook.setOnClickListener(this);
		haveborrowedbook.setOnClickListener(this);
		newpassword.setOnClickListener(this);
		bookidefication.setOnClickListener(this);
		String account = pref.getString("account", "");
		try {
			showusername.setText("欢迎"+JIAMI.decrypt(SecondActivity.masterPassword, account));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		}


	@Override
	public void onClick(View v) {
      switch (v.getId()) {
     case R.id.borrow_book:
     {
    	 Intent intent = new Intent(ThirdActivity.this,MainActivity.class);
    	 startActivity(intent);
    	 
     }
	   break;
     case R.id.have_borrowed_book:
     {
    	Intent intent = new Intent(ThirdActivity.this,MyBookActivity.class);
    	startActivity(intent);
    	
     }
	   break;
     case R.id. new_password:
     {
    	 Toast.makeText(ThirdActivity.this, "本功能暂未开放", Toast.LENGTH_LONG).show();
    	 
     }
	   break;
     case R.id.book_idefication:
     {
    	Intent intent = new Intent(ThirdActivity.this,Future.class);
     	startActivity(intent);
     }
     break;
     default:
	   break;
    }		
	}
	

}
