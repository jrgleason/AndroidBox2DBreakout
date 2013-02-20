package org.gleason.superhockey;


import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MotionEvent;
import android.widget.TextView;

public class SplashActivity extends Activity {
	private Intent intent;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Typeface myTypeface = Typeface.createFromAsset(getAssets(), "modern_lcd-7.ttf");
	    TextView myTextView = (TextView)findViewById(R.id.tv);
	    myTextView.setTypeface(myTypeface);
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		return true;
	}
	
	@Override
	public boolean dispatchTouchEvent(MotionEvent ev) {
		// TODO Auto-generated method stub
		if(intent == null){
			intent = new Intent(this, MainActivity.class);
			startActivity(intent);
		}
		else{
			intent = null;
		}
		return super.dispatchTouchEvent(ev);
	}
}
