package com.chenxulu.library.utils;

import android.content.Context;

import com.chenxulu.library.widget.CustomProgressDialog;
import com.chenxulu.myapps.R;

public class DialogUtil {
	private static CustomProgressDialog mDialog;

	public static DialogUtil getInstance() {
		return null;
	}

	public static void showDialog(Context context) {
		mDialog = new CustomProgressDialog(context, R.style.myDialog);
		mDialog.setCanceledOnTouchOutside(false);
		mDialog.show();
	}

	public static void showDialog(Context context, String message) {
		mDialog = new CustomProgressDialog(context, R.style.myDialog);
		mDialog.setTitle(message);
		mDialog.setCanceledOnTouchOutside(false);
		mDialog.show();
	}
}
