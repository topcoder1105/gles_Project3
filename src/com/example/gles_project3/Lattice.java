package com.example.gles_project3;

import java.util.Vector;

import javax.microedition.khronos.opengles.GL10;

public class Lattice {

	static int lattice[][][] = new int[20][20][30];
	
	
	Vector<Board> BoardVector = new Vector<Board>();
		
	Lattice()
	{	
		for ( int i = 0 ; i < 20; i++ ){
			for ( int j = 0 ; j < 20; j++){
				for ( int k = 0 ; k < 30; k++){
					lattice[i][j][k] = 0;
				}
			}
		}
		
		// �ٴڸ� ����� �ش� ������ 1�� üũ
		BoardVector.add(new Board(0, 0, 0, 20, 20, 1));
		for(int i = 0 ; i < 20; i++){
			for(int j = 0 ; j < 20; j++){
				for(int k=0; k < 1; k++){
					lattice[i][j][k] = 1;
				}
			}
		}
	}
	
	
	void addBlock(int _PosX, int _PosY, int _PosZ, int _SizeX, int _SizeY, int _SizeZ)
	{
		boolean possible = true;
		
		
		// ���� ���� ���� ��ġ�� ��ġ�� ������ �ִ��� Ȯ��
		for(int i = 0 ; i < _SizeX; i++){
			for(int j = 0 ; j < _SizeY; j++){
				for(int k=0; k<_SizeZ; k++){
					if ( lattice[_PosX+i][_PosY+j][_PosZ+k] == 1 )
						possible = false;
				}
			}
		}

		// ���� ���� �� �ؿ� �����ΰ� �������� ���� �ִ��� Ȯ��
		boolean bottom = false;
		for(int i = 0 ; i < _SizeX; i++){
			for(int j = 0 ; j < _SizeY; j++){
				for(int k=0; k<_SizeZ; k++){
					if ( lattice[_PosX+i][_PosY+j][_PosZ+ k - 1] == 1 )
						bottom = true; // �ؿ� �����ΰ� �������� ���� �ִٸ� true
				}
			}
		} // �ؿ� �ƹ��͵� ������ false �̹Ƿ� �Ұ���
		if ( bottom )
			possible = false;
				
		if ( possible )
		{
			BoardVector.add(new Board(_PosX, _PosY, _PosZ, _SizeX, _SizeY, _SizeZ));
			for(int i = 0 ; i < _SizeX; i++){
				for(int j = 0 ; j < _SizeY; j++){
					for(int k=0; k<_SizeZ; k++){
						lattice[_PosX+i][_PosY+j][_PosZ+k] = 1;
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
