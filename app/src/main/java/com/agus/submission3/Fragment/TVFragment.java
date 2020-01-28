package com.agus.submission3.Fragment;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.agus.submission3.R;
import com.agus.submission3.Adapter.CardViewTVAdapter;
import com.agus.submission3.Model.TV;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class TVFragment extends Fragment {
	private RecyclerView rvTV;
	private ArrayList<TV> list = new ArrayList<>();
	
	public TVFragment() {
		// Required empty public constructor
	}
	
	@Override
	public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		
//		rvTV = view.findViewById(R.id.rv_tv);

//		list.addAll(getListTV());
		
//		showRecyclerCardView(view);
	}
	
	private void showRecyclerCardView(View view){
		rvTV.setLayoutManager(new LinearLayoutManager(view.getContext()));
		CardViewTVAdapter cardViewTVAdapter = new CardViewTVAdapter(list);
		rvTV.setAdapter(cardViewTVAdapter );
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
									 Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		return inflater.inflate(R.layout.fragment_tv, container, false);
	}
	
	public ArrayList<TV> getListTV() {
		TypedArray dataImage;
		
		String[] dataTitle = getResources().getStringArray(R.array.title_tv);
		String[] dataDescription = getResources().getStringArray(R.array.description_tv);
		String[] dataDate = getResources().getStringArray(R.array.date_tv);
		String[] dataRating = getResources().getStringArray(R.array.rating_tv);
//		dataImage = getResources().obtainTypedArray(R.array.image_tv);
		
		ArrayList<TV> listTV = new ArrayList<>();
		for (int i = 0; i < dataTitle.length; i++) {
			TV tv = new TV();
			tv.setTv_title(dataTitle[i]);
			tv.setTv_description(dataDescription[i]);
//			tv.setTv_image(dataImage.getResourceId(i, -1));
			tv.setTv_date(dataDate[i]);
			tv.setTv_rating(dataRating[i]);
			listTV.add(tv);
		}
		
		return listTV;
	}
}
