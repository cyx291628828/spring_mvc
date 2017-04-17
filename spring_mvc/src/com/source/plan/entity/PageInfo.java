package com.source.plan.entity;

import java.util.List;

public class PageInfo {
	List<User> users;
	List<Question> questions;
	List<Notice> notices;
	List<Answer> answers;
	Answer answer;
	Question question;
	List<Genre> genres;
	List<Dowload> downloads;
	Dowload download;

	public Dowload getDownload() {
		return download;
	}
	public void setDownload(Dowload download) {
		this.download = download;
	}
	public Answer getAnswer() {
		return answer;
	}
	public void setAnswer(Answer answer) {
		this.answer = answer;
	}
	public List<Dowload> getDownloads() {
		return downloads;
	}
	public void setDownloads(List<Dowload> downloads) {
		this.downloads = downloads;
	}
	public Question getQuestion() {
		return question;
	}
	public void setQuestion(Question question) {
		this.question = question;
	}
	public List<Answer> getAnswers() {
		return answers;
	}
	public void setAnswers(List<Answer> answers) {
		this.answers = answers;
	}
	public List<Question> getQuestions() {
		return questions;
	}
	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}
	
	public List<Notice> getNotices() {
		return notices;
	}
	public void setNotices(List<Notice> notices) {
		this.notices = notices;
	}

	int pageNum; //当前页面
	int pageSum; //总页面
	public List<User> getUsers() {
		return users;
	}
	public void setUsers(List<User> users) {
		this.users = users;
	}
	
	public int getPageNum() {
		return pageNum;
	}
	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}
	public int getPageSum() {
		return pageSum;
	}
	public void setPageSum(int pageSum) {
		this.pageSum = pageSum;
	}
	public List<Genre> getGenres() {
		return genres;
	}
	public void setGenres(List<Genre> genres) {
		this.genres = genres;
	}
	
}
