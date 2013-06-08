package com.example.gles_project3;

import javax.microedition.khronos.opengles.GL10;

public class Block extends Object {

	public static final int CUBE = 0;
	
	
	
	Block(Size _Size, int _Type)
	{
		size = _Size;
		type = _Type;
		
		switch ( type )
		{
		case CUBE:
			Cube();
			break;
		}
		
		
	}
	
	
	@Override
	void draw(GL10 gl) {
		// TODO Auto-generated method stub
		drawObject(gl);
	}
	
	void Cube()
	{
		this.vertices = new float[3*8];
		
		for ( int i = 0 ; i < 8 ; i++ )
		{
			if( i%2 == 0 ){	vertices[i*3] = 0.1f * size.getX();	}
			else{	vertices[i*3] =	-0.1f * size.getX();	}
			if ( (i/2)%2 == 0 ){	vertices[i*3 + 1] = 0.1f * size.getY();	}
			else{	vertices[i*3 + 1] = -0.1f * size.getY();	}
			if ( (i/4) == 0 ){	vertices[i*3 + 2] = 0.1f * size.getZ(); }
			else{ vertices[i*3 + 2] = -0.1f * size.getZ(); }			
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
