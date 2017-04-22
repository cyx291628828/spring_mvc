package com.source.plan.entity;

import java.util.Set;

/**
 * Answer entity. @author MyEclipse Persistence Tools
 */
public class Answer extends AbstractAnswer implements java.io.Serializable {

	// Constructors
	/**
	 * 添加的字段
	 */
	int zan_num = 0;
	int pinglun_num = 0;
	int download_num = 0;
	

	public int getDownload_num() {
		return download_num;
	}

	public void setDownload_num(int download_num) {
		this.download_num = download_num;
	}

	public int getZan_num() {
		return zan_num;
	}

	public void setZan_num(int zan_num) {
		this.zan_num = zan_num;
	}

	public int getPinglun_num() {
		return pinglun_num;
	}

	public void setPinglun_num(int pinglun_num) {
		this.pinglun_num = pinglun_num;
	}

	/** default constructor */
	public Answer() {
	}

	/** minimal constructor */
	public Answer(String answerId) {
		super(answerId);
	}

	/** full constructor */
	public Answer(String answerId, Question question, User user,
			String answerContent, String answerTime, String answerFile,
			Integer answerIsSelect, Set dowloads, Set notices) {
		super(answerId, question, user, answerContent, answerTime, answerFile,
				answerIsSelect, dowloads, notices);
	}

}
