package com.helc.complain.entity;

import java.io.Serializable;

import org.bson.types.Binary;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Photo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	private String _id;

	private Binary photo;

	private String photoName;

	public Photo() {
		super();
	}

	public String get_id() {
		return _id;
	}

	public void set_id(String _id) {
		this._id = _id;
	}

	public Binary getPhoto() {
		return photo;
	}

	public void setPhoto(Binary photo) {
		this.photo = photo;
	}

	public String getPhotoName() {
		return photoName;
	}

	public void setPhotoName(String photoName) {
		this.photoName = photoName;
	}

	@Override
	public String toString() {
		return "Photo [_id=" + _id + ", photo=" + photo + ", photoName=" + photoName + "]";
	}

}
