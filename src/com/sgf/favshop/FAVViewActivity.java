package com.sgf.favshop;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ListView;

import com.sgf.adapters.FAVViewListItem;
import com.sgf.database.ArticleDAO;
import com.sgf.listeners.FAVViewListView;
import com.sgf.pojo.Article;

public class FAVViewActivity extends Activity {

	private FAVViewListItem _FAVview_listitem;
	private FAVViewListView _FAVview_listview;
	private ArticleDAO article_dao;
	private List<Article> _article_list;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_favview);
		
		/*article_dao = new ArticleDAO(this);
		article_dao.open(true);
		article_dao.insertArticle(new Article("666777","Un article diabolique", "Cet article diabolique respire la chance ! Je le veux, je le veux ! ... mais pas à n\'importe quel prix.","","UPPA-store, everything on discount",13.37f,0.42f,new Date(2014,02,23),new Date()));
		article_dao.close();*/
		// -- Articles list init -- //
		article_dao = new ArticleDAO(this);
		article_dao.open(false);
		_article_list=new ArrayList<Article>();
		_article_list=article_dao.getAllArticles();
		article_dao.close();
				
		// -- Adapters -- //
		_FAVview_listitem = new FAVViewListItem(this, R.layout.item_favview, _article_list);
		ListView lv_favs = (ListView) findViewById(R.id.listViewFavView);
		lv_favs.setAdapter(_FAVview_listitem);
		
		// -- Listeners -- //
		_FAVview_listview = new FAVViewListView(this, _article_list);
		lv_favs.setOnItemClickListener(_FAVview_listview);
	}
	
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }

}
