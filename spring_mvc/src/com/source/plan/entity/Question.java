package com.source.plan.entity;

import java.util.Set;

/**
 * Question entity. @author MyEclipse Persistence Tools
 */
public class Question extends AbstractQuestion implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public Question() {
	}

	/** minimal constructor */
	public Question(String questionId) {
		super(questionId);
	}

	/** full constructor */
	public Question(String questionId, User user, String questionTheme,
			String questionContent, String questionStaTime,
			String questionEndTime, String questionFinTime,
			String questionGenre, String questionFile, Integer questionMoney,
			Integer questionIsFinish, Integer questionIsDownload,
			Integer questionDownMoney, Integer questionPageView, Set dowloads,
			Set notices, Set messages, Set answers, Set collects) {
		super(questionId, user, questionTheme, questionContent,
				questionStaTime, questionEndTime, questionFinTime,
				questionGenre, questionFile, questionMoney, questionIsFinish,
				questionIsDownload, questionDownMoney, questionPageView,
				dowloads, notices, messages, answers, collects);
	}

}
