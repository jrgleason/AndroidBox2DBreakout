package org.gleason.superhockey;

import org.gleason.superhockey.opengl.GameRenderer;

import android.app.Activity;
import android.opengl.GLSurfaceView;
import android.os.Bundle;

public class OpenGLActivity extends Activity {
	private GLSurfaceView view;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

	    view = new GLSurfaceView(this);

	    // Tell EGL to use a ES 2.0 Context
	    view.setEGLContextClientVersion(2);
	    view.setEGLConfigChooser(8 , 8, 8, 8, 16, 0);

	    // Set the renderer
	    GLSurfaceView.Renderer render = new GameRenderer();
	    view.setRenderer(render);

	    setContentView(view);
	}
}
