package com.sgf.favshop;

import com.sgf.tool.ImageUtility;
import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.Surface;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.sgf.database.ArticleDAO;
import com.sgf.pojo.Article;

public class ShowFAVActivity extends Activity{
	
	private ArticleDAO _article_dao;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
        //Get the article to show, it has to be put into the extra
        Bundle data = getIntent().getExtras();
        long id = (Long) data.get("article_toshow");
		
		// -- Articles list init -- //
		_article_dao = new ArticleDAO(this);
		_article_dao.open(false);
		Article a = _article_dao.getArticleById(id);
		_article_dao.close();
		
		if(a!=null){
			if(a.onSail()){
		        if( getRotation(this) == 2 ){
		        	setContentView(R.layout.activity_showfav_onsail_landscape);
		        }else{
		        	setContentView(R.layout.activity_showfav_onsail);
		        }
			}else{
				setContentView(R.layout.activity_showfav);
			}
			
			TextView tv_title = (TextView) findViewById(R.id.textViewShowFavTitle);
			tv_title.setText(a.getTitle());
			
			TextView tv_store = (TextView) findViewById(R.id.textViewShowFavStoreContent);
			tv_store.setText(a.getStore()+" - ");
			
			TextView tv_barcode = (TextView) findViewById(R.id.textViewShowFavBarcodeContent);
			tv_barcode.setText(a.getBarcode());
			
			if(a.getUrl()!=null){
				ImageView iv_pic = (ImageView) findViewById(R.id.imageViewShowFavPic);
				ImageUtility.display_photo(this,iv_pic,Uri.parse(a.getUrl()),500,500);
			}
			
			TextView tv_description = (TextView) findViewById(R.id.textViewShowFavDescriptionContent);
			tv_description.setText(a.getDescription());
			
			TextView tv_initPrice = (TextView) findViewById(R.id.textViewShowFavInitPriceContent);
			tv_initPrice.setText(String.valueOf(a.getInitprice())+"€");
			
			TextView tv_sailPrice = (TextView) findViewById(R.id.textViewShowFavSailPriceContent);
			tv_sailPrice.setText(String.valueOf(a.getSailprice())+"€");
			
			TextView tv_percentPrice = (TextView) findViewById(R.id.textViewShowFavPercentContent);
			tv_percentPrice.setText(String.valueOf(a.getSailprice()*100/a.getInitprice())+"%");
		}else{
			Toast t = Toast.makeText(this, "Erreur lors de la récupération de l'article.", Toast.LENGTH_SHORT);
			t.show();
			this.finish();
		}
	}
	
    public int getRotation(Context context){
    	final int rotation = ((WindowManager) context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay().getOrientation();
    	switch (rotation) {
	    case Surface.ROTATION_0:
	    	return 1;
	    case Surface.ROTATION_90:
	    	return 2;
	    case Surface.ROTATION_180:
	    	return -1;
	    default:
	    	return -2;
	   }
    }
}
