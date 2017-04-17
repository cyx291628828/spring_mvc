package com.source.plan.entity;

/**
 * AbstractCollect entity provides the base persistence definition of the
 * Collect entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractCollect implements java.io.Serializable {

	// Fields

	private String collectId;
	private Question question;
	private User user;
	private String collectTime;

	// Constructors

	/** default constructor */
	public AbstractCollect() {
	}

	/** minimal constructor */
	public AbstractCollect(String collectId) {
		this.collectId = collectId;
	}

	/** full constructor */
	public AbstractCollect(String collectId, Question question, User user,
			String collectTime) {
		this.collectId = collectId;
		this.question = question;
		this.user = user;
		this.collectTime = collectTime;
	}

	// Property accessors

	public String getCollectId() {
		return this.collectId;
	}

	public void setCollectId(String collectId) {
		this.collectId = collectId;
	}

	public Question getQuestion() {
		return this.question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getCollectTime() {
		return this.collectTime;
	}

	public void setCollectTime(String collectTime) {
		this.collectTime = collectTime;
	}

}