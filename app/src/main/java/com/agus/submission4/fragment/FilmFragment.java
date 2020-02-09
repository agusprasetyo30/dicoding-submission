package com.agus.submission4.fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.agus.submission4.R;
import com.agus.submission4.adapter.CardViewFilmAdapter;
import com.agus.submission4.model.Film;
import com.agus.submission4.viewmodel.MainViewModel;

import java.util.ArrayList;
import java.util.Locale;

public class FilmFragment extends Fragment {
	private RecyclerView rvFilm;
	private ProgressBar progressBar;
	private CardViewFilmAdapter cardViewFilmAdapter;
	private MainViewModel mainViewModel;
	private String language = Locale.getDefault().toLanguageTag();
	
	public FilmFragment() {
		// Required empty public constructor
	}
	
	@Override
	public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		rvFilm = view.findViewById(R.id.rv_film);
		progressBar = view.findViewById(R.id.progressBarFilm);
		showLoading(true);
		
		showRecyclerCardView(view);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
									 Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		return inflater.inflate(R.layout.fragment_film, container, false);
	}
	
	// Menampilkan Recycler View
	private void showRecyclerCardView(View view){
		rvFilm.setLayoutManager(new LinearLayoutManager(view.getContext()));
		
		cardViewFilmAdapter = new CardViewFilmAdapter();
		cardViewFilmAdapter.notifyDataSetChanged();
		
		// Inisialisasi View Model
		this.mainViewModel = ViewModelProviders.of(FilmFragment.this).get(MainViewModel.class);
		mainViewModel.setFilm(view, language); // memanggil API dan mengisi ke dalam arrayList
		
		mainViewModel.getFilm().observe(getViewLifecycleOwner(), new Observer<ArrayList<Film>>() { // Mengambil data arraylist di ViewModel dan mengisi ke dalam data Adapter
			@Override
			public void onChanged(ArrayList<Film> films) {
				if (films != null) {
					cardViewFilmAdapter.setData(films);
					showLoading(false);
				}
			}
		});
		
		rvFilm.setAdapter(cardViewFilmAdapter); // Mengisi data recycler view
	}
	
	// Progress bar
	private void showLoading(Boolean state) {
		if (state) {
			progressBar.setVisibility(View.VISIBLE);
		} else {
			progressBar.setVisibility(View.GONE);
		}
	}
}
