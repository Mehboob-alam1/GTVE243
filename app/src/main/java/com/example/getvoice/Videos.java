package com.example.getvoice;

import android.net.Uri;


import android.os.Bundle;
import android.app.Activity;

import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup.LayoutParams;

import android.widget.MediaController;
import android.widget.VideoView;

public class Videos extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_videos);
		
		
		VideoView videoView =(VideoView)findViewById(R.id.videoView1);   
    Uri uri=Uri.parse("android.resource://"+getPackageName() + "/" + R.raw.video);         
        
   
    
      //Setting MediaController and URI, then starting the videoView  
   videoView.setMediaController(new MediaController(this));  
   videoView.setVideoURI(uri);          
    
   videoView.start();
		
   videoView.requestFocus(); 	
		
	}
	
	
	
	
		
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.videos, menu);
		return true;
	}

}
