package com.sgf.listeners;

import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;

import com.sgf.favshop.FAVViewActivity;
import com.sgf.favshop.MainActivity;

public class FAVViewButton implements OnClickListener{

	private MainActivity _main_activity;
	
	public FAVViewButton(MainActivity main_activity){
		_main_activity=main_activity;
	}
	
	@Override
	public void onClick(View v) {
		Intent i = new Intent(_main_activity.getBaseContext(), FAVViewActivity.class);
		_main_activity.startActivity(i);
	}
}
