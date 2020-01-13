package com.agus.submission2.fragment;


import android.content.res.TypedArray;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.agus.submission2.R;
import com.agus.submission2.adapter.CardViewFilmAdapter;
import com.agus.submission2.model.Film;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class FilmFragment extends Fragment {
	private RecyclerView rvFilm;
	private ArrayList<Film> list = new ArrayList<>();
	
	public FilmFragment() {
		// Required empty public constructor
	}
	
	@Override
	public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		rvFilm = view.findViewById(R.id.rv_film);
		
		list.addAll(getListFilm());
		
		showRecyclerCardView(view);
	}
	
	private void showRecyclerCardView(View view){
		rvFilm.setLayoutManager(new LinearLayoutManager(view.getContext()));
		CardViewFilmAdapter cardViewFilmAdapter = new CardViewFilmAdapter(list);
		rvFilm.setAdapter(cardViewFilmAdapter);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
									 Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		return inflater.inflate(R.layout.fragment_film, container, false);
	}
	
	public ArrayList<Film> getListFilm() {
		TypedArray dataImage;
		
		String[] dataTitle = getResources().getStringArray(R.array.title_film);
		String[] dataDescription = getResources().getStringArray(R.array.description_film);
		String[] dataDate = getResources().getStringArray(R.array.date_film);
		String[] dataRating = getResources().getStringArray(R.array.rating_film);
		
		dataImage = getResources().obtainTypedArray(R.array.image_film);
		
		ArrayList<Film> listFilm = new ArrayList<>();
		for (int i = 0; i < dataTitle.length; i++) {
			Film film = new Film();
			film.setFilm_title(dataTitle[i]);
			film.setFilm_description(dataDescription[i]);
			film.setFilm_image(dataImage.getResourceId(i, -1));
			film.setFilm_date(dataDate[i]);
			film.setFilm_rating(dataRating[i]);
			listFilm.add(film);
		}
		
		return listFilm;
	}
}
