package com.example.gles_project3;

import javax.microedition.khronos.opengles.GL10;

public class UnitBlock extends Object {

	UnitBlock()
	{	
		
	}
	
	
	@Override
	void draw(GL10 gl) {
	
		drawObject(gl);
	}
	
	void Cube()
	{
		this.vertices = new float[3*8];
				
		for ( int i = 0 ; i < 8 ; i++ )
		{
			
			
			
		}
		
		mVertexBuffer = getFloatBufferFromFloatArray(vertices);
		
		this.colors = new float[3*8];
		for ( int i = 0 ; i < 3*8 ; i++ )
		{
			colors[i] = 1.0f;
		}
		
		mColorBuffer = getFloatBufferFromFloatArray(colors);
		
	}

}
