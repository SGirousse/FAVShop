package ExemplePhoto.activity;

import ExemplePhoto.listener.AddPhotoPressListener;
import ExemplePhoto.tool.ImageUtility;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.sgf.favshop.R;

public class ExemplePhotoActivity extends Activity {

	private ImageButton buttonAdd;
	private Button buttonSearch;
	private ImageView imageView;
	private final static int CAMERA_REQUEST = 24;
	private final static int SELECT_PHOTO = 42;
	
	@SuppressLint("WrongViewCast")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		buttonAdd = (ImageButton)findViewById(R.id.imageButtonTakePic);
		buttonAdd.setOnClickListener(new AddPhotoPressListener(this,CAMERA_REQUEST));
		
//		buttonSearch = (Button) findViewById(R.id.buttonSearch);
//		buttonSearch.setOnClickListener(new SearchPhotoPressListener(this,SELECT_PHOTO));
		
		imageView = (ImageView)this.findViewById(R.id.imageViewPic);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);	
		return true;
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) { 
		
		if(resultCode == RESULT_OK){
			switch(requestCode){
				case CAMERA_REQUEST:
					ImageUtility.display_photo(this,imageView,data.getData(),128,128);
					break;
				case SELECT_PHOTO:
					ImageUtility.display_photo(this,imageView,data.getData(),128,-1);
					break;
			}
		}
    }
}
