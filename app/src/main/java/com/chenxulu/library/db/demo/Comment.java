package com.chenxulu.library.db.demo;

import java.io.Serializable;

public class Comment implements Serializable {
	private static final long serialVersionUID = 658063163606459367L;

	private String commentID;
	private String userID;
	private String nickName;
	private String userHeadURL;
	private String content;
	private String voiceURL;
	private String voiceDuration;
	private String createTime;
	private int type;

	public String getCommentID() {
		return commentID;
	}

	public void setCommentID(String commentID) {
		this.commentID = commentID;
	}

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getUserHeadURL() {
		return userHeadURL;
	}

	public void setUserHeadURL(String userHeadURL) {
		this.userHeadURL = userHeadURL;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getVoiceURL() {
		return voiceURL;
	}

	public void setVoiceURL(String voiceURL) {
		this.voiceURL = voiceURL;
	}

	public String getVoiceDuration() {
		return voiceDuration;
	}

	public void setVoiceDuration(String voiceDuration) {
		this.voiceDuration = voiceDuration;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

}
