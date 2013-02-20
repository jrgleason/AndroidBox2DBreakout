package org.gleason.superhockey.model.levels;

import java.util.List;

import org.gleason.superhockey.ca.Cell;
import org.gleason.superhockey.ca.Grid;
import org.gleason.superhockey.model.CellBox;
import org.gleason.superhockey.model.GameActor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

public class Level3 extends Level {
	public void genBoxMap(World world, Vector2 location, boolean isMeters) {
		super.genBoxMap(world);
		Grid grid = new Grid(28, 20, true);
		grid.setLocation(location);
		grid.itterateGeneration();
		grid.itterateGeneration();
		grid.itterateGeneration();
		float halfWidth = grid.getGridWidth();
		float halfHeight = grid.getGridHeight();
		float currentLocationX = BORDER_VAL+10f*2f;
		float currentLocationY = Gdx.graphics.getHeight()-(2*BORDER_VAL)-10f;
		if (!isMeters) {
			location.x = GameActor.convertPixelsToMeters(location.x);
			location.y = GameActor.convertPixelsToMeters(location.y);
			currentLocationX = GameActor.convertPixelsToMeters(currentLocationX);
			currentLocationY = GameActor.convertPixelsToMeters(currentLocationY);
		}
		
		
		
		for (List<Cell> cells : grid.getGrid()) {
			int count = 0;
			for (Cell c : cells) {
				if(count==0){
					currentLocationX = GameActor.convertPixelsToMeters(BORDER_VAL+10f*2f);
					currentLocationY = currentLocationY - (c.getHeight() * 2);
					count++;
				}
				currentLocationX = currentLocationX + (c.getWidth() * 2);
				if (c.isState()) {
					
					CellBox cb = new CellBox(world, currentLocationX, currentLocationY);
//					CellBox cb2 = new CellBox(world, currentLocationX, locale);
//					locale+=c.getWidth();
				}
				
			}
			
		}
	}

	public void genBoxMap(World world, Vector2 location) {
		this.genBoxMap(world, location, true);
	}
}
