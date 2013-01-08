package org.gleason.superhockey.games;

import org.gleason.superhockey.screens.HockeyScreen;

import android.view.MotionEvent;

import com.badlogic.gdx.Game;

public class SuperHockeyGame extends Game {

	@Override
	public void create() {
		// TODO Auto-generated method stub
		setScreen(new HockeyScreen());
	}
	
	public void onTouch(MotionEvent ev){
		if(getScreen() instanceof HockeyScreen){
			((HockeyScreen)getScreen()).onTouch(ev);
		}
	}
}
