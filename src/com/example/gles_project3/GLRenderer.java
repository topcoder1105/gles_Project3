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

	private Lattice lattice;

	private float tX = 0, tY = 0, tZ = -5f;
	private float rX = 0, rY = 0;

	public GLRenderer(GLSurfaceView glSurfaceView) {
		TouchManager.getInstance().setRenderer(this);
		
		lattice = new Lattice();
		lattice.addBlock(1, 1, 1, 2, 3, 3, 0);
		lattice.addBlock(1, 1, 4, 2, 3, 3, 2);
		lattice.addBlock(1, 1, 7, 2, 3, 3, 3);
		lattice.addBlock(2, 3, 10, 2, 3, 3, 4);
		lattice.addBlock(10, 10, 1, 2, 3, 3, 5);
		
	}	
	
	@Override
	public void onDrawFrame(GL10 gl) {
		gl.glClear(GL10.GL_COLOR_BUFFER_BIT|GL10.GL_DEPTH_BUFFER_BIT);
		gl.glLoadIdentity();

		gl.glTranslatef(tX, tY, tZ);
				
		gl.glRotatef(rX, 1, 0, 0);
		gl.glRotatef(rY, 0, 1, 0);
		lattice.draw(gl);

	}

	@Override
	public void onSurfaceChanged(GL10 gl, int width, int height) {
		gl.glViewport(0, 0, width, height);
		gl.glMatrixMode(GL10.GL_PROJECTION);
		gl.glLoadIdentity();
		GLU.gluPerspective(gl, 45.0f, (float) width/height , 0.01f, 1000.0f);
		gl.glMatrixMode(GL10.GL_MODELVIEW);
		gl.glLoadIdentity();
		
	}

	@Override
	public void onSurfaceCreated(GL10 gl, EGLConfig config) {
		gl.glEnable(GL10.GL_LIGHT0);											//Enable Light 0 ( NEW )
		gl.glLightfv(GL10.GL_LIGHT0, GL10.GL_AMBIENT, staticVar.lightAmbientBuffer);		//Setup The Ambient Light ( NEW )
		gl.glLightfv(GL10.GL_LIGHT0, GL10.GL_DIFFUSE, staticVar.lightDiffuseBuffer);		//Setup The Diffuse Light ( NEW )
		gl.glLightfv(GL10.GL_LIGHT0, GL10.GL_POSITION, staticVar.lightPositionBuffer);	//Position The Light ( NEW )
		gl.glLightfv(GL10.GL_LIGHT0, GL10.GL_SPECULAR, staticVar.lightSpecularBuffer);	//Specular The Light ( NEW )		
 		gl.glEnable(GL10.GL_LIGHTING);
		
		gl.glEnable(GL10.GL_COLOR_MATERIAL);
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

	public float getrX() {
		return rX;
	}

	public void setrX(float rX) {
		this.rX = rX;
	}

	public float getrY() {
		return rY;
	}

	public void setrY(float rY) {
		this.rY = rY;
	}
	
	FloatBuffer getFloatBufferFromFloatArray(float array[]) {
		ByteBuffer tempBuffer = ByteBuffer.allocateDirect(array.length * 4);
		tempBuffer.order(ByteOrder.nativeOrder());
		FloatBuffer buffer = tempBuffer.asFloatBuffer();
		buffer.put(array);
		buffer.position(0);
		return buffer;
	}
}
