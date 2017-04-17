package com.source.plan.entity;

/**
 * AbstractGenre entity provides the base persistence definition of the Genre
 * entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractGenre implements java.io.Serializable {

	// Fields

	private String genreId;
	private String genreName;
	private String genreType;

	// Constructors

	/** default constructor */
	public AbstractGenre() {
	}

	/** minimal constructor */
	public AbstractGenre(String genreId) {
		this.genreId = genreId;
	}

	/** full constructor */
	public AbstractGenre(String genreId, String genreName, String genreType) {
		this.genreId = genreId;
		this.genreName = genreName;
		this.genreType = genreType;
	}

	// Property accessors

	public String getGenreId() {
		return this.genreId;
	}

	public void setGenreId(String genreId) {
		this.genreId = genreId;
	}

	public String getGenreName() {
		return this.genreName;
	}

	public void setGenreName(String genreName) {
		this.genreName = genreName;
	}

	public String getGenreType() {
		return this.genreType;
	}

	public void setGenreType(String genreType) {
		this.genreType = genreType;
	}

}