package org.gleason.superhockey.model.box;

import org.gleason.superhockey.model.TargetBox;

import com.badlogic.gdx.physics.box2d.World;

public class IndustructableTargetBox extends TargetBox {

	public IndustructableTargetBox(World world) {
		super(world);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void hit(){
//		hits++;
	}

	@Override
	public boolean isDead() {
		// TODO Auto-generated method stub
		return true;
	}
}
