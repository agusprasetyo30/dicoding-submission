package com.agus.submission4.database;

import android.content.Context;

import androidx.room.Room;

// mengambil database pada RoomDatabase
public class AppDbProvider {
	private static AppDatabase appDatabase;
	
	public static AppDatabase getAppDbInstance(Context context)
	{
		if (AppDbProvider.appDatabase == null) {
			AppDbProvider.appDatabase = Room.databaseBuilder(context, AppDatabase.class, "app.db").build();
		}
		
		return AppDbProvider.appDatabase;
	}
}
