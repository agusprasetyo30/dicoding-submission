package com.agus.submission4.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.agus.submission4.dao.FilmDao;
import com.agus.submission4.dao.TVDao;
import com.agus.submission4.model.Film;
import com.agus.submission4.model.TV;

// untuk mengambil data tabel
@Database(entities = {Film.class, TV.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
	
	public abstract FilmDao filmDAO();
	public abstract TVDao tvDAO();
}
