package com.agus.submission1.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.agus.submission1.Model.Catalogue;
import com.agus.submission1.R;

import java.util.ArrayList;

public class CatalogueAdapter extends BaseAdapter
{
	private Context context;
	private ArrayList<Catalogue> catalogues = new ArrayList<>();
	
	public CatalogueAdapter(Context context) {
		this.context = context;
	}
	
	public void setCatalogues(ArrayList<Catalogue> catalogues) {
		this.catalogues = catalogues;
	}
	
	@Override
	public int getCount() {
		return catalogues.size();
	}
	
	@Override
	public Object getItem(int i) {
		return catalogues.get(i);
	}
	
	@Override
	public long getItemId(int i) {
		return i;
	}
	
	@Override
	public View getView(int i, View view, ViewGroup viewGroup) {
		View itemView = view;
		
		if (itemView == null) {
			itemView = LayoutInflater.from(context).inflate(R.layout.item_film, viewGroup, false);
		}
		
		ViewHolder viewHolder = new ViewHolder(itemView);
		Catalogue catalogue = (Catalogue) getItem(i);
		viewHolder.bind(catalogue);
		
		return itemView;
	}
	
	private class ViewHolder
	{
		private TextView txtTitle;
		private TextView txtDescription;
		private TextView txtDate;
		private ImageView imgFilm;
		
		public ViewHolder(View view)
		{
			txtTitle = view.findViewById(R.id.tv_title_film);
			txtDescription = view.findViewById(R.id.tv_description);
			txtDate = view.findViewById(R.id.tv_date_film);
			imgFilm = view.findViewById(R.id.img_film);
		}
		
		void bind(Catalogue catalogue)
		{
			txtTitle.setText(catalogue.getTitle());
			txtDate.setText(catalogue.getDate());
			txtDescription.setText(catalogue.getDescription());
			imgFilm.setImageResource(catalogue.getImage());
		}
	}
	
}
