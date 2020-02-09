package com.agus.submission4;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.agus.submission4.adapter.SectionsPagerFavoriteAdapter;
import com.google.android.material.tabs.TabLayout;

public class FavoriteActivity extends AppCompatActivity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_favorite);
		setTitle(R.string.favorite_title_menu);
		
		SectionsPagerFavoriteAdapter sectionsPagerFavoriteAdapter = new SectionsPagerFavoriteAdapter(this, getSupportFragmentManager());
		ViewPager viewPager = findViewById(R.id.view_pager_favorite);
		viewPager.setAdapter(sectionsPagerFavoriteAdapter);
		TabLayout tabs = findViewById(R.id.tabs_favorite);
		tabs.setupWithViewPager(viewPager);
		
		getSupportActionBar().setElevation(0);
	}
}
