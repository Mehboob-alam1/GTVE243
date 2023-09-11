package com.example.getvoice;

import android.os.Bundle;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;

import android.widget.Button;
import android.widget.ImageButton;
import android.widget.PopupWindow;
import android.widget.TextView;

public class MainActivity extends Activity {
	AlertDialog dialog;
	AlertDialog dialog1;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ImageButton user_manual=(ImageButton) findViewById(R.id.user_manual_btn);
		ImageButton start_conversation=(ImageButton) findViewById(R.id.start_conversation_btn);
		ImageButton videos=(ImageButton) findViewById(R.id.videos_btn);
		ImageButton about_app=(ImageButton) findViewById(R.id.about_app_btn);
		Typeface Mytypeface=Typeface.createFromAsset(getAssets(), "Berlin-Sans-Fb.ttf");
		TextView tv1=(TextView)findViewById(R.id.textView1);
		tv1.setTypeface(Mytypeface);
		
		
start_conversation.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				AlertDialog.Builder mBuilder= new AlertDialog.Builder(MainActivity.this);
				View mView=getLayoutInflater().inflate(R.layout.activity_dialogstart_convo1,null);
				ImageButton ok=(ImageButton) mView.findViewById(R.id.ok_btn);
				
				ok.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View arg0) {
						// TODO Auto-generated method stub
						AlertDialog.Builder mBuilder= new AlertDialog.Builder(MainActivity.this);
						View mView=getLayoutInflater().inflate(R.layout.activity_dialog_start_convo,null);
						ImageButton Yes=(ImageButton) mView.findViewById(R.id.yes_btn);
						ImageButton No=(ImageButton) mView.findViewById(R.id.no_btn);
						Yes.setOnClickListener(new OnClickListener() {
							@Override
							public void onClick(View arg0) {
								// TODO Auto-generated method stub
								Intent intent= new Intent(com.example.getvoice.MainActivity.this,com.example.getvoice.StartConversation.class);
							   	startActivity(intent);
								
							}
						});
						No.setOnClickListener(new OnClickListener() {
							@Override
							public void onClick(View arg0) {
								// TODO Auto-generated method stub
								Intent intent= new Intent(com.example.getvoice.MainActivity.this,com.example.getvoice.MainActivity.class);
							   	startActivity(intent);
								
							}
						});
						
						mBuilder.setView(mView);
						 dialog=mBuilder.create();
						dialog.show();

						
					}
					
				});
				
				mBuilder.setView(mView);
				dialog1=mBuilder.create();
				dialog1.show();
			}
		});
		
		
		user_manual.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent= new Intent(com.example.getvoice.MainActivity.this,com.example.getvoice.UserManual.class);
			   	startActivity(intent);
				
			}
		});
		
		videos.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent= new Intent(com.example.getvoice.MainActivity.this,com.example.getvoice.Videos.class);
			   	startActivity(intent);
				
			}
		});
about_app.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent= new Intent(com.example.getvoice.MainActivity.this,com.example.getvoice.AboutApp.class);
			   	startActivity(intent);
				
			}
		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	
}
