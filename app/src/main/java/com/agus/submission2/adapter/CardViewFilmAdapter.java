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
import com.agus.submission2.model.Film;

import java.util.ArrayList;

public class CardViewFilmAdapter extends RecyclerView.Adapter<CardViewFilmAdapter.CardViewViewHolder> {
	private ArrayList<Film> listFilm;
	
	public CardViewFilmAdapter(ArrayList<Film> listFilm) {
		this.listFilm = listFilm;
	}
	
	
	@NonNull
	@Override
	public CardViewFilmAdapter.CardViewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cardview_film, parent, false);
		return new CardViewViewHolder(view);
	}
	
	@Override
	public void onBindViewHolder(@NonNull CardViewFilmAdapter.CardViewViewHolder holder, int position) {
		final Film film = listFilm.get(position);
		
		holder.img_film.setImageResource(film.getFilm_image());
		holder.tvTitleFilm.setText(film.getFilm_title());
		holder.tvDescription.setText(film.getFilm_description());
		
		holder.itemView.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				DetailActivity.status = "FILM"; // Merubah status
				
				Toast.makeText(view.getContext(), film.getFilm_date(), Toast.LENGTH_SHORT).show();
				Intent intent = new Intent(view.getContext(), DetailActivity.class);
				intent.putExtra(DetailActivity.EXTRA_INTENT_FILM, film);
				view.getContext().startActivity(intent);
			}
		});
	}
	
	@Override
	public int getItemCount() {
		return listFilm.size();
	}
	
	public class CardViewViewHolder extends RecyclerView.ViewHolder {
		ImageView img_film;
		TextView tvTitleFilm, tvDescription;
		
		public CardViewViewHolder(@NonNull View itemView) {
			super(itemView);
			
			img_film = itemView.findViewById(R.id.img_photo_film);
			tvTitleFilm = itemView.findViewById(R.id.tv_item_film_name);
			tvDescription = itemView.findViewById(R.id.tv_film_description);
		}
	}
}
