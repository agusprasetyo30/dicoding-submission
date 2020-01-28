package com.agus.submission3.Fragment;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.agus.submission3.R;
import com.agus.submission3.Adapter.CardViewFilmAdapter;
import com.agus.submission3.Model.Film;
import com.agus.submission3.ViewModel.MainViewModel;

import java.util.ArrayList;

public class FilmFragment extends Fragment {
	RecyclerView rvFilm;
	CardViewFilmAdapter cardViewFilmAdapter;
	MainViewModel mainViewModel;
	
	ArrayList<Film> list = new ArrayList<>();
	
	public FilmFragment() {
		// Required empty public constructor
	}
	
	@Override
	public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		rvFilm = view.findViewById(R.id.rv_film);
		
		
//		list.addAll(getListFilm());
		showRecyclerCardView(view);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
									 Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		return inflater.inflate(R.layout.fragment_film, container, false);
	}
	
	private void showRecyclerCardView(View view){
		rvFilm.setLayoutManager(new LinearLayoutManager(view.getContext()));
		
		cardViewFilmAdapter = new CardViewFilmAdapter();
		cardViewFilmAdapter.notifyDataSetChanged();
		rvFilm.setAdapter(cardViewFilmAdapter);
		
		mainViewModel = new ViewModelProvider(getViewModelStore(), new ViewModelProvider.NewInstanceFactory()).get(MainViewModel.class);
		mainViewModel.setFilm();
		
		mainViewModel.getFilm().observe(getViewLifecycleOwner(), new Observer<ArrayList<Film>>() {
			@Override
			public void onChanged(ArrayList<Film> films) {
				if (films != null) {
					cardViewFilmAdapter.setData(films);
				}
			}
		});
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
//			film.setFilm_image(dataImage.getResourceId(i, -1));
			film.setFilm_date(dataDate[i]);
//			film.setFilm_rating(dataRating[i]);
			listFilm.add(film);
		}
		
		return listFilm;
	}
}
