package org.gleason.superhockey.model.levels;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import org.gleason.superhockey.ca.Grid;
import org.gleason.superhockey.model.ArenaBarrier;
import org.gleason.superhockey.model.GameActor;
import org.gleason.superhockey.model.MediumTargetBox;
import org.gleason.superhockey.model.Neighborhood;
import org.gleason.superhockey.model.TargetBox;
import org.gleason.superhockey.model.box.BoxType;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

public class Level2 extends Level {
	private float baseX = BORDER_VAL+10f*2f+5f;
	public void genBoxMap(World world, Vector2 location, boolean isMeters) {
		Texture mTexture = new Texture(Gdx.files.internal("Background.jpg"));
		setBkgSprite(new Sprite(mTexture, Gdx.graphics.getWidth(),
				Gdx.graphics.getHeight()));
		getBkgSprite().setPosition(0, 0);
		super.genBoxMap(world);
		int numberOfCells = (Gdx.graphics.getWidth()-(int)BORDER_VAL*2)/2;
		Grid grid = new Grid(10, 20, false);
		grid.setLocation(location);
		
		float currentLocationY = Gdx.graphics.getHeight()-(2*BORDER_VAL)-10f;
		if (!isMeters) {
			location.x = GameActor.convertPixelsToMeters(location.x);
			location.y = GameActor.convertPixelsToMeters(location.y);
			baseX = GameActor.convertPixelsToMeters(baseX);
			currentLocationY = GameActor.convertPixelsToMeters(currentLocationY);
		}
		float currentLocationX = baseX;
		FileHandle file = Gdx.files.internal("1.level");
		try {
			generateMapFromFile(file.read(), world, currentLocationX, currentLocationY);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private void generateMapFromFile(InputStream is, World world, float locX, float locY) throws IOException{
		BufferedReader r = new BufferedReader(new InputStreamReader(is, "US-ASCII"));
		TargetBox tb = null;
		float boxWidth = GameActor.convertPixelsToMeters(TargetBox.WIDTH);
		float currentWidth =  0;
		float currentHeight = 0;
		float rowHeight = 0;
		float cellHeight = 0;
		try {
			int intch;
			while ((intch = r.read()) != -1) {
				char ch = (char) intch;
			    if (Character.isLetter(ch)) {
			    	BoxType bt = BoxType.getTypeByChar(ch);
			    	switch (bt) {
					case EMPTY:
						break;
					default:
						break;
					}
			    	locX += (TargetBox.WIDTH*2);
					currentWidth += TargetBox.WIDTH;
					if(currentWidth >= boxWidth){
						currentWidth = 0;
						currentHeight+=cellHeight;
					}
			    }
			    else if(ch == '\n'){
			    	locX = baseX;
					locY -= (TargetBox.HEIGHT * 2);
					rowHeight = GameActor.convertPixelsToMeters(TargetBox.HEIGHT);
					cellHeight = TargetBox.HEIGHT;
			    }
			    else{
			    	tb = (TargetBox)TargetBox.create(world, locX, locY, true);
			    	int i = Character.getNumericValue(ch);
			    	tb.setHardness(i);
			    	getTargetBoxes().add(tb);
			    	locX += (TargetBox.WIDTH*2);
					currentWidth += TargetBox.WIDTH;
					if(currentWidth >= boxWidth){
						currentWidth = 0;
						currentHeight+=cellHeight;
					}
			    }
			    
			}
		} finally {
			   r.close();
		}
	}
}
