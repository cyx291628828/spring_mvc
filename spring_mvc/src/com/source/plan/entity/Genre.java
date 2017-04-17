package com.source.plan.entity;

/**
 * Genre entity. @author MyEclipse Persistence Tools
 */
public class Genre extends AbstractGenre implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public Genre() {
	}

	/** minimal constructor */
	public Genre(String genreId) {
		super(genreId);
	}

	/** full constructor */
	public Genre(String genreId, String genreName, String genreType) {
		super(genreId, genreName, genreType);
	}

}
