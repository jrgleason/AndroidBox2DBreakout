package org.gleason.superhockey;

import java.util.Iterator;
import java.util.Map;

import org.gleason.superhockey.games.SuperHockeyGame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceManager;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
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
		loadPreferences();
	}
	
	private void loadPreferences() {
		SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
	    Iterator it = prefs.getAll().entrySet().iterator();
	    Preferences gdxprefs = Gdx.app.getPreferences("breakout-preferences");
		
	    while (it.hasNext()) {
	        Map.Entry pairs = (Map.Entry)it.next();
	        gdxprefs.putString(pairs.getKey().toString(), pairs.getValue().toString());
	        it.remove(); // avoids a ConcurrentModificationException
	    }
	    
	    gdxprefs.flush();
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
	
}
