package com.source.plan.entity;

import java.util.HashSet;
import java.util.Set;

/**
 * AbstractUser entity provides the base persistence definition of the User
 * entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractUser implements java.io.Serializable {

	// Fields

	private String userId;
	private String userName;
	private String userPass;
	private String userRegTime;
	private String userSex;
	private String userBirth;
	private String userJob;
	private Integer userFakeMoney;
	private String userInterest;
	private Integer userLevel;
	private String userImage;
	private Integer userAnswerNum;
	private Integer userAnswerScsNum;
	private Set noticesForNoticeReceiveId = new HashSet(0);
	private Set messages = new HashSet(0);
	private Set collects = new HashSet(0);
	private Set answers = new HashSet(0);
	private Set noticesForNoticeGiveId = new HashSet(0);
	private Set discusses = new HashSet(0);
	private Set questions = new HashSet(0);
	private Set dowloads = new HashSet(0);

	// Constructors

	/** default constructor */
	public AbstractUser() {
	}

	/** minimal constructor */
	public AbstractUser(String userId) {
		this.userId = userId;
	}

	/** full constructor */
	public AbstractUser(String userId, String userName, String userPass,
			String userRegTime, String userSex, String userBirth,
			String userJob, Integer userFakeMoney, String userInterest,
			Integer userLevel, String userImage, Integer userAnswerNum,
			Integer userAnswerScsNum, Set noticesForNoticeReceiveId,
			Set messages, Set collects, Set answers,
			Set noticesForNoticeGiveId, Set discusses, Set questions,
			Set dowloads) {
		this.userId = userId;
		this.userName = userName;
		this.userPass = userPass;
		this.userRegTime = userRegTime;
		this.userSex = userSex;
		this.userBirth = userBirth;
		this.userJob = userJob;
		this.userFakeMoney = userFakeMoney;
		this.userInterest = userInterest;
		this.userLevel = userLevel;
		this.userImage = userImage;
		this.userAnswerNum = userAnswerNum;
		this.userAnswerScsNum = userAnswerScsNum;
		this.noticesForNoticeReceiveId = noticesForNoticeReceiveId;
		this.messages = messages;
		this.collects = collects;
		this.answers = answers;
		this.noticesForNoticeGiveId = noticesForNoticeGiveId;
		this.discusses = discusses;
		this.questions = questions;
		this.dowloads = dowloads;
	}

	// Property accessors

	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPass() {
		return this.userPass;
	}

	public void setUserPass(String userPass) {
		this.userPass = userPass;
	}

	public String getUserRegTime() {
		return this.userRegTime;
	}

	public void setUserRegTime(String userRegTime) {
		this.userRegTime = userRegTime;
	}

	public String getUserSex() {
		return this.userSex;
	}

	public void setUserSex(String userSex) {
		this.userSex = userSex;
	}

	public String getUserBirth() {
		return this.userBirth;
	}

	public void setUserBirth(String userBirth) {
		this.userBirth = userBirth;
	}

	public String getUserJob() {
		return this.userJob;
	}

	public void setUserJob(String userJob) {
		this.userJob = userJob;
	}

	public Integer getUserFakeMoney() {
		return this.userFakeMoney;
	}

	public void setUserFakeMoney(Integer userFakeMoney) {
		this.userFakeMoney = userFakeMoney;
	}

	public String getUserInterest() {
		return this.userInterest;
	}

	public void setUserInterest(String userInterest) {
		this.userInterest = userInterest;
	}

	public Integer getUserLevel() {
		return this.userLevel;
	}

	public void setUserLevel(Integer userLevel) {
		this.userLevel = userLevel;
	}

	public String getUserImage() {
		return this.userImage;
	}

	public void setUserImage(String userImage) {
		this.userImage = userImage;
	}

	public Integer getUserAnswerNum() {
		return this.userAnswerNum;
	}

	public void setUserAnswerNum(Integer userAnswerNum) {
		this.userAnswerNum = userAnswerNum;
	}

	public Integer getUserAnswerScsNum() {
		return this.userAnswerScsNum;
	}

	public void setUserAnswerScsNum(Integer userAnswerScsNum) {
		this.userAnswerScsNum = userAnswerScsNum;
	}

	public Set getNoticesForNoticeReceiveId() {
		return this.noticesForNoticeReceiveId;
	}

	public void setNoticesForNoticeReceiveId(Set noticesForNoticeReceiveId) {
		this.noticesForNoticeReceiveId = noticesForNoticeReceiveId;
	}

	public Set getMessages() {
		return this.messages;
	}

	public void setMessages(Set messages) {
		this.messages = messages;
	}

	public Set getCollects() {
		return this.collects;
	}

	public void setCollects(Set collects) {
		this.collects = collects;
	}

	public Set getAnswers() {
		return this.answers;
	}

	public void setAnswers(Set answers) {
		this.answers = answers;
	}

	public Set getNoticesForNoticeGiveId() {
		return this.noticesForNoticeGiveId;
	}

	public void setNoticesForNoticeGiveId(Set noticesForNoticeGiveId) {
		this.noticesForNoticeGiveId = noticesForNoticeGiveId;
	}

	public Set getDiscusses() {
		return this.discusses;
	}

	public void setDiscusses(Set discusses) {
		this.discusses = discusses;
	}

	public Set getQuestions() {
		return this.questions;
	}

	public void setQuestions(Set questions) {
		this.questions = questions;
	}

	public Set getDowloads() {
		return this.dowloads;
	}

	public void setDowloads(Set dowloads) {
		this.dowloads = dowloads;
	}

}