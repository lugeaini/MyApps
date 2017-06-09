package com.chenxulu.library.db;

import android.os.Parcel;
import android.os.Parcelable;

public class Comment implements Parcelable {
    private String commentID;
    private String userID;
    private String nickName;
    private String userHeadURL;
    private String content;
    private String voiceURL;
    private String voiceDuration;
    private String createTime;
    private int type;

    public Comment() {
    }

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


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.commentID);
        dest.writeString(this.userID);
        dest.writeString(this.nickName);
        dest.writeString(this.userHeadURL);
        dest.writeString(this.content);
        dest.writeString(this.voiceURL);
        dest.writeString(this.voiceDuration);
        dest.writeString(this.createTime);
        dest.writeInt(this.type);
    }

    protected Comment(Parcel in) {
        this.commentID = in.readString();
        this.userID = in.readString();
        this.nickName = in.readString();
        this.userHeadURL = in.readString();
        this.content = in.readString();
        this.voiceURL = in.readString();
        this.voiceDuration = in.readString();
        this.createTime = in.readString();
        this.type = in.readInt();
    }

    public static final Creator<Comment> CREATOR = new Creator<Comment>() {
        @Override
        public Comment createFromParcel(Parcel source) {
            return new Comment(source);
        }

        @Override
        public Comment[] newArray(int size) {
            return new Comment[size];
        }
    };
}
