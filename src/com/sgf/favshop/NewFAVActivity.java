package com.sgf.favshop;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.Button;

import com.sgf.listeners.SaveNewFAVButton;

public class NewFAVActivity extends Activity{
	
	private SaveNewFAVButton _saveNewFAV_button;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newfav);
        
        // -- Listeners -- //
        _saveNewFAV_button = new SaveNewFAVButton(this);
        Button saveNewFAV_button = (Button) findViewById(R.id.buttonSave);
        saveNewFAV_button.setOnClickListener(_saveNewFAV_button);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
}
