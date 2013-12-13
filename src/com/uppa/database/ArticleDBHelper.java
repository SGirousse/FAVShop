package com.uppa.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * DB Helper for articles
 * 
 * @author S.Girousse
 * @date 13.12.2013
 */
public class ArticleDBHelper extends SQLiteOpenHelper{
	
	/**
	 * Database
	 */
	public static final int DB_VERSION = 1;
	public static final String DB_NAME = "articles.db";
	
	/**
	 * Tables
	 */
	//Article customer table
	public static final String TABLE_CUSTOMER_ARTICLE = "table_customer_article";
	public static final String COL_ARTICLE_ID= "_id";
	public static final String COL_ARTICLE_BARCODE = "barcode";
	public static final String COL_ARTICLE_TITLE = "title";
	public static final String COL_ARTICLE_DESCRIPTION = "description";
	public static final String COL_ARTICLE_URL = "url";
	public static final String COL_ARTICLE_STORE = "store";
	public static final String COL_ARTICLE_INITPRICE = "initprice";
	public static final String COL_ARTICLE_SAILPRICE = "sailprice";
	public static final String COL_ARTICLE_ENDOFSAIL = "endofsail";
	public static final String COL_ARTICLE_FLASHDATE = "flashdate";
	//Store table
	
	/**
	 * Database create script
	 */
	public static final String DB_CREATE_CUSTOMER_ARTICLE =
			"create table " + TABLE_CUSTOMER_ARTICLE + "(" 
		    + COL_ARTICLE_ID + " integer primary key autoincrement, "
		    + COL_ARTICLE_BARCODE + " text not null, "
		    + COL_ARTICLE_TITLE + " text, "
		    + COL_ARTICLE_DESCRIPTION + " text, "
		    + COL_ARTICLE_URL + " text, "
		    + COL_ARTICLE_STORE + " text not null, "
		    + COL_ARTICLE_INITPRICE + " real not null, "
		    + COL_ARTICLE_SAILPRICE + " real, "
		    + COL_ARTICLE_ENDOFSAIL + " text, "
		    + COL_ARTICLE_FLASHDATE + " text not null "
		    +")";

	
	public ArticleDBHelper(Context context) {
		super(context, DB_NAME, null, DB_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		Log.i("TRACE_DB", "ArticleDBHelper *** public void onCreate(SQLiteDatabase db) ");
		db.execSQL(DB_CREATE_CUSTOMER_ARTICLE);		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
	    Log.w( ArticleDBHelper.class.getName(),
	            "Upgrading com.uppa.database from version " + oldVersion + " to "
	                + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CUSTOMER_ARTICLE);
        onCreate(db);
	}
	
	public String[] getAllArticlesColumns(){
		String[] allArticleColumns = new String[]{COL_ARTICLE_ID, COL_ARTICLE_BARCODE, COL_ARTICLE_TITLE, COL_ARTICLE_DESCRIPTION, COL_ARTICLE_URL, COL_ARTICLE_STORE, COL_ARTICLE_INITPRICE, COL_ARTICLE_SAILPRICE, COL_ARTICLE_FLASHDATE}; 
		return allArticleColumns;
	}
}