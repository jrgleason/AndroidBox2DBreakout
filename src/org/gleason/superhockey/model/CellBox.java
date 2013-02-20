package org.gleason.superhockey.model;

import org.gleason.superhockey.ca.Cell;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.Shape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;

public class CellBox extends Cell implements Actor {

	private Body body;
	private Shape shape;
	private GameActor actor;
	
	public CellBox(World world, float x,float y){
		super();
		this.configureObject(world,x,y);
	}
	public CellBox(World world, float x,float y, boolean isMeters){
		if(!isMeters){
			x = GameActor.convertPixelsToMeters(x);
			y = GameActor.convertPixelsToMeters(y);
		}
		this.configureObject(world,x,y);
	}
	
	private void configureObject(World world, float x,float y){
		createBody(world, x, y);
		resize();
		createFixture();
	}
	
	public CellBox(World world, Vector2 location, boolean isMeters){
		this(world,location.x,location.y,isMeters);
	}
	
	private void createBody(World world, float x,float y){
		BodyDef bodyDef = new BodyDef();
		bodyDef.type = BodyType.StaticBody;
		bodyDef.position.set(x, y);
		bodyDef.angle = 0;
		body = world.createBody(bodyDef);
	}
	

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
		shape.setAsBox(getWidth(),  getHeight());
		this.shape = shape;
	}

	@Override
	public Fixture createFixture() {
		// TODO Auto-generated method stub
		FixtureDef fd = new FixtureDef();
		fd.shape = shape;
		fd.density = 20f;
		fd.restitution = 1.0f;
		return getBody().createFixture(fd);
	}

	@Override
	public Shape getShape() {
		// TODO Auto-generated method stub
		return shape;
	}

	@Override
	public Body getBody() {
		// TODO Auto-generated method stub
		return body;
	}
	/**
	 * @return the actor
	 */
	public GameActor getActor() {
		return actor;
	}
	/**
	 * @param actor the actor to set
	 */
	public void setActor(GameActor actor) {
		this.actor = actor;
	}

}
