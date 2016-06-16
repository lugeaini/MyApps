package com.chenxulu.library.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

public class BaseCrashActivity extends Activity {
	private FrameLayout frameLayout;
	private Button send;
	private String crashString;
	private TextView error;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		Intent intent = getIntent();
		if (intent != null) {
			crashString = intent.getStringExtra("crash_info");
		}

		frameLayout = new FrameLayout(this);
		LayoutParams layoutParams = new LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.MATCH_PARENT);
		frameLayout.setLayoutParams(layoutParams);
		setContentView(frameLayout);

		initView();
		if (!TextUtils.isEmpty(crashString)) {
			error.setText(crashString);
		}
	}

	private void initView() {
		error = new TextView(getApplicationContext());
		send = new Button(getApplicationContext());
		send.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(Intent.ACTION_SEND);
				intent.setType("message/rfc822");
				intent.putExtra(Intent.EXTRA_EMAIL,
						new String[] { "455066552@qq.com" });
				intent.putExtra(Intent.EXTRA_TEXT, crashString);
				intent.putExtra(Intent.EXTRA_SUBJECT, "Crash Log");
				try {
					startActivity(Intent.createChooser(intent, ""));
				} catch (android.content.ActivityNotFoundException ex) {
					Toast.makeText(BaseCrashActivity.this,
							"There are no email clients installed.",
							Toast.LENGTH_SHORT).show();
				}
			}
		});
	}

	@Override
	public void onBackPressed() {
		finish();
		android.os.Process.killProcess(android.os.Process.myPid());
	}

}
