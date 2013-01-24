package org.gleason.superhockey.model;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;

public class Puck extends GameActor {
	private float radius;
	private static final float ACCELERATOR_TIME = 10;
	private float currentTime=0;
	
	public Puck(){
		super();
		setRadius(convertPixelsToMeters(10.0f));
		resize();
	}
	
	public void accelerate(){
		if(currentTime<ACCELERATOR_TIME){
			startBody();
			currentTime++;
		}
	}
	
	@Override
	public Fixture createFixture(){
		FixtureDef fd = new FixtureDef();
		fd.shape = getShape();
		fd.density = 1f;
		fd.restitution = 1.0f;
		fd.friction = 0.0f;
		return getBody().createFixture(fd);
	}

	@Override
	public void resize() {
		// TODO Auto-generated method stub
		CircleShape shape = new CircleShape();
		shape.setRadius(radius);
		setShape(shape);
	}
	
	public static Puck createNew(World world, float x,float y){
		BodyDef bodyDef = new BodyDef();
		bodyDef.type = BodyType.DynamicBody;
		bodyDef.position.set(x, y);
		bodyDef.angle = 0;
		Puck returnVal = new Puck();
		returnVal.setBody(world.createBody(bodyDef));
		returnVal.createFixture();
		addSprite(returnVal);
		return returnVal;
	}
	
	public static Puck create(World world, float x,float y, boolean inMeters){
		if(inMeters){
			return createNew(world, x, y);
		}
		else{
			return createNew(world, convertPixelsToMeters(x), convertPixelsToMeters(y));
		}
	}
	
	public void startBody(){
		if(getBody() != null){
			getBody().applyForceToCenter(-10.0f, -10.0f);
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

	@Override
	public long getScoreValue() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	public float getSpriteXLocation(){
		//Adding correction factor
		return convertMetersToPixels(getBody().getPosition().x - (radius));
	}
	public float getSpriteYLocation(){
		return convertMetersToPixels(getBody().getPosition().y - (radius));
	}
	
	public void drawSprite(SpriteBatch batch){
		getSprite().setPosition(getSpriteXLocation(), getSpriteYLocation());
		getSprite().draw(batch);
	}
	
	private static void addSprite(Puck puck){
		Texture mTexture = new Texture(Gdx.files.internal("BuckeyeBall.png"));
		Sprite mSprite = new Sprite(mTexture);
		mSprite.setPosition(puck.getSpriteXLocation(), puck.getSpriteYLocation());
		puck.setSprite(mSprite);
	}
}
