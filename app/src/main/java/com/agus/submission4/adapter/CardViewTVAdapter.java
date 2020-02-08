package com.agus.submission4.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.agus.submission4.DetailActivity;
import com.agus.submission4.R;
import com.agus.submission4.model.TV;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CardViewTVAdapter extends RecyclerView.Adapter<CardViewTVAdapter.CardViewViewHolder>  {
	private ArrayList<TV> listTV = new ArrayList<>();
	
	public CardViewTVAdapter() {
	}
	
	// Menambahkan data ke dalam array list
	public void setData(ArrayList<TV> item)
	{
		listTV.clear();
		listTV.addAll(item);
		notifyDataSetChanged();
	}
	
	@NonNull
	@Override
	public CardViewTVAdapter.CardViewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cardview_tv, parent, false);
		return new CardViewViewHolder(view);
	}
	
	@Override
	public void onBindViewHolder(@NonNull CardViewTVAdapter.CardViewViewHolder holder, int position) {
		final TV tv = listTV.get(position);
		holder.bind(tv);
		
		holder.itemView.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				DetailActivity.status = "TV"; // Merubah status
				
				Intent intent = new Intent(view.getContext(), DetailActivity.class);
				intent.putExtra(DetailActivity.EXTRA_INTENT_TV, tv);
				view.getContext().startActivity(intent);
			}
		});
	}
	
	@Override
	public int getItemCount()
	{
		if (listTV == null) {
			return 0;
		} else {
			return listTV.size();
		}
	}
	
	public class CardViewViewHolder extends RecyclerView.ViewHolder
	{
		private ImageView img_TV;
		private TextView tvTitleTV, tvDescription;
		
		public CardViewViewHolder(@NonNull View itemView)
		{
			super(itemView);
			
			img_TV = itemView.findViewById(R.id.img_photo_tv);
			tvTitleTV = itemView.findViewById(R.id.tv_item_tv_name);
			tvDescription = itemView.findViewById(R.id.tv_tv_description);
		}
		
		public void bind(final TV tv)
		{
			String notice = "Tidak ada deskripsi dalam bahasa indonesia";
			
			Picasso.get()
				.load(tv.getTv_image())
				.fit()
				.into(img_TV);
			
			tvTitleTV.setText(tv.getTv_title());
			tvDescription.setText(tv.getTv_description().length() == 0 ? notice : tv.getTv_description());
		}
	}
}
