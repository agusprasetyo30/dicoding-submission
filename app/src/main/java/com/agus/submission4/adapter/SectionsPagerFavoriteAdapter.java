package com.agus.submission4.adapter;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.agus.submission4.R;
import com.agus.submission4.fragment.FilmFavoriteFragment;
import com.agus.submission4.fragment.TVFavoriteFragment;

public class SectionsPagerFavoriteAdapter extends FragmentPagerAdapter {
	private final Context mContext;
	
	public SectionsPagerFavoriteAdapter(Context context, FragmentManager fm) {
		super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
		mContext = context;
	}
	
	@StringRes
	private final int[] TAB_TITLES = new int [] {
		R.string.tab_text_1,
		R.string.tab_text_2
	};
	
	@NonNull
	@Override
	public Fragment getItem(int position) {
		Fragment fragment = null;
		
		switch (position)
		{
			case 0:
				fragment = new FilmFavoriteFragment();
				break;
				
			case 1:
				fragment = new TVFavoriteFragment();
		}
		
		return fragment;
	}
	
	@Override
	public int getCount() {
		return 2;
	}
	
	@Nullable
	@Override
	public CharSequence getPageTitle(int position) {
		return mContext.getResources().getString(TAB_TITLES[position]);
	}
}
