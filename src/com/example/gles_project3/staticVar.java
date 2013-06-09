package com.example.gles_project3;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;

public class staticVar {


		
	static float white_ambient[] = {0.1f, 0.0f, 0.0f, 1.0f};
	static float white_diffuse[] = {0.1f, 0.0f, 0.0f, 1.0f};
	static float white_specular[] = {0.1f, 0.0f, 0.0f, 1.0f};
	
	static FloatBuffer white_ambientbuffer = getFloatBufferFromFloatArray(white_ambient);
	static FloatBuffer white_diffusebuffer = getFloatBufferFromFloatArray(white_diffuse);
	static FloatBuffer white_specularbuffer = getFloatBufferFromFloatArray(white_specular);
 
	static void Material_Flat_White(GL10 gl)
    {
    	gl.glMaterialfv(GL10.GL_FRONT, GL10.GL_AMBIENT, white_ambientbuffer);
    	gl.glMaterialfv(GL10.GL_FRONT, GL10.GL_DIFFUSE, white_diffusebuffer);
    	gl.glMaterialfv(GL10.GL_FRONT, GL10.GL_SPECULAR, white_specularbuffer);
    	gl.glMaterialx(GL10.GL_FRONT, GL10.GL_SHININESS, 16);
		gl.glColor4f(1.0f,0.0f, 0.0f, 1.0f);
    }

	
	static FloatBuffer getFloatBufferFromFloatArray(float array[]) {
		ByteBuffer tempBuffer = ByteBuffer.allocateDirect(array.length * 4);
		tempBuffer.order(ByteOrder.nativeOrder());
		FloatBuffer buffer = tempBuffer.asFloatBuffer();
		buffer.put(array);
		buffer.position(0);
		return buffer;
	}
	static ByteBuffer getByteBufferFromByteArray( byte array[]) {
		ByteBuffer buffer = ByteBuffer.allocateDirect(array.length);
		buffer.put(array);
		buffer.position(0);
		return buffer;
	}


	
}
