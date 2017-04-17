package com.source.plan.entity;

import java.util.HashSet;
import java.util.Set;

/**
 * AbstractDiscuss entity provides the base persistence definition of the
 * Discuss entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractDiscuss implements java.io.Serializable {

	// Fields

	private String discussId;
	private Discuss discussByThemeDiscussId;
	private User user;
	private Discuss discussByReplyDiscussId;
	private String discussTime;
	private String discussContent;
	private Integer isTheme;
	private Integer isReply;
	private String lastTime;
	private Set discussesForReplyDiscussId = new HashSet(0);
	private Set discussesForThemeDiscussId = new HashSet(0);

	// Constructors

	/** default constructor */
	public AbstractDiscuss() {
	}

	/** minimal constructor */
	public AbstractDiscuss(String discussId) {
		this.discussId = discussId;
	}

	/** full constructor */
	public AbstractDiscuss(String discussId, Discuss discussByThemeDiscussId,
			User user, Discuss discussByReplyDiscussId, String discussTime,
			String discussContent, Integer isTheme, Integer isReply,
			String lastTime, Set discussesForReplyDiscussId,
			Set discussesForThemeDiscussId) {
		this.discussId = discussId;
		this.discussByThemeDiscussId = discussByThemeDiscussId;
		this.user = user;
		this.discussByReplyDiscussId = discussByReplyDiscussId;
		this.discussTime = discussTime;
		this.discussContent = discussContent;
		this.isTheme = isTheme;
		this.isReply = isReply;
		this.lastTime = lastTime;
		this.discussesForReplyDiscussId = discussesForReplyDiscussId;
		this.discussesForThemeDiscussId = discussesForThemeDiscussId;
	}

	// Property accessors

	public String getDiscussId() {
		return this.discussId;
	}

	public void setDiscussId(String discussId) {
		this.discussId = discussId;
	}

	public Discuss getDiscussByThemeDiscussId() {
		return this.discussByThemeDiscussId;
	}

	public void setDiscussByThemeDiscussId(Discuss discussByThemeDiscussId) {
		this.discussByThemeDiscussId = discussByThemeDiscussId;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Discuss getDiscussByReplyDiscussId() {
		return this.discussByReplyDiscussId;
	}

	public void setDiscussByReplyDiscussId(Discuss discussByReplyDiscussId) {
		this.discussByReplyDiscussId = discussByReplyDiscussId;
	}

	public String getDiscussTime() {
		return this.discussTime;
	}

	public void setDiscussTime(String discussTime) {
		this.discussTime = discussTime;
	}

	public String getDiscussContent() {
		return this.discussContent;
	}

	public void setDiscussContent(String discussContent) {
		this.discussContent = discussContent;
	}

	public Integer getIsTheme() {
		return this.isTheme;
	}

	public void setIsTheme(Integer isTheme) {
		this.isTheme = isTheme;
	}

	public Integer getIsReply() {
		return this.isReply;
	}

	public void setIsReply(Integer isReply) {
		this.isReply = isReply;
	}

	public String getLastTime() {
		return this.lastTime;
	}

	public void setLastTime(String lastTime) {
		this.lastTime = lastTime;
	}

	public Set getDiscussesForReplyDiscussId() {
		return this.discussesForReplyDiscussId;
	}

	public void setDiscussesForReplyDiscussId(Set discussesForReplyDiscussId) {
		this.discussesForReplyDiscussId = discussesForReplyDiscussId;
	}

	public Set getDiscussesForThemeDiscussId() {
		return this.discussesForThemeDiscussId;
	}

	public void setDiscussesForThemeDiscussId(Set discussesForThemeDiscussId) {
		this.discussesForThemeDiscussId = discussesForThemeDiscussId;
	}

}