package com.sgf.listeners;

import java.util.List;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

import com.sgf.favshop.FAVViewActivity;
import com.sgf.favshop.ShowFAVActivity;
import com.sgf.pojo.Article;

public class FAVViewListView implements OnItemClickListener{
	
	private FAVViewActivity _FAVView_activity;
	private List<Article> _a_list;
	
	public FAVViewListView(FAVViewActivity FAVView_activity){
		_FAVView_activity=FAVView_activity;
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		//Get the article into list
		Article a = _a_list.get(arg2);
		
		//Ask for the profile activity
		Intent i = new Intent(_FAVView_activity.getBaseContext(), ShowFAVActivity.class );
		//i.putExtra("article_toshow", a);
		
		_FAVView_activity.startActivity(i);
	}
}
