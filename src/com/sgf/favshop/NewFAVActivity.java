package com.sgf.favshop;

import java.io.File;
import java.io.IOException;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.text.format.Time;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.sgf.libext.ZBarConstants;
import com.sgf.libext.ZBarScannerActivity;
import com.sgf.listeners.AddPhotoPressListener;
import com.sgf.listeners.SaveNewFAVButton;
import com.sgf.tool.ImageUtility;

public class NewFAVActivity extends Activity implements OnClickListener{
	
	private SaveNewFAVButton _saveNewFAV_button;
	private static final int ZBAR_SCANNER_REQUEST = 0;
	private ImageView imageView;
	private final static int CAMERA_REQUEST = 24;
	private final static int SELECT_PHOTO = 42;
	private ImageButton buttonAdd;
	private String _image_view_uri;
	private final String dir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES) + "/picFolder/"; 
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
		File newDir = null;
		File newFile = null;
		String fileName = null;
		Time now = new Time();
		
        super.onCreate(savedInstanceState);
        
        //Load uri ifexists
        if (savedInstanceState != null){
        	_image_view_uri = savedInstanceState.getString("uri");
        }else{
    		try {
    			//Photos taken by the ACTION_IMAGE_CAPTURE are not registered
    			//in the MediaStore automatically on all devices
    			newDir = new File(dir);
    			newDir.mkdirs();
    			
    			now.setToNow();
    			fileName = dir+"FAVShop"+now.getCurrentTimezone()+".jpg";
    			newFile = new File(fileName);
    			newFile.createNewFile();
    			
    			_image_view_uri = Uri.fromFile(newFile).toString();
    		} catch (IOException e) {
    			e.printStackTrace();
    		}
        }
        
        setContentView(R.layout.activity_newfav);    
        // -- Listeners -- //
        _saveNewFAV_button = new SaveNewFAVButton(this);
        
        buttonAdd = (ImageButton) findViewById(R.id.imageButtonTakePic);
        Button saveNewFAV_button = (Button) findViewById(R.id.buttonSave);
        Button scan_button = (Button) findViewById(R.id.buttonScan);
        imageView = (ImageView)this.findViewById(R.id.imageViewPic);
        
        buttonAdd.setOnClickListener(new AddPhotoPressListener(this,CAMERA_REQUEST,Uri.parse(_image_view_uri)));
        saveNewFAV_button.setOnClickListener(_saveNewFAV_button);
        scan_button.setOnClickListener(this);
    }
    
    @Override
    public void onSaveInstanceState(Bundle bundle)
    {
    	super.onSaveInstanceState(bundle);
    	bundle.putString("uri", _image_view_uri);
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

	    	//Some camera apps ignore the putExtra uri, and store the pic in a different place
	    	if(data != null && data.getData() != null){
	    		_image_view_uri = data.getData().toString();
	    	}
	    	
			switch(requestCode){
				case CAMERA_REQUEST:
					ImageUtility.display_photo(this,imageView,Uri.parse(_image_view_uri),60,60);
					break;
				case SELECT_PHOTO:
					ImageUtility.display_photo(this,imageView,Uri.parse(_image_view_uri),60,-1);
					break;
			}
		}

	}
	
	public String getImageViewURI(){
		return _image_view_uri;
	}
}
