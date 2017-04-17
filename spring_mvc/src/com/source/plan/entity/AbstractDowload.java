package com.source.plan.entity;

/**
 * AbstractDowload entity provides the base persistence definition of the
 * Dowload entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractDowload implements java.io.Serializable {

	// Fields

	private String downId;
	private Question question;
	private User user;
	private Answer answer;
	private String downTime;
	private Integer downMoney;
	private Integer isZan;
	private Integer isPing;
	private String pingConcent;
	private String pingTime;

	// Constructors

	/** default constructor */
	public AbstractDowload() {
	}

	/** minimal constructor */
	public AbstractDowload(String downId) {
		this.downId = downId;
	}

	/** full constructor */
	public AbstractDowload(String downId, Question question, User user,
			Answer answer, String downTime, Integer downMoney, Integer isZan,
			Integer isPing, String pingConcent, String pingTime) {
		this.downId = downId;
		this.question = question;
		this.user = user;
		this.answer = answer;
		this.downTime = downTime;
		this.downMoney = downMoney;
		this.isZan = isZan;
		this.isPing = isPing;
		this.pingConcent = pingConcent;
		this.pingTime = pingTime;
	}

	// Property accessors

	public String getDownId() {
		return this.downId;
	}

	public void setDownId(String downId) {
		this.downId = downId;
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

	public Answer getAnswer() {
		return this.answer;
	}

	public void setAnswer(Answer answer) {
		this.answer = answer;
	}

	public String getDownTime() {
		return this.downTime;
	}

	public void setDownTime(String downTime) {
		this.downTime = downTime;
	}

	public Integer getDownMoney() {
		return this.downMoney;
	}

	public void setDownMoney(Integer downMoney) {
		this.downMoney = downMoney;
	}

	public Integer getIsZan() {
		return this.isZan;
	}

	public void setIsZan(Integer isZan) {
		this.isZan = isZan;
	}

	public Integer getIsPing() {
		return this.isPing;
	}

	public void setIsPing(Integer isPing) {
		this.isPing = isPing;
	}

	public String getPingConcent() {
		return this.pingConcent;
	}

	public void setPingConcent(String pingConcent) {
		this.pingConcent = pingConcent;
	}

	public String getPingTime() {
		return this.pingTime;
	}

	public void setPingTime(String pingTime) {
		this.pingTime = pingTime;
	}

}