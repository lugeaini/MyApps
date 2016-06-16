package com.chenxulu.library.utils.download;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import android.content.Context;
import android.os.Handler;
import android.os.Message;

class DownLoadFlie {
	private static final int STOP = 1;

	private static final int SUCCESS = 2;
	private static final int PROGRESS = 3;
	private static final int FAIL = 4;

	private DownLoadListener listener;
	private String urlPath;
	private String filePath;
	private Handler handler;
	private boolean progressState;
	private int downType;
	private int progress;

	/**
	 * listener progress
	 * 
	 * @param httpUrl
	 * @param localFilePath
	 */
	DownLoadFlie(Context context, String httpUrl, String localFilePath) {
		this(context, httpUrl, localFilePath, true);
	}

	/**
	 * 
	 * @param httpUrl
	 * @param localFilePath
	 * @param progressState
	 *            if listener progress
	 */
	DownLoadFlie(Context context, String httpUrl, String localFilePath,
			boolean progressState) {
		this.urlPath = httpUrl;
		this.filePath = localFilePath;
		this.progressState = progressState;

		this.handler = new Handler(context.getMainLooper()) {
			@Override
			public void handleMessage(Message msg) {
				switch (msg.what) {
				case FAIL:
					listener.onFailure(urlPath, filePath);
					break;
				case SUCCESS:
					listener.onSuccess(urlPath, filePath);
					break;
				case PROGRESS:
					listener.onProgress(urlPath, filePath, progress);
					break;
				default:
					break;
				}

			}
		};
	}

	void start() {
		new Thread(new Runnable() {

			@Override
			public void run() {
				HttpURLConnection httpConnection = null;
				InputStream inputStream = null;
				FileOutputStream outputStream = null;
				try {
					File file = new File(filePath);
					file.createNewFile();
					outputStream = new FileOutputStream(file);

					URL url = new URL(urlPath);
					httpConnection = (HttpURLConnection) url.openConnection();
					httpConnection.connect();
					inputStream = httpConnection.getInputStream();
					byte[] buffer = new byte[1024];
					int size = httpConnection.getContentLength();
					int count = 0;
					int len = -1;
					while ((len = inputStream.read(buffer)) != -1) {
						if (downType == STOP) {
							file.delete();
							break;
						}
						outputStream.write(buffer, 0, len);
						if (progressState) {
							count += len;
							progress = count * 100 / size;
							handler.sendEmptyMessage(PROGRESS);
						}
					}
					if (downType != STOP) {
						handler.sendEmptyMessage(SUCCESS);
					}
				} catch (Exception e) {
					e.printStackTrace();
					handler.sendEmptyMessage(FAIL);
				} finally {
					if (outputStream != null) {
						try {
							outputStream.flush();
							outputStream.close();
							outputStream = null;
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
					if (inputStream != null) {
						try {
							inputStream.close();
							inputStream = null;
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
					if (httpConnection != null) {
						httpConnection.disconnect();
						httpConnection = null;
					}

				}
			}
		}).start();
	}

	int getProgress() {
		return progress;
	}

	void stop() {
		downType = STOP;
	}

	void addListener(DownLoadListener downLoadCallBack) {
		this.listener = downLoadCallBack;
	}

}