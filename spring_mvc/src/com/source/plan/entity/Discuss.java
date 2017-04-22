package com.source.plan.entity;

import java.util.Set;

/**
 * Discuss entity. @author MyEclipse Persistence Tools
 */
public class Discuss extends AbstractDiscuss implements java.io.Serializable {

	// Constructors

	/**
	 * 添加的字段
	 */
	String Re_lastTime = "";
	String Re_lastName = "";
	
	

	public String getRe_lastTime() {
		return Re_lastTime;
	}

	public void setRe_lastTime(String re_lastTime) {
		Re_lastTime = re_lastTime;
	}

	public String getRe_lastName() {
		return Re_lastName;
	}

	public void setRe_lastName(String re_lastName) {
		Re_lastName = re_lastName;
	}

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
