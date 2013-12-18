package com.sgf.favshop;

import com.sgf.adapters.FAVViewListItem;
import com.sgf.listeners.FAVViewListView;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ListView;

public class FAVViewActivity extends Activity {

	private FAVViewListItem _FAVview_listitem;
	private FAVViewListView _FAVview_listview;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_favview);
		
		// -- Adapters -- //
		
		// -- Listeners -- //
		_FAVview_listview = new FAVViewListView(this);
		ListView lv_favs = (ListView) findViewById(R.id.listViewFavView);
		lv_favs.setOnItemClickListener(_FAVview_listview);
	}
	
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }

}
