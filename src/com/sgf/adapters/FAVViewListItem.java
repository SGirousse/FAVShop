package com.sgf.adapters;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.text.Spannable;
import android.text.Spanned;
import android.text.style.StrikethroughSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.sgf.favshop.R;
import com.sgf.pojo.Article;

public class FAVViewListItem extends ArrayAdapter<Article>{

	private static final StrikethroughSpan STRIKE_THROUGH_SPAN = new StrikethroughSpan();
	
	private Context _context;
	private int _layout_id;
	private List<Article> _a_list;			//article list
	
	public FAVViewListItem(Context context, int layout_id, List<Article> a_list) {
		super(context, layout_id, a_list);
		
		_context=context;
		_layout_id=layout_id;
		_a_list=a_list;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Log.i("TRACE", "FAVViewListItem *** public View getView(int position, View convertView, ViewGroup parent)");

		//if (convertView==null) { #FIX BUG OF STYLE ITEMS DUPLICATIONS
			LayoutInflater layoutInflater = (LayoutInflater) _context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = layoutInflater.inflate(_layout_id, parent, false);   
		//}
		
		//Article to show
		Article a = _a_list.get(position);

		TextView text_view_barcode = (TextView) convertView.findViewById(R.id.textViewFavViewBarcode);		
		text_view_barcode.setText(a.getBarcode());
		
		TextView text_view_title = (TextView) convertView.findViewById(R.id.textViewFavViewTitle);		
		text_view_title.setText(a.getTitle());
		
		TextView text_view_price = (TextView) convertView.findViewById(R.id.textViewFavViewInitPrice);
		String sInit_price = String.valueOf(a.getInitprice())+"€";
		text_view_price.setText(sInit_price,TextView.BufferType.SPANNABLE);
		
		TextView text_view_sailprice = (TextView) convertView.findViewById(R.id.textViewFavViewSailPrice);
		
		TextView text_view_onsail = (TextView) convertView.findViewById(R.id.textViewFavViewOnSail);
		
		//If the article is on sail
		if(a.onSail()){

			convertView.setBackgroundColor(Color.parseColor("#dceddc"));
			
			//put sail info
			text_view_sailprice.setText(String.valueOf(a.getSailprice())+"€");
			if(a.getEndofsail()!=new Date(2000,12,31)){
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
				text_view_onsail.setText("En promotion jusqu'au "+sdf.format(a.getEndofsail()));
			}else{
				text_view_onsail.setText("En promotion !");
			}
			
			text_view_onsail.setTextColor(Color.parseColor("#088A08"));
			text_view_sailprice.setTextColor(Color.parseColor("#088A08"));
			text_view_onsail.setTypeface(null, Typeface.BOLD);
			text_view_sailprice.setTypeface(null, Typeface.BOLD);
			
			//strike the older price
			Spannable spannable = (Spannable) text_view_price.getText();
			spannable.setSpan(STRIKE_THROUGH_SPAN, 0, sInit_price.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
		}
		
		TextView text_view_store = (TextView) convertView.findViewById(R.id.textViewFavViewStore);		
		text_view_store.setText(a.getStore()+" - ");
		
		TextView text_view_description = (TextView) convertView.findViewById(R.id.textViewFavViewDescription);		
		text_view_description.setText(a.getDescription());
		text_view_description.setTypeface(null, Typeface.ITALIC);

		CheckBox check_box_article = (CheckBox) convertView.findViewById(R.id.checkBoxDelete);
		check_box_article.setTag(a.getId());
		
		return convertView;
	}
}
