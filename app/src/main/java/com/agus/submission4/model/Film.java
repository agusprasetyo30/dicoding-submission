package com.agus.submission4.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Film implements Parcelable
{
	private int film_id;
	private String film_image;
	private String film_title;
	private String film_description;
	private String film_date;
	private int film_rating;
	
	public Film() {}
	
	public int getFilm_id() {
		return film_id;
	}
	
	public void setFilm_id(int film_id) {
		this.film_id = film_id;
	}
	
	public String getFilm_image() {
		return film_image;
	}
	
	public void setFilm_image(String film_image) {
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
	
	public int getFilm_rating() {
		return film_rating;
	}
	
	public void setFilm_rating(int film_rating) {
		this.film_rating = film_rating;
	}
	
	protected Film(Parcel in) {
		film_id = in.readInt();
		film_image = in.readString();
		film_title = in.readString();
		film_description = in.readString();
		film_date = in.readString();
		film_rating = in.readInt();
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
	
	@Override
	public int describeContents() {
		return 0;
	}
	
	@Override
	public void writeToParcel(Parcel parcel, int i) {
		
		parcel.writeInt(film_id);
		parcel.writeString(film_image);
		parcel.writeString(film_title);
		parcel.writeString(film_description);
		parcel.writeString(film_date);
		parcel.writeInt(film_rating);
	}
}
