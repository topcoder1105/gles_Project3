package com.example.gles_project3;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.util.Random;

import javax.microedition.khronos.opengles.GL10;

public class UnitBlock extends Object {
	
	private FloatBuffer normalBuffer;

	
	private float verticesUnit[] = { 0.1f, 0.1f, -0.1f, -0.1f, 0.1f, -0.1f,   
									 -0.1f, -0.1f, -0.1f, 0.1f, -0.1f, -0.1f,
									 0.1f, 0.1f, 0.1f, -0.1f, 0.1f, 0.1f,   
									 -0.1f, -0.1f, 0.1f, 0.1f, -0.1f, 0.1f };
	private float vertices[] = {
			// Front face
			-1.0f, -1.0f, 1.0f, //v0
			1.0f, -1.0f, 1.0f, 	//v1
			-1.0f, 1.0f, 1.0f, 	//v2
			1.0f, 1.0f, 1.0f, 	//v3
		
			// Right face
			1.0f, -1.0f, 1.0f, 	// ...
			1.0f, -1.0f, -1.0f, 
			1.0f, 1.0f, 1.0f, 
			1.0f, 1.0f, -1.0f,
		
			// Back face
			1.0f, -1.0f, -1.0f, 
			-1.0f, -1.0f, -1.0f, 
			1.0f, 1.0f, -1.0f, 
			-1.0f, 1.0f, -1.0f,
		
			// Left face
			-1.0f, -1.0f, -1.0f, 
			-1.0f, -1.0f, 1.0f, 
			-1.0f, 1.0f, -1.0f, 
			-1.0f, 1.0f, 1.0f,
		
			// Bottom face
			-1.0f, -1.0f, -1.0f, 
			1.0f, -1.0f, -1.0f, 
			-1.0f, -1.0f, 1.0f, 
			1.0f, -1.0f, 1.0f,
		
			// Up face
			-1.0f, 1.0f, 1.0f, 
			1.0f, 1.0f, 1.0f, 
			-1.0f, 1.0f, -1.0f, 
			1.0f, 1.0f, -1.0f, 
		};

	
	private float normals[] = {
			// Normals
			// Front face
			0.0f, 0.0f, 1.0f, 						
			0.0f, 0.0f, 1.0f, 
			0.0f, 0.0f, 1.0f, 
			0.0f, 0.0f, 1.0f, 
			
			// Right face		
			1.0f, 0.0f, 0.0f, 
			1.0f, 0.0f, 0.0f, 
			1.0f, 0.0f, 0.0f, 
			1.0f, 0.0f, 0.0f, 

			// Back face
			0.0f, 0.0f, -1.0f, 
			0.0f, 0.0f, -1.0f, 
			0.0f, 0.0f, -1.0f, 
			0.0f, 0.0f, -1.0f, 

			// Left face
			-1.0f, 0.0f, 0.0f, 
			-1.0f, 0.0f, 0.0f, 
			-1.0f, 0.0f, 0.0f, 
			-1.0f, 0.0f, 0.0f, 

			// Bottom face
			0.0f, -1.0f, 0.0f, 
			0.0f, -1.0f, 0.0f, 
			0.0f, -1.0f, 0.0f, 
			0.0f, -1.0f, 0.0f, 

			// Up face
			0.0f, 1.0f, 0.0f, 
			0.0f, 1.0f, 0.0f, 
			0.0f, 1.0f, 0.0f, 
			0.0f, 1.0f, 0.0f, 
		};
	
	private byte indices[] = {
			// Faces definition
			0, 1, 3, 0, 3, 2, 		// Face front
			4, 5, 7, 4, 7, 6, 		// Face right
			8, 9, 11, 8, 11, 10, 	// ...
			12, 13, 15, 12, 15, 14, 
			16, 17, 19, 16, 19, 18, 
			20, 21, 23, 20, 23, 22, 
		};
	
		
	@Override
	void draw(GL10 gl) {
	//	drawObject(gl);
		gl.glEnableClientState(GL10.GL_NORMAL_ARRAY);
        gl.glEnableClientState(GL10.GL_COLOR_ARRAY);
		gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
		gl.glEnableClientState(GL10.GL_COLOR_MATERIAL);
		gl.glEnable(GL10.GL_BLEND);
		gl.glFrontFace(GL10.GL_CW);

		Material_Flat_Gray(gl);

        gl.glVertexPointer(3, GL10.GL_FLOAT, 0, mVertexBuffer);

       gl.glColorPointer(4, GL10.GL_FLOAT, 0, mColorBuffer);
        
		gl.glNormalPointer(GL10.GL_FLOAT, 0, normalBuffer);
		
		//Set the face rotation

		//Point to our buffers

//        gl.glDisable(GL10.GL_LIGHTING);
        gl.glDrawElements(GL10.GL_TRIANGLES, 36, GL10.GL_UNSIGNED_BYTE, mIndexBuffer);
//        gl.glDrawElements(GL10.GL_LINE_LOOP, 36, GL10.GL_UNSIGNED_BYTE, mIndexBuffer);
       
//		gl.glDisable(GL10.GL_BLEND);
		//Disable the client state before leaving
		gl.glDisableClientState(GL10.GL_COLOR_MATERIAL);

        gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
        gl.glDisableClientState(GL10.GL_COLOR_ARRAY);
		gl.glDisableClientState(GL10.GL_NORMAL_ARRAY);
	
	}
	
	UnitBlock(){
		mVertexBuffer = getFloatBufferFromFloatArray(vertices);
		mIndexBuffer = getByteBufferFromByteArray(indices);
		normalBuffer = getFloatBufferFromFloatArray(normals);
		
		colors = new float[96];
		for ( int i = 0 ; i < 24 ; i++ )
		{
			colors[i*4] = 1.0f;//(float) Math.random();;
			colors[i*4 + 1] = 0.0f;//(float) Math.random();
			colors[i*4 + 2] = 0.0f;//(float) Math.random();;
			colors[i*4 + 3] = 1.0f;
			
		}
		
		mColorBuffer = getFloatBufferFromFloatArray(colors);
		
	}
	
	
	
}
