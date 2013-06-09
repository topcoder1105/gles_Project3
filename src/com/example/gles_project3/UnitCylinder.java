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
	private float[] colors = new float[4*39];	
	
	final float unit_size = 0.2f;
	final float unit_height = 0.08f;
	
	final float half_unit_size = UnitObject.unit_size/3;
	final float half_unit_height = UnitObject.unit_height/3;
	
	private float vertices[] = new float[3*39];
	
	private float normals[] = new float [3*39];
		
	private byte indices[] = new byte [3*12 + 6*12];

	
	UnitCylinder(Color _CLR)
	{

		this.CLR = _CLR;
		
		vertices[0] = 0.0f;// (float) ((float) UnitBoard.half_unit_size*Math.cos( (float) Math.PI/6.0f * i ));
		vertices[1] = 0.0f;//(float) ((float)  UnitBoard.half_unit_size*Math.sin( (float) Math.PI/6.0f * i ));
		vertices[2] = half_unit_height; 
		normals[0] = 0.0f;
		normals[1] = 0.0f;
		normals[2] = 1.0f;
		
		
		for ( int i = 0 ; i < 12 ; i++){
			vertices[3 + i*3] =  (float) ((float) half_unit_size*Math.cos( (float) Math.PI/6.0f * i ));
			vertices[3 + i*3+1] = (float) ((float)  half_unit_size*Math.sin( (float) Math.PI/6.0f * i ));
			vertices[3 + i*3+2] = half_unit_height; 			
			normals[3 + i*3] = 0.0f;
			normals[3 + i*3+1] = 0.0f;
			normals[3 + i*3+2] = 1.0f;
		}
				
		for ( int i = 0 ; i < 13 ; i++){
			vertices[39 + i*3] =  (float) ((float) half_unit_size*Math.cos( (float) Math.PI/6.0f * i ));
			vertices[39 + i*3+1] = (float) ((float) half_unit_size*Math.sin( (float) Math.PI/6.0f * i ));
			vertices[39 + i*3+2] = half_unit_height; 			
			normals[39 + i*3] = (float) ((float) half_unit_size*Math.cos( (float) Math.PI/6.0f * i + Math.PI/12.0f ));
			normals[39 + i*3+1] = (float) ((float) half_unit_size*Math.sin( (float) Math.PI/6.0f * i + Math.PI/12.0f));
			normals[39 + i*3+2] = 0.0f;		
		}
		for ( int i = 0 ; i < 13; i++){
			vertices[78 + i*3] =  (float) ((float) half_unit_size*Math.cos( (float) Math.PI/6.0f * i ));
			vertices[78 + i*3+1] = (float) ((float) half_unit_size*Math.sin( (float) Math.PI/6.0f * i ));
			vertices[78 + i*3+2] = -half_unit_height; 				
			normals[78 + i*3] = (float) ((float) half_unit_size*Math.cos( (float) Math.PI/6.0f * i + Math.PI/12.0f ));
			normals[78 + i*3+1] = (float) ((float) half_unit_size*Math.sin( (float) Math.PI/6.0f * i + Math.PI/12.0f));
			normals[78 + i*3+2] = 0.0f;		
		}
		
		for ( int i = 0 ; i < 12 ; i++)
		{
			indices[i*3] = 0;
			indices[i*3+1] = (byte) ( i + 1);
			
			if ( i == 11){ indices[i*3+2] = (byte) (i + 2 - 12);	}
			else{	indices[i*3+2] = (byte) (i + 2);	}	
		}
		
		for ( int i = 0 ; i < 12 ; i++)
		{
			indices[36+ i*6] = (byte) (13 + i);
			indices[36+ i*6+1] = (byte) (14 + i);
			indices[36+ i*6+2] = (byte) (26 + i);
			indices[36+ i*6+3] = (byte) (26 + i);
			indices[36+ i*6+4] = (byte) (14 + i);
			indices[36+ i*6+5] = (byte) (27 + i);			
		}

		for ( int i = 0 ; i < 39; i++){	
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
		gl.glEnableClientState(GL10.GL_NORMAL_ARRAY);
//		gl.glEnableClientState(GL10.GL_COLOR_ARRAY);
		
		//Set the face rotation
		gl.glFrontFace(GL10.GL_CW);
		
		//Point to our buffers
		gl.glVertexPointer(3, GL10.GL_FLOAT, 0, mVertexBuffer);
//		gl.glColorPointer(4, GL10.GL_FLOAT, 0, mColorBuffer);
		gl.glNormalPointer(GL10.GL_FLOAT, 0, mNormalBuffer);
		
		//Draw the vertices as triangles, based on the Index Buffer information
		UnitObject.Material_Color(gl, this.CLR);
		gl.glDrawElements(GL10.GL_TRIANGLES, indices.length, GL10.GL_UNSIGNED_BYTE, mIndexBuffer);
		
		//Disable the client state before leaving
		gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
//		gl.glDisableClientState(GL10.GL_COLOR_ARRAY);
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
