package com.source.plan.beans;

public class Question {
	String questionID; //问题ID
	String questionTheme; //问题题目
	String questionContent; //问题正文
	String questionUserID; //提问者ID
	String questionUsername; //提问者名字
	String questionStaTime; // 问题发布时间
	String questionEndTime; //问题截至时间
	String questionFinTime; //问题完成时间
	String questionGenre; //问题分类
	String questionFile; //问题文件路径
	String questionMoney; // 问题赏金
	String questionDownMoney; //问题下载金额
	int questionUserLevel; //提问者等级
	int questionPageView; //问题浏览量
	int questionAnwserQuantity; //问题回复量
	int questionisFinish; //问题是否完成
	int questionisDownload; //问题是否能下载
	int questionDownload; //
	String questionUserImage;
	String collectTime;
	
	public String getCollectTime() {
		return collectTime;
	}
	public void setCollectTime(String collectTime) {
		this.collectTime = collectTime;
	}
	public String getQuestionUserImage() {
		return questionUserImage;
	}
	public void setQuestionUserImage(String questionUserImage) {
		this.questionUserImage = questionUserImage;
	}
	public int getQuestionUserLevel() {
		return questionUserLevel;
	}
	public void setQuestionUserLevel(int questionUserLevel) {
		this.questionUserLevel = questionUserLevel;
	}
	public int getQuestionisDownload() {
		return questionisDownload;
	}
	public void setQuestionisDownload(int questionisDownload) {
		this.questionisDownload = questionisDownload;
	}
	public int getQuestionPageView() {
		return questionPageView;
	}
	public void setQuestionPageView(int questionPageView) {
		this.questionPageView = questionPageView;
	}
	public String getQuestionMoney() {
		return questionMoney;
	}
	public void setQuestionMoney(String questionMoney) {
		this.questionMoney = questionMoney;
	}
	public String getQuestionEndTime() {
		return questionEndTime;
	}
	public void setQuestionEndTime(String questionEndTime) {
		this.questionEndTime = questionEndTime;
	}
	public String getQuestionID() {
		return questionID;
	}
	public void setQuestionID(String questionID) {
		this.questionID = questionID;
	}
	public String getQuestionTheme() {
		return questionTheme;
	}
	public void setQuestionTheme(String questionTheme) {
		this.questionTheme = questionTheme;
	}
	public String getQuestionContent() {
		return questionContent;
	}
	public void setQuestionContent(String questionContent) {
		this.questionContent = questionContent;
	}
	public String getQuestionUserID() {
		return questionUserID;
	}
	public void setQuestionUserID(String questionUserID) {
		this.questionUserID = questionUserID;
	}
	public String getQuestionUsername() {
		return questionUsername;
	}
	public void setQuestionUsername(String questionUsername) {
		this.questionUsername = questionUsername;
	}
	public String getQuestionStaTime() {
		return questionStaTime;
	}
	public void setQuestionStaTime(String questionStaTime) {
		this.questionStaTime = questionStaTime;
	}
	public String getQuestionFinTime() {
		return questionFinTime;
	}
	public void setQuestionFinTime(String questionFinTime) {
		this.questionFinTime = questionFinTime;
	}
	public String getQuestionGenre() {
		return questionGenre;
	}
	public void setQuestionGenre(String questionGenre) {
		this.questionGenre = questionGenre;
	}
	public String getQuestionFile() {
		return questionFile;
	}
	public void setQuestionFile(String questionFile) {
		this.questionFile = questionFile;
	}
	public String getQuestionDownMoney() {
		return questionDownMoney;
	}
	public void setQuestionDownMoney(String questionDownMoney) {
		this.questionDownMoney = questionDownMoney;
	}
	public int getQuestionAnwserQuantity() {
		return questionAnwserQuantity;
	}
	public void setQuestionAnwserQuantity(int questionAnwserQuantity) {
		this.questionAnwserQuantity = questionAnwserQuantity;
	}
	public int getQuestionisFinish() {
		return questionisFinish;
	}
	public void setQuestionisFinish(int questionisFinish) {
		this.questionisFinish = questionisFinish;
	}
	public int getQuestionDownload() {
		return questionDownload;
	}
	public void setQuestionDownload(int questionDownload) {
		this.questionDownload = questionDownload;
	}
	
}
