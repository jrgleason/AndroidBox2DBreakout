package org.gleason.superhockey.model;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.EdgeShape;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.World;

public class ArenaBarrier extends GameActor {

	private float halfX, halfY;
	
	public ArenaBarrier(float halfX, float halfY){
		super();
		setHalfX(halfX);
		setHalfY(halfY);
		resize();
	}
	
	public static GameActor create(World world, 
			float x, float y, 
			float halfX, float halfY){
		BodyDef bodyDef = new BodyDef();
		bodyDef.type = BodyType.StaticBody;
		bodyDef.position.set(x, y);
		bodyDef.angle = 0;
		ArenaBarrier floor = new ArenaBarrier(halfX,halfY);
		floor.setBody(world.createBody(bodyDef));
		floor.createFixture();
		return floor;
	}
	
	@Override
	public void resize() {
		// TODO Auto-generated method stub
		setShape(new EdgeShape());
		((EdgeShape)getShape()).set(
				new Vector2(-getHalfX(), -getHalfY()), 
				new Vector2(getHalfX(), getHalfY()));
	}

	/**
	 * @return the halfX
	 */
	public float getHalfX() {
		return halfX;
	}

	/**
	 * @param halfX the halfX to set
	 */
	public void setHalfX(float halfX) {
		this.halfX = halfX;
	}

	/**
	 * @return the halfY
	 */
	public float getHalfY() {
		return halfY;
	}

	/**
	 * @param halfY the halfY to set
	 */
	public void setHalfY(float halfY) {
		this.halfY = halfY;
	}

	@Override
	public boolean isTouched(float x, float y) {
		// TODO Handle this better later for now we don't care if we touch a wall
		return false;
	}

	@Override
	public long getScoreValue() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void drawSprite(SpriteBatch batch) {
		// TODO Auto-generated method stub
		
	}

}
