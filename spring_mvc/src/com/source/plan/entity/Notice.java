package com.source.plan.entity;

/**
 * Notice entity. @author MyEclipse Persistence Tools
 */
public class Notice extends AbstractNotice implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public Notice() {
	}

	/** minimal constructor */
	public Notice(String noticeId) {
		super(noticeId);
	}

	/** full constructor */
	public Notice(String noticeId, Question question, User userByNoticeGiveId,
			User userByNoticeReceiveId, Answer answer, String noticeContent,
			String noticeAriseTime, String noticeIsCheck) {
		super(noticeId, question, userByNoticeGiveId, userByNoticeReceiveId,
				answer, noticeContent, noticeAriseTime, noticeIsCheck);
	}

}
