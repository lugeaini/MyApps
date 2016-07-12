package com.chenxulu.video.widget;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.Handler;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.VideoView;

import com.chenxulu.video.R;

/**
 * Created by xulu on 16/5/6.
 */
public class MyVideoLayout extends FrameLayout implements View.OnClickListener, MediaPlayer.OnPreparedListener,
        MediaPlayer.OnErrorListener, MediaPlayer.OnCompletionListener, CustomMediaController.HideCallBack {
    private static final int VIDEO_STATE_IDLE = 0;
    private static final int VIDEO_STATE_PREPARE = 1;
    private static final int VIDEO_STATE_PLAYING = 2;
    private static final int VIDEO_STATE_PAUSED = 3;

    public static final int SCREEN_DEFAULT = 0;
    public static final int SCREEN_SMALL = 1;
    public static final int SCREEN_FULL = 2;

    private View layout;
    private ImageView closeView;
    private ImageView playView;
    private VideoView videoView;

    private View bottomLayout;
    private SeekBar seekBar;
    private TextView timeTxt;
    private ImageView fullScreenView;

    private View prepareLayout;

    private String videoPath;
    private int videoState;
    private MyVideoLayoutListener mListener;

    private int screenType;

    public MyVideoLayout(Context context) {
        super(context);
        initView();
    }

    public MyVideoLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public MyVideoLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initView();
    }

    private void initView() {
        layout = LayoutInflater.from(getContext()).inflate(R.layout.layout_video, null);

        addView(layout);
        closeView = (ImageView) layout.findViewById(R.id.close_view);
        closeView.setOnClickListener(this);
        playView = (ImageView) layout.findViewById(R.id.play_view);
        playView.setOnClickListener(this);

        bottomLayout = layout.findViewById(R.id.bottom_layout);
        seekBar = (SeekBar) layout.findViewById(R.id.progress_view);
        seekBar.setOnSeekBarChangeListener(seekBarChangeListener);
        timeTxt = (TextView) layout.findViewById(R.id.time);
        fullScreenView = (ImageView) layout.findViewById(R.id.full_screen_view);
        fullScreenView.setOnClickListener(this);

        videoView = (VideoView) layout.findViewById(R.id.video_view);
        videoView.setOnErrorListener(this);
        videoView.setOnCompletionListener(this);
        videoView.setOnPreparedListener(this);

        CustomMediaController mediaController = new CustomMediaController(getContext());
        mediaController.setHideCallBack(this);
        mediaController.setVisibility(GONE);
        videoView.setMediaController(mediaController);

        prepareLayout = findViewById(R.id.prepare_layout);

        closeView.setVisibility(INVISIBLE);
        playView.setVisibility(INVISIBLE);
        bottomLayout.setVisibility(INVISIBLE);

        videoState = VIDEO_STATE_IDLE;
    }

    /**
     * set video path
     *
     * @param videoPath
     */
    public void setVideoPath(String videoPath) {
        this.videoPath = videoPath;
    }

    /**
     * set screen style
     *
     * @param screenType
     */
    public void setScreenType(int screenType) {
        if (this.screenType != screenType) {
            this.screenType = screenType;
            if (screenType == SCREEN_DEFAULT) {
                seekBar.setVisibility(VISIBLE);
                timeTxt.setVisibility(VISIBLE);
                fullScreenView.setImageResource(R.drawable.btn_video_to_screen_selector);
            } else if (screenType == SCREEN_SMALL) {
                seekBar.setVisibility(INVISIBLE);
                timeTxt.setVisibility(INVISIBLE);
                fullScreenView.setImageResource(R.drawable.btn_video_to_screen_selector);
            } else {
                seekBar.setVisibility(VISIBLE);
                timeTxt.setVisibility(VISIBLE);
                fullScreenView.setImageResource(R.drawable.btn_video_to_window_selector);
            }
        }
    }

    /**
     * set listener
     */
    public void setMyVideoLayoutListener(MyVideoLayoutListener listener) {
        this.mListener = listener;
    }

    /**
     * video start play
     */
    public void startPlay() {
        if (TextUtils.isEmpty(videoPath)) {
            return;
        }
        if (videoState == VIDEO_STATE_IDLE) {
            videoView.setVideoPath(videoPath);
            videoView.requestFocus();
            videoView.start();
            videoView.resume();
            videoState = VIDEO_STATE_PREPARE;

            prepareLayout.setVisibility(VISIBLE);
            playView.setImageResource(R.drawable.btn_video_pause_selector);
        }
    }

    /**
     * video pause
     */
    public void pause() {
        if (videoState == VIDEO_STATE_PLAYING) {
            videoView.pause();
            videoState = VIDEO_STATE_PAUSED;
            playView.setImageResource(R.drawable.btn_video_start_selector);
        }
    }

    /**
     * video stop
     */
    public void stop() {
        videoState = VIDEO_STATE_IDLE;
        videoView.stopPlayback();
        seekBar.setProgress(0);
        timeTxt.setText(intToStr(0) + "/" + intToStr(videoView.getDuration()));
        closeView.setVisibility(INVISIBLE);
        playView.setVisibility(INVISIBLE);
        bottomLayout.setVisibility(INVISIBLE);
    }

    @Override
    public void onPrepared(MediaPlayer mp) {
        System.out.println("onPrepared()");
        videoState = VIDEO_STATE_PLAYING;
        prepareLayout.setVisibility(GONE);
        startTime();
    }

    @Override
    public void onCompletion(MediaPlayer mp) {
        System.out.println("onCompletion()");
        stop();
        if (mListener != null)
            mListener.playOnCompletion();
    }

    @Override
    public boolean onError(MediaPlayer mp, int what, int extra) {
        System.out.println("onError()" + "-" + what + "-" + extra);
        stop();
        if (mListener != null)
            mListener.playOnError();
        return true;
    }

    /**
     * play or pause on click
     */
    private void playOnClick() {
        if (videoState == VIDEO_STATE_PAUSED) {
            videoView.start();
            videoState = VIDEO_STATE_PLAYING;
            playView.setImageResource(R.drawable.btn_video_pause_selector);
        } else if (videoState == VIDEO_STATE_PLAYING) {
            videoView.pause();
            videoState = VIDEO_STATE_PAUSED;
            playView.setImageResource(R.drawable.btn_video_start_selector);
        }
    }

    /**
     * close on click
     */
    private void closeOnClick() {
        if (mListener != null)
            mListener.closeOnClick();
    }

    /**
     * full screen on click
     */
    private void fullScreenClick() {
        if (mListener != null)
            mListener.fullScreenChange();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.close_view:
                closeOnClick();
                break;
            case R.id.full_screen_view:
                fullScreenClick();
                break;
            case R.id.play_view:
                playOnClick();
                break;
            default:
                break;
        }
    }

    @Override
    public void hide() {
        closeView.setVisibility(INVISIBLE);
        playView.setVisibility(INVISIBLE);
        bottomLayout.setVisibility(INVISIBLE);
    }

    @Override
    public void show() {
        closeView.setVisibility(VISIBLE);
        playView.setVisibility(VISIBLE);
        bottomLayout.setVisibility(VISIBLE);
    }

    private SeekBar.OnSeekBarChangeListener seekBarChangeListener = new SeekBar.OnSeekBarChangeListener() {
        int seekIndex = 0;

        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            seekIndex = progress;
            timeTxt.setText(intToStr(videoView.getDuration() * progress / 100) + "/" + intToStr(videoView.getDuration()));
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {
            if (seekIndex != 0) {
                videoView.seekTo(videoView.getDuration() * seekIndex / 100);
            }
        }
    };

    /**
     * start record time
     */
    private void startTime() {
        handler.post(timeRunnable);
    }

    private Handler handler = new Handler();
    private Runnable timeRunnable = new Runnable() {
        @Override
        public void run() {
            if (videoState != VIDEO_STATE_IDLE) {
                int duration = videoView.getDuration();
                int progress = videoView.getCurrentPosition();
                if (duration != -1 && duration != 0 && screenType != SCREEN_SMALL) {
                    seekBar.setProgress(progress * 100 / duration);
                    timeTxt.setText(intToStr(progress) + "/" + intToStr(duration));
                }
                handler.postDelayed(timeRunnable, 1000);
            }
        }
    };

    /**
     * millisecond format to "00:00"
     *
     * @param duration
     * @return
     */
    private String intToStr(int duration) {
        StringBuilder mBuilder = new StringBuilder();
        duration = duration / 1000;
        if (duration / 3600 > 0) {
            int hour = duration % 60 % 60;
            int minute = duration % 60;
            int second = (duration - minute * 60) % 60;
            mBuilder = hour >= 10 ? mBuilder.append(hour).append(":")
                    : mBuilder.append("0").append(hour).append(":");
            mBuilder = minute >= 10 ? mBuilder.append(minute).append(":")
                    : mBuilder.append("0").append(minute).append(":");
            mBuilder = second >= 10 ? mBuilder.append(second) : mBuilder
                    .append("0").append(second);
        } else {
            int minute = duration / 60;
            int second = (duration - minute * 60) % 60;
            mBuilder = minute >= 10 ? mBuilder.append(minute).append(":")
                    : mBuilder.append("0").append(minute).append(":");
            mBuilder = second >= 10 ? mBuilder.append(second) : mBuilder
                    .append("0").append(second);
        }
        return mBuilder.toString();
    }

}
