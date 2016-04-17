package com.shiyuanhui.iot;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;

import com.example.qr_codescan.R;



public class AboutMe extends Activity{
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.about);
 }
}