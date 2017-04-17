package com.source.plan.entity;

import java.util.Set;

/**
 * Discuss entity. @author MyEclipse Persistence Tools
 */
public class Discuss extends AbstractDiscuss implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public Discuss() {
	}

	/** minimal constructor */
	public Discuss(String discussId) {
		super(discussId);
	}

	/** full constructor */
	public Discuss(String discussId, Discuss discussByThemeDiscussId,
			User user, Discuss discussByReplyDiscussId, String discussTime,
			String discussContent, Integer isTheme, Integer isReply,
			String lastTime, Set discussesForReplyDiscussId,
			Set discussesForThemeDiscussId) {
		super(discussId, discussByThemeDiscussId, user,
				discussByReplyDiscussId, discussTime, discussContent, isTheme,
				isReply, lastTime, discussesForReplyDiscussId,
				discussesForThemeDiscussId);
	}

}
