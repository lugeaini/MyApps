package com.chenxulu.library.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

public class UTF_8 {
	public static String code(String string) {
		try {
			string = URLEncoder.encode(string, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return string;
	}

	public static String decode(String string) {
		try {
			string = URLDecoder.decode(string, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return string;
	}
}
