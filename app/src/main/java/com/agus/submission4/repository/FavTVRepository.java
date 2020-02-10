package com.agus.submission4.repository;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import androidx.lifecycle.LiveData;

import com.agus.submission4.R;
import com.agus.submission4.dao.TVDao;
import com.agus.submission4.database.AppDatabase;
import com.agus.submission4.database.AppDbProvider;
import com.agus.submission4.model.TV;

import java.util.List;

public class FavTVRepository
{
	private Context context;
	// memanggil class database
	private AppDatabase appDatabase;
	
	// untuk menyimpan data livedata list
	private LiveData<List<TV>> favTVList;
	
	public FavTVRepository(Context context)
	{
		this.appDatabase = AppDbProvider.getAppDbInstance(context);
		this.context = context;
	}
	
	// mengambil data list favorite film
	public LiveData<List<TV>> getFavTVList()
	{
		this.getFavFilmDataDB();
		
		return favTVList;
	}
	
	// menmanggil fungsi untuk menyimpan data favorite tv
	public void saveFavTV(TV tv)
	{
		this.saveFavTVDB(tv);
	}
	
	// memanggil fungsi untuk menghapus data favorit tv
	public void deleteFavTV(TV tv)
	{
		new DeleteFavTVTask().execute(tv);
	}
	
	
	// mengambil semua data tabel pada database
	private void getFavFilmDataDB()
	{
		TVDao TVDao = appDatabase.tvDAO();
		
		// mengambil data lewat DAO
		this.favTVList = TVDao.getAllFavoriteTV();
	}
	
	// menyimpan data ke DB
	private void saveFavTVDB(TV tv)
	{
		// menyimpan data secara Asyncronous
		new SaveFavTVTask().execute(tv);
	}
	
	// Membuat task yang digunakan untuk menyimpan data secara Asyncronous
	private class SaveFavTVTask extends AsyncTask<TV, Boolean, Boolean>
	{
		@Override
		protected Boolean doInBackground(TV... tvs) {
			try {
				TVDao tvDao = appDatabase.tvDAO();
				// mengambil data pertama dari object
				TV tv = tvs[0];
				tvDao.insertFavoriteTV(tv);
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
	private class DeleteFavTVTask extends AsyncTask<TV, Boolean, Boolean>
	{
		@Override
		protected Boolean doInBackground(TV... tvs) {
			try {
				TVDao tvDao = appDatabase.tvDAO();
				
				// Mengambil data pertama dari parameter
				TV tv = tvs[0];
				tvDao.deleteFavoriteTV(tv);
				
				return true;
				
			} catch (Exception e) {
				return true;
			}
		}
	}
}
