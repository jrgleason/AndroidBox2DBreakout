package org.gleason.superhockey.games;

import org.gleason.superhockey.MainActivity;
import org.gleason.superhockey.screens.HockeyScreen;

import android.app.Activity;
import android.view.MotionEvent;

import com.badlogic.gdx.Game;

public class SuperHockeyGame extends Game {

	private MainActivity gameActivity;
	
	public SuperHockeyGame(MainActivity thisAct){
		super();
		gameActivity = thisAct;
	}
	
	@Override
	public void create() {
		// TODO Auto-generated method stub
		setScreen(new HockeyScreen(this));
	}
	
	public void onTouch(MotionEvent ev){
		if(getScreen() instanceof HockeyScreen){
			((HockeyScreen)getScreen()).onTouch(ev);
		}
	}
	
	@Override
	public void dispose() {
		// TODO Auto-generated method stub
//		gameActivity.callOnBack();
		super.dispose();
	}
}
