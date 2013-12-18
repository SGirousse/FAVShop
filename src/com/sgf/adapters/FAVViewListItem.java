package com.sgf.adapters;

import java.util.List;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.sgf.pojo.Article;

public class FAVViewListItem extends ArrayAdapter<Article>{

	private Context _context;
	private int _layout_id;
	private List<Article> _a_list;
	
	public FAVViewListItem(Context context, int layout_id, List<Article> a_list) {
		super(context, layout_id);
		
		_context=context;
		_layout_id=layout_id;
		_a_list=a_list;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Log.i("TRACE", "FAVViewListItem *** public View getView(int position, View convertView, ViewGroup parent)");

		if (convertView==null) {
			LayoutInflater layoutInflater = (LayoutInflater) _context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = layoutInflater.inflate(_layout_id, parent, false);   
		} 
		
		//Article to show
		Article a = _a_list.get(position);

		//exemple
//		TextView text_view_name = (TextView) convertView.findViewById(org.jeudego.ffg.R.id.textViewPlayerName);		
//		text_view_name.setText(p.getName());

		return convertView;
	}
}
