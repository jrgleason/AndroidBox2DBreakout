package org.gleason.superhockey.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;

public class ScoreBoard {
	private long score = 0;
	private BitmapFont font;
	private BitmapFont font2;
	private static final String BOARD_TEXT="Score Is: ";
	public static final String FONT_CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
	
	public static ScoreBoard create(){
		ScoreBoard returnValue = new ScoreBoard();
		FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("modern_lcd-7.ttf"));
		returnValue.font = generator.generateFont(18);
		returnValue.font.setColor(1.0f, 1.0f, 1.0f, 1.0f);
		FreeTypeFontGenerator generator2 = new FreeTypeFontGenerator(Gdx.files.internal("modern_lcd-7.ttf"));
		returnValue.font2 = generator2.generateFont(18);
//		returnValue.font2.setScale(1.25f);
		returnValue.font2.setColor(1.0f, 1.0f, 1.0f, 1.0f);
		return returnValue;
	}
	
	public void resetScore(){
		score = 0;
	}
	
	public String getScoreText(){
		return ScoreBoard.BOARD_TEXT;
	}
	
	public String getScoreString(){
		return String.valueOf(getScore());
	}
	
	public void addPoints(long points){
		score += points;
	}
	
	public long getScore(){
		return score;
	}
	/**
	 * @return the font
	 */
	public BitmapFont getFont() {
		return font;
	}
	public BitmapFont getFont2() {
		return font2;
	}
	/**
	 * @param font the font to set
	 */
	public void setFont(BitmapFont font) {
		this.font = font;
	}
}
