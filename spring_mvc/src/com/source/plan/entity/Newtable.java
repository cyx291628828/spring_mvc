package com.source.plan.entity;

/**
 * Newtable entity. @author MyEclipse Persistence Tools
 */
public class Newtable extends AbstractNewtable implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public Newtable() {
	}

	/** minimal constructor */
	public Newtable(Integer id) {
		super(id);
	}

	/** full constructor */
	public Newtable(Integer id, String name) {
		super(id, name);
	}

}
