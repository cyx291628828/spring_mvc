package com.source.plan.entity;

/**
 * AbstractNotice entity provides the base persistence definition of the Notice
 * entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractNotice implements java.io.Serializable {

	// Fields

	private String noticeId;
	private Question question;
	private User userByNoticeGiveId;
	private User userByNoticeReceiveId;
	private Answer answer;
	private String noticeContent;
	private String noticeAriseTime;
	private String noticeIsCheck;

	// Constructors

	/** default constructor */
	public AbstractNotice() {
	}

	/** minimal constructor */
	public AbstractNotice(String noticeId) {
		this.noticeId = noticeId;
	}

	/** full constructor */
	public AbstractNotice(String noticeId, Question question,
			User userByNoticeGiveId, User userByNoticeReceiveId, Answer answer,
			String noticeContent, String noticeAriseTime, String noticeIsCheck) {
		this.noticeId = noticeId;
		this.question = question;
		this.userByNoticeGiveId = userByNoticeGiveId;
		this.userByNoticeReceiveId = userByNoticeReceiveId;
		this.answer = answer;
		this.noticeContent = noticeContent;
		this.noticeAriseTime = noticeAriseTime;
		this.noticeIsCheck = noticeIsCheck;
	}

	// Property accessors

	public String getNoticeId() {
		return this.noticeId;
	}

	public void setNoticeId(String noticeId) {
		this.noticeId = noticeId;
	}

	public Question getQuestion() {
		return this.question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	public User getUserByNoticeGiveId() {
		return this.userByNoticeGiveId;
	}

	public void setUserByNoticeGiveId(User userByNoticeGiveId) {
		this.userByNoticeGiveId = userByNoticeGiveId;
	}

	public User getUserByNoticeReceiveId() {
		return this.userByNoticeReceiveId;
	}

	public void setUserByNoticeReceiveId(User userByNoticeReceiveId) {
		this.userByNoticeReceiveId = userByNoticeReceiveId;
	}

	public Answer getAnswer() {
		return this.answer;
	}

	public void setAnswer(Answer answer) {
		this.answer = answer;
	}

	public String getNoticeContent() {
		return this.noticeContent;
	}

	public void setNoticeContent(String noticeContent) {
		this.noticeContent = noticeContent;
	}

	public String getNoticeAriseTime() {
		return this.noticeAriseTime;
	}

	public void setNoticeAriseTime(String noticeAriseTime) {
		this.noticeAriseTime = noticeAriseTime;
	}

	public String getNoticeIsCheck() {
		return this.noticeIsCheck;
	}

	public void setNoticeIsCheck(String noticeIsCheck) {
		this.noticeIsCheck = noticeIsCheck;
	}

}