package org.gleason.superhockey.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.World;

public class Pad extends BoxActor {
	public Pad(){
		super();
		setHeight(10, false);
		setWidth(40, false);
		resize();
	}
	public static Pad createNew(World world, float x,float y){
		BodyDef bodyDef = new BodyDef();
		bodyDef.type = BodyType.KinematicBody;
		bodyDef.position.set(x, y);
		bodyDef.angle = 0;
		Pad returnVal = new Pad();
		returnVal.setBody(world.createBody(bodyDef));
		returnVal.createFixture();
		addSprite(returnVal);
		return returnVal;
	}
	public static Pad create(World world, float x,float y, boolean isMeters){
		if(isMeters){
			return createNew(world, x, y);
		}
		else{
			return createNew(world, convertPixelsToMeters(x), convertPixelsToMeters(y));
		}
	}
	@Override
	public FileHandle getImage() {
		// TODO Auto-generated method stub
		return Gdx.files.internal("Pad.png");
	}
	
	
}
