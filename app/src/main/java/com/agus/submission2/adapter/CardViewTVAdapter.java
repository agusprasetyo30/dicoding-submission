package com.agus.submission2.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.agus.submission2.DetailActivity;
import com.agus.submission2.R;
import com.agus.submission2.model.TV;

import java.util.ArrayList;

public class CardViewTVAdapter extends RecyclerView.Adapter<CardViewTVAdapter.CardViewViewHolder>  {
	private ArrayList<TV> listTV;
	
	public CardViewTVAdapter(ArrayList<TV> listTV) {
		this.listTV = listTV;
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
		
		holder.img_TV.setImageResource(tv.getTv_image());
		holder.tvTitleTV.setText(tv.getTv_title());
		holder.tvDescription.setText(tv.getTv_description());
		
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
	public int getItemCount() {
		return listTV.size();
	}
	
	public class CardViewViewHolder extends RecyclerView.ViewHolder {
		ImageView img_TV;
		TextView tvTitleTV, tvDescription;
		
		public CardViewViewHolder(@NonNull View itemView) {
			super(itemView);
			
			img_TV = itemView.findViewById(R.id.img_photo_tv);
			tvTitleTV = itemView.findViewById(R.id.tv_item_tv_name);
			tvDescription = itemView.findViewById(R.id.tv_tv_description);
		}
	}
}
