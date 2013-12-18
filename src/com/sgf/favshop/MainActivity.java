package com.sgf.favshop;

import com.sgf.listeners.FAVItButton;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.ImageButton;

public class MainActivity extends Activity {
	
	private FAVItButton _FAVIt_button;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        // -- Listeners -- //
        //Fav it
        _FAVIt_button = new FAVItButton(this);
        ImageButton favit_imagebutton = (ImageButton) findViewById(R.id.imageButtonFAVIt);
        favit_imagebutton.setOnClickListener(_FAVIt_button);
        
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
}
