package activity;

import tool.ImageUtility;
import listener.AddPhotoPressListener;
import listener.SearchPhotoPressListener;
import com.example.printcameraforresult.R;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.widget.Button;
import android.widget.ImageView;

public class ExemplePhotoActivity extends Activity {

	private Button buttonAdd;
	private Button buttonSearch;
	private ImageView imageView;
	private final static int CAMERA_REQUEST = 24;
	private final static int SELECT_PHOTO = 42;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		buttonAdd = (Button) findViewById(R.id.buttonAdd);
		buttonAdd.setOnClickListener(new AddPhotoPressListener(this,CAMERA_REQUEST));
		
		buttonSearch = (Button) findViewById(R.id.buttonSearch);
		buttonSearch.setOnClickListener(new SearchPhotoPressListener(this,SELECT_PHOTO));
		
		imageView = (ImageView)this.findViewById(R.id.imageView);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);	
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
