package com.sgf.favshop;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.sgf.listeners.FAVItButton;
import com.sgf.listeners.FAVViewButton;
import com.sgf.listeners.InfoButtonListener;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.Menu;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.TextView;

public class MainActivity extends Activity {
	
	private FAVItButton _FAVIt_button;
	private FAVViewButton _FAVview_button;
	private InfoButtonListener _infoButtonListener;
	public static final String EXTRA_MESSAGE = "message";
    public static final String PROPERTY_REG_ID = "registration_id";

    String SENDER_ID = "44913897700";

    static final String TAG = "test";
    GoogleCloudMessaging gcm;
    Context context;
    String regid;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        // -- Listeners -- //
        //Fav it
        _FAVIt_button = new FAVItButton(this);
        ImageButton favit_imagebutton = (ImageButton) findViewById(R.id.imageButtonFAVIt);
        favit_imagebutton.setOnClickListener(_FAVIt_button);
        //Show all FAVs
        _FAVview_button = new FAVViewButton(this);
        ImageButton favview_imagebutton = (ImageButton) findViewById(R.id.imageButtonFAVView);
        favview_imagebutton.setOnClickListener(_FAVview_button);

		_infoButtonListener = new InfoButtonListener(this);
		ImageButton info_imagebutton = (ImageButton) findViewById(R.id.imageButtonInfo);
		info_imagebutton.setOnClickListener(_infoButtonListener);
		
		gcm = GoogleCloudMessaging.getInstance(this);

		new RegisterBackground().execute();	
		
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
    class RegisterBackground extends AsyncTask<String,String,String>{

		@Override
		protected String doInBackground(String... arg0) {
			// TODO Auto-generated method stub
			String msg = "";
			try {
                if (gcm == null) {
                    gcm = GoogleCloudMessaging.getInstance(context);
                }
                regid = gcm.register(SENDER_ID);
                msg = "Dvice registered, registration ID=" + regid;
                Log.d("111", msg);
                sendRegistrationIdToBackend(regid);

            } catch (IOException ex) {
                msg = "Error :" + ex.getMessage();
            }
            return msg;
        }


		private void sendRegistrationIdToBackend(String regid) {
                  // this code will send registration id of a device to our own server.
			String url = "http://openbeelab.org/getdevice.php";
			List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("regid", regid));
           DefaultHttpClient httpClient = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost(url);
            try {
				httpPost.setEntity(new UrlEncodedFormEntity(params));
			} catch (UnsupportedEncodingException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

            try {
				HttpResponse httpResponse = httpClient.execute(httpPost);
			} catch (ClientProtocolException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}  
}}
}