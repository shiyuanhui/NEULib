package com.shiyuanhui.iot;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ResourceBundle;

import javax.xml.parsers.SAXParserFactory;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EncodingUtils;
import org.apache.http.util.EntityUtils;
import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.qr_codescan.R;
import com.shiyuanhui.xml.BookContentHandler;



public class MyBookActivity extends Activity{
	/*public static final int SHOW_RESPONSE = 0;
	private Handler handler = new Handler(){
		public void handleMessage(Message msg){
			switch (msg.what) {
			case SHOW_RESPONSE:
				String response = (String)msg.obj;
				
				break;

			default:
				break;
			}
		}
	}*/
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.list_book);
		//sendhttp2();
		getFromAssets("resource.xml");
		String[]boo = new String[BookContentHandler.data.size()];
		for(int i = 0;i<boo.length;i++)
		{
			boo[i] = BookContentHandler.data.get(i);
		}	
		ArrayAdapter<String>adapter = new ArrayAdapter<String>(MyBookActivity.this, android.R.layout.simple_list_item_1,boo);
    	ListView listView = (ListView)findViewById(R.id.booklist);
    	listView.setAdapter(adapter);
	}
	
	private void sendhttp(){
		new Thread(new Runnable() {
		@Override
		public void run() {
			try {
				HttpClient httpClient = new DefaultHttpClient();
				HttpGet httpGet = new HttpGet("http://localhost:8081/book/resource.xml"); 
				HttpResponse httpResponse = httpClient.execute(httpGet);
				System.out.println(httpResponse.getStatusLine().getStatusCode());
				if(httpResponse.getStatusLine().getStatusCode() == 304)
				{
					HttpEntity entity = httpResponse.getEntity();
					String response = EntityUtils.toString(entity,"utf-8");
					//System.out.println("11111111111111");
				   		    
					parseXMLWithSax(response);
					
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
		}}).start();}

		private void parseXMLWithSax(String xmlData) {
			try {
				SAXParserFactory factory = SAXParserFactory.newInstance();
				XMLReader xmlReader = factory.newSAXParser().getXMLReader();
				BookContentHandler handler = new BookContentHandler();
				xmlReader.setContentHandler(handler);
				xmlReader.parse(new InputSource(new StringReader(xmlData)));
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	 
		/*private void sendhttp2(){
			new Thread(new Runnable() {
				
				@Override
				public void run() {
					//HttpURLConnection connection = null;
					try {
						URL url = new URL("http://localhost:8081/book/resource.xml");
						//connection = (HttpURLConnection)url.openConnection();
						//connection.setRequestMethod("GET");
						//connection.setConnectTimeout(8000);
						//connection.setReadTimeout(8000);
						//InputStream in = getResources().getAssets().open(resources);
						//InputStream in = connection.getInputStream();
						//BufferedReader reader = new BufferedReader(new InputStreamReader(in));
						StringBuilder response = new StringBuilder();
						String line;
						while((line = reader.readLine()) != null){
							response.append(line);
						}
						System.out.println(response.toString());
						parseXMLWithSax(response.toString());
					} catch (Exception e) {
						// TODO: handle exception
					}finally{
						if(connection != null){
							connection.disconnect();
						}
					}
				}
			}).start();
		}*/
		public void getFromAssets(String fileName){ 
			String Result="";
            try { 
                 InputStreamReader inputReader = new InputStreamReader( getResources().getAssets().open(fileName) ); 
                BufferedReader bufReader = new BufferedReader(inputReader);
                String line="";
                
                while((line = bufReader.readLine()) != null)
                    Result += line;
                
            } catch (Exception e) { 
                e.printStackTrace(); 
            }
            parseXMLWithSax(Result);
    }
}
