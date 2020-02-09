package com.agus.submission4;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
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
	public static String status = "FILM";
	private Film film;
	private TV tv;
	
	TextView txtTitle, txtDate, txtRating, txtDescription, txtDataID;
	ImageView imgCoba;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_detail);
		setTitle(R.string.detail_activity_title);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		
		this.mainViewModel = ViewModelProviders.of(DetailActivity.this).get(MainViewModel.class);
		
		initData();
		initComponent();
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.menu_favorite, menu);
		return super.onCreateOptionsMenu(menu);
	}
	
	public boolean onOptionsItemSelected(@NonNull MenuItem item) {
		if (item.getItemId() == R.id.add_favorite) {
			if (status.equalsIgnoreCase("FILM")) {
				Film newFavFilm = this.film;
				
				this.mainViewModel.saveFavFilm(newFavFilm);
				Toast.makeText(this, "Tambah Favorit film sukses", Toast.LENGTH_SHORT).show();
				
				Log.d("CEK", "Save Sukses");
				Log.d("CEK", "ID : " + newFavFilm.getFilm_id() + " | image Link : " + newFavFilm.getFilm_image() +
					" | Title : " + newFavFilm.getFilm_title() + " | Date : " + newFavFilm.getFilm_date() + " | Rating : " + newFavFilm.getFilm_rating() +
					" | Description : " + newFavFilm.getFilm_description());
				
			} else if (status.equalsIgnoreCase("TV")) {
			
			}
			
			Intent i = new Intent(DetailActivity.this, FavoriteActivity.class);
			startActivity(i);
			finish();
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
		String notice = "Tidak ada deskripsi dalam bahasa indonesia";
		
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
			txtDescription.setText(film.getFilm_description().length() == 0 ? notice : film.getFilm_description());
			
		} else if (status.equalsIgnoreCase("TV")) {
			tv = getIntent().getParcelableExtra(EXTRA_INTENT_TV);
			
			Picasso.get()
				.load(tv.getTv_image())
				.fit()
				.into(imgCoba);
			txtTitle.setText(tv.getTv_title());
			txtDate.setText(tv.getTv_date());
			txtRating.setText(String.valueOf(tv.getTv_rating()));
			txtDescription.setText(tv.getTv_description().length() == 0 ? notice : tv.getTv_description());
		}
		
	}
}
