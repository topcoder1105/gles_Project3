package com.example.gles_project3;

import javax.microedition.khronos.opengles.GL10;

public class Board {

	UnitBoard block[][][];
	
	float posX = -1.0f;
	float posY = -1.0f;
	float posZ = -3.0f;
	float sizeX;
	
	float sizeY;
	float sizeZ;
	
	Board(int _PosX, int _PosY, int _PosZ, int _SizeX, int _SizeY, int _SizeZ)
	{
		block = new UnitBoard[_SizeX][_SizeY][_SizeZ];		
	
		sizeX = _SizeX;
		sizeY = _SizeY;
		sizeZ = _SizeZ;

		for ( int i = 0 ; i < sizeX; i++){
			for ( int j = 0 ; j < sizeY; j++){
				for ( int k = 0 ; k < sizeZ; k++){
					block[i][j][k] = new UnitBoard(new Color(255,0,0,255));
				}
			}
		}
		
		posX += (float) (_PosX+1)*UnitBoard.unit_size;
		posY += (float) (_PosY+1)*UnitBoard.unit_size;
		posZ += (float) (_PosZ+1)*UnitBoard.unit_height;
	
	}
	
	
	public void draw(GL10 gl)
	{
		gl.glTranslatef(posX, posY, posZ);
		for ( int i = 0 ; i < sizeX; i++){
			for ( int j = 0 ; j < sizeY; j++){
				for ( int k = 0 ; k < sizeZ; k++){
					gl.glPushMatrix();
					gl.glTranslatef(i*UnitBoard.unit_size, j*UnitBoard.unit_size, k*UnitBoard.unit_height);
					block[i][j][k].draw(gl);
					gl.glPopMatrix();
				}
			}
		}
	}
}
