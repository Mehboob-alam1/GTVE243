package com.example.getvoice;

import android.os.Bundle;
import android.app.Activity;
import android.graphics.Typeface;
import android.view.Menu;
import android.widget.TextView;


public class UserManual extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user_manual);
		Typeface Mytypeface=Typeface.createFromAsset(getAssets(), "Berlin-Sans-Fb.ttf");
		TextView tv1=(TextView)findViewById(R.id.textView1);
		tv1.setTypeface(Mytypeface);
		TextView tv2=(TextView)findViewById(R.id.textView2);
		tv2.setTypeface(Mytypeface);
		TextView tv3=(TextView)findViewById(R.id.textView3);
		tv3.setTypeface(Mytypeface);
		TextView tv4=(TextView)findViewById(R.id.textView4);
		tv4.setTypeface(Mytypeface);
		TextView tv5=(TextView)findViewById(R.id.textView5);
		tv5.setTypeface(Mytypeface);
		TextView tv6=(TextView)findViewById(R.id.textView6);
		tv6.setTypeface(Mytypeface);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.user_manual, menu);
		return true;
	}

}
