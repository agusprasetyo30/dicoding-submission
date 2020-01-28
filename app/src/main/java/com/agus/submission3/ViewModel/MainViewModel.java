package com.agus.submission3.ViewModel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.agus.submission3.Model.Film;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class MainViewModel extends ViewModel {
	// API KEY
	private static final String API_KEY = "be07a5b2e7b720776a91a17880225678";
	//	data list film
	private MutableLiveData<ArrayList<Film>> listFilm = new MutableLiveData<>();
	
	// Mengisi data FILM API ke dalam Array List
	public void setFilm()
	{
		AsyncHttpClient client = new AsyncHttpClient();
		final ArrayList<Film> listItems = new ArrayList<>();
		String url = "https://api.themoviedb.org/3/movie/upcoming?api_key="+ API_KEY +"&language=en-US&page=1";
		
		client.get(url, new AsyncHttpResponseHandler() {
			@Override
			public void onSuccess(int statusCode, Header[] headers, byte[] responseBody)
			{
				try {
					// Path untuk menampilkan foto
					String path = "https://image.tmdb.org/t/p/w500";
					
					String result = new String(responseBody);
					JSONObject responseObject = new JSONObject(result);
					JSONArray list = responseObject.getJSONArray("results");
					
					for (int i = 0; i < list.length(); i++) {
						JSONObject getFilm = list.getJSONObject(i);
						Film filmItem = new Film();
						filmItem.setFilm_id(getFilm.getInt("id"));
						filmItem.setFilm_title(getFilm.getString("title"));
						filmItem.setFilm_image(path + getFilm.getString("poster_path"));
						filmItem.setFilm_date(getFilm.getString("release_date"));
						filmItem.setFilm_description(getFilm.getString("overview"));
						filmItem.setFilm_rating(getFilm.getInt("vote_average"));
						
						listItems.add(filmItem);
					}
					
					listFilm.postValue(listItems);
					
				} catch (JSONException e) {
					e.printStackTrace();
					Log.d("Exception", e.getMessage());
				}
				
			}
			
			@Override
			public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error)
			{
				Log.d("onFailure", error.getMessage());
			}
		});
	}
	
	// Mengambil data FILM API ke dalam Array List
	public LiveData<ArrayList<Film>> getFilm() {
		return listFilm;
	}
}
