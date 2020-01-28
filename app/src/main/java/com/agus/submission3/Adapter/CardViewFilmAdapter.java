package com.agus.submission3.Adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.agus.submission3.DetailActivity;
import com.agus.submission3.R;
import com.agus.submission3.Model.Film;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CardViewFilmAdapter extends RecyclerView.Adapter<CardViewFilmAdapter.CardViewViewHolder> {
	private ArrayList<Film> listFilm = new ArrayList<>();
	
	public CardViewFilmAdapter() {}
	
	// Menambahkan data ke dalam array list
	public void setData(ArrayList<Film> item)
	{
		listFilm.clear();
		listFilm.addAll(item);
		notifyDataSetChanged();
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
		
		holder.bind(film);
		holder.itemView.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				DetailActivity.status = "FILM";
				
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
		
		void bind(final Film film) {
			
			Picasso.get()
				.load(film.getFilm_image())
				.fit()
				.into(img_film);
			
			tvTitleFilm.setText(film.getFilm_title());
			tvDescription.setText(film.getFilm_description());
			
		}
		
	}
}
