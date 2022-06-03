package com.example.womensecurity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ProgressBar;

public class SplashActivity extends Activity
{
	ProgressBar pb;
	Thread t;
	Handler h = new Handler();
	Integer i = 1;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.activity_splash);
		pb = (ProgressBar)findViewById(R.id.progressBar1);
		t = new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
			while(i<=100)
			{
				h.post(new Runnable() {
					
					@Override
					public void run() {
						// TODO Auto-generated method stub
					pb.setProgress(i);
					}
				});
				try {
					t.sleep(50);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				i = i+1;
			}
			Intent j = new Intent(SplashActivity.this,RegistrationActivity.class);
			startActivity(j);
			finish();
			}
		});
		t.start();
	}
}