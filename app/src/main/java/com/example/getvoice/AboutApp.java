package com.example.getvoice;

import android.os.Bundle;
import android.app.Activity;
import android.graphics.Typeface;
import android.view.Menu;
import android.widget.TextView;

public class AboutApp extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_about_app);
		Typeface Mytypeface=Typeface.createFromAsset(getAssets(), "Berlin-Sans-Fb.ttf");
		TextView tv1=(TextView)findViewById(R.id.textView1);
		tv1.setTypeface(Mytypeface);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.about_app, menu);
		return true;
	}

}
