package com.sgf.libext;

/* http://techlovejump.in/2013/11/android-push-notification-using-google-cloud-messaging-gcm-php-google-play-service-library/
 * techlovejump.in
 * tutorial link
 * 
 *  */

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.IntentService;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.sgf.database.ArticleDAO;
import com.sgf.favshop.R;
import com.sgf.favshop.ShowFAVActivity;
import com.sgf.pojo.Article;
public class GcmIntentService extends IntentService{
	Context context;
	public static final int NOTIFICATION_ID = 1;
    private NotificationManager mNotificationManager;
    NotificationCompat.Builder builder;
    public static final String TAG = "GCM Demo";
	private ArticleDAO _article_dao;
	private long _id_article_notif;

	public GcmIntentService() {
		super("GcmIntentService");
	}

	@Override
	protected void onHandleIntent(Intent intent) {

		Bundle extras = intent.getExtras();
		String msg = intent.getStringExtra("message");
		GoogleCloudMessaging gcm = GoogleCloudMessaging.getInstance(this);
		String messageType = gcm.getMessageType(intent);

		 if (!extras.isEmpty()) {

			 if (GoogleCloudMessaging.
	                    MESSAGE_TYPE_SEND_ERROR.equals(messageType)) {
	                sendNotification("Send error: " + extras.toString());
	            } else if (GoogleCloudMessaging.
	                    MESSAGE_TYPE_DELETED.equals(messageType)) {
	                sendNotification("Deleted messages on server: " +
	                        extras.toString());
	            // If it's a regular GCM message, do some work.
	            } else if (GoogleCloudMessaging.
	                    MESSAGE_TYPE_MESSAGE.equals(messageType)) {
	                // This loop represents the service doing some work.
	                for (int i=0; i<5; i++) {
	                    Log.i(TAG, "Working... " + (i+1)
	                            + "/5 @ " + SystemClock.elapsedRealtime());
	                    try {
	                        Thread.sleep(500);
	                    } catch (InterruptedException e) {
	                    }
	                }
	                Log.i(TAG, "Completed work @ " + SystemClock.elapsedRealtime());
	                
	                // Update database according to received message
	                updateDatabase(msg);
	                // Post notification of received message
	                sendNotification(msg);
	                Log.i(TAG, "Received: " + extras.toString());
	            }
	        }
		 GcmBroadcastReceiver.completeWakefulIntent(intent);
	}
	
	private void sendNotification(String msg) {
        mNotificationManager = (NotificationManager)
                this.getSystemService(Context.NOTIFICATION_SERVICE);
        
        if(_id_article_notif>0){
	        Intent i = new Intent(this, ShowFAVActivity.class );
			i.putExtra("article_toshow", _id_article_notif);
			
			PendingIntent contentIntent = PendingIntent.getActivity(this, 0,  i, PendingIntent.FLAG_UPDATE_CURRENT);
	    
	
	/*
	        Intent myintent = new Intent(this, ReceiveActivity.class);
			myintent.putExtra("message", msg);
	        PendingIntent contentIntent = PendingIntent.getActivity(this, 0,
	        		myintent, PendingIntent.FLAG_UPDATE_CURRENT);*/
	
		    NotificationCompat.Builder mBuilder =
		            new NotificationCompat.Builder(this)
		    .setSmallIcon(R.drawable.ic_stat_gcm)
		    .setContentTitle("GCM Notification")
		    .setStyle(new NotificationCompat.BigTextStyle()
		    .bigText(msg))
		    .setContentText(msg);
		
		    mBuilder.setContentIntent(contentIntent);
		    mNotificationManager.notify(NOTIFICATION_ID, mBuilder.build());
        }
    }

	private void updateDatabase(String message){
		Log.i("TRACE_NOTIF", "private void updateDatabase(String message)");
		try {
			JSONObject json = new JSONObject(message);
			
			String barcode = json.getString("codebarre");
			String store = json.getString("magasin");
			String endofsail = json.getString("datefin");
			String sailprice = json.getString("nvPrix");
			
			// --- DB Update --- //
			_article_dao = new ArticleDAO(this);
			_article_dao.open(true);
			
			//Get matching flashed articles
			ArrayList<Article> a_list = _article_dao.getArticleByBarcode(barcode);
			
			//If there's an article matching
			if(a_list!=null){
				Log.i("TRACE_NOTIF", "private void updateDatabase(String message) : at least one article exists.");
				if(a_list.size()==1){ //Most of the cases
					Log.i("TRACE_NOTIF", "private void updateDatabase(String message) : "+a_list.get(0).getBarcode()+" "+a_list.get(0).getTitle());
					Article a = a_list.get(0);
					SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
					try {
						Date d = sdf.parse(endofsail);
						a.setEndofsail(d);
					} catch (ParseException e) {
						e.printStackTrace();
					}
					a.setSailprice(Float.valueOf(sailprice));
					_article_dao.updateArticle(a.getId(), a);
					_id_article_notif = a.getId();
				}else{
					//TODO Manage store+barcode conflict here - needs localization stuff
				}
			}
			_article_dao.close();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

		Log.i("TRACE_NOTIF", "private void updateDatabase(String message) - THE END");
		
	}
	
	
}