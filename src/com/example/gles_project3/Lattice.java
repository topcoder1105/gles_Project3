package com.example.gles_project3;

import java.util.Vector;

import javax.microedition.khronos.opengles.GL10;

public class Lattice {

	static int lattice[][][] = new int[100][100][100];
	
	
	Vector<Board> BoardVector = new Vector<Board>();
		
	Lattice()
	{	
		for ( int i = 0 ; i < 100; i++ ){
			for ( int j = 0 ; j < 100; j++){
				for ( int k = 0 ; k < 100; k++){
					lattice[i][j][k] = 0;
				}
			}
		}
		
		
	}
	
	void addBlock(int _PosX, int _PosY, int _PosZ, int _SizeX, int _SizeY, int _SizeZ)
	{
		boolean possible = true;
		
		
		// 새로 놓을 블럭의 위치에 겹치는 물건이 있는지 확인
		for(int i = 0 ; i < _SizeX; i++){
			for(int j = 0 ; j < _SizeY; j++){
				for(int k=0; k<_SizeZ; k++){
					if ( lattice[_PosX+_SizeX][_PosY+_SizeY][_PosZ+_SizeZ] == 1 )
						possible = false;
				}
			}
		}

		// 새로 놓을 블럭 밑에 무엇인가 지탱해줄 것이 있는지 확인
		boolean bottom = false;
		for(int i = 0 ; i < _SizeX; i++){
			for(int j = 0 ; j < _SizeY; j++){
				for(int k=0; k<_SizeZ; k++){
					if ( lattice[_PosX+_SizeX][_PosY+_SizeY][_PosZ+_SizeZ - 1] == 1 )
						bottom = true; // 밑에 무엇인가 지탱해줄 것이 있다면 true
				}
			}
		} // 밑에 아무것도 없으면 false 이므로 불가능
		if ( bottom )
			possible = false;
				
		if ( possible )
		{
			BoardVector.add(new Board(_PosX, _PosY, _PosZ, _SizeX, _SizeY, _SizeZ));
			for(int i = 0 ; i < _SizeX; i++){
				for(int j = 0 ; j < _SizeY; j++){
					for(int k=0; k<_SizeZ; k++){
						lattice[_PosX+_SizeX][_PosY+_SizeY][_PosZ+_SizeZ] = 1;
					}
				}
			}
			
		}
	}
	
	void draw(GL10 gl){
		for ( int i = 0 ; i < BoardVector.size() ; i++)
		{
			BoardVector.get(i).draw(gl);
		}
	}
	

	
}
