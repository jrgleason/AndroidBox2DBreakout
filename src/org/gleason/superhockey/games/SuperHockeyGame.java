package org.gleason.superhockey.games;


import org.gleason.superhockey.MainActivity;
import org.gleason.superhockey.model.levels.Level;
import org.gleason.superhockey.model.levels.Level1;
import org.gleason.superhockey.screens.HockeyScreen;

import android.app.Activity;
import android.view.MotionEvent;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class SuperHockeyGame extends Game {

	private MainActivity gameActivity;
	private SpriteBatch batch;
	private Level level;
	
	public SuperHockeyGame(MainActivity thisAct, int level){
		super();
		gameActivity = thisAct;
		switch(level){
		case(1):
			this.level = new Level1("1.level");
			break;
		case(2):
			this.level = new Level1("2.level");
		}
	}
	
	@Override
	public void create() {
		// TODO Auto-generated method stub
		resetScreen();
	}
	
	public void resetScreen(){
		batch = new SpriteBatch();
		setScreen(new HockeyScreen(this, batch,level));
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
	
	public void showMenu(){
		//TODO: Finish wiring menu
//		((HockeyScreen)getScreen()).showMenu();
	}
}
