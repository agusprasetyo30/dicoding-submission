package com.agus.submission1.Model;

import android.os.Parcel;
import android.os.Parcelable;

public class Catalogue implements Parcelable
{
	private int image;
	private String title;
	private String description;
	private String date;
	private String rating;
	
	public String getRating() {
		return rating;
	}
	
	public void setRating(String rating) {
		this.rating = rating;
	}
	
	public Catalogue(){}
	
	public int getImage() {
		return image;
	}
	
	public void setImage(int image) {
		this.image = image;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getDate() {
		return date;
	}
	
	public void setDate(String date) {
		this.date = date;
	}
	
	protected Catalogue(Parcel in) {
		image = in.readInt();
		title = in.readString();
		description = in.readString();
		date = in.readString();
		rating = in.readString();
	}
	
	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeInt(image);
		dest.writeString(title);
		dest.writeString(description);
		dest.writeString(date);
		dest.writeString(rating);
	}
	
	@Override
	public int describeContents() {
		return 0;
	}
	
	public static final Creator<Catalogue> CREATOR = new Creator<Catalogue>() {
		@Override
		public Catalogue createFromParcel(Parcel in) {
			return new Catalogue(in);
		}
		
		@Override
		public Catalogue[] newArray(int size) {
			return new Catalogue[size];
		}
	};
}
