package org.gleason.superhockey.model.box;

public enum BoxType {
	EMPTY('e');
	private char mapChar;
	private BoxType(char mapChar){
		this.mapChar = mapChar;
	}
	public static BoxType getTypeByChar(char mapChar){
		for(BoxType bt : BoxType.values()){
			if(bt.mapChar == mapChar){
				return bt;
			}
		}
		return EMPTY;
	}
}
