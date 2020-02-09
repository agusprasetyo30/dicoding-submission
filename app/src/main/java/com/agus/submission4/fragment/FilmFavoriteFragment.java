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
import com.agus.submission4.adapter.CardViewFavFilmAdapter;
import com.agus.submission4.model.Film;
import com.agus.submission4.viewmodel.MainViewModel;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class FilmFavoriteFragment extends Fragment {
	private RecyclerView rvFavoriteFilm;
	private CardViewFavFilmAdapter cardViewFavFilmAdapter;
	private MainViewModel mainViewModel;
	
	
	public FilmFavoriteFragment() {
		// Required empty public constructor
	}
	
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
									 Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		return inflater.inflate(R.layout.fragment_film_favorite, container, false);
	}
	
	@Override
	public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		rvFavoriteFilm = view.findViewById(R.id.rv_favorite_film);
		
		this.showRecyclerCardView(view);
	}
	
	// Menampilkan data SQLite pada Recycler View
	private void showRecyclerCardView(View view){
		rvFavoriteFilm.setLayoutManager(new LinearLayoutManager(view.getContext()));
		
		cardViewFavFilmAdapter = new CardViewFavFilmAdapter();
		cardViewFavFilmAdapter.notifyDataSetChanged();
		
		// Inisialisasi View Model
		this.mainViewModel = ViewModelProviders.of(FilmFavoriteFragment.this).get(MainViewModel.class);
		rvFavoriteFilm.setAdapter(cardViewFavFilmAdapter); // Mengisi data recycler view
		
		this.mainViewModel.getFavFilmList().observe(getViewLifecycleOwner(), new Observer<List<Film>>() {
				@Override
				public void onChanged(List<Film> films) {
					if (films != null) {
						cardViewFavFilmAdapter.setData(films);
					}
				}
		});
	}
}
