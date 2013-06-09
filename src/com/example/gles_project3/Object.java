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
	
	
	float white_ambient[] = {0.1f, 0.1f, 0.1f, 1.0f};
	float white_diffuse[] = {0.6f, 0.6f, 0.5f, 1.0f};
	float white_specular[] = {0.5f, 0.5f, 0.5f, 1.0f};
	
	float gray_ambient[] = {0.1f, 0.1f, 0.1f, 1.0f};
	float gray_diffuse[] = {0.6f, 0.6f, 0.5f, 1.0f};
	float gray_specular[] = {0.5f, 0.5f, 0.5f, 1.0f};
	
	float glass_ambient[] = {0.3f, 0.3f, 0.3f, 0.3f};
	float glass_diffuse[] = {0.3f, 0.3f, 0.3f, 0.3f};
	float glass_specular[] = {1.0f, 1.0f, 1.0f, 1.0f};
	
    FloatBuffer white_ambientbuffer = getFloatBufferFromFloatArray(white_ambient);
    FloatBuffer white_diffusebuffer = getFloatBufferFromFloatArray(white_diffuse);
    FloatBuffer white_specularbuffer = getFloatBufferFromFloatArray(white_specular);
    
    FloatBuffer gray_ambientbuffer = getFloatBufferFromFloatArray(gray_ambient);
    FloatBuffer gray_diffusebuffer = getFloatBufferFromFloatArray(gray_diffuse);
    FloatBuffer gray_specularbuffer = getFloatBufferFromFloatArray(gray_specular);
    
    FloatBuffer glass_ambientbuffer = getFloatBufferFromFloatArray(glass_ambient);
    FloatBuffer glass_diffusebuffer = getFloatBufferFromFloatArray(glass_diffuse);
    FloatBuffer glass_specularbuffer = getFloatBufferFromFloatArray(glass_specular);
	
	
	void Material_Flat_Gray(GL10 gl)
    {
    	gl.glMaterialfv(GL10.GL_FRONT, GL10.GL_AMBIENT, gray_ambientbuffer);
    	gl.glMaterialfv(GL10.GL_FRONT, GL10.GL_DIFFUSE, gray_diffusebuffer);
    	gl.glMaterialfv(GL10.GL_FRONT, GL10.GL_SPECULAR, gray_specularbuffer);
    	gl.glMaterialx(GL10.GL_FRONT, GL10.GL_SHININESS, 16);
		gl.glColor4f(0.4f, 0.0f, 0.0f, 1.0f);
    }
    
    void Material_Flat_White(GL10 gl)
    {
    	gl.glMaterialfv(GL10.GL_FRONT, GL10.GL_AMBIENT, white_ambientbuffer);
    	gl.glMaterialfv(GL10.GL_FRONT, GL10.GL_DIFFUSE, white_diffusebuffer);
    	gl.glMaterialfv(GL10.GL_FRONT, GL10.GL_SPECULAR, white_specularbuffer);
    	gl.glMaterialx(GL10.GL_FRONT, GL10.GL_SHININESS, 16);
		gl.glColor4f(1.0f, 1.0f, 0.95f, 1.0f);
    }
    
    void Material_Glass(GL10 gl)
    {
    	gl.glMaterialfv(GL10.GL_FRONT, GL10.GL_AMBIENT, glass_ambientbuffer);
    	gl.glMaterialfv(GL10.GL_FRONT, GL10.GL_DIFFUSE, glass_diffusebuffer);
    	gl.glMaterialfv(GL10.GL_FRONT, GL10.GL_SPECULAR, glass_specularbuffer);
    	gl.glMaterialx(GL10.GL_FRONT_AND_BACK, GL10.GL_SHININESS, 128);
	    gl.glColor4f(0.3f, 0.3f, 0.3f, 0.3f);
    }
    
}
