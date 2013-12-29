package com.sgf.widget;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.Toast;

import com.sgf.favshop.R;

public class widgetProv extends AppWidgetProvider {
	
	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		
		if (intent.getAction().equals(ACTION_WIDGET_RECEIVER)) {
			String msg = "null";
			try {
			msg = intent.getStringExtra("msg");
			} catch (NullPointerException e) {
			Log.e("Error", "msg = null");
			}
			Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
			PendingIntent contentIntent = PendingIntent.getActivity(context, 0, intent, 0);
			NotificationManager notificationManager =
			(NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
			Notification noty = new Notification(R.drawable.appwidget_dark_bg, "Button 1 clicked",
			System.currentTimeMillis());
			noty.setLatestEventInfo(context, "Notice", msg, contentIntent);
			notificationManager.notify(1, noty);
			 }
		
		super.onReceive(context, intent);
	}

	public static String ACTION_WIDGET_RECEIVER = "ActionReceiverWidget";
	
  @Override
  public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
    super.onUpdate(context, appWidgetManager, appWidgetIds);

      RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.activity_widgetfav);
      
      Intent active = new Intent(context, com.sgf.favshop.NewFAVActivity.class);
      active.setAction(ACTION_WIDGET_RECEIVER);

      PendingIntent actionPendingIntent = PendingIntent.getActivity(context, 0, active, 0);
      views.setOnClickPendingIntent(R.id.boutonWidget, actionPendingIntent);

      appWidgetManager.updateAppWidget(appWidgetIds, views);
      
    }

}