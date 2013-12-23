package com.sgf.listeners;

import java.util.List;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

import com.sgf.favshop.FAVViewActivity;
import com.sgf.favshop.ShowFAVActivity;
import com.sgf.pojo.Article;

public class FAVViewListView implements OnItemClickListener{
	
	private FAVViewActivity _FAVView_activity;
	private List<Article> _a_list;
	
	public FAVViewListView(FAVViewActivity FAVView_activity, List<Article> a_list){
		_FAVView_activity=FAVView_activity;
		_a_list=a_list;
	}

	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		Log.i("TRACE", "FAVViewListView *** public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3)");

		int id = _a_list.get(arg2).getId();
		
		//Ask for the profile activity
		Intent i = new Intent(_FAVView_activity.getBaseContext(), ShowFAVActivity.class );
		i.putExtra("article_toshow", id);
		
		_FAVView_activity.startActivity(i);
	}
}
