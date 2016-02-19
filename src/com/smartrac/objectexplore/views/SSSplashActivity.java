package com.smartrac.objectexplore.views;

import com.smartrac.objectexplore.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class SSSplashActivity extends Activity {
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);

		Thread timer = new Thread() {
			public void run() {
				try {
					sleep(3*1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				} finally {
					Intent intent = new Intent(getApplicationContext(),
							SSLoginActivity.class);
					startActivity(intent);
					finish();
				}
			}
		};

		timer.start();
	}
}