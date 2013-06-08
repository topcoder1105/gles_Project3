package com.example.gles_project3;

import android.content.Context;

public class GLSurfaceView extends android.opengl.GLSurfaceView {
	private GLRenderer renderer;
	
	public GLSurfaceView(Context context) {
		super(context);
		renderer = new GLRenderer(this);
		setRenderer(renderer);		
	}
}