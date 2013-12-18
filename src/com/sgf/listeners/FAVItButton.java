package com.sgf.listeners;

import com.sgf.favshop.MainActivity;

import android.view.View;
import android.view.View.OnClickListener;

/**
 * This the main button : FAV'It. Allow user to "fav" a new article.
 * 
 * @author simeon
 *
 */
public class FAVItButton implements OnClickListener {
	
	private MainActivity _main_activity;
	
	public FAVItButton(MainActivity main_activity) {
		_main_activity=main_activity;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
	}

}
