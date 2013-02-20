package org.gleason.superhockey.model;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Shape;

public interface Actor {
	boolean isTouched(float x, float y);
	void drawSprite(SpriteBatch batch);
	void resize();
	Fixture createFixture();
	Shape getShape();
	Body getBody();
}
