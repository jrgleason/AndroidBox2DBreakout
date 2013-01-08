package org.gleason.superhockey.model;

import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;

public class TargetBox extends BoxActor {
	public TargetBox(){
		super();
		setHeight(20);
		setWidth(20);
		resize();
	}
	public static GameActor create(World world, float x,float y){
		BodyDef bodyDef = new BodyDef();
		bodyDef.type = BodyType.StaticBody;
		bodyDef.position.set(x, y);
		bodyDef.angle = 0;
		GameActor returnVal = new TargetBox();
		returnVal.setBody(world.createBody(bodyDef));
		returnVal.createFixture();
		return returnVal;
	}
}
