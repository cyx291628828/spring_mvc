package com.source.plan.entity;

/**
 * Dowload entity. @author MyEclipse Persistence Tools
 */
public class Dowload extends AbstractDowload implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public Dowload() {
	}

	/** minimal constructor */
	public Dowload(String downId) {
		super(downId);
	}

	/** full constructor */
	public Dowload(String downId, Question question, User user, Answer answer,
			String downTime, Integer downMoney, Integer isZan, Integer isPing,
			String pingConcent, String pingTime) {
		super(downId, question, user, answer, downTime, downMoney, isZan,
				isPing, pingConcent, pingTime);
	}

}
