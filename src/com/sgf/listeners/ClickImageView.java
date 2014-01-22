package com.sgf.listeners;

import com.sgf.favshop.R;
import com.sgf.tool.ImageUtility;
import android.app.Activity;
import android.app.Dialog;
import android.net.Uri;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

public class ClickImageView  implements OnClickListener {

	private Activity activity;
	private Uri uri;
	
	public ClickImageView(Activity activity, String uri){
		this.activity = activity;
		this.uri = Uri.parse(uri);
	}
	
	@Override
    public void onClick(View v) {
		Dialog dia = new Dialog(activity);
		dia.setContentView(R.layout.large_image_view);
		ImageUtility.display_photo(dia.getContext(),(ImageView)dia.findViewById(R.id.imageViewPic),uri,600,600);
		dia.show();
    }
}
