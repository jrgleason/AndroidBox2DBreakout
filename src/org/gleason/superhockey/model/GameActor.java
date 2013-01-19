package org.gleason.superhockey.model;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.Shape;

public abstract class GameActor {
	private Shape shape;
	private Body body;
	
	private Vector2 gravity = new Vector2(0f, 0f);;
	
	public GameActor(){
		resize();
	}
	public abstract void resize();
	public Fixture createFixture(){
		FixtureDef fd = new FixtureDef();
		fd.shape = getShape();
		fd.density = 20f;
		fd.restitution = 1.0f;
		return getBody().createFixture(fd);
	}
	public float[] getBottomLeft(){
		Vector2 middle = this.getBody().getPosition();
		PolygonShape shape = (PolygonShape)getShape();
		Vector2 first = new Vector2();
		shape.getVertex(0, first);
		float left = middle.x + first.x;
		float bottom = middle.y + first.y;
		float[] returnValue = {left,bottom};
		return returnValue;
	}
	
	public void move(float xIncrement, float yIncrement){
		Vector2 currentPosition = getBody().getPosition();
		relocate(currentPosition.x+xIncrement, currentPosition.y, currentPosition.angle());
	}
	public void relocate(float x, float y){
		relocate(x, y, 0f);
	}
	public void relocate(float x, float y, float angle){
		getBody().setTransform(new Vector2(x, y), angle);
	}
	
	public abstract boolean isTouched(float x, float y);
	
	/**
	 * Getters/Setters
	 */
	
	/**
	 * @return the shape
	 */
	public Shape getShape() {
		return shape;
	}
	/**
	 * @param shape the shape to set
	 */
	public void setShape(Shape shape) {
		this.shape = shape;
	}
	/**
	 * @return the body
	 */
	public Body getBody() {
		return body;
	}
	/**
	 * @param body the body to set
	 */
	public void setBody(Body body) {
		this.body = body;
	}
	
	/**
	 * @return the gravity
	 */
	public Vector2 getGravity() {
		return gravity;
	}
	/**
	 * @param gravity the gravity to set
	 */
	public void setGravity(Vector2 gravity) {
		this.gravity = gravity;
	}
}
