package ExemplePhoto.listener;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;

public class AddPhotoPressListener implements OnClickListener {

	private Activity activity;
	private int CAMERA_REQUEST;
	
	public AddPhotoPressListener(Activity activity, int camera_request){
		this.activity = activity;
		this.CAMERA_REQUEST = camera_request;
	}
	
	@Override
    public void onClick(View v) {
		Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        activity.startActivityForResult(cameraIntent, CAMERA_REQUEST); 
    }
}
