package com.source.plan.entity;

import java.util.HashSet;
import java.util.Set;

/**
 * AbstractAnswer entity provides the base persistence definition of the Answer
 * entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractAnswer implements java.io.Serializable {

	// Fields

	private String answerId;
	private Question question;
	private User user;
	private String answerContent;
	private String answerTime;
	private String answerFile;
	private Integer answerIsSelect;
	private Set dowloads = new HashSet(0);
	private Set notices = new HashSet(0);

	// Constructors

	/** default constructor */
	public AbstractAnswer() {
	}

	/** minimal constructor */
	public AbstractAnswer(String answerId) {
		this.answerId = answerId;
	}

	/** full constructor */
	public AbstractAnswer(String answerId, Question question, User user,
			String answerContent, String answerTime, String answerFile,
			Integer answerIsSelect, Set dowloads, Set notices) {
		this.answerId = answerId;
		this.question = question;
		this.user = user;
		this.answerContent = answerContent;
		this.answerTime = answerTime;
		this.answerFile = answerFile;
		this.answerIsSelect = answerIsSelect;
		this.dowloads = dowloads;
		this.notices = notices;
	}

	// Property accessors

	public String getAnswerId() {
		return this.answerId;
	}

	public void setAnswerId(String answerId) {
		this.answerId = answerId;
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

	public String getAnswerContent() {
		return this.answerContent;
	}

	public void setAnswerContent(String answerContent) {
		this.answerContent = answerContent;
	}

	public String getAnswerTime() {
		return this.answerTime;
	}

	public void setAnswerTime(String answerTime) {
		this.answerTime = answerTime;
	}

	public String getAnswerFile() {
		return this.answerFile;
	}

	public void setAnswerFile(String answerFile) {
		this.answerFile = answerFile;
	}

	public Integer getAnswerIsSelect() {
		return this.answerIsSelect;
	}

	public void setAnswerIsSelect(Integer answerIsSelect) {
		this.answerIsSelect = answerIsSelect;
	}

	public Set getDowloads() {
		return this.dowloads;
	}

	public void setDowloads(Set dowloads) {
		this.dowloads = dowloads;
	}

	public Set getNotices() {
		return this.notices;
	}

	public void setNotices(Set notices) {
		this.notices = notices;
	}

}