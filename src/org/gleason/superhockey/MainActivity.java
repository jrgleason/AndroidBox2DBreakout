package org.gleason.superhockey;

import org.gleason.superhockey.games.SuperHockeyGame;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MotionEvent;

public class MainActivity extends AndroidApplication {

	static {
		System.loadLibrary("gdx");
	}
	
	private SuperHockeyGame game;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		config.useAccelerometer = true;
		config.useCompass = false;
		config.useWakelock = true;
		config.useGL20 = true;
		game = new SuperHockeyGame(this);
		initialize(game, config);
	}

	public void callOnBack(){
		this.onBackPressed();
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}
	@Override
	public boolean dispatchTouchEvent(MotionEvent ev) {
		// TODO Auto-generated method stub
		game.onTouch(ev);
		return super.dispatchTouchEvent(ev);
		
	}
	public boolean onKeyUp(int keyCode, KeyEvent event) {
	    if (keyCode == KeyEvent.KEYCODE_MENU) {
//	        game.showMenu();
	        return super.onKeyUp(keyCode, event);
	    } else {
	        return super.onKeyUp(keyCode, event);
	    }
	}
}
