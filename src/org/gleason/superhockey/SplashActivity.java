package org.gleason.superhockey;


import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

public class SplashActivity extends Activity  {
	private Intent intent;
	private View splashView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Typeface myTypeface = Typeface.createFromAsset(getAssets(), "modern_lcd-7.ttf");
	    TextView myTextView = (TextView)findViewById(R.id.tv);
	    myTextView.setTypeface(myTypeface);
	    splashView = findViewById(R.id.tv);
	    splashView.setOnClickListener(myhandler1);
	    
	}
	View.OnClickListener myhandler1 = new View.OnClickListener() {
		    public void onClick(View v) {
//		    	if(intent == null){
//        			intent = new Intent(getApplicationContext(), MainActivity.class);
		    		intent = new Intent(getApplicationContext(), LevelSelectorActivity.class);
        			startActivity(intent);
//        		}
		    }
		  };
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		MenuInflater inflater = getMenuInflater();
	    inflater.inflate(R.menu.activity_main, menu);
	    
	    return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch (item.getItemId()) {
        case (R.id.menu_settings):
        	Intent intent = new Intent(getApplicationContext(), GamePreferencesActivity.class);
            startActivity(intent);
            break;
        }
        return false;
	}
	
	
	
//	@Override
//	public boolean dispatchTouchEvent(MotionEvent ev) {
//		// TODO Auto-generated method stub
//		if(intent == null && startGame){
//			intent = new Intent(this, MainActivity.class);
//			startActivity(intent);
//		}
//		else{
//			intent = null;
//		}
//		return super.dispatchTouchEvent(ev);
//	}
}
