package com.agus.submission4;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.Menu;
import android.view.MenuItem;

public class DetailFavoriteActivity extends AppCompatActivity {
	public static final String EXTRA_INTENT_FILM = "extra_intent_film";
	public static final String EXTRA_INTENT_TV = "extra_intent_tv";
	public static String status = "FILM";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_detail_favorite);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		getMenuInflater().inflate(R.menu.menu_favorite_detail, menu);
		return super.onCreateOptionsMenu(menu);
	}
	
	@Override
	public boolean onOptionsItemSelected(@NonNull MenuItem item) {
		if (item.getItemId() == R.id.delete_favorite) {
		
		}
		
		return super.onOptionsItemSelected(item);
	}
	
}
