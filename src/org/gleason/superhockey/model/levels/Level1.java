package org.gleason.superhockey.model.levels;

import java.util.List;

import org.gleason.superhockey.ca.Cell;
import org.gleason.superhockey.ca.Grid;
import org.gleason.superhockey.model.CellBox;
import org.gleason.superhockey.model.GameActor;
import org.gleason.superhockey.model.TargetBox;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

public class Level1 extends Level {
//	@Override
	public void genBoxMap(World world, Vector2 location, boolean isMeters) {
		super.genBoxMap(world);
		// TODO Auto-generated method stub
		Texture mTexture = new Texture(Gdx.files.internal("Background.jpg"));
		setBkgSprite(new Sprite(mTexture, Gdx.graphics.getWidth(),
				Gdx.graphics.getHeight()));
		getBkgSprite().setPosition(0, 0);
		super.genBoxMap(world);
		Grid grid = new Grid(29, 20, false);
//		Grid grid = new Grid(10, 20, false);
		grid.setLocation(location);
//		grid.itterateGeneration();
//		grid.itterateGeneration();
//		grid.itterateGeneration();
//		float halfWidth = grid.getGridWidth();
//		float halfHeight = grid.getGridHeight();
		float baseX = BORDER_VAL+10f*2f+20f;
		
		float currentLocationY = Gdx.graphics.getHeight()-(2*BORDER_VAL)-10f;
		if (!isMeters) {
			location.x = GameActor.convertPixelsToMeters(location.x);
			location.y = GameActor.convertPixelsToMeters(location.y);
			baseX = GameActor.convertPixelsToMeters(baseX);
			currentLocationY = GameActor.convertPixelsToMeters(currentLocationY);
		}
		float currentLocationX = baseX;
		float boxWidth = GameActor.convertPixelsToMeters(TargetBox.WIDTH);
		float currentWidth =  0;
		float currentHeight = 0;
		float rowHeight = 0;
		
		float cellHeight = 0;
		for (List<Cell> cells : grid.getGrid()) {
			int count = 0;
			for (Cell c : cells) {
				if(count==0){
					currentLocationX = baseX;
					currentLocationY -= (c.getHeight() * 2);
					rowHeight = GameActor.convertPixelsToMeters(TargetBox.HEIGHT);
					cellHeight = c.getHeight();
					count++;
				}
				if (c.isState() && currentWidth == 0 && currentHeight == 0) {
					getTargetBoxes().add(TargetBox.create(world, currentLocationX, currentLocationY, true));
//					CellBox cb = new CellBox(world, currentLocationX, currentLocationY);
//					
				}
				currentLocationX += (c.getWidth()*2);
				currentWidth += c.getWidth();
				if(currentWidth >= boxWidth){
					currentWidth = 0;
				}
				
			}
			currentHeight+=cellHeight;
			if(currentHeight>=rowHeight){
				currentHeight = 0;
			}
		}
//		for (float y = (getTopValue() / 2 + 200); y < (getTopValue() - 100); y = y
//				+ TargetBox.HEIGHT * 2) {
//			for (float x = BORDER_VAL + TargetBox.WIDTH + 50; x < getRightValue()
//					- (TargetBox.WIDTH * 2); x = x + (TargetBox.WIDTH * 2)) {
//				TargetBox tb = (TargetBox) TargetBox.create(world, x, y, false, 2);
//				getTargetBoxes().add(tb);
//			}
//		}
	}

}
