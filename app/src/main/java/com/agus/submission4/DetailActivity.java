package com.agus.submission4;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.agus.submission4.model.Film;
import com.agus.submission4.model.TV;
import com.squareup.picasso.Picasso;


public class DetailActivity extends AppCompatActivity {
	public static final String EXTRA_INTENT_FILM = "extra_intent_film";
	public static final String EXTRA_INTENT_TV = "extra_intent_tv";
	public static String status = "FILM";
	
	TextView txtTitle, txtDate, txtRating, txtDescription;
	ImageView imgCoba;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_detail);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		
		initData();
		initComponent();
	}
	
	private void initData()
	{
		imgCoba = findViewById(R.id.img_coba);
		txtTitle = findViewById(R.id.tv_title_detail);
		txtDate = findViewById(R.id.tv_date_detail);
		txtRating = findViewById(R.id.tv_rating_detail);
		txtDescription = findViewById(R.id.tv_description_detail);
	}
	
	private void initComponent()
	{
		String notice = "Tidak ada deskripsi dalam bahasa indonesia";
		
		if (status.equalsIgnoreCase("FILM")) {
			Film film = getIntent().getParcelableExtra(EXTRA_INTENT_FILM);
			
			Picasso.get()
				.load(film.getFilm_image())
				.fit()
				.into(imgCoba);
			
			txtTitle.setText(film.getFilm_title());
			txtDate.setText(film.getFilm_date());
			txtRating.setText(String.valueOf(film.getFilm_rating()));
			txtDescription.setText(film.getFilm_description().length() == 0 ? notice : film.getFilm_description());
			
		} else if (status.equalsIgnoreCase("TV")) {
			TV tv = getIntent().getParcelableExtra(EXTRA_INTENT_TV);
			
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
