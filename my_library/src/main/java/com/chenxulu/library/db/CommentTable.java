package com.chenxulu.library.db;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class CommentTable {

	private static final int _ID = 0;
	private static final int DYNAMIC_ID = 1;
	private static final int COMMENT_ID = 2;
	private static final int USER_ID = 3;
	private static final int CONTENT = 4;
	private static final int VOICE_URL = 5;
	private static final int VOICE_DURATION = 6;
	private static final int CREATE_TIME = 7;
	private static final int COMMENT_TYPE = 8;

	private static final String TABLE_NAME = "comment_table";
	private static final String COLUMNS[] = { "_id", "dynamic_id",
			"comment_id", "user_id", "_content",
			"voice_url", "voice_duration", "create_time", "comment_type" };

	public static final String CREATE_SQL = "CREATE TABLE IF NOT EXISTS "
			+ TABLE_NAME + " (" + COLUMNS[_ID]
			+ " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMNS[DYNAMIC_ID]
			+ " VARCHAR," + COLUMNS[COMMENT_ID] + " VARCHAR,"
			+ COLUMNS[USER_ID] + " VARCHAR," + COLUMNS[CONTENT] + " VARCHAR,"
			+ COLUMNS[VOICE_URL] + " VARCHAR," + COLUMNS[VOICE_DURATION]
			+ " VARCHAR," + COLUMNS[CREATE_TIME] + " VARCHAR,"
			+ COLUMNS[COMMENT_TYPE] + " INTEGER" + ");";
	private SQLiteDatabase database;

	CommentTable(SQLiteDatabase sqLiteDatabase) {
		database = sqLiteDatabase;
	}

	long insertComment(String dynamicID, Comment comment) {
		ContentValues values = new ContentValues();
		values.put(COLUMNS[DYNAMIC_ID], dynamicID);
		values.put(COLUMNS[COMMENT_ID], comment.getCommentID());
		values.put(COLUMNS[USER_ID], comment.getUserID());
		values.put(COLUMNS[CONTENT], comment.getContent());
		values.put(COLUMNS[VOICE_URL], comment.getVoiceURL());
		values.put(COLUMNS[VOICE_DURATION], comment.getVoiceDuration());
		values.put(COLUMNS[CREATE_TIME], comment.getCreateTime());
		values.put(COLUMNS[COMMENT_TYPE], comment.getType());
		return database.insert(TABLE_NAME, null, values);
	}

	int deleteComment(String commentId) {
		String whereClause = COLUMNS[COMMENT_ID] + "=?";
		String whereArgs[] = { commentId };
		return database.delete(TABLE_NAME, whereClause, whereArgs);
	}

	int deleteDynamicComment(String dynamicID) {
		String whereClause = COLUMNS[DYNAMIC_ID] + "=?";
		String whereArgs[] = { dynamicID };
		return database.delete(TABLE_NAME, whereClause, whereArgs);
	}

	ArrayList<Comment> queryComments(String dynamicID) {
		String selection = COLUMNS[DYNAMIC_ID] + "=?";
		String selectionArgs[] = { dynamicID };
		Cursor cursor = database.query(TABLE_NAME, COLUMNS, selection,
				selectionArgs, null, null, null);
		ArrayList<Comment> comments = new ArrayList<Comment>();
		while (cursor.moveToNext()) {
			Comment comment = new Comment();
			comment.setCommentID(cursor.getString(COMMENT_ID));
			comment.setUserID(cursor.getString(USER_ID));
			comment.setContent(cursor.getString(CONTENT));
			comment.setVoiceURL(cursor.getString(VOICE_URL));
			comment.setVoiceDuration(cursor.getString(VOICE_DURATION));
			comment.setCreateTime(cursor.getString(CREATE_TIME));
			comment.setType(cursor.getInt(COMMENT_TYPE));
			comments.add(comment);
		}
		cursor.close();
		return comments;
	}

	Comment queryComment(String commentId) {
		String selection = COLUMNS[COMMENT_ID] + "=?";
		String selectionArgs[] = { commentId };
		Cursor cursor = database.query(TABLE_NAME, COLUMNS, selection,
				selectionArgs, null, null, null);
		Comment comment = null;
		while (cursor.moveToNext()) {
			comment = new Comment();
			comment.setCommentID(cursor.getString(COMMENT_ID));
			comment.setUserID(cursor.getString(USER_ID));
			comment.setContent(cursor.getString(CONTENT));
			comment.setVoiceURL(cursor.getString(VOICE_URL));
			comment.setVoiceDuration(cursor.getString(VOICE_DURATION));
			comment.setCreateTime(cursor.getString(CREATE_TIME));
			comment.setType(cursor.getInt(COMMENT_TYPE));
		}
		cursor.close();
		return comment;
	}

	boolean isExit(String commentId) {
		String selection = COLUMNS[COMMENT_ID] + "=?";
		String selectionArgs[] = { commentId };
		Cursor cursor = database.query(TABLE_NAME, COLUMNS, selection,
				selectionArgs, null, null, null);
		boolean isExit = cursor.moveToNext();
		cursor.close();
		return isExit;
	}
}
