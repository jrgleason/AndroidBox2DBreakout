package org.gleason.superhockey.model.box;

import org.gleason.superhockey.model.GameActor;
import org.gleason.superhockey.model.Prize;
import org.gleason.superhockey.model.TargetBox;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;

public class PrizeBox extends TargetBox {
	
	public PrizeBox(World world) {
		super(world);
		// TODO Auto-generated constructor stub
	}
	
	public void releasePrize(){
		Vector2 v = getBody().getPosition();
		Prize p = Prize.createNew(getWorld(), v.x, v.y, "ignored");
		p.getBody().setLinearVelocity(0f, -3f);
	}
	
	//TODO: this needs to be made more generic.
	public static GameActor createPrizeBox(World world, float x,float y, boolean isMeters){
		if(isMeters){
			return createNewPrizeBox(world, x, y);
		}
		else{
			return createNewPrizeBox(world, convertPixelsToMeters(x), convertPixelsToMeters(y));
		}
	}
	
	private static GameActor createNewPrizeBox(World world, float x,float y){
		BodyDef bodyDef = new BodyDef();
		bodyDef.type = BodyType.StaticBody;
		bodyDef.position.set(x, y);
		bodyDef.angle = 0;
		TargetBox returnVal = new PrizeBox(world);
		returnVal.setBody(world.createBody(bodyDef));
		returnVal.createFixture();
		addSprite(returnVal);
		return returnVal;
	}
	
}
