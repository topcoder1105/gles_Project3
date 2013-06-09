package com.example.gles_project3;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;

public class UnitCylinder {

	private FloatBuffer mVertexBuffer = null;
	private FloatBuffer mColorBuffer = null;
	private FloatBuffer mNormalBuffer = null;
	private ByteBuffer mIndexBuffer = null;
	
	private Color CLR;
	private float[] colors = new float[4*24];	
	
	final float unit_size = 0.2f;
	final float unit_height = 0.08f;
	
	final float half_unit_size = unit_size/2;
	final float half_unit_height = unit_height/2;
		
	final float[] vertices = {
		
			// À­¸é
			-half_unit_size, -half_unit_size, half_unit_height, //v0
			half_unit_size, -half_unit_size, half_unit_height,	//v1
			-half_unit_size, half_unit_size, half_unit_height, 	//v2
			half_unit_size, half_unit_size, half_unit_height, 	//v3
		
			// ¾Õ¸é
			half_unit_size, -half_unit_size, half_unit_height, 	// ...
			half_unit_size, -half_unit_size, -half_unit_height, 
			half_unit_size, half_unit_size, half_unit_height, 
			half_unit_size, half_unit_size, -half_unit_height,
		
			// ¹Ù´Ú¸é
			half_unit_size, -half_unit_size, -half_unit_height, 
			-half_unit_size, -half_unit_size, -half_unit_height, 
			half_unit_size, half_unit_size, -half_unit_height, 
			-half_unit_size, half_unit_size, -half_unit_height,
		
			// µÞ¸é
			-half_unit_size, -half_unit_size, -half_unit_height, 
			-half_unit_size, -half_unit_size, half_unit_height, 
			-half_unit_size, half_unit_size, -half_unit_height, 
			-half_unit_size, half_unit_size, half_unit_height,
		
			// ¿ÞÂÊ¸é
			-half_unit_size, -half_unit_size, -half_unit_height, 
			half_unit_size, -half_unit_size, -half_unit_height, 
			-half_unit_size, -half_unit_size, half_unit_height, 
			half_unit_size, -half_unit_size, half_unit_height,
		
			// ¿À¸¥ÂÊ¸é
			-half_unit_size, half_unit_size, half_unit_height, 
			half_unit_size, half_unit_size, half_unit_height,
			-half_unit_size, half_unit_size, -half_unit_height, 
			half_unit_size, half_unit_size, -half_unit_height, 
	};
 	
	private float normals[] = {
			
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

	
	UnitCylinder(Color _CLR)
	{
		this.CLR = _CLR;
		for ( int i = 0 ; i < 24; i++){	
			colors[i*4] = CLR.r;	colors[i*4+1] = CLR.g;	colors[i*4+2] = CLR.b;	colors[i*4+3] = CLR.a;
		}
		
		mVertexBuffer = getFloatBufferFromFloatArray(vertices);
		mColorBuffer = getFloatBufferFromFloatArray(colors);
		mNormalBuffer = getFloatBufferFromFloatArray(normals);
		mIndexBuffer = getByteBufferFromByteArray(indices);
	}
	
	void draw(GL10 gl)
	{
		//Enable the vertex, texture and normal state
		gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
		gl.glEnableClientState(GL10.GL_COLOR_ARRAY);
		gl.glEnableClientState(GL10.GL_NORMAL_ARRAY);

		//Set the face rotation
		gl.glFrontFace(GL10.GL_CCW);
		
		//Point to our buffers
		gl.glVertexPointer(3, GL10.GL_FLOAT, 0, mVertexBuffer);
		gl.glColorPointer(4, GL10.GL_FLOAT, 0, mColorBuffer);
		gl.glNormalPointer(GL10.GL_FLOAT, 0, mNormalBuffer);
		
		//Draw the vertices as triangles, based on the Index Buffer information
		gl.glDrawElements(GL10.GL_TRIANGLES, indices.length, GL10.GL_UNSIGNED_BYTE, mIndexBuffer);
		
		//Disable the client state before leaving
		gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
		gl.glDisableClientState(GL10.GL_COLOR_ARRAY);
		gl.glDisableClientState(GL10.GL_NORMAL_ARRAY);	
		
	}


	
	FloatBuffer getFloatBufferFromFloatArray(float array[]) {
		ByteBuffer tempBuffer = ByteBuffer.allocateDirect(array.length * 4);
		tempBuffer.order(ByteOrder.nativeOrder());
		FloatBuffer buffer = tempBuffer.asFloatBuffer();
		buffer.put(array);
		buffer.position(0);
		return buffer;
	}
	ByteBuffer getByteBufferFromByteArray( byte array[]) {
		ByteBuffer buffer = ByteBuffer.allocateDirect(array.length);
		buffer.put(array);
		buffer.position(0);
		return buffer;
	}

}
