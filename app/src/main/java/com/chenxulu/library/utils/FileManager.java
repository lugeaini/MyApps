package com.chenxulu.library.utils;

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Locale;

import android.text.TextUtils;

/**
 * 文件的管理,单例模式
 */
public class FileManager {

	/**
	 * create File
	 * 
	 * @param fileName
	 * @return
	 * @throws IOException
	 */
	public static File createFile(String fileName) throws IOException {
		File file = new File(fileName);
		file.createNewFile();
		return file;
	}

	/**
	 * create file Dir
	 * 
	 * @param dirName
	 */
	public static void createFileDir(String dirName) {
		File dir = new File(dirName);
		if (!dir.exists()) {
			dir.mkdirs();
		}
	}

	/**
	 * is File Exist
	 * 
	 * @param file
	 * @return
	 */
	public static boolean isFileExist(String file) {
		if (TextUtils.isEmpty(file)) {
			return false;
		} else {
			File file2 = new File(file);
			if (file2.exists()) {
				return true;
			} else {
				return false;
			}
		}
	}

	/**
	 * get File Size
	 * 
	 * @param filePath
	 * @return
	 */
	public static int getFileSize(String filePath) {
		File mFile = new File(filePath);
		if (mFile.exists()) {
			return (int) mFile.length();
		}
		return 0;
	}

	/**
	 * get folder Size
	 * 
	 * @param dir
	 * @return
	 */
	public static long getFolderSize(File dir) {
		long size = 0;
		File flist[] = dir.listFiles();
		for (int i = 0; i < flist.length; i++) {
			if (flist[i].isDirectory()) {
				size = size + getFolderSize(flist[i]);
			} else {
				size = size + flist[i].length();
			}
		}
		return size;
	}

	/**
	 * format File Size
	 * 
	 * @param fileSize
	 * @return 1B,2K,3M,4G
	 */
	public static String formatFileSize(long fileSize) {
		DecimalFormat df = new DecimalFormat("#.00");
		String fileSizeString = "";
		if (fileSize == 0) {
			fileSizeString = "0";
		} else if (fileSize < 1024) {
			fileSizeString = df.format((double) fileSize) + "B";
		} else if (fileSize < 1048576) {
			fileSizeString = df.format((double) fileSize / 1024) + "K";
		} else if (fileSize < 1073741824) {
			fileSizeString = df.format((double) fileSize / 1048576) + "M";
		} else {
			fileSizeString = df.format((double) fileSize / 1073741824) + "G";
		}
		return fileSizeString;
	}

	/**
	 * get this folder's children file count
	 * 
	 * @param dir
	 * @return
	 */
	public static long getFileCount(File dir) {
		long size = 0;
		File flist[] = dir.listFiles();
		size = flist.length;
		for (int i = 0; i < flist.length; i++) {
			if (flist[i].isDirectory()) {
				size = size + getFileCount(flist[i]);
				size = size - 1;
			}
		}
		return size;
	}

	/**
	 * delete folder And this folder's all children file.
	 * 
	 * @param fileDir
	 */
	public static void deleteFolder(String fileDir) {
		File parent = new File(fileDir);
		if (parent.exists() && parent.isDirectory()) {
			File[] allFile = new File(fileDir).listFiles();
			for (File file : allFile) {
				if (file.isFile()) {
					file.delete();
				} else {
					deleteFolder(file.getAbsolutePath());
				}
			}
		}
		parent.delete();
	}

	/**
	 * delete Single File
	 * 
	 * @param filePath
	 */
	public static void deleteSingleFile(String filePath) {
		File file = new File(filePath);
		if (file.exists() && file.isFile()) {
			file.delete();
		}
	}

	/**
	 * get File Name
	 * 
	 * @param filePath
	 * @return if filePath is null or "",return ""
	 */
	public static String getFileName(String filePath) {
		if (!TextUtils.isEmpty(filePath)) {
			if (filePath.contains("/")) {
				String[] fileStr = filePath.split("/");
				return fileStr[fileStr.length - 1];
			}
		}
		return "";
	}

	/**
	 * is HTTP File
	 * 
	 * @param filePath
	 * @return
	 */
	public static boolean isHttpFile(String filePath) {
		if (!TextUtils.isEmpty(filePath)) {
			filePath = filePath.toLowerCase(Locale.getDefault());
			if (filePath.contains("http://")) {
				return true;
			}
		}
		return false;
	}

	/**
	 * rename a new file and delete old file
	 * 
	 * @param oldPath
	 * @param newPath
	 * @throws IOException
	 */
	public static void renameFile(String oldPath, String newPath) {
		if (!oldPath.equals(newPath)) {
			try {
				File oldFile = new File(oldPath);
				if (oldFile.exists()) {
					File newFile = new File(newPath);
					newFile.createNewFile();
					oldFile.renameTo(newFile);
					oldFile.delete();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
