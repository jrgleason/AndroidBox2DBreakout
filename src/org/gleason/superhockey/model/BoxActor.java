package org.gleason.superhockey.model;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.PolygonShape;

public abstract class BoxActor extends GameActor {
	private float height;
	private float width;
	private boolean dead;
	
	public BoxActor(){
		super();
		setHeight(20, false);
		setWidth(20, false);
		resize();
	}
	
	public static void addSprite(BoxActor box){
		Texture mTexture = new Texture(box.getImage());
		Sprite mSprite = new Sprite(mTexture);
		mSprite.setPosition(box.getStartX(), box.getStartY());
		box.setSprite(mSprite);
	}
	
	public static void addSprite(BoxActor box, int x, int y){
		Texture mTexture = new Texture(box.getImage());
		Sprite mSprite = new Sprite(mTexture, x, y);
		mSprite.setPosition(box.getStartX(), box.getStartY());
		box.setSprite(mSprite);
	}
	
	public abstract FileHandle getImage();
	
	public void drawSprite(SpriteBatch batch){
		getSprite().setPosition(getStartX(), getStartY());
		if(this.isDead()){
			getSprite().draw(batch, .5f);
		}
		else{
			getSprite().draw(batch);
		}
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
	/**
	 * Returns Pixels for openGL
	 * @return
	 */
	public float getStartX(){
		Vector2 position = this.getBody().getPosition();
		return convertMetersToPixels(position.x-getWidth());
	}
	public float getStartY(){
		Vector2 position = this.getBody().getPosition();
		return convertMetersToPixels(position.y-getHeight());
	}
	public float getEndX(){
		Vector2 position = this.getBody().getPosition();
		return convertMetersToPixels(position.x)+convertMetersToPixels(getWidth());
	}
	public float getEndY(){
		Vector2 position = this.getBody().getPosition();
		return convertMetersToPixels(position.y+getHeight());
	}
	
	public float getStartXMeters(){
		Vector2 position = this.getBody().getPosition();
		return position.x-getWidth();
	}
	public float getStartYMeters(){
		Vector2 position = this.getBody().getPosition();
		return position.y-getHeight();
	}
	public float getEndXMeters(){
		Vector2 position = this.getBody().getPosition();
		return position.x + getWidth();
	}
	public float getEndYMeters(){
		Vector2 position = this.getBody().getPosition();
		return position.y+getHeight();
	}
	
	public float getHeight() {
		return height;
	}
	public void setHeight(float height) {
		this.height = height;
	}
	public float getHeightAsPixel(){
		return convertMetersToPixels(getHeight());
	}
	public void setHeight(float height, boolean inMeters) {
		if(inMeters){
			setHeight(height);
		}
		else{
			setHeight(convertPixelsToMeters(height));
		}
	}
	public float getWidth() {
		return width;
	}
	public float getWidthAsPixel(){
		return convertMetersToPixels(getWidth());
	}
	public void setWidth(float width) {
		this.width = width;
	}
	public void setWidth(float width, boolean inMeters) {
		if(inMeters){
			setWidth(width);
		}
		else{
			setWidth(convertPixelsToMeters(width));
		}
	}
	public long getScoreValue() {
		// TODO Auto-generated method stub
		return 0;
	}
	public boolean isDead(){
		return dead;
	}
	public void setDead(boolean dead){
		this.dead = dead;
	}
	
}
