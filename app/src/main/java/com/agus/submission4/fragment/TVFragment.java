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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.agus.submission4.R;
import com.agus.submission4.adapter.CardViewTVAdapter;
import com.agus.submission4.model.TV;
import com.agus.submission4.viewmodel.MainViewModel;

import java.util.ArrayList;
import java.util.Locale;

public class TVFragment extends Fragment {
	private RecyclerView rvTV;
	private ProgressBar progressBar;
	private CardViewTVAdapter cardViewTVAdapter;
	private MainViewModel mainViewModel;
	private String language = Locale.getDefault().toLanguageTag();
	
	public TVFragment() {
		// Required empty public constructor
	}
	
	@Override
	public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState)
	{
		super.onViewCreated(view, savedInstanceState);
		rvTV = view.findViewById(R.id.rv_tv);
		progressBar = view.findViewById(R.id.progressBarTV);
		showLoading(true);

		showRecyclerCardView(view);
	}
	
	private void showRecyclerCardView(final View view)
	{
		rvTV.setLayoutManager(new LinearLayoutManager(view.getContext()));
		
		cardViewTVAdapter = new CardViewTVAdapter();
		cardViewTVAdapter.notifyDataSetChanged();
		
		// Inisialisasi View Model
		mainViewModel = new ViewModelProvider(getViewModelStore(), new ViewModelProvider.NewInstanceFactory()).get(MainViewModel.class);
		mainViewModel.setTV(view, language); // memanggil API dan mengisi ke dalam arrayList
		
		mainViewModel.getTV().observe(getViewLifecycleOwner(), new Observer<ArrayList<TV>>() { // Mengambil data arraylist di ViewModel dan mengisi ke dalam data Adapter
			@Override
			public void onChanged(ArrayList<TV> films) {
				if (films != null) {
					cardViewTVAdapter.setData(films);
					showLoading(false);
				}
			}
		});
		
		rvTV.setAdapter(cardViewTVAdapter); // Mengisi data recycler view
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
									 Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		return inflater.inflate(R.layout.fragment_tv, container, false);
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
