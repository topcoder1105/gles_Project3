package com.example.gles_project3;

import javax.microedition.khronos.opengles.GL10;

public class UnitBlock extends Object {
	
	private float verticesUnit[] = { 0.1f, 0.1f, -0.1f, -0.1f, 0.1f, -0.1f,   
									 -0.1f, -0.1f, -0.1f, 0.1f, -0.1f, -0.1f,
									 0.1f, 0.1f, 0.1f, -0.1f, 0.1f, 0.1f,   
									 -0.1f, -0.1f, 0.1f, 0.1f, -0.1f, 0.1f };

	
	private byte indices[] = {
			0, 1, 2, 2, 3, 0, // back face
			4, 5, 6, 6, 7, 4, // front face
			0, 3, 7, 7, 4, 0, // left face
			1, 2, 6, 6, 5, 1, // right face
			0, 4, 5, 5, 1, 0, // bottom face
			3, 7, 6, 6, 2, 3, // up face	 	
	};
		
	@Override
	void draw(GL10 gl) {
	//	drawObject(gl);
		gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
        gl.glVertexPointer(3, GL10.GL_FLOAT, 0, mVertexBuffer);

        gl.glEnableClientState(GL10.GL_COLOR_ARRAY);
        gl.glColorPointer(4, GL10.GL_FLOAT, 0, mColorBuffer);
        
        gl.glDrawElements(GL10.GL_TRIANGLES, 36, GL10.GL_UNSIGNED_BYTE, mIndexBuffer);
        		            
        gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
        gl.glDisableClientState(GL10.GL_COLOR_ARRAY);

	}
		
	UnitBlock(){
		mVertexBuffer = getFloatBufferFromFloatArray(verticesUnit);
		mIndexBuffer = getByteBufferFromByteArray(indices);
			
	
		this.colors = new float[4*8];
		for ( int i = 0 ; i < 3*8 ; i++ )
		{
			colors[i] = 0.5f;
		}
		mColorBuffer = getFloatBufferFromFloatArray(colors);
	
	}
}
