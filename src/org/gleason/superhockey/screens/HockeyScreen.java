package org.gleason.superhockey.screens;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.gleason.superhockey.games.SuperHockeyGame;
import org.gleason.superhockey.model.GameActor;
import org.gleason.superhockey.model.Neighborhood;
import org.gleason.superhockey.model.Pad;
import org.gleason.superhockey.model.Puck;
import org.gleason.superhockey.model.ScoreBoard;
import org.gleason.superhockey.model.TargetBox;
import org.gleason.superhockey.model.box.IndustructableTargetBox;
import org.gleason.superhockey.model.box.PrizeBox;
import org.gleason.superhockey.model.levels.Level;

import android.view.MotionEvent;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.Input.Peripheral;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.World;

public class HockeyScreen implements Screen, ContactListener {

	private int count = 0;
	private World world;
	private Vector2 gravity = new Vector2(0, 0f);
	private SpriteBatch batch;

	private GameActor leftPad;
	private GameActor puck;
	private List<GameActor> deadActors = new ArrayList<GameActor>();
	private Box2DDebugRenderer debugRenderer;
	private OrthographicCamera camera;
	private boolean accelerometerPresent;

	private static final float TIME_STEP = 1.0f / 45.0f;
	private static final int VELOCITY_ITTERATIONS = 2;
	private static final int POSITION_ITTERATIONS = 8;

	private float accelX;
	private float accelY;
	private float accelZ;

	private ScoreBoard scoreBoard;

	private Level level1;
	private Level currentLevel;
	private boolean paused;

	private Neighborhood daHood;

	private SuperHockeyGame game;

	static {
		System.loadLibrary("gl-jni");
	}

	public HockeyScreen(SuperHockeyGame game, SpriteBatch batch, Level level) {
		paused = false;
		this.game = game;
		world = new World(getGravity(), true);
		// World.setVelocityThreshold(0.0f);
		world.setContactListener(this);
		setBatch(batch);
		scoreBoard = ScoreBoard.create();
		setLeftPad(Pad.create(world, 50.0f, 30.0f, false));
		setPuck(Puck.create(world, Gdx.graphics.getWidth() / 2,
				Gdx.graphics.getHeight() / 2, false));
		float value = GameActor
				.convertPixelsToMeters(Gdx.graphics.getWidth() - 40);
		// daHood = Neighborhood.create(GameActor.convertPixelsToMeters(230),
		// GameActor.convertPixelsToMeters(Gdx.graphics.getWidth()-230),
		// GameActor.convertPixelsToMeters(Gdx.graphics.getHeight() - 200),
		// GameActor.convertPixelsToMeters(Gdx.graphics.getHeight()/2 + 200),
		// world);

		Vector2 location = new Vector2(Gdx.graphics.getWidth() / 2,
				Gdx.graphics.getHeight() - Gdx.graphics.getHeight() / 3);
		// Level3 test = new Level3();
		// Level1 test = new Level1();
//		Level2 test = new Level2();
//		test.genBoxMap(world, location, false);
		// test.genBoxMap(world);
		level1 = level;
		level1.genBoxMap(world, location, false);
		currentLevel = level1;

		setCamera(new OrthographicCamera());
		getCamera().viewportHeight = GameActor
				.convertPixelsToMeters(Gdx.graphics.getHeight());
		getCamera().viewportWidth = GameActor
				.convertPixelsToMeters(Gdx.graphics.getWidth());
		// getCamera().position.set(getCamera().viewportWidth * .5f,
		// getCamera().viewportHeight * .5f, 0f);
		getCamera().position.set(getCamera().viewportWidth * .5f,
				getCamera().viewportHeight * .5f, 0f);
		getCamera().update();
		setDebugRenderer(new Box2DDebugRenderer());
		((Puck) puck).startBody();
		setAccelerometerPresent(Gdx.input
				.isPeripheralAvailable(Peripheral.Accelerometer));
	}

	private void genBoxMap() {

	}

	public void refreshGravity() {
		world.setGravity(getGravity());
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub

	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void render(float arg0) {
		if (!paused) {
			((Puck) puck).accelerate();
			float currentLocation = GameActor.convertMetersToPixels(leftPad
					.getBody().getPosition().x);
			if (stopX != 0) {
				if (goingRight && currentLocation > stopX) {
					leftPadStop();
					stopX = 0;
				} else if (goingLeft && currentLocation < stopX) {
					leftPadStop();
					stopX = 0;
				}
			}
			if (needsStoppedRight() && goingRight) {
				leftPadStop();
			}
			if (needsStoppedLeft() && goingLeft) {
				leftPadStop();
			}
			resetScreen();
			Iterator<GameActor> i = deadActors.iterator();
			while (i.hasNext()) {
				GameActor walkingDead = i.next();
				world.destroyBody(walkingDead.getBody());
				if(walkingDead instanceof PrizeBox){
					((PrizeBox) walkingDead).releasePrize();
				}
				if (currentLevel != null) {
					currentLevel.getTargetBoxes().remove(walkingDead);
				}
				i.remove();
			}
			world.step(TIME_STEP, VELOCITY_ITTERATIONS, POSITION_ITTERATIONS);
			if (puck.getBody().getPosition().x < 0
					|| puck.getBody().getPosition().y < 0) {
				puck.setDead(true);
			}
			debugRenderer.render(world, getCamera().combined);
//			 batch.begin();
//			 currentLevel.getBkgSprite().draw(batch);
//			 for (GameActor box : currentLevel.getTargetBoxes()) {
//			 box.drawSprite(batch);
//			 }
//			 leftPad.drawSprite(batch);
//			 scoreBoard.getFont2().draw(batch, scoreBoard.getScoreText(), 20f,
//			 currentLevel.getTopValue() - 10f);
//			 scoreBoard.getFont().draw(batch, scoreBoard.getScoreString(),
//			 170f,
//			 currentLevel.getTopValue() - 10f);
//			 if (!puck.isDead()) {
//			 puck.drawSprite(batch);
//			 } else {
//			 scoreBoard.getFont().draw(batch,
//			 "YOU DIED! (Hit back to restart)",
//			 Gdx.graphics.getWidth() / 2 - 225,
//			 Gdx.graphics.getHeight() / 2 - 50);
//			 }
//			 batch.end();
		}
	}

	private boolean needsStoppedRight() {
		Pad pad = (Pad) leftPad;
		if (pad.getEndX() > currentLevel.getRightValue()) {
			return true;
		}
		return false;
	}

	private boolean needsStoppedLeft() {
		Pad pad = (Pad) leftPad;
		if (pad.getStartX() < currentLevel.getLeftValue()) {
			return true;
		}
		return false;
	}

	@Override
	public void resize(int arg0, int arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void show() {
		// TODO Auto-generated method stub

	}

	@Override
	public void beginContact(Contact contact) {

	}

	@Override
	public void endContact(Contact contact) {
		// TODO Auto-generated method stub
		Body bodyA = contact.getFixtureA().getBody();
		Body bodyB = contact.getFixtureB().getBody();
		for (GameActor targetBox : currentLevel.getTargetBoxes()) {
			Body targetBoxBody = targetBox.getBody();
			if (targetBoxBody != null
					&& (bodyA.equals(targetBoxBody) || bodyB
							.equals(targetBoxBody))) {
				if (targetBox instanceof TargetBox) {
					((TargetBox) targetBox).hit();
					if (((TargetBox) targetBox).isDead()
							&& !(targetBox instanceof IndustructableTargetBox)) {
						deadActors.add(targetBox);
						scoreBoard.addPoints(targetBox.getScoreValue());
					}
				}
			}
		}
	}

	@Override
	public void postSolve(Contact arg0, ContactImpulse arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void preSolve(Contact arg0, Manifold arg1) {
		// TODO Auto-generated method stub

	}

	public void onTouch(MotionEvent ev) {
		if (puck.isDead()) {
			// game.resetScreen();
		} else {
			moveHorizantal(ev);
		}
	}

	private float startX = 0;
	private Boolean goingLeft = false;
	private Boolean goingRight = false;

	float stopX = 0;

	private void moveHorizantal(MotionEvent ev) {
		float x = Gdx.graphics.getWidth() - ev.getX();

		if (ev.getAction() == MotionEvent.ACTION_DOWN) {
			startX = x;
		} else if (ev.getAction() == MotionEvent.ACTION_MOVE) {
			float currentMotion = x - startX;
			stopX = Gdx.graphics.getWidth() - x;
			applyForce(currentMotion);
		}

	}

	private static final String PAD_SPEED_PREF_KEY = "pad_speed_interval";

	private int strikeCount = 0;

	private void applyForce(float currentMotion) {
		Preferences prefs = Gdx.app.getPreferences("breakout-preferences");
		String ballSpeed = prefs.getString(PAD_SPEED_PREF_KEY);
		float speed;
		if (ballSpeed != null) {
			// Implement later
			if (ballSpeed.equalsIgnoreCase("medium")) {
				speed = 10.0f;
			} else if (ballSpeed.equalsIgnoreCase("Light Speed")) {
				speed = 30.0f;
			} else if (ballSpeed.equalsIgnoreCase("Slow")) {
				speed = 5.0f;
			} else if (ballSpeed.equalsIgnoreCase("Fast")) {
				speed = 15.0f;
			} else {
				speed = 10.0f;
				prefs.putString(PAD_SPEED_PREF_KEY, "Medium");
			}
		} else {
			speed = 10.0f;
			prefs.putString(PAD_SPEED_PREF_KEY, "Medium");
		}
		prefs.flush();
		if (currentMotion < 0 && !goingRight && !needsStoppedRight()) {
			if (strikeCount == 1) {
				leftPad.getBody().setLinearVelocity(1.0f * speed, 0);
				goingRight = true;
				goingLeft = false;
				strikeCount = 0;
			}
			strikeCount++;
		} else if (currentMotion > 0 && !goingLeft && !needsStoppedLeft()) {
			if (strikeCount == 1) {
				leftPad.getBody().setLinearVelocity(-1.0f * speed, 0);
				goingLeft = true;
				goingRight = false;
			}
			strikeCount++;
		}
	}

	private void leftPadStop() {
		startX = 0;
		leftPad.getBody().setLinearVelocity(0, 0f);
		goingRight = false;
		goingLeft = false;
	}

	public native String resetScreen();

	/**
	 * @return the count
	 */
	public int getCount() {
		return count;
	}

	/**
	 * @param count
	 *            the count to set
	 */
	public void setCount(int count) {
		this.count = count;
	}

	/**
	 * @return the world
	 */
	public World getWorld() {
		return world;
	}

	/**
	 * @param world
	 *            the world to set
	 */
	public void setWorld(World world) {
		this.world = world;
	}

	/**
	 * @return the gravity
	 */
	public Vector2 getGravity() {
		return gravity;
	}

	/**
	 * @param gravity
	 *            the gravity to set
	 */
	public void setGravity(Vector2 gravity) {
		this.gravity = gravity;
	}

	/**
	 * @return the batch
	 */
	public SpriteBatch getBatch() {
		return batch;
	}

	/**
	 * @param batch
	 *            the batch to set
	 */
	public void setBatch(SpriteBatch batch) {
		this.batch = batch;
	}

	public GameActor getLeftPad() {
		return leftPad;
	}

	public void setLeftPad(GameActor leftPad) {
		this.leftPad = leftPad;
	}

	public Box2DDebugRenderer getDebugRenderer() {
		return debugRenderer;
	}

	public void setDebugRenderer(Box2DDebugRenderer debugRenderer) {
		this.debugRenderer = debugRenderer;
	}

	/**
	 * @return the camera
	 */
	public OrthographicCamera getCamera() {
		return camera;
	}

	/**
	 * @param camera
	 *            the camera to set
	 */
	public void setCamera(OrthographicCamera camera) {
		this.camera = camera;
	}

	public boolean isAccelerometerPresent() {
		return accelerometerPresent;
	}

	public void setAccelerometerPresent(boolean accelerometerPresent) {
		this.accelerometerPresent = accelerometerPresent;
	}

	/**
	 * @return the accelX
	 */
	public float getAccelX() {
		return accelX;
	}

	/**
	 * @param accelX
	 *            the accelX to set
	 */
	public void setAccelX(float accelX) {
		this.accelX = accelX;
	}

	/**
	 * @return the accelY
	 */
	public float getAccelY() {
		return accelY;
	}

	/**
	 * @param accelY
	 *            the accelY to set
	 */
	public void setAccelY(float accelY) {
		this.accelY = accelY;
	}

	/**
	 * @return the accelZ
	 */
	public float getAccelZ() {
		return accelZ;
	}

	/**
	 * @param accelZ
	 *            the accelZ to set
	 */
	public void setAccelZ(float accelZ) {
		this.accelZ = accelZ;
	}

	/**
	 * @return the puck
	 */
	public GameActor getPuck() {
		return puck;
	}

	/**
	 * @param puck
	 *            the puck to set
	 */
	public void setPuck(GameActor puck) {
		this.puck = puck;
	}

}
