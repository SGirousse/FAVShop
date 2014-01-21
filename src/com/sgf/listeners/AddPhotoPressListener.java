package com.sgf.listeners;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;

public class AddPhotoPressListener implements OnClickListener {

	private Activity activity;
	private int CAMERA_REQUEST;
	private Uri uri;
	
	public AddPhotoPressListener(Activity activity, int camera_request, Uri uri){
		this.activity = activity;
		this.CAMERA_REQUEST = camera_request;
		this.uri = uri;
	}
	
	@Override
    public void onClick(View v) {
		Log.i("info",uri.toString());
		Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
		cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
        activity.startActivityForResult(cameraIntent, CAMERA_REQUEST); 
    }
}
