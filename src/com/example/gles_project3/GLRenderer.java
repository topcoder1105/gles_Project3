package com.example.gles_project3;

import java.util.Vector;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import android.opengl.GLSurfaceView.Renderer;
import android.opengl.GLU;

public class GLRenderer implements Renderer {

<<<<<<< HEAD
	Vector<Object> object;
	public GLRenderer(GLSurfaceView glSurfaceView) {
		object = new Vector<Object>();

		object.add(new UnitBlock());
=======
	Vector<Object> object = null;
	
	public GLRenderer(GLSurfaceView glSurfaceView) {
	
>>>>>>> 0fa311869c7ea91ccf31ca1213c35603dcb7b099
	}

	@Override
	public void onDrawFrame(GL10 gl) {
		gl.glClear(GL10.GL_COLOR_BUFFER_BIT|GL10.GL_DEPTH_BUFFER_BIT);
		gl.glLoadIdentity();
<<<<<<< HEAD
		gl.glTranslatef(0.5f, -0.5f, -5.0f);
		object.get(0).draw(gl);
		gl.glLoadIdentity();

=======
		gl.glTranslatef(0.0f, 0.0f, -6.0f);
		
		object.get(0).draw(gl);
		
>>>>>>> 0fa311869c7ea91ccf31ca1213c35603dcb7b099
	}

	@Override
	public void onSurfaceChanged(GL10 gl, int width, int height) {
		gl.glViewport(0, 0, width, height);
		gl.glMatrixMode(GL10.GL_PROJECTION);
		gl.glLoadIdentity();
		GLU.gluPerspective(gl, 45.0f, (float) width/height , 1.0f, 30.f);
		gl.glMatrixMode(GL10.GL_MODELVIEW);
		gl.glLoadIdentity();
		
<<<<<<< HEAD
=======
		object.add(new Block(new Size(1,1,1), 0));
>>>>>>> 0fa311869c7ea91ccf31ca1213c35603dcb7b099
	}

	@Override
	public void onSurfaceCreated(GL10 gl, EGLConfig config) {
		gl.glShadeModel(GL10.GL_SMOOTH);
		gl.glClearColor(0.5f, 0.5f, 0.5f, 0.5f);
		gl.glClearDepthf(1.0f);
		gl.glEnable(GL10.GL_DEPTH_TEST);
		gl.glDepthFunc(GL10.GL_LEQUAL);
		gl.glHint(GL10.GL_PERSPECTIVE_CORRECTION_HINT, GL10.GL_NICEST);
	}
}
