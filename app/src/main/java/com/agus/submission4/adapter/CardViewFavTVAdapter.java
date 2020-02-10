package com.agus.submission4.adapter;

import android.app.Activity;
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

import java.util.List;

public class CardViewFavTVAdapter extends RecyclerView.Adapter<CardViewFavTVAdapter.CardViewViewHolder>
{
	private List<TV> listFavTV;
	
	public CardViewFavTVAdapter() {
	}
	
	public void setData(List<TV> listFavTV)
	{
		this.listFavTV = listFavTV;
		notifyDataSetChanged();
	}
	
	@NonNull
	@Override
	public CardViewFavTVAdapter.CardViewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
	{
		View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cardview_tv, parent, false);
		return new CardViewViewHolder(view);
	}
	
	@Override
	public void onBindViewHolder(@NonNull CardViewFavTVAdapter.CardViewViewHolder holder, int position)
	{
		final TV tv = listFavTV.get(position);
		
		holder.bind(tv);
		holder.itemView.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				DetailActivity.status = "TV";
				DetailActivity.dataType = "DB";
				
				Activity activity = (Activity)view.getContext();
				
				Intent intent = new Intent(view.getContext(), DetailActivity.class);
				intent.putExtra(DetailActivity.EXTRA_INTENT_TV, tv);
				view.getContext().startActivity(intent);
				activity.finish();
			}
		});
	}
	
	@Override
	public int getItemCount()
	{
		if (listFavTV == null) {
			return 0;
		} else {
			return listFavTV.size();
		}
	}
	
	public class CardViewViewHolder extends RecyclerView.ViewHolder {
		private ImageView img_tv;
		private TextView tvTitleTV, tvDescription;
		
		public CardViewViewHolder(@NonNull View itemView) {
			super(itemView);
			
			img_tv = itemView.findViewById(R.id.img_photo_tv);
			tvTitleTV = itemView.findViewById(R.id.tv_item_tv_name);
			tvDescription = itemView.findViewById(R.id.tv_tv_description);
		}
		
		// Mengisi data ke dalam item
		public void bind(final TV tv) {
			
			Picasso.get()
				.load(tv.getTv_image())
				.fit()
				.into(img_tv);
			
			tvTitleTV.setText(tv.getTv_title());
			tvDescription.setText(tv.getTv_description());
		}
	}
}
