package com.source.plan.entity;

/**
 * AbstractNewtable entity provides the base persistence definition of the
 * Newtable entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractNewtable implements java.io.Serializable {

	// Fields

	private Integer id;
	private String name;

	// Constructors

	/** default constructor */
	public AbstractNewtable() {
	}

	/** minimal constructor */
	public AbstractNewtable(Integer id) {
		this.id = id;
	}

	/** full constructor */
	public AbstractNewtable(Integer id, String name) {
		this.id = id;
		this.name = name;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

}