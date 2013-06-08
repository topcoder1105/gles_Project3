package com.example.gles_project3;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;

public abstract class Object {

	protected FloatBuffer mVertexBuffer = null;
	protected ByteBuffer mIndexBuffer = null;
	protected FloatBuffer mColorBuffer = null;
	protected FloatBuffer mTextureBuffer = null;
	protected FloatBuffer mNormalBuffer = null;
		
	protected float[] colors;
	protected float[] vertices;
	protected int[] indices;
	
	protected Coord center = null;
	protected Size size = null;
	protected int type = 0;
	
	abstract void draw(GL10 gl);

	
	void drawObject(GL10 gl)
	{
		gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
        gl.glVertexPointer(3, GL10.GL_FLOAT, 0, mVertexBuffer);

        gl.glEnableClientState(GL10.GL_COLOR_ARRAY);
        gl.glColorPointer(4, GL10.GL_FLOAT, 0, mColorBuffer);
        
//        gl.glDrawElements(GL10.GL_TRIANGLES, 36, GL10.GL_UNSIGNED_BYTE, mIndexBuffer);
//        gl.glDrawArrays(GL10.GL_TRIANGLES, 0, );
        		            
        gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
        gl.glDisableClientState(GL10.GL_COLOR_ARRAY);
        			
        
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
