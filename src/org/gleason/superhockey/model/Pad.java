package org.gleason.superhockey.model;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.World;

public class Pad extends BoxActor {
	public Pad(){
		super();
		setHeight(40);
		setWidth(10);
		resize();
	}
	public static Pad create(World world, float x,float y){
		BodyDef bodyDef = new BodyDef();
		bodyDef.type = BodyType.KinematicBody;
		bodyDef.position.set(x, y);
		bodyDef.angle = 0;
		Pad returnVal = new Pad();
		returnVal.setBody(world.createBody(bodyDef));
		returnVal.createFixture();
		return returnVal;
	}
	
}
