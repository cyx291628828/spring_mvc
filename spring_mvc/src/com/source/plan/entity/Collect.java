package com.source.plan.entity;

/**
 * Collect entity. @author MyEclipse Persistence Tools
 */
public class Collect extends AbstractCollect implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public Collect() {
	}

	/** minimal constructor */
	public Collect(String collectId) {
		super(collectId);
	}

	/** full constructor */
	public Collect(String collectId, Question question, User user,
			String collectTime) {
		super(collectId, question, user, collectTime);
	}

}
