package com.source.plan.beans;

import java.util.ArrayList;
import java.util.List;

public class Discuss {
	String DiscussID;//消息编号
	String DiscussUserID;//回复人的ID
	String UserName;//回复人的名字
	String UserImage;//回复人的图像
	String DiscussTime;//消息的时间
	String DiscussContent;//消息内容
	int IsTheme;//是否是楼层
	int IsReply;//是否是回复
	String ReplyDiscussID;//回复的消息编号
	String LastName;//最后一个回复的人的名字
	String LastTime;//最后一个回复的人的时间
	int ReplyNumber;//回复的消息个数
	List<Discuss> replydiscuss = new ArrayList<Discuss>();//存放楼层内的消息
	//int pageSum;//消息的总个数    用来设置分页显示
	//int pageNum;//当前页数  当前第几页
	
	public List<Discuss> getReplydiscuss() {
		return replydiscuss;
	}
	public String getUserImage() {
		return UserImage;
	}
	public void setUserImage(String userImage) {
		UserImage = userImage;
	}
	public void setReplydiscuss(List<Discuss> replydiscuss) {
		this.replydiscuss = replydiscuss;
	}
	public String getLastName() {
		return LastName;
	}
	public void setLastName(String lastName) {
		LastName = lastName;
	}
	public String getDiscussID() {
		return DiscussID;
	}
	public void setDiscussID(String discussID) {
		DiscussID = discussID;
	}
	public String getDiscussUserID() {
		return DiscussUserID;
	}
	public void setDiscussUserID(String discussUserID) {
		DiscussUserID = discussUserID;
	}
	public String getUserName() {
		return UserName;
	}
	public void setUserName(String userName) {
		UserName = userName;
	}
	public String getDiscussTime() {
		return DiscussTime;
	}
	public void setDiscussTime(String discussTime) {
		DiscussTime = discussTime;
	}
	public String getDiscussContent() {
		return DiscussContent;
	}
	public void setDiscussContent(String discussContent) {
		DiscussContent = discussContent;
	}
	public int getIsTheme() {
		return IsTheme;
	}
	public void setIsTheme(int isTheme) {
		IsTheme = isTheme;
	}
	public int getIsReply() {
		return IsReply;
	}
	public void setIsReply(int isReply) {
		IsReply = isReply;
	}
	public String getReplyDiscussID() {
		return ReplyDiscussID;
	}
	public void setReplyDiscussID(String replyDiscussID) {
		ReplyDiscussID = replyDiscussID;
	}
	public String getLastTime() {
		return LastTime;
	}
	public void setLastTime(String lastTime) {
		LastTime = lastTime;
	}
	public int getReplyNumber() {
		return ReplyNumber;
	}
	public void setReplyNumber(int replyNumber) {
		ReplyNumber = replyNumber;
	}
	
}
