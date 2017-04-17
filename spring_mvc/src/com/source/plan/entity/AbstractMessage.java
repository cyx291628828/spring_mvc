package com.source.plan.entity;

import java.util.HashSet;
import java.util.Set;

/**
 * AbstractMessage entity provides the base persistence definition of the
 * Message entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractMessage implements java.io.Serializable {

	// Fields

	private String messageId;
	private Question question;
	private User user;
	private Message message;
	private String messageTime;
	private String messageContent;
	private Integer messageIsReply;
	private Set messages = new HashSet(0);

	// Constructors

	/** default constructor */
	public AbstractMessage() {
	}

	/** minimal constructor */
	public AbstractMessage(String messageId) {
		this.messageId = messageId;
	}

	/** full constructor */
	public AbstractMessage(String messageId, Question question, User user,
			Message message, String messageTime, String messageContent,
			Integer messageIsReply, Set messages) {
		this.messageId = messageId;
		this.question = question;
		this.user = user;
		this.message = message;
		this.messageTime = messageTime;
		this.messageContent = messageContent;
		this.messageIsReply = messageIsReply;
		this.messages = messages;
	}

	// Property accessors

	public String getMessageId() {
		return this.messageId;
	}

	public void setMessageId(String messageId) {
		this.messageId = messageId;
	}

	public Question getQuestion() {
		return this.question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Message getMessage() {
		return this.message;
	}

	public void setMessage(Message message) {
		this.message = message;
	}

	public String getMessageTime() {
		return this.messageTime;
	}

	public void setMessageTime(String messageTime) {
		this.messageTime = messageTime;
	}

	public String getMessageContent() {
		return this.messageContent;
	}

	public void setMessageContent(String messageContent) {
		this.messageContent = messageContent;
	}

	public Integer getMessageIsReply() {
		return this.messageIsReply;
	}

	public void setMessageIsReply(Integer messageIsReply) {
		this.messageIsReply = messageIsReply;
	}

	public Set getMessages() {
		return this.messages;
	}

	public void setMessages(Set messages) {
		this.messages = messages;
	}

}