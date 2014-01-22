package com.sgf.listeners;

import java.util.List;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;

import com.sgf.adapters.FAVViewListItem;
import com.sgf.database.ArticleDAO;
import com.sgf.pojo.Article;

public class DeleteArticleButton implements OnClickListener{
	
	private List<Article> _article_list;
	private List<Long> _article_to_delete_list;
	private ArticleDAO _article_dao;
	private Context _c;
	private FAVViewListItem _adapter;
	
	public DeleteArticleButton(List<Article> article_list, List<Long> article_to_delete_list, Context c, FAVViewListItem adapter){
		_article_list = article_list;
		_article_to_delete_list = article_to_delete_list;
		_c = c;
		_adapter=adapter;
	}

	@Override
	public void onClick(View v) {
		_article_dao = new ArticleDAO(_c);
		_article_dao.open(true);		

		//delete all the checked elements in database
		for(int i=0;i<_article_to_delete_list.size();i++){
			_article_dao.deleteArticleById(_article_to_delete_list.get(i));
		}
		
		//clear old lists
		_article_to_delete_list.clear();
		_article_list.clear();
			
		//rebuild the item list
		_article_list = _article_dao.getAllArticles();
		_article_dao.close();
		
		_adapter.notifyDataSetChanged();
	}
}
