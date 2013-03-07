package org.gleason.superhockey.model;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;

public class Prize extends GameActor {

	@Override
	public boolean isTouched(float x, float y) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void drawSprite(SpriteBatch batch) {
		// TODO Auto-generated method stub

	}

	@Override
	public void resize() {
		// TODO Auto-generated method stub
		PolygonShape shape = new PolygonShape();
		shape.setAsBox(.1f,  .1f);
		setShape(shape);
	}

	@Override
	public long getScoreValue() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	//TODO: We need to make the type a Enum
	public static Prize createNew(World world, float x, float y, String type) {
		BodyDef bodyDef = new BodyDef();
		bodyDef.type = BodyType.DynamicBody;
		bodyDef.position.set(x, y);
		bodyDef.angle = 0;
		Prize returnVal = new Prize();
		returnVal.setBody(world.createBody(bodyDef));
		returnVal.createFixture();
//		addSprite(returnVal);
		return returnVal;
	}

}
