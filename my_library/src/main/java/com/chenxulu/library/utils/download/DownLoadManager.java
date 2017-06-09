package com.chenxulu.library.utils.download;

import android.content.Context;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class DownLoadManager {
	private static DownLoadManager downLoadManager = null;
	private HashMap<String, DownLoadFlie> downloads;
	private HashMap<String, ArrayList<DownLoadListener>> listeners;

	private DownLoadManager() {
		downloads = new HashMap<String, DownLoadFlie>();
		listeners = new HashMap<String, ArrayList<DownLoadListener>>();
	}

	public static synchronized DownLoadManager getInstance() {
		if (downLoadManager == null) {
			downLoadManager = new DownLoadManager();
		}
		return downLoadManager;
	}

	public void addTask(Context context, String urlPath, String filePath,
			DownLoadListener downLoadCallBack) {
		if (isExsit(urlPath)) {
			downloads.get(urlPath).addListener(downLoadCallBack);
		} else {
			DownLoadFlie downLoad = new DownLoadFlie(context, urlPath, filePath);
			downLoad.addListener(new DownLoadListener() {

				@Override
				public void onProgress(String urlPath, String filePath,
						int progress) {
					super.onProgress(urlPath, filePath, progress);
					if (listeners.containsKey(urlPath)) {
						ArrayList<DownLoadListener> list = listeners
								.get(urlPath);
						for (int i = 0; i < list.size(); i++) {
							list.get(i).onProgress(urlPath, filePath, progress);
						}
					}
				}

				@Override
				public void onSuccess(String urlPath, String filePath) {
					super.onSuccess(urlPath, filePath);
					if (listeners.containsKey(urlPath)) {
						ArrayList<DownLoadListener> list = listeners
								.get(urlPath);
						for (int i = 0; i < list.size(); i++) {
							list.get(i).onSuccess(urlPath, filePath);
						}
					}
					onFinish(urlPath);
				}

				@Override
				public void onFailure(String urlPath, String filePath) {
					super.onFailure(urlPath, filePath);
					if (listeners.containsKey(urlPath)) {
						ArrayList<DownLoadListener> list = listeners
								.get(urlPath);
						for (int i = 0; i < list.size(); i++) {
							list.get(i).onFailure(urlPath, filePath);
						}
					}
					onFinish(urlPath);
				}

				private void onFinish(String urlPath) {
					listeners.remove(urlPath);
					downloads.remove(urlPath);
				}
			});
			downloads.put(urlPath, downLoad);
			addCallBack(urlPath, downLoadCallBack);
			downLoad.start();
		}
	}

	private void addCallBack(String urlPath, DownLoadListener downLoadCallBack) {
		if (listeners.containsKey(urlPath)) {
			listeners.get(urlPath).add(downLoadCallBack);
		} else {
			ArrayList<DownLoadListener> list = new ArrayList<DownLoadListener>();
			list.add(downLoadCallBack);
			listeners.put(urlPath, list);
		}
	}

	public boolean isExsit(String urlPath) {
		return downloads.containsKey(urlPath);
	}

	public void remove(String urlPath) {
		if (downloads.containsKey(urlPath)) {
			DownLoadFlie downLoad = downloads.get(urlPath);
			downLoad.stop();
			downloads.remove(urlPath);
		}
		if (listeners.containsKey(urlPath)) {
			listeners.remove(urlPath);
		}
	}

	public void close() {
		Iterator<DownLoadFlie> downs = downloads.values().iterator();
		while (downs.hasNext()) {
			DownLoadFlie downLoad = downs.next();
			downLoad.stop();
		}
		downloads.clear();
		listeners.clear();
	}

}
