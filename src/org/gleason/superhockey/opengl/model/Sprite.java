package org.gleason.superhockey.opengl.model;

public class Sprite {
	static 
	{
		System.loadLibrary("game-gl");
	}

	public static native void init();
	public static native void resize(int width, int height);
	public static native void render();
}
