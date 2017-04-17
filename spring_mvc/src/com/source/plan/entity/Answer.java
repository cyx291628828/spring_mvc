package com.source.plan.entity;

import java.util.Set;

/**
 * Answer entity. @author MyEclipse Persistence Tools
 */
public class Answer extends AbstractAnswer implements java.io.Serializable {

	// Constructors

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
