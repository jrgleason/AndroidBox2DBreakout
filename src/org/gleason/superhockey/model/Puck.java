package org.gleason.superhockey.model;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;

public class Puck extends GameActor {
	private float radius;
	
	public Puck(){
		super();
		setRadius(10.0f);
		resize();
	}
	
	@Override
	public Fixture createFixture(){
		FixtureDef fd = new FixtureDef();
		fd.shape = getShape();
		fd.density = 20f;
		fd.restitution = 1.0f;
		fd.friction = 0f;
		return getBody().createFixture(fd);
	}

	@Override
	public void resize() {
		// TODO Auto-generated method stub
		CircleShape shape = new CircleShape();
		shape.setRadius(radius);
		setShape(shape);
	}
	
	public static Puck create(World world, float x,float y){
		BodyDef bodyDef = new BodyDef();
		bodyDef.type = BodyType.DynamicBody;
		bodyDef.position.set(x, y);
		bodyDef.angle = 0;
		Puck returnVal = new Puck();
		returnVal.setBody(world.createBody(bodyDef));
		returnVal.createFixture();
		return returnVal;
	}
	
	public void startBody(){
		if(getBody() != null){
			getBody().setLinearVelocity(new Vector2(-1000000.0f, -1000000.0f));
		}
	}
	
	public float getRadius() {
		return radius;
	}
	public void setRadius(float radius) {
		this.radius = radius;
	}

	@Override
	public boolean isTouched(float x, float y) {
		Vector2 position = getBody().getPosition();
		// per http://cboard.cprogramming.com/game-programming/7470-point-lies-circle.html
		float distance = (float) Math.sqrt(
				(x - position.x) * (x - position.x) + 
						(y - position.y) * (y - position.y));
		boolean returnVal = false;
		if(!(distance > getRadius())){
			returnVal = true;
		}
		return returnVal;
	}
}
