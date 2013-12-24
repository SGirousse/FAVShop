package listener;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;

public class SearchPhotoPressListener implements OnClickListener {

	private Activity activity;
	private int SELECT_PHOTO;
	
	public SearchPhotoPressListener(Activity activity, int camera_request){
		this.activity = activity;
		this.SELECT_PHOTO = camera_request;
	}
	
	@Override
    public void onClick(View v) {
		Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
		photoPickerIntent.setType("image/*");
		activity.startActivityForResult(photoPickerIntent, SELECT_PHOTO);  
    }
}
