package com.example.gles_project3;

import javax.microedition.khronos.opengles.GL10;

public class Object {

	UnitObject block[][][];
	

	float posX = -2.0f;
	float posY = -2.0f;
	float posZ = -4.0f;
	float sizeX;
	float sizeY;
	float sizeZ;
	
	Color red = new Color(100,0,0,255);
	Color green = new Color(0,100,0,255);
	Color blue = new Color(0,0,100,255);
	Color yellow = new Color(100,100,0,255);
	Color skyblue = new Color(0,100,100,255);
	Color purple = new Color(100,0,100,255);

	
	Object(int _PosX, int _PosY, int _PosZ, int _SizeX, int _SizeY, int _SizeZ, int _Color)
	{
		block = new UnitObject[_SizeX][_SizeY][_SizeZ];		
	
		sizeX = _SizeX;
		sizeY = _SizeY;
		sizeZ = _SizeZ;
	
		Color color = new Color(0,0,0,255);
		switch ( _Color )
		{
			case 0:
				color = new Color(red);
				break;
			case 1:
				color = new Color(green);
				break;
			case 2:
				color = new Color(blue);
				break;
			case 3:
				color = new Color(yellow);
				break;
			case 4:
				color = new Color(skyblue);
				break;
			case 5:
				color = new Color(purple);		
		}
		
		for ( int i = 0 ; i < sizeX; i++){
			for ( int j = 0 ; j < sizeY; j++){
				for ( int k = 0 ; k < sizeZ; k++){
					block[i][j][k] = new UnitObject(color);
				}
			}
		}
		
		posX += (float) (_PosX)*UnitObject.unit_size;
		posY += (float) (_PosY)*UnitObject.unit_size;
		posZ += (float) (_PosZ)*UnitObject.unit_height;
	
	}
	
	
	public void draw(GL10 gl)
	{
		for ( int i = 0 ; i < sizeX; i++){
			for ( int j = 0 ; j < sizeY; j++){
				for ( int k = 0 ; k < sizeZ; k++){
					gl.glPushMatrix();
					gl.glTranslatef(posX, posY, posZ);
					gl.glTranslatef(i*UnitObject.unit_size, j*UnitObject.unit_size, k*UnitObject.unit_height);
					block[i][j][k].draw(gl);
					gl.glPopMatrix();
				}
			}
		}
	}
}
