package com.agus.submission3.ViewModel;

import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.agus.submission3.Fragment.FilmFragment;
import com.agus.submission3.Model.Film;
import com.agus.submission3.Model.TV;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Objects;

import cz.msebera.android.httpclient.Header;

public class MainViewModel extends ViewModel
{
	// API KEY
	private static final String API_KEY = "be07a5b2e7b720776a91a17880225678";
	//	data list film
	private MutableLiveData<ArrayList<Film>> listFilm = new MutableLiveData<>();
	// data list TV
	private MutableLiveData<ArrayList<TV>> listTV = new MutableLiveData<>();
	
	// Mengisi data FILM API ke dalam Array List
	public void setFilm(final View view, String language)
	{
		AsyncHttpClient client = new AsyncHttpClient();
		final ArrayList<Film> listItems = new ArrayList<>();
		String url = "https://api.themoviedb.org/3/movie/upcoming?api_key="+ API_KEY +"&language="+language+"&page=1";
		
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
					Log.d("Exception", Objects.requireNonNull(e.getMessage()));
				}
			}
			
			@Override
			public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error)
			{
				Toast.makeText(view.getContext() ,"Data Tidak bisa ditampilkan, harap periksa koneksi anda dan buka ulang aplikasi", Toast.LENGTH_LONG).show();
				Log.d("onFailure", Objects.requireNonNull(error.getMessage()));
			}
		});
	}
	
	// Mengambil data FILM API ke dalam Array List
	public LiveData<ArrayList<Film>> getFilm()
	{
		return listFilm;
	}
	
	// Mengisi data TV API ke dalam ArrayList
	public void setTV(final View view, String language)
	{
		AsyncHttpClient client = new AsyncHttpClient();
		final ArrayList<TV> listItems = new ArrayList<>();
		String url = "https://api.themoviedb.org/3/tv/popular?api_key="+ API_KEY +"&language="+language+"&page=1";
		
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
						JSONObject getTV = list.getJSONObject(i);
						TV tvItem = new TV();
						tvItem.setTv_id(getTV.getInt("id"));
						tvItem.setTv_title(getTV.getString("name"));
						tvItem.setTv_image(path + getTV.getString("poster_path"));
						tvItem.setTv_date(getTV.getString("first_air_date"));
						tvItem.setTv_description(getTV.getString("overview"));
						tvItem.setTv_rating(getTV.getInt("vote_average"));
						
						listItems.add(tvItem);
					}
					
					listTV.postValue(listItems);
					
				} catch (JSONException e) {
					e.printStackTrace();
					Log.d("Exception", Objects.requireNonNull(e.getMessage()));
				}
			}
			
			@Override
			public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error)
			{
				Toast.makeText(view.getContext() ,"Data Tidak bisa ditampilkan, harap periksa koneksi anda dan buka ulang aplikasi", Toast.LENGTH_LONG).show();
				Log.d("onFailure", Objects.requireNonNull(error.getMessage()));
			}
		});
	}
	
	// Mengambil data TV API ke dalam Array List
	public LiveData<ArrayList<TV>> getTV()
	{
		return listTV;
	}
}
