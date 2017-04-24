package com.source.plan.beans;
//问题类别的bean
public class Genre {
	String genreID;  //分类的ID
	String genreName;//分类名称
	String genreType;//分类的分类
	
	public String getGenreType() {
		return genreType;
	}
	public void setGenreType(String genreType) {
		this.genreType = genreType;
	}
	public String getGenreID() {
		return genreID;
	}
	public void setGenreID(String genreID) {
		this.genreID = genreID;
	}
	public String getGenreName() {
		return genreName;
	}
	public void setGenreName(String genreName) {
		this.genreName = genreName;
	}
	
}
