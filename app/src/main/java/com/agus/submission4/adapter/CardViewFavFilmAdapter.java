package com.agus.submission4.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.agus.submission4.DetailFavoriteActivity;
import com.agus.submission4.R;
import com.agus.submission4.model.Film;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CardViewFavFilmAdapter extends RecyclerView.Adapter<CardViewFavFilmAdapter.CardViewViewHolder>
{
	private List<Film> listFavFilm;
	
	public CardViewFavFilmAdapter() {
	}
	
	public void setData(List<Film> listFavFilm)
	{
		this.listFavFilm = listFavFilm;
		notifyDataSetChanged();
	}
	
	@NonNull
	@Override
	public CardViewFavFilmAdapter.CardViewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
	{
		View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cardview_film, parent, false);
		return new CardViewViewHolder(view);
	}
	
	@Override
	public void onBindViewHolder(@NonNull CardViewFavFilmAdapter.CardViewViewHolder holder, int position)
	{
		final Film film = listFavFilm.get(position);
		
		holder.bind(film);
		holder.itemView.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				DetailFavoriteActivity.status = "FILM";
				
				Toast.makeText(view.getContext(), film.getFilm_title(), Toast.LENGTH_SHORT).show();
//				Intent intent = new Intent(view.getContext(), DetailFavoriteActivity.class);
//				intent.putExtra(DetailFavoriteActivity.EXTRA_INTENT_FILM, film);
//				view.getContext().startActivity(intent);
			}
		});
	}
	
	@Override
	public int getItemCount() {
		if (listFavFilm == null) {
			return 0;
		} else {
			return listFavFilm.size();
		}
	}
	
	public class CardViewViewHolder extends RecyclerView.ViewHolder
	{
		private ImageView img_film;
		private TextView tvTitleFilm, tvDescription;

		public CardViewViewHolder(@NonNull View itemView)
		{
			super(itemView);
			
			img_film = itemView.findViewById(R.id.img_photo_film);
			tvTitleFilm = itemView.findViewById(R.id.tv_item_film_name);
			tvDescription = itemView.findViewById(R.id.tv_film_description);
		}
		
		// Mengisi data ke dalam item
		public void bind(final Film film) {
			String notice = "Tidak ada deskripsi dalam bahasa indonesia";
			
			Picasso.get()
				.load(film.getFilm_image())
				.fit()
				.into(img_film);
			
			tvTitleFilm.setText(film.getFilm_title());
			
			tvDescription.setText(film.getFilm_description().length() == 0 ? notice : film.getFilm_description());
		}
	}
}
