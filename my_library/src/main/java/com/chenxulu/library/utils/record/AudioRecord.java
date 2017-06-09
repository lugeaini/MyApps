package com.chenxulu.library.utils.record;

import android.media.MediaRecorder;
import android.os.Handler;

import com.chenxulu.library.utils.FileManager;

public class AudioRecord {
	private static final int DEFAULT_MAX = 60 * 3;
	private static final int DEFAULT_MIN = 0;

	private MediaRecorder mMediaRecorder;

	private String filePath;
	private AudioRecordListener listener;
	private Handler handler;
	private boolean state;

	private int maxTime = DEFAULT_MAX;
	private int minTime = DEFAULT_MIN;
	private int time;

	public AudioRecord(String filePath, int maxTime, int minTime) {
		this.filePath = filePath;
		this.maxTime = maxTime;
		this.minTime = minTime;
	}

	public void setListener(AudioRecordListener audioRecordListener) {
		this.listener = audioRecordListener;
	}

	public void start() {
		try {
			mMediaRecorder = new MediaRecorder();
			mMediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
			mMediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.AMR_NB);
			mMediaRecorder.setAudioSamplingRate(8000);
			mMediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
			mMediaRecorder.setOutputFile(filePath);
			mMediaRecorder.prepare();
			mMediaRecorder.start();

			state = true;
			stateTime();
		} catch (Exception e) {
			e.printStackTrace();
			state = false;
			release();
			FileManager.deleteSingleFile(filePath);
			listener.onFailure();
		}
	}

	/**
	 * timer start
	 */
	private void stateTime() {
		handler = new Handler();
		handler.post(timeRunnable);
		handler.post(micRunnable);
	}

	private Runnable micRunnable = new Runnable() {

		@Override
		public void run() {
			if (state) {
				handler.postDelayed(micRunnable, 300);
				int vuSize = 6 * mMediaRecorder.getMaxAmplitude() / 32768;
				if (listener != null) {
					listener.micSize(vuSize + 1);
				}
			}
		}
	};

	private Runnable timeRunnable = new Runnable() {

		@Override
		public void run() {
			if (state) {
				handler.postDelayed(timeRunnable, 1000);
				time++;
				if (listener != null) {
					listener.onProgress(time);
				}
				if (time > maxTime) {
					finish();
				}
			}
		}
	};

	public void finish() {
		if (state) {
			state = false;
			release();
			if (time < minTime) {
				FileManager.deleteSingleFile(filePath);
				listener.onFailure();
			} else {
				listener.onSuccess(filePath, time);
			}
		} else {
			release();
		}
	}

	private void release() {
		if (mMediaRecorder != null) {
			mMediaRecorder.reset();
			mMediaRecorder.release();
			mMediaRecorder = null;
		}
	}

	public void cancel() {
		state = false;
		release();
		FileManager.deleteSingleFile(filePath);
	}
}
