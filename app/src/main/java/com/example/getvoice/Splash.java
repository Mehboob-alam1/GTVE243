package com.example.getvoice;

import android.os.Bundle;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.view.Menu;
import android.widget.TextView;

public class Splash extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);
		Typeface Mytypeface=Typeface.createFromAsset(getAssets(), "Berlin-Sans-Fb.ttf");
		TextView tv1=(TextView)findViewById(R.id.textView1);
		tv1.setTypeface(Mytypeface);
		Thread timer=new Thread(){
			public void run(){
				try{
				sleep(5000);
				}catch(InterruptedException e){
					e.printStackTrace();
				}finally
			{	
				Intent openMainActivity=new Intent("com.example.getvoice.MainActivity");
				startActivity(openMainActivity);
				
			}
			}
		 };
		
timer.start();
	}
	
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		finish();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.splash, menu);
		return true;
	}

}
