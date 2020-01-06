package com.agus.submission1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.agus.submission1.Adapter.CatalogueAdapter;
import com.agus.submission1.Model.Catalogue;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
	private CatalogueAdapter adapter;
	private String[] dataTitle, dataDescription, dataDate, dataRating;
	private TypedArray dataImage;
	private ArrayList<Catalogue> catalogues;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ListView listView = findViewById(R.id.lv_list);
		adapter = new CatalogueAdapter(this);
		listView.setAdapter(adapter);
		
		prepare();
		addItem();
		
		listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			// event/perintah ketika di klik
			public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
				Catalogue catalogue = new Catalogue();
				catalogue.setTitle(catalogues.get(i).getTitle());
				catalogue.setDate(catalogues.get(i).getDate());
				catalogue.setDescription(catalogues.get(i).getDescription());
				catalogue.setImage(catalogues.get(i).getImage());
				catalogue.setRating(catalogues.get(i).getRating());
				Intent intent = new Intent(MainActivity.this, DetailCatalogueActivity.class);
				intent.putExtra(DetailCatalogueActivity.EXTRA_CATALOGUE, catalogue);
				startActivity(intent);
			}
		});
	}
	
	private void prepare()
	{
		dataTitle = getResources().getStringArray(R.array.title_film);
		dataDate = getResources().getStringArray(R.array.date_film);
		dataDescription = getResources().getStringArray(R.array.description_film);
		dataImage = getResources().obtainTypedArray(R.array.image_film);
		dataRating = getResources().getStringArray(R.array.rating_film);
	}
	
	private void addItem()
	{
		catalogues = new ArrayList<>();
		
		for (int i = 0; i < dataTitle.length; i++) {
			Catalogue catalogue = new Catalogue();
			catalogue.setImage(dataImage.getResourceId(i, -1));
			catalogue.setTitle(dataTitle[i]);
			catalogue.setDescription(dataDescription[i]);
			catalogue.setDate(dataDate[i]);
			catalogue.setRating(dataRating[i]);
			
			catalogues.add(catalogue);
		}
		adapter.setCatalogues(catalogues);
		
	}
	
}
