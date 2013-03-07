package org.gleason.superhockey.model.box;

public enum BoxType {
	EMPTY('e'),
	PRIZEBOX('b');
	private char mapChar;
	private BoxType(char mapChar){
		this.mapChar = mapChar;
	}
	public static BoxType getTypeByChar(char mapChar){
		for(BoxType bt : BoxType.values()){
			if(Character.toUpperCase(bt.mapChar) == 
					Character.toUpperCase(mapChar)){
				return bt;
			}
		}
		return EMPTY;
	}
}
