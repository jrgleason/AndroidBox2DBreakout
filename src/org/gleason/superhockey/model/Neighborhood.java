package org.gleason.superhockey.model;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.Shape;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.World;

public class Neighborhood {
	private float left;
	private float right;
	private float top;
	private float bottom;
	private Body body;
	private List<TargetBox> cells = new ArrayList<TargetBox>();

	public static Neighborhood create(float left, float right, float top, float bottom, World world){
		Neighborhood returnValue = new Neighborhood();
		returnValue.setBottom(bottom);
		returnValue.setLeft(left);
		returnValue.setRight(right);
		returnValue.setTop(top);
		BodyDef bodyDef = new BodyDef();
		bodyDef.type = BodyType.KinematicBody;
		bodyDef.position.set(returnValue.getLocation());
		bodyDef.angle = 0;
		returnValue.body = world.createBody(bodyDef);
		returnValue.createFixture();
		return returnValue;
	}
	
	public Fixture createFixture(){
		FixtureDef fd = new FixtureDef();
		fd.shape = getShape();
		fd.density = 0f;
		fd.restitution = 0.0f;
		fd.friction = 0.0f;
		return body.createFixture(fd);
	}
	
	public Shape getShape(){
		PolygonShape shape = new PolygonShape();
		shape.setAsBox(getWidth(),  getHeight());
		return shape;
	}

	public Vector2 getLocation(){
		float x = getLeft()+((getRight()-getLeft())/2);
		float y = getBottom()+((getTop()-getBottom())/2);
		return new Vector2(x,y);
	}
	
	/**
	 * @return the left
	 */
	public float getLeft() {
		return left;
	}

	/**
	 * @param left the left to set
	 */
	public void setLeft(float left) {
		this.left = left;
	}

	/**
	 * @return the right
	 */
	public float getRight() {
		return right;
	}

	/**
	 * @param right the right to set
	 */
	public void setRight(float right) {
		this.right = right;
	}

	/**
	 * @return the top
	 */
	public float getTop() {
		return top;
	}

	/**
	 * @param top the top to set
	 */
	public void setTop(float top) {
		this.top = top;
	}

	/**
	 * @return the bottom
	 */
	public float getBottom() {
		return bottom;
	}

	/**
	 * @param bottom the bottom to set
	 */
	public void setBottom(float bottom) {
		this.bottom = bottom;
	}

	/**
	 * @return the height
	 */
	public float getHeight() {
		return top - bottom;
	}


	/**
	 * @return the width
	 */
	public float getWidth() {
		return right-left;
	}
	
	public float getXPosition(){
		return getLocation().x - getWidth();
	}
	public float getYPosition(){
		return getLocation().y - getHeight();
	}
}
