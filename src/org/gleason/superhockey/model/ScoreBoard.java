package org.gleason.superhockey.model;

public class ScoreBoard {
	private long score = 0;
	
	public void resetScore(){
		score = 0;
	}
	public void addPoints(long points){
		score += points;
	}
	
	public long getScore(){
		return score;
	}
}
