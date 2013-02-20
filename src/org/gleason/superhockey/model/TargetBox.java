package org.gleason.superhockey.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;

public class TargetBox extends BoxActor {
//	public static final float HEIGHT = 25;
//	public static final float WIDTH = 25;
	public static final float HEIGHT = .25f;
	public static final float WIDTH = .25f;
	private World world;
	private int hardness;
	private int hits;
	
	
	public TargetBox(World world){
		super();
		this.world = world;
		setHeight(HEIGHT, true);
		setWidth(WIDTH, true);
		setHardness(1);
		resize();
	}
	public static GameActor createNew(World world, float x,float y){
		BodyDef bodyDef = new BodyDef();
		bodyDef.type = BodyType.StaticBody;
		bodyDef.position.set(x, y);
		bodyDef.angle = 0;
		TargetBox returnVal = new TargetBox(world);
		returnVal.setBody(world.createBody(bodyDef));
		returnVal.createFixture();
		addSprite(returnVal);
		return returnVal;
	}
	public static GameActor createNew(World world, float x,float y, int hardness){
		BodyDef bodyDef = new BodyDef();
		bodyDef.type = BodyType.StaticBody;
		bodyDef.position.set(x, y);
		bodyDef.angle = 0;
		TargetBox returnVal = new TargetBox(world);
		returnVal.setBody(world.createBody(bodyDef));
		returnVal.createFixture();
		returnVal.setHardness(hardness);
		addSprite(returnVal);
		return returnVal;
	}
	public static GameActor create(World world, float x,float y, boolean isMeters){
		if(isMeters){
			return createNew(world, x, y);
		}
		else{
			return createNew(world, convertPixelsToMeters(x), convertPixelsToMeters(y));
		}
	}
	public static GameActor create(World world, float x,float y, boolean isMeters, int hardness){
		if(isMeters){
			return createNew(world, x, y, hardness);
		}
		else{
			return createNew(world, convertPixelsToMeters(x), convertPixelsToMeters(y), hardness);
		}
	}
	public void hit(){
		hits++;
	}
	@Override
	public boolean isDead() {
		// TODO Auto-generated method stub
		if(hits>=hardness){
			return true;
		}
		return false;
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
	@Override
	public FileHandle getImage() {
		// TODO Auto-generated method stub
		return Gdx.files.internal("WolverineBlock.png");
	}
	/**
	 * @return the hardness
	 */
	public int getHardness() {
		return hardness;
	}
	/**
	 * @param hardness the hardness to set
	 */
	public void setHardness(int hardness) {
		this.hardness = hardness;
	}
	
	
}
