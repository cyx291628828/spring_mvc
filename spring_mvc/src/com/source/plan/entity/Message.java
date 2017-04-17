package com.source.plan.entity;

import java.util.Set;

/**
 * Message entity. @author MyEclipse Persistence Tools
 */
public class Message extends AbstractMessage implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public Message() {
	}

	/** minimal constructor */
	public Message(String messageId) {
		super(messageId);
	}

	/** full constructor */
	public Message(String messageId, Question question, User user,
			Message message, String messageTime, String messageContent,
			Integer messageIsReply, Set messages) {
		super(messageId, question, user, message, messageTime, messageContent,
				messageIsReply, messages);
	}

}
