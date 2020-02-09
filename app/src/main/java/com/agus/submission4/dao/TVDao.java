package com.agus.submission4.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.agus.submission4.model.TV;

import java.util.List;

@Dao
public interface TVDao {

	// insert data favorite tv
	@Insert
	void insertFavoriteTV(TV... tv);
	
	// menampilkan keseluruhan data
	@Query("SELECT * FROM tb_fav_tv")
	LiveData<List<TV>> getAllFavoriteTV();
	
	// menghapus favorite TV
	@Delete
	void deleteFavoriteTV(TV tv);
}
