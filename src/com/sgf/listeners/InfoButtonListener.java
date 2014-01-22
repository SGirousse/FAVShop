package com.sgf.listeners;

import android.app.Activity;
import android.app.Dialog;
import android.view.View;
import android.view.View.OnClickListener;
import com.sgf.favshop.R;

public class InfoButtonListener implements OnClickListener{

	private Activity _current_activity;
	
	public InfoButtonListener(Activity current_activity){
		_current_activity=current_activity;
	}

	@Override
	public void onClick(View v) {
		Dialog dia = new Dialog(_current_activity);
		dia.setContentView(R.layout.about_favshop);
		dia.show();
	}
	
}
