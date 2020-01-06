package com.agus.submission1;

import androidx.appcompat.app.AppCompatActivity;

import android.media.Image;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.agus.submission1.Model.Catalogue;

public class DetailCatalogueActivity extends AppCompatActivity {
	public static final String EXTRA_CATALOGUE = "extra_catalogue";
	TextView txtTitle, txtDate, txtRating, txtDescription;
	ImageView imgCoba;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_detail_catalogue);
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
		Catalogue catalogue = getIntent().getParcelableExtra(EXTRA_CATALOGUE);
		imgCoba.setImageResource(catalogue.getImage());
		txtTitle.setText(catalogue.getTitle());
		txtDate.setText(catalogue.getDate());
		txtRating.setText(catalogue.getRating());
		txtDescription.setText(catalogue.getDescription());
	}
}
