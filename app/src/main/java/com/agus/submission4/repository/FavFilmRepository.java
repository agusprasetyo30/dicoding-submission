package com.agus.submission4.repository;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import androidx.lifecycle.LiveData;

import com.agus.submission4.R;
import com.agus.submission4.dao.FilmDao;
import com.agus.submission4.database.AppDatabase;
import com.agus.submission4.database.AppDbProvider;
import com.agus.submission4.model.Film;

import java.util.List;

public class FavFilmRepository {
	private Context context;
	// memanggil class database
	private AppDatabase appDatabase;
	
	// untuk menyimpan data livedata list
	private LiveData<List<Film>> favFilmList;
	
	public FavFilmRepository(Context context)
	{
		this.appDatabase = AppDbProvider.getAppDbInstance(context);
		this.context = context;
	}
	
	// mengambil data list favorite film
	public LiveData<List<Film>> getFavFilmList()
	{
		this.getFavFilmDataDB();
		
		return favFilmList;
	}
	
	// menmanggil fungsi untuk menyimpan data favorite film
	public void saveFavFilm(Film film)
	{
		this.saveFavFilmDB(film);
	}
	
	// memanggil fungsi untuk menghapus data favorit film
	public void deleteFavFilm(Film film)
	{
		new DeleteFavFilmTask().execute(film);
	}
	
	
	// mengambil semua data tabel pada database
	private void getFavFilmDataDB()
	{
		FilmDao filmDao = appDatabase.filmDAO();
		
		// mengambil data lewat DAO
		this.favFilmList = filmDao.getAllFavoriteFilm();
	}
	
	// menyimpan data ke DB
	private void saveFavFilmDB(Film film)
	{
		// menyimpan data secara Asyncronous
		new SaveFavFilmTask().execute(film);
	}
	
	// mengambil semua data tabel pada database
	
	// Membuat task yang digunakan untuk menyimpan data secara Asyncronous
	private class SaveFavFilmTask extends AsyncTask<Film, Boolean, Boolean>
	{
		@Override
		protected Boolean doInBackground(Film... films) {
				try {
					FilmDao filmDao = appDatabase.filmDAO();
					// mengambil data pertama dari object
					Film film = films[0];
					filmDao.insertFavoriteFilm(film);
					return true;
					
				} catch (Exception e) {
					return false;
				}
		}
		
		@Override
		protected void onPostExecute(Boolean aBoolean) {
			super.onPostExecute(aBoolean);
			if (!aBoolean) {
				Toast.makeText(context, R.string.error_message_add_favorite, Toast.LENGTH_LONG).show();
			}
		}
	}
	
	// Membuat TASK yang dgunakan untuk menyimpan data agar bersifat asyncronous
	private class DeleteFavFilmTask extends AsyncTask<Film, Boolean, Boolean>
	{
		@Override
		protected Boolean doInBackground(Film... films) {
			try {
				FilmDao filmDao = appDatabase.filmDAO();
				
				// Mengambil data pertama dari parameter
				Film film =films[0];
				filmDao.deleteFavoriteFilm(film);
				
				return true;

			} catch (Exception e) {
				return true;
			}
		}
	}
}
