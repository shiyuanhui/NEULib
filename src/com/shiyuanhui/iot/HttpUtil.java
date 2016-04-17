package com.shiyuanhui.iot;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import android.os.Handler;
import android.os.Message;
import android.widget.Toast;

public class HttpUtil {
	
	private static boolean resultsuccess = false;
	public static void sendRequestWithHttpClient(final String username,final String password,final String operation){
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				try {
					HttpClient httpclient = new DefaultHttpClient();
					HttpPost httpPost = new HttpPost("http://ipgw.neu.edu.cn/ipgw/ipgw.ipgw");
					List<NameValuePair>params = new ArrayList<NameValuePair>();
					params.add(new BasicNameValuePair("uid", username));
					params.add(new BasicNameValuePair("password", password));
					params.add(new BasicNameValuePair("operation", operation));
					params.add(new BasicNameValuePair("range", "2"));
					params.add(new BasicNameValuePair("timeout", "1"));
					UrlEncodedFormEntity entity = new UrlEncodedFormEntity(params,"utf-8");
					httpPost.setEntity(entity);
					//System.out.println(entity.toString());
					HttpResponse httpResponse = httpclient.execute(httpPost);
					//System.out.println(httpResponse.getStatusLine().getStatusCode());
					if(httpResponse.getStatusLine().getStatusCode() == 200){
						HttpEntity httpentity = httpResponse.getEntity();
						String response = EntityUtils.toString(httpentity,"utf-8");
						if(response.indexOf("当前连接数超过预定值") != -1 || response.indexOf("网络断开成功") != -1 || response.indexOf("断开全部连接成功") != -1 || response.indexOf("网络连接成功") != -1)
						{
							resultsuccess = true;
						}
						
					else {
						resultsuccess = false;
						
					}
					
				} 
				}catch (Exception e) {
					// TODO: handle exception
				}
			}
		}).start();
	}
	
	public static boolean EditSucess(){
		return resultsuccess;
		
	}
	
	

}
