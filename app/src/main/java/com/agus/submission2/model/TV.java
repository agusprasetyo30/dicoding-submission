package com.agus.submission2.model;

import android.os.Parcel;
import android.os.Parcelable;

public class TV implements Parcelable
{
	private int tv_image;
	private String tv_title;
	private String tv_description;
	private String tv_date;
	private String tv_rating;
	
	public TV(){}
	
	public int getTv_image() {
		return tv_image;
	}
	
	public void setTv_image(int tv_image) {
		this.tv_image = tv_image;
	}
	
	public String getTv_title() {
		return tv_title;
	}
	
	public void setTv_title(String tv_title) {
		this.tv_title = tv_title;
	}
	
	public String getTv_description() {
		return tv_description;
	}
	
	public void setTv_description(String tv_description) {
		this.tv_description = tv_description;
	}
	
	public String getTv_date() {
		return tv_date;
	}
	
	public void setTv_date(String tv_date) {
		this.tv_date = tv_date;
	}
	
	public String getTv_rating() {
		return tv_rating;
	}
	
	public void setTv_rating(String tv_rating) {
		this.tv_rating = tv_rating;
	}
	
	protected TV(Parcel in) {
		tv_image = in.readInt();
		tv_title = in.readString();
		tv_description = in.readString();
		tv_date = in.readString();
		tv_rating = in.readString();
	}
	
	public static final Creator<TV> CREATOR = new Creator<TV>() {
		@Override
		public TV createFromParcel(Parcel in) {
			return new TV(in);
		}
		
		@Override
		public TV[] newArray(int size) {
			return new TV[size];
		}
	};
	
	@Override
	public int describeContents() {
		return 0;
	}
	
	@Override
	public void writeToParcel(Parcel parcel, int i) {
		
		parcel.writeInt(tv_image);
		parcel.writeString(tv_title);
		parcel.writeString(tv_description);
		parcel.writeString(tv_date);
		parcel.writeString(tv_rating);
	}
}
