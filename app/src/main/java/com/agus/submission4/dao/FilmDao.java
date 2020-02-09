package com.agus.submission4.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.agus.submission4.model.Film;

import java.util.List;

@Dao
public interface FilmDao {
	// insert data favorite tv
	@Insert
	void insertFavoriteFilm(Film... film);
	
	// menampilkan keseluruhan data
	@Query("SELECT * FROM tb_fav_film")
	LiveData<List<Film>> getAllFavoriteFilm();
	
	// menghapus favorite TV
	@Delete
	void deleteFavoriteFilm(Film film);
}
