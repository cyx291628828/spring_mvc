package com.source.plan.beans;

public class Notice {
	String noticeID;  //消息ID
	String noticeContent; //消息内容
	String noticeDate; // 消息日期
	int noticeIsCheck; // 消息是否被查看
	String noticeProblemID; //消息对应的问题ID
	String noticeAnswerID;  //消息对应的回复ID
	
	public String getNoticeProblemID() {
		return noticeProblemID;
	}
	public void setNoticeProblemID(String noticeProblemID) {
		this.noticeProblemID = noticeProblemID;
	}
	public String getNoticeID() {
		return noticeID;
	}
	public void setNoticeID(String noticeID) {
		this.noticeID = noticeID;
	}
	public String getNoticeContent() {
		return noticeContent;
	}
	public void setNoticeContent(String noticeContent) {
		this.noticeContent = noticeContent;
	}
	public String getNoticeDate() {
		return noticeDate;
	}
	public void setNoticeDate(String noticeDate) {
		this.noticeDate = noticeDate;
	}
	public int getNoticeIsCheck() {
		return noticeIsCheck;
	}
	public void setNoticeIsCheck(int noticeIsCheck) {
		this.noticeIsCheck = noticeIsCheck;
	}
	
}
