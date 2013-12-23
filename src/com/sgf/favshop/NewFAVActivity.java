package com.sgf.favshop;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.sgf.libext.ZBarConstants;
import com.sgf.libext.ZBarScannerActivity;
import com.sgf.listeners.SaveNewFAVButton;

public class NewFAVActivity extends Activity implements OnClickListener{
	
	private SaveNewFAVButton _saveNewFAV_button;
	private static final int ZBAR_SCANNER_REQUEST = 0;
	private static final int ZBAR_QR_SCANNER_REQUEST = 1;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newfav);
        
        // -- Listeners -- //
        _saveNewFAV_button = new SaveNewFAVButton(this);
        Button saveNewFAV_button = (Button) findViewById(R.id.buttonSave);
        Button scan_button = (Button) findViewById(R.id.buttonScan);
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
	    if (resultCode == RESULT_OK) 
	    {
	        // Scan result is available by making a call to data.getStringExtra(ZBarConstants.SCAN_RESULT)
	        // Type of the scan result is available by making a call to data.getStringExtra(ZBarConstants.SCAN_RESULT_TYPE)
	        //Toast.makeText(this, "Scan Result = " + data.getStringExtra(ZBarConstants.SCAN_RESULT), Toast.LENGTH_SHORT).show();
	        //Toast.makeText(this, "Scan Result Type = " + data.getStringExtra(ZBarConstants.SCAN_RESULT_TYPE), Toast.LENGTH_SHORT).show();
	        EditText barcode_box = (EditText) findViewById(R.id.editTextNewFavBarcodeContent);
	        barcode_box.setText(data.getStringExtra(ZBarConstants.SCAN_RESULT));
	        // The value of type indicates one of the symbols listed in Advanced Options below.
	    } else if(resultCode == RESULT_CANCELED) {
	        Toast.makeText(this, "Camera unavailable", Toast.LENGTH_SHORT).show();
	    }
	}
}
