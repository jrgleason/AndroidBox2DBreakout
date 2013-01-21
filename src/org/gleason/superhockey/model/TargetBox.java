package org.gleason.superhockey.model;

import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;

public class TargetBox extends BoxActor {
	public static final float HEIGHT = 50;
	public static final float WIDTH = 50;
	private World world;
	private boolean dead;
	
	public TargetBox(World world){
		super();
		this.world = world;
		setHeight(HEIGHT);
		setWidth(WIDTH);
		resize();
	}
	public static GameActor create(World world, float x,float y){
		BodyDef bodyDef = new BodyDef();
		bodyDef.type = BodyType.StaticBody;
		bodyDef.position.set(x, y);
		bodyDef.angle = 0;
		GameActor returnVal = new TargetBox(world);
		returnVal.setBody(world.createBody(bodyDef));
		returnVal.createFixture();
		return returnVal;
	}
	public boolean isDead(){
		return dead;
	}
	public void setDead(boolean dead){
		this.dead = dead;
	}
	public void killed(){
		if(getBody() != null){
			try{
				world.destroyBody(getBody());
			}
			catch(Exception e){
				String test = "sdasdsa";
			}
			
		}
	}
	@Override
	public long getScoreValue() {
		// TODO Auto-generated method stub
		return 100;
	}
}
