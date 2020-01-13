package com.agus.submission2.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Film implements Parcelable
{
	private int film_image;
	private String film_title;
	private String film_description;
	private String film_date;
	private String film_rating;
	
	public Film() {}
	
	protected Film(Parcel in) {
		film_image = in.readInt();
		film_title = in.readString();
		film_description = in.readString();
		film_date = in.readString();
		film_rating = in.readString();
	}
	
	public int getFilm_image() {
		return film_image;
	}
	
	public void setFilm_image(int film_image) {
		this.film_image = film_image;
	}
	
	public String getFilm_title() {
		return film_title;
	}
	
	public void setFilm_title(String film_title) {
		this.film_title = film_title;
	}
	
	public String getFilm_description() {
		return film_description;
	}
	
	public void setFilm_description(String film_description) {
		this.film_description = film_description;
	}
	
	public String getFilm_date() {
		return film_date;
	}
	
	public void setFilm_date(String film_date) {
		this.film_date = film_date;
	}
	
	public String getFilm_rating() {
		return film_rating;
	}
	
	public void setFilm_rating(String film_rating) {
		this.film_rating = film_rating;
	}
	
	
	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeInt(film_image);
		dest.writeString(film_title);
		dest.writeString(film_description);
		dest.writeString(film_date);
		dest.writeString(film_rating);
	}
	
	@Override
	public int describeContents() {
		return 0;
	}
	
	public static final Creator<Film> CREATOR = new Creator<Film>() {
		@Override
		public Film createFromParcel(Parcel in) {
			return new Film(in);
		}
		
		@Override
		public Film[] newArray(int size) {
			return new Film[size];
		}
	};
}
