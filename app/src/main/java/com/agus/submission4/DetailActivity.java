package com.agus.submission4;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import com.agus.submission4.model.Film;
import com.agus.submission4.model.TV;
import com.agus.submission4.viewmodel.MainViewModel;
import com.squareup.picasso.Picasso;


public class DetailActivity extends AppCompatActivity {
	private MainViewModel mainViewModel;
	
	public static final String EXTRA_INTENT_FILM = "extra_intent_film";
	public static final String EXTRA_INTENT_TV = "extra_intent_tv";
	// Variabel ini digunakan untuk menentukan apakah data yang dipilih kategori TV atau Film
	public static String status = "FILM";
	// Variabel ini digunakan untuk menentukan apakah data tersebut dari API atau DB
	public static String dataType = "API";
	
	private Film film;
	private TV tv;
	
	TextView txtTitle, txtDate, txtRating, txtDescription, txtDataID;
	ImageView imgCoba;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_detail);
		// Menampilkan title sesuai denegan type data
		if (dataType.equalsIgnoreCase("API")) {
			setTitle(R.string.detail_activity_title);
			getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		
		} else if ((dataType.equalsIgnoreCase("DB"))) {
			setTitle(R.string.detail_favorite_activity_title);
			getSupportActionBar().setDisplayHomeAsUpEnabled(false);
		}
		
		this.mainViewModel = ViewModelProviders.of(DetailActivity.this).get(MainViewModel.class);
		
		initData();
		initComponent();
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		if (dataType.equalsIgnoreCase("API")) {
			getMenuInflater().inflate(R.menu.menu_favorite, menu);
			
		} else if (dataType.equalsIgnoreCase("DB")) {
			getMenuInflater().inflate(R.menu.menu_favorite_detail, menu);
		}
		return super.onCreateOptionsMenu(menu);
	}
	
	public boolean onOptionsItemSelected(@NonNull MenuItem item)
	{
		switch(item.getItemId())
		{
			case R.id.add_favorite: // tombol tambah favorit
				if (status.equalsIgnoreCase("FILM")) {
						Film newFavFilm = this.film;
						
						this.mainViewModel.saveFavFilm(newFavFilm);
						Toast.makeText(this, R.string.success_message_add_favorite_film, Toast.LENGTH_SHORT).show();
						
						Log.d("CEK", "Save Sukses");
						Log.d("CEK", "ID : " + newFavFilm.getFilm_id() + " | image Link : " + newFavFilm.getFilm_image() +
							" | Title : " + newFavFilm.getFilm_title() + " | Date : " + newFavFilm.getFilm_date() + " | Rating : " + newFavFilm.getFilm_rating() +
							" | Description : " + newFavFilm.getFilm_description());
					
				} else if (status.equalsIgnoreCase("TV")) {
						TV newFavTV = this.tv;
						
						this.mainViewModel.saveFavTV(newFavTV);
						Toast.makeText(this, R.string.success_message_add_favorite_tv, Toast.LENGTH_SHORT).show();
						
						Log.d("CEK", "Save Sukses");
						Log.d("CEK", "ID : " + newFavTV.getTv_id() + " | image Link : " + newFavTV.getTv_image() +
							" | Title : " + newFavTV.getTv_title() + " | Date : " + newFavTV.getTv_date() + " | Rating : " + newFavTV.getTv_rating() +
							" | Description : " + newFavTV.getTv_description());
				}
				
				Intent i = new Intent(DetailActivity.this, MainActivity.class);
				startActivity(i);
				finish();
				
			break;
				
			case R.id.delete_favorite: // tombol hapus favorit
				if (status.equalsIgnoreCase("FILM")) {
					Film deleteFavFilm = this.film;
					this.mainViewModel.deleteFavFilm(deleteFavFilm);
					Toast.makeText(this, R.string.success_delete_favorit_film, Toast.LENGTH_SHORT).show();
					
				} else if (status.equalsIgnoreCase("TV")) {
					TV deleteFavTV = this.tv;
					this.mainViewModel.deleteFavTV(deleteFavTV);
					Toast.makeText(this, R.string.success_delete_favorit_tv, Toast.LENGTH_SHORT).show();
				}
				
				Intent favActivityIntent = new Intent(DetailActivity.this, FavoriteActivity.class);
				startActivity(favActivityIntent);
				finish();
			break;
		}
			return super.onOptionsItemSelected(item);
		}
		
		
	private void initData()
	{
		txtDataID = findViewById(R.id.data_id_favorite);
		imgCoba = findViewById(R.id.img_favorite_detail);
		txtTitle = findViewById(R.id.tv_title_detail_favorite);
		txtDate = findViewById(R.id.tv_date_detail_favorite);
		txtRating = findViewById(R.id.tv_rating_detail_favorite);
		txtDescription = findViewById(R.id.tv_description_detail_favorite);
	}
	
	private void initComponent()
	{
		if (status.equalsIgnoreCase("FILM")) {
			film = getIntent().getParcelableExtra(EXTRA_INTENT_FILM);
			
			Picasso.get()
				.load(film.getFilm_image())
				.fit()
				.into(imgCoba);
			txtDataID.setText(String.valueOf(film.getFilm_id()));
			txtTitle.setText(film.getFilm_title());
			txtDate.setText(film.getFilm_date());
			txtRating.setText(String.valueOf(film.getFilm_rating()));
			txtDescription.setText(film.getFilm_description());
			
		} else if (status.equalsIgnoreCase("TV")) {
			tv = getIntent().getParcelableExtra(EXTRA_INTENT_TV);
			
			Picasso.get()
				.load(tv.getTv_image())
				.fit()
				.into(imgCoba);
			txtTitle.setText(tv.getTv_title());
			txtDate.setText(tv.getTv_date());
			txtRating.setText(String.valueOf(tv.getTv_rating()));
			txtDescription.setText(tv.getTv_description());
		}
		
	}
}
