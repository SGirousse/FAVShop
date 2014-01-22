package com.sgf.favshop;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.sgf.libext.ZBarConstants;
import com.sgf.libext.ZBarScannerActivity;
import com.sgf.listeners.AddPhotoPressListener;
import com.sgf.listeners.SaveNewFAVButton;
import com.sgf.listeners.SearchPhotoPressListener;
import com.sgf.tool.ImageUtility;

public class NewFAVActivity extends Activity implements OnClickListener{
	
	private SaveNewFAVButton _saveNewFAV_button;
	private static final int ZBAR_SCANNER_REQUEST = 0;
	private ImageView imageView;
	private final static int CAMERA_REQUEST = 24;
	private final static int SELECT_PHOTO = 42;
	private ImageButton buttonTakePic;
	private ImageButton buttonChoosePic;
	private String _image_view_uri;
	private float dpWidthHope = 60;
	private float dpHeightHope = 60;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
		
        super.onCreate(savedInstanceState);
        
        setContentView(R.layout.activity_newfav);    
        // -- Listeners -- //
        _saveNewFAV_button = new SaveNewFAVButton(this);
        
        buttonTakePic = (ImageButton) findViewById(R.id.imageButtonTakePic);
        buttonChoosePic = (ImageButton) findViewById(R.id.imageButtonChoosePic);
        Button saveNewFAV_button = (Button) findViewById(R.id.buttonSave);
        Button scan_button = (Button) findViewById(R.id.buttonScan);
        imageView = (ImageView)this.findViewById(R.id.imageViewPic);
        
        buttonTakePic.setOnClickListener(new AddPhotoPressListener(this,CAMERA_REQUEST));
        buttonChoosePic.setOnClickListener(new SearchPhotoPressListener(this,SELECT_PHOTO));
        saveNewFAV_button.setOnClickListener(_saveNewFAV_button);
        scan_button.setOnClickListener(this);
        
		////////////////////////////////////
		/// TEST AND DEBUG - TO BE FIXED ///
        TextView tv = (TextView) findViewById(R.id.textViewNewFavPriceContent);
        String s = String.valueOf((int) (Math.random()*1000));
        tv.setText(s);
		/// TEST AND DEBUG - TO BE FIXED ///		
		////////////////////////////////////
    }
    
    @Override
    public void onSaveInstanceState(Bundle bundle)
    {
    	super.onSaveInstanceState(bundle);
    	if(_image_view_uri != null){
    		bundle.putString("uri", _image_view_uri);
    	}
    }
    
    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        _image_view_uri = savedInstanceState.getString("uri");
        if(_image_view_uri != null){
        	ImageUtility.display_photo(this,imageView,Uri.parse(_image_view_uri),dpWidthHope,dpHeightHope);
        	imageView.setOnClickListener(new ClickImageView(this,_image_view_uri));
        }
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		Intent intent = new Intent(this, ZBarScannerActivity.class);
		startActivityForResult(intent, ZBAR_SCANNER_REQUEST);
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data)
	{   	

	    if (resultCode == RESULT_FIRST_USER) 
	    {
	        EditText barcode_box = (EditText) findViewById(R.id.editTextNewFavBarcodeContent);
	        barcode_box.setText(data.getStringExtra(ZBarConstants.SCAN_RESULT));
	    } else if(resultCode == RESULT_CANCELED) {
	        Toast.makeText(this, "Exit cam", Toast.LENGTH_SHORT).show();
	    } else if(resultCode == RESULT_OK){

	    	//Kitkat doesn't work
	    	if(data != null && data.getData() != null){
	    		_image_view_uri = data.getData().toString();
	    		imageView.setOnClickListener(new ClickImageView(this,_image_view_uri));
	    		
				switch(requestCode){
					case CAMERA_REQUEST:
						ImageUtility.display_photo(this,imageView,data.getData(),dpWidthHope,dpHeightHope);
						break;
					case SELECT_PHOTO:
						ImageUtility.display_photo(this,imageView,data.getData(),dpWidthHope,dpHeightHope);
						break;
				}
	    	}else{
	    		Toast t = Toast.makeText(this, "Your version is not supported camera", Toast.LENGTH_SHORT);
	    		t.show();
	    	}
		}

	}
	
	public String getImageViewURI(){
		return _image_view_uri;
	}
}
