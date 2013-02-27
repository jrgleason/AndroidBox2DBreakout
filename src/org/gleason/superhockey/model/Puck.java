package org.gleason.superhockey.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
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
	private static final float ACCELERATOR_TIME = 1;
	private float currentTime = 0;

	public Puck() {
		super();
		setRadius(convertPixelsToMeters(10.0f));
		resize();
	}

	public void accelerate() {
		if (currentTime < ACCELERATOR_TIME) {
			startBody();
			currentTime++;
		}
	}

	@Override
	public Fixture createFixture() {
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

	public static Puck createNew(World world, float x, float y) {
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

	public static Puck create(World world, float x, float y, boolean inMeters) {
		if (inMeters) {
			return createNew(world, x, y);
		} else {
			return createNew(world, convertPixelsToMeters(x),
					convertPixelsToMeters(y));
		}
	}

	public void startBody() {
		applyForce();
	}

	private void applyForce(float x, float y) {
		if (getBody() != null) {
			getBody().applyForceToCenter(x, y);
		}
	}

	private static final String BALL_SPEED_PREF_KEY = "ball_speed_interval";

	private void applyForce() {
		Preferences prefs = Gdx.app.getPreferences("breakout-preferences");
		String ballSpeed = prefs.getString(BALL_SPEED_PREF_KEY);
		if (ballSpeed != null) {
			// Implement later
			if (ballSpeed.equalsIgnoreCase("medium")) {
				applyForce(-10.0f, -10.0f);
			} else if (ballSpeed.equalsIgnoreCase("Light Speed")) {
				applyForce(-30.0f, -3.0f);
			} else if (ballSpeed.equalsIgnoreCase("Slow")) {
				applyForce(-5.0f, -5.0f);
			} else if (ballSpeed.equalsIgnoreCase("Fast")) {
				applyForce(-15.0f, -15.0f);
			} else {
				applyForce(-10.0f, -10.0f);
				prefs.putString(BALL_SPEED_PREF_KEY, "Medium");
			}
		} else {
			applyForce(-10.0f, -10.0f);
			prefs.putString(BALL_SPEED_PREF_KEY, "Medium");
		}
		prefs.flush();
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
		// per
		// http://cboard.cprogramming.com/game-programming/7470-point-lies-circle.html
		float distance = (float) Math.sqrt((x - position.x) * (x - position.x)
				+ (y - position.y) * (y - position.y));
		boolean returnVal = false;
		if (!(distance > getRadius())) {
			returnVal = true;
		}
		return returnVal;
	}

	@Override
	public long getScoreValue() {
		// TODO Auto-generated method stub
		return 0;
	}

	public float getSpriteXLocation() {
		// Adding correction factor
		return convertMetersToPixels(getBody().getPosition().x - (radius));
	}

	public float getSpriteYLocation() {
		return convertMetersToPixels(getBody().getPosition().y - (radius));
	}

	public void drawSprite(SpriteBatch batch) {
		getSprite().setPosition(getSpriteXLocation(), getSpriteYLocation());
		getSprite().draw(batch);
	}

	private static void addSprite(Puck puck) {
		Texture mTexture = new Texture(Gdx.files.internal("ball.png"));
		Sprite mSprite = new Sprite(mTexture);
		mSprite.setPosition(puck.getSpriteXLocation(),
				puck.getSpriteYLocation());
		puck.setSprite(mSprite);
	}
}
