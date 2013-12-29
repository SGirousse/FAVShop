package com.sgf.favshop;

import ExemplePhoto.listener.AddPhotoPressListener;
import ExemplePhoto.tool.ImageUtility;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
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
import com.sgf.listeners.SaveNewFAVButton;

public class NewFAVActivity extends Activity implements OnClickListener{
	
	private SaveNewFAVButton _saveNewFAV_button;
	private static final int ZBAR_SCANNER_REQUEST = 0;
	private ImageView imageView;
	private final static int CAMERA_REQUEST = 24;
	private final static int SELECT_PHOTO = 42;
	private ImageButton buttonAdd;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newfav);
        
        // -- Listeners -- //
        _saveNewFAV_button = new SaveNewFAVButton(this);
        
        buttonAdd = (ImageButton) findViewById(R.id.imageButtonTakePic);
        Button saveNewFAV_button = (Button) findViewById(R.id.buttonSave);
        Button scan_button = (Button) findViewById(R.id.buttonScan);
        imageView = (ImageView)this.findViewById(R.id.imageViewPic);
        
        buttonAdd.setOnClickListener(new AddPhotoPressListener(this,CAMERA_REQUEST));
        saveNewFAV_button.setOnClickListener(_saveNewFAV_button);
        scan_button.setOnClickListener(this);
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
	    }
	    
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
