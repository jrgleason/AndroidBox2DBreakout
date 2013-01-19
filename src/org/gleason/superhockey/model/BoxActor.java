package org.gleason.superhockey.model;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.PolygonShape;

public class BoxActor extends GameActor {
	private float height;
	private float width;
	
	public BoxActor(){
		super();
		setHeight(20);
		setWidth(20);
		resize();
	}
	
	@Override
	public void resize() {
		// TODO Auto-generated method stub
		PolygonShape shape = new PolygonShape();
		shape.setAsBox(getWidth(),  getHeight());
		setShape(shape);
	}
	
	public boolean isTouched(float x, float y){
		
		
		boolean returnVal = false;
		if(x<=getEndX() && x >= getStartX() && y>=getStartY() && y <= getEndY()){
			returnVal = true;
		}
		return returnVal;
	}
	
	public float getStartX(){
		Vector2 position = this.getBody().getPosition();
		return position.x-getWidth();
	}
	public float getStartY(){
		Vector2 position = this.getBody().getPosition();
		return position.y-getHeight();
	}
	public float getEndX(){
		Vector2 position = this.getBody().getPosition();
		return position.x+getWidth();
	}
	public float getEndY(){
		Vector2 position = this.getBody().getPosition();
		return position.y+getHeight();
	}
	
	public float getHeight() {
		return height;
	}
	public void setHeight(float height) {
		this.height = height;
	}
	public float getWidth() {
		return width;
	}
	public void setWidth(float width) {
		this.width = width;
	}
}
