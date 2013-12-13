package database;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

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
}
