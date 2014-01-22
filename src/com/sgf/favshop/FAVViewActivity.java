package com.sgf.favshop;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ListView;

import com.sgf.adapters.FAVViewListItem;
import com.sgf.database.ArticleDAO;
import com.sgf.listeners.DeleteArticleButton;
import com.sgf.listeners.FAVViewListView;
import com.sgf.listeners.InfoButtonListener;
import com.sgf.pojo.Article;

public class FAVViewActivity extends Activity {

	private FAVViewListItem _FAVview_listitem;
	private FAVViewListView _FAVview_listview;
	private InfoButtonListener _infoButtonListener;
	private DeleteArticleButton _deleteArticleButton;
	private ArticleDAO _article_dao;
	private List<Article> _article_list;
	private List<Long> _article_to_delete;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_favview);
		
		// -- Articles list init -- //
		_article_dao = new ArticleDAO(this);
		_article_dao.open(false);
		_article_list=new ArrayList<Article>();
		_article_list=_article_dao.getAllArticles();
		_article_dao.close();
		
		_article_to_delete=new ArrayList<Long>();
				
		//list view
		ListView lv_favs = (ListView) findViewById(R.id.listViewFavView);
		
		// -- Adapters -- //
		_FAVview_listitem = new FAVViewListItem(this, R.layout.item_favview, _article_list);
		lv_favs.setAdapter(_FAVview_listitem);
		
		// -- Listeners -- //
		_FAVview_listview = new FAVViewListView(this, _article_list);
		lv_favs.setOnItemClickListener(_FAVview_listview);
		_infoButtonListener = new InfoButtonListener(this);
		ImageButton info_imagebutton = (ImageButton) findViewById(R.id.imageButtonInfo);
		info_imagebutton.setOnClickListener(_infoButtonListener);
		_deleteArticleButton = new DeleteArticleButton(_article_list, _article_to_delete, this.getBaseContext(),_FAVview_listitem);
		ImageButton del_imagebutton = (ImageButton) findViewById(R.id.imageButtonDelete);
		del_imagebutton.setOnClickListener(_deleteArticleButton);
	}
	
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }

    public void delChecked(View v){
		CheckBox cb = (CheckBox) v;
		
		//Tagged in adapter
		long position = Integer.parseInt(cb.getTag().toString());

		if (cb.isChecked()) {
			_article_to_delete.add(position);
		} else {
			_article_to_delete.remove(_article_to_delete.indexOf(position));
		}
		
    }
    
}
