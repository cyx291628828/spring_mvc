package com.source.plan.entity;

import java.util.Set;

/**
 * User entity. @author MyEclipse Persistence Tools
 */
public class User extends AbstractUser implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public User() {
	}

	/** minimal constructor */
	public User(String userId) {
		super(userId);
	}

	/** full constructor */
	public User(String userId, String userName, String userPass,
			String userRegTime, String userSex, String userBirth,
			String userJob, Integer userFakeMoney, String userInterest,
			Integer userLevel, String userImage, Integer userAnswerNum,
			Integer userAnswerScsNum, Set noticesForNoticeReceiveId,
			Set messages, Set collects, Set answers,
			Set noticesForNoticeGiveId, Set discusses, Set questions,
			Set dowloads) {
		super(userId, userName, userPass, userRegTime, userSex, userBirth,
				userJob, userFakeMoney, userInterest, userLevel, userImage,
				userAnswerNum, userAnswerScsNum, noticesForNoticeReceiveId,
				messages, collects, answers, noticesForNoticeGiveId, discusses,
				questions, dowloads);
	}

}
