package com.sgf.listeners;

import android.util.Log;
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
		
		//Get data from layout
		EditText et_barcode = (EditText) _newFAV_activity.findViewById(R.id.editTextNewFavBarcodeContent);
		EditText et_article = (EditText) _newFAV_activity.findViewById(R.id.editTextNewFavArticleContent);
		EditText et_description = (EditText) _newFAV_activity.findViewById(R.id.editTextNewFavDescription);
		TextView tv_price = (TextView) _newFAV_activity.findViewById(R.id.textViewNewFavPriceContent);
		ImageView iv_picture = (ImageView) _newFAV_activity.findViewById(R.id.imageViewPic);
		
		//Easier to use it like that
		String barcode = et_barcode.getText().toString();
		String title = et_article.getText().toString();
		String description = et_description.getText().toString();
		float initprice = Float.valueOf(tv_price.getText().toString());
		String path = _newFAV_activity.getImageViewURI();
		String store = "store";
		
		//More variables
		String sToast = "";
		
		//Check articles data are valid
		if(barcode.length()<1){
			sToast="Code-barre obligatoire.";
		}else{
			//Save new data in db
			ArticleDAO articleDAO = new ArticleDAO(_newFAV_activity);
			articleDAO.open(true);
			
			//new Article(barcode, title, description, url, store, initprice, sailprice, endofsail, flashdate)
			long id = articleDAO.insertArticle(new Article(barcode, title, description, path, store, initprice));
			Log.i("TRACE", "SaveNewFAVButton *** public void onClick(View v) : Article numero "+id+" enregistre");
			
			articleDAO.close();
			
			sToast="Article enregistré.";
			
			//Back to MainActivity
			_newFAV_activity.finish();
		}
		

		//Show result with a Toast
		Toast t = Toast.makeText(_newFAV_activity, sToast, Toast.LENGTH_SHORT);
		t.show();
	}

}
