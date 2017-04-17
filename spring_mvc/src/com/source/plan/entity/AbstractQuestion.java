package com.source.plan.entity;

import java.util.HashSet;
import java.util.Set;

/**
 * AbstractQuestion entity provides the base persistence definition of the
 * Question entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractQuestion implements java.io.Serializable {

	// Fields

	private String questionId;
	private User user;
	private String questionTheme;
	private String questionContent;
	private String questionStaTime;
	private String questionEndTime;
	private String questionFinTime;
	private String questionGenre;
	private String questionFile;
	private Integer questionMoney;
	private Integer questionIsFinish;
	private Integer questionIsDownload;
	private Integer questionDownMoney;
	private Integer questionPageView;
	private Set dowloads = new HashSet(0);
	private Set notices = new HashSet(0);
	private Set messages = new HashSet(0);
	private Set answers = new HashSet(0);
	private Set collects = new HashSet(0);

	// Constructors

	/** default constructor */
	public AbstractQuestion() {
	}

	/** minimal constructor */
	public AbstractQuestion(String questionId) {
		this.questionId = questionId;
	}

	/** full constructor */
	public AbstractQuestion(String questionId, User user, String questionTheme,
			String questionContent, String questionStaTime,
			String questionEndTime, String questionFinTime,
			String questionGenre, String questionFile, Integer questionMoney,
			Integer questionIsFinish, Integer questionIsDownload,
			Integer questionDownMoney, Integer questionPageView, Set dowloads,
			Set notices, Set messages, Set answers, Set collects) {
		this.questionId = questionId;
		this.user = user;
		this.questionTheme = questionTheme;
		this.questionContent = questionContent;
		this.questionStaTime = questionStaTime;
		this.questionEndTime = questionEndTime;
		this.questionFinTime = questionFinTime;
		this.questionGenre = questionGenre;
		this.questionFile = questionFile;
		this.questionMoney = questionMoney;
		this.questionIsFinish = questionIsFinish;
		this.questionIsDownload = questionIsDownload;
		this.questionDownMoney = questionDownMoney;
		this.questionPageView = questionPageView;
		this.dowloads = dowloads;
		this.notices = notices;
		this.messages = messages;
		this.answers = answers;
		this.collects = collects;
	}

	// Property accessors

	public String getQuestionId() {
		return this.questionId;
	}

	public void setQuestionId(String questionId) {
		this.questionId = questionId;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getQuestionTheme() {
		return this.questionTheme;
	}

	public void setQuestionTheme(String questionTheme) {
		this.questionTheme = questionTheme;
	}

	public String getQuestionContent() {
		return this.questionContent;
	}

	public void setQuestionContent(String questionContent) {
		this.questionContent = questionContent;
	}

	public String getQuestionStaTime() {
		return this.questionStaTime;
	}

	public void setQuestionStaTime(String questionStaTime) {
		this.questionStaTime = questionStaTime;
	}

	public String getQuestionEndTime() {
		return this.questionEndTime;
	}

	public void setQuestionEndTime(String questionEndTime) {
		this.questionEndTime = questionEndTime;
	}

	public String getQuestionFinTime() {
		return this.questionFinTime;
	}

	public void setQuestionFinTime(String questionFinTime) {
		this.questionFinTime = questionFinTime;
	}

	public String getQuestionGenre() {
		return this.questionGenre;
	}

	public void setQuestionGenre(String questionGenre) {
		this.questionGenre = questionGenre;
	}

	public String getQuestionFile() {
		return this.questionFile;
	}

	public void setQuestionFile(String questionFile) {
		this.questionFile = questionFile;
	}

	public Integer getQuestionMoney() {
		return this.questionMoney;
	}

	public void setQuestionMoney(Integer questionMoney) {
		this.questionMoney = questionMoney;
	}

	public Integer getQuestionIsFinish() {
		return this.questionIsFinish;
	}

	public void setQuestionIsFinish(Integer questionIsFinish) {
		this.questionIsFinish = questionIsFinish;
	}

	public Integer getQuestionIsDownload() {
		return this.questionIsDownload;
	}

	public void setQuestionIsDownload(Integer questionIsDownload) {
		this.questionIsDownload = questionIsDownload;
	}

	public Integer getQuestionDownMoney() {
		return this.questionDownMoney;
	}

	public void setQuestionDownMoney(Integer questionDownMoney) {
		this.questionDownMoney = questionDownMoney;
	}

	public Integer getQuestionPageView() {
		return this.questionPageView;
	}

	public void setQuestionPageView(Integer questionPageView) {
		this.questionPageView = questionPageView;
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

	public Set getMessages() {
		return this.messages;
	}

	public void setMessages(Set messages) {
		this.messages = messages;
	}

	public Set getAnswers() {
		return this.answers;
	}

	public void setAnswers(Set answers) {
		this.answers = answers;
	}

	public Set getCollects() {
		return this.collects;
	}

	public void setCollects(Set collects) {
		this.collects = collects;
	}

}