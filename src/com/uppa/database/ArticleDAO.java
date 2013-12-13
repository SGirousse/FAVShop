package com.uppa.database;


import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.uppa.pojo.Article;

public class ArticleDAO {
	private SQLiteDatabase _articles_db;
	private ArticleDBHelper _articles_db_helper;
	
	public ArticleDAO(Context c){
		_articles_db_helper = new ArticleDBHelper(c);
	}
	
	public void open(boolean writable){
		if(writable){
			_articles_db = _articles_db_helper.getWritableDatabase();
		}else{
			_articles_db = _articles_db_helper.getReadableDatabase();
		}
	}
	
	public void close(){
		_articles_db.close();
	}
	
	public long insertArticle(Article a){
		return 1;
	}
	
	public List<Article> getAllArticles(){
		List<Article> a_list = new ArrayList<Article>();
		
		return a_list;
	}
	
	public int deleteAllArticles(){
		Log.w("TRACE_DB", "ArticleDAO *** public int deleteAllArticles()");
		
		return _articles_db.delete(_articles_db_helper.TABLE_CUSTOMER_ARTICLE, null, null);
	}
	
	public Article cursorToArticle(Cursor c){
		Article a = new Article();
		
		return a;
	}
}
