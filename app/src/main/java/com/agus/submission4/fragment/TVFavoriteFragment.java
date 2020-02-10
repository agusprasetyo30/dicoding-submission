package com.agus.submission4.fragment;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.agus.submission4.R;
import com.agus.submission4.adapter.CardViewFavTVAdapter;
import com.agus.submission4.model.TV;
import com.agus.submission4.viewmodel.MainViewModel;

import java.util.List;

public class TVFavoriteFragment extends Fragment {
	private RecyclerView rvFavoriteTV;
	private CardViewFavTVAdapter cardViewFavTVAdapter;
	private MainViewModel mainViewModel;
	
	public TVFavoriteFragment() {
		// Required empty public constructor
	}
	
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
									 Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		return inflater.inflate(R.layout.fragment_tv_favorite, container, false);
	}
	
	@Override
	public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		rvFavoriteTV = view.findViewById(R.id.rv_favorite_tv);
		
		this.showRecyclerCardView(view);
	}
	
	// Menampilkan data SQLite pada Recycler View
	private void showRecyclerCardView(View view) {
		rvFavoriteTV.setLayoutManager(new LinearLayoutManager(view.getContext()));
		
		cardViewFavTVAdapter = new CardViewFavTVAdapter();
		cardViewFavTVAdapter.notifyDataSetChanged();
		
		// Inisialisasi View Model
		this.mainViewModel = ViewModelProviders.of(TVFavoriteFragment.this).get(MainViewModel.class);
		rvFavoriteTV.setAdapter(cardViewFavTVAdapter); // Mengisi data recycler view
		
		this.mainViewModel.getFavTVList().observe(getViewLifecycleOwner(), new Observer<List<TV>>() {
			@Override
			public void onChanged(List<TV> tvs) {
				if (tvs != null) {
					cardViewFavTVAdapter.setData(tvs);
				}
			}
		});
	}
}
