package com.example.gles_project3;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.util.Vector;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import android.opengl.GLSurfaceView.Renderer;
import android.opengl.GLU;

public class GLRenderer implements Renderer {

	Vector<Object> object;
	
	private float tX = 0, tY = 0, tZ = -5f;

	UnitBoard unitboard = null;
	
	private float[] lightAmbient = {1.0f, 0.1f, 0.1f, 0.1f};
	private float[] lightDiffuse = {1.0f, 0.1f, 0.1f, 0.1f};
	private float[] lightPosition = {0.0f, 0.0f, 10.0f, 0.0f};
	private float[] lightSpecular = {0.1f, 0.0f, 0.0f, 1.0f}; // Specular
	
	
	/* The buffers for our light values ( NEW ) */
	private FloatBuffer lightAmbientBuffer;
	private FloatBuffer lightDiffuseBuffer;
	private FloatBuffer lightPositionBuffer;
	private FloatBuffer lightSpecularBuffer;
	private float tX = 0, tY = 0, tZ = -5f;
	

	public GLRenderer(GLSurfaceView glSurfaceView) {
		TouchManager.getInstance().setRenderer(this);
<<<<<<< HEAD
				
=======
		
		object = new Vector<Object>();
		object.add(new UnitBlock());
>>>>>>> 563c61fc155def45272b182aeeace4c0b6e61d23
		
//		object = new Vector<Object>();
//		object.add(new UnitBlock());
		unitboard = new UnitBoard(new Color(255, 0, 0, 255));
		
		
	}	
	
	@Override
	public void onDrawFrame(GL10 gl) {
		gl.glClear(GL10.GL_COLOR_BUFFER_BIT|GL10.GL_DEPTH_BUFFER_BIT);
		gl.glLoadIdentity();
<<<<<<< HEAD

=======
//		gl.glTranslatef(0.5f, -0.5f, -5.0f);
		gl.glTranslatef(tX, tY, tZ);
		object.get(0).draw(gl);
>>>>>>> 563c61fc155def45272b182aeeace4c0b6e61d23
	}

	@Override
	public void onSurfaceChanged(GL10 gl, int width, int height) {
		gl.glViewport(0, 0, width, height);
		gl.glMatrixMode(GL10.GL_PROJECTION);
		gl.glLoadIdentity();
		GLU.gluPerspective(gl, 45.0f, (float) width/height , 1.0f, 30.f);
		gl.glMatrixMode(GL10.GL_MODELVIEW);
		gl.glLoadIdentity();
		
	}

	@Override
	public void onSurfaceCreated(GL10 gl, EGLConfig config) {
		gl.glLightfv(GL10.GL_LIGHT0, GL10.GL_AMBIENT, lightAmbientBuffer);		//Setup The Ambient Light ( NEW )
		gl.glLightfv(GL10.GL_LIGHT0, GL10.GL_DIFFUSE, lightDiffuseBuffer);		//Setup The Diffuse Light ( NEW )
		gl.glLightfv(GL10.GL_LIGHT0, GL10.GL_POSITION, lightPositionBuffer);	//Position The Light ( NEW )
		gl.glLightfv(GL10.GL_LIGHT0, GL10.GL_SPECULAR, lightSpecularBuffer);	//Specular The Light ( NEW )		

		gl.glEnable(GL10.GL_LIGHT0);											//Enable Light 0 ( NEW )
		gl.glEnable(GL10.GL_LIGHTING);
		
		
		gl.glShadeModel(GL10.GL_SMOOTH);
		gl.glClearColor(1.0f, 1.0f, 1.0f, 1.0f);
		gl.glClearDepthf(1.0f);
		gl.glEnable(GL10.GL_DEPTH_TEST);
		gl.glDepthFunc(GL10.GL_LEQUAL);
		gl.glHint(GL10.GL_PERSPECTIVE_CORRECTION_HINT, GL10.GL_NICEST);
	}

	
	// getters and setters
	public float gettX() {
		return tX;
	}

	public void settX(float tX) {
		this.tX = tX;
	}

	public float gettY() {
		return tY;
	}

	public void settY(float tY) {
		this.tY = tY;
	}

	public float gettZ() {
		return tZ;
	}

	public void settZ(float tZ) {
		this.tZ = tZ;
	}
	
}
