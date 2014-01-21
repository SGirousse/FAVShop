package com.sgf.database;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.sgf.pojo.Article;

/**
 * A Data Access Object for an Article.
 * 
 * @author S. Girousse
 * @see Article, ArticleDBHelper
 *
 */
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
	
	/**
	 * Insert a new article
	 * 
	 * @param a Article
	 * @return int : the new _id
	 */
	public long insertArticle(Article a){
		Log.i("TRACE_DB", "PlayerDataSource *** public long insertPlayer(Player p)");
		Log.i("TRACE_DB", a.getBarcode());
		
		ContentValues values = new ContentValues();
		
		values.put(_articles_db_helper.COL_ARTICLE_BARCODE, a.getBarcode());
		values.put(_articles_db_helper.COL_ARTICLE_TITLE, a.getTitle());
		values.put(_articles_db_helper.COL_ARTICLE_DESCRIPTION, a.getDescription());
		values.put(_articles_db_helper.COL_ARTICLE_STORE, a.getStore());
		values.put(_articles_db_helper.COL_ARTICLE_URL, a.getUrl());
		values.put(_articles_db_helper.COL_ARTICLE_INITPRICE, a.getInitprice());
		values.put(_articles_db_helper.COL_ARTICLE_SAILPRICE, a.getSailprice());
		values.put(_articles_db_helper.COL_ARTICLE_ENDOFSAIL, a.getEndofsail().toString());
		values.put(_articles_db_helper.COL_ARTICLE_FLASHDATE, a.getFlashdate().toString());
		
		//Return the affected _id
		return _articles_db.insert(_articles_db_helper.TABLE_CUSTOMER_ARTICLE, null, values);
	}
	
	/**
	 * Update an article (all the values are updated) according to the given id and the article object.
	 * 
	 * @param id long
	 * @param a Article
	 * @return int
	 * @see Article
	 */
	public int updateArticle(long id, Article a){
		Log.i("TRACE_DB", "ArticleDAO *** public int updateArticle(long id, Article a)");
		ContentValues values = new ContentValues();
		
		values.put(_articles_db_helper.COL_ARTICLE_BARCODE, a.getBarcode());
		values.put(_articles_db_helper.COL_ARTICLE_TITLE, a.getTitle());
		values.put(_articles_db_helper.COL_ARTICLE_DESCRIPTION, a.getDescription());
		values.put(_articles_db_helper.COL_ARTICLE_STORE, a.getStore());
		values.put(_articles_db_helper.COL_ARTICLE_URL, a.getUrl());
		values.put(_articles_db_helper.COL_ARTICLE_INITPRICE, a.getInitprice());
		values.put(_articles_db_helper.COL_ARTICLE_SAILPRICE, a.getSailprice());
		values.put(_articles_db_helper.COL_ARTICLE_ENDOFSAIL, a.getEndofsail().toString());
		values.put(_articles_db_helper.COL_ARTICLE_FLASHDATE, a.getFlashdate().toString());
		
		return _articles_db.update(_articles_db_helper.TABLE_CUSTOMER_ARTICLE, values, _articles_db_helper.COL_ARTICLE_ID + " = " +id, null);
	}
	
	/**
	 * Return the whole article list.
	 * 
	 * @return List<Article>
	 * @see Article
	 */
	public List<Article> getAllArticles(){
		Log.i("TRACE_DB", "ArticleDAO *** public List<Article> getAllArticles()");
		List<Article> a_list = new ArrayList<Article>();
		
		//select * from table_articles
		Cursor cursor = _articles_db.query(_articles_db_helper.TABLE_CUSTOMER_ARTICLE,
		        null, null, null, null, null, null);
		
		
		//read through the list
		if(cursor.moveToFirst()){
			do{
	    		a_list.add(cursorToArticle(cursor));
		    }while(cursor.moveToNext());
	    }
		    
	    //close the cursor
	    cursor.close();
		    

		Log.i("TRACE_TMP", "Liste prete a etre retournee");
		return a_list;
	}
	
	public Article getArticleById(long id){
		Article a = null;
		
		//select * from table_articles
		Cursor cursor = _articles_db.query(_articles_db_helper.TABLE_CUSTOMER_ARTICLE,
		        null, _articles_db_helper.COL_ARTICLE_ID + "=?", new String[]{String.valueOf(id)}, null, null, null);
		
		if(cursor.moveToFirst()){
			a = cursorToArticle(cursor);
		}
		
		return a;
		
	}
	
	public ArrayList <Article> getArticleByBarcode(String barcode){
		ArrayList<Article> a_list = new ArrayList<Article>();
		
		//select * from table_articles
		Cursor cursor = _articles_db.query(_articles_db_helper.TABLE_CUSTOMER_ARTICLE,
		        null, _articles_db_helper.COL_ARTICLE_BARCODE + "=?", new String[]{barcode}, null, null, null);
		
		if(cursor.moveToFirst()){
			do{
				a_list.add(cursorToArticle(cursor));
			}while(cursor.moveToNext());
		}
		
		return a_list;
	}
	
	/**
	 * Destroy the whole <code>table_article</code> table
	 * 
	 * @return int
	 */
	public int deleteAllArticles(){
		Log.w("TRACE_DB", "ArticleDAO *** public int deleteAllArticles()");
		
		return _articles_db.delete(_articles_db_helper.TABLE_CUSTOMER_ARTICLE, null, null);
	}
	
	/**
	 * Transform a cursor, after a <code>select * from table_article</code>, into in an Article
	 * 
	 * @param c Cursor
	 * @return Article
	 * @see Article, Cursor
	 */
	public Article cursorToArticle(Cursor c){
		Log.i("TRACE_DB", "ArticleDAO *** public Player cursorToArticle(Cursor c)");
		
		Article a = new Article();
		
		a.setId(c.getInt(0));
		a.setBarcode(c.getString(1));
		a.setTitle(c.getString(2));
		a.setDescription(c.getString(3));
		a.setUrl(c.getString(4));
		a.setStore(c.getString(5));
		a.setInitprice(c.getFloat(6));
		a.setSailprice(c.getFloat(7));
		/*a.setEndofsail(new Date(c.getString(8)));
		a.setFlashdate(new Date(c.getString(9)));*/
		
		return a;
	}
}
