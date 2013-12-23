package com.sgf.favshop;

import com.sgf.listeners.FAVItButton;
import com.sgf.listeners.FAVViewButton;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.Window;
import android.widget.ImageButton;

public class MainActivity extends Activity {
	
	private FAVItButton _FAVIt_button;
	private FAVViewButton _FAVview_button;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        // -- Listeners -- //
        //Fav it
        _FAVIt_button = new FAVItButton(this);
        ImageButton favit_imagebutton = (ImageButton) findViewById(R.id.imageButtonFAVIt);
        favit_imagebutton.setOnClickListener(_FAVIt_button);
        //Show all FAVs
        _FAVview_button = new FAVViewButton(this);
        ImageButton favview_imagebutton = (ImageButton) findViewById(R.id.imageButtonFAVView);
        favview_imagebutton.setOnClickListener(_FAVview_button);
        
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
}
