package com.sgf.listeners;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.sgf.database.ArticleDAO;
import com.sgf.favshop.NewFAVActivity;
import com.sgf.favshop.R;
import com.sgf.pojo.Article;

public class SaveNewFAVButton implements OnClickListener{

	private NewFAVActivity _newFAV_activity;
	
	public SaveNewFAVButton(NewFAVActivity newFAV_activity){
		_newFAV_activity=newFAV_activity;
	}
	
	@Override
	public void onClick(View v) {
		
		TextView tv_barcode = (TextView) _newFAV_activity.findViewById(R.id.textViewNewFavBarcodeContent);
		EditText et_article = (EditText) _newFAV_activity.findViewById(R.id.editTextNewFavArticleContent);
		EditText et_description = (EditText) _newFAV_activity.findViewById(R.id.editTextNewFavArticleContent);
		TextView tv_price = (TextView) _newFAV_activity.findViewById(R.id.textViewNewFavPriceContent);
		ImageView iv_picture = (ImageView) _newFAV_activity.findViewById(R.id.imageViewPic);
		
		String barcode = tv_barcode.getText().toString();
		String title = et_article.getText().toString();
		String description = et_description.getText().toString();
		float initprice = Float.valueOf(tv_price.getText().toString());
		String path = null;
		String store = "store";
		
		//Check articles data are valid
		if(barcode.compareTo("")==0){
			Toast t = Toast.makeText(_newFAV_activity, "Merci de remplir le code-barre", Toast.LENGTH_SHORT);
		}
		
		//Save picture and get path
		// TODO LAPIN
		
		//Save new data in db
		ArticleDAO articleDAO = new ArticleDAO(_newFAV_activity);
		articleDAO.open(true);
		
		//new Article(barcode, title, description, url, store, initprice, sailprice, endofsail, flashdate)
		articleDAO.insertArticle(new Article(barcode, title, description, path, store, initprice));
		
		articleDAO.close();
		
		//Back to MainActivity
		_newFAV_activity.finish();
	}

}
