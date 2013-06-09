package com.example.gles_project3;

import java.util.Vector;

import javax.microedition.khronos.opengles.GL10;

public class Lattice {
	// 각 격자들의 상태( 객체가 있음, 없음 등)을 저장하는 3차원 배열
	int lattice[][][] = new int[20][20][30];
	
	public static final int lat_empty = 101; // 격자에 아무것도 없을 때
	public static final int lat_exist = 102; // 격자에 객체가 존재할 때
	public static final int lat_width = 20;
	public static final int lat_height = 20;

	
	// 좌표의 기준이 되는 점(x,y,z 축의 가장 작은 값)
	public static final CRD_float OriginPoint = new CRD_float(0.0f,0.0f,0.0f);
	
	// 객체들을 저장하는 벡터
	Vector<Object> ObjectVector = new Vector<Object>();
	
	Lattice()
	{	// 격자의 모든 상태를 빈상태로 표현
		for(int i=0; i<Lattice.lat_width; i++)
			for(int j=0; j<Lattice.lat_height; j++)
				for(int k=0; k<30; k++)
					lattice[i][j][k] = Lattice.lat_empty;
	
		// 바닥면 만들고 해당 지역에 1로 체크
		ObjectVector.add(new Object(new CRD_int(0, 0, 0), new CRD_int(20,20, 1), new Color(Color.green) ));
		for(int i=0; i<Lattice.lat_width; i++)
			for(int j=0; j<Lattice.lat_height; j++)
				for(int k=0; k<1; k++)
					lattice[i][j][k] = Lattice.lat_exist;		
	}
		
	void addBlock(CRD_int _Pos, CRD_int _Size, Color _Color)
	{
		// 객체가 생성 가능 위치인지 확인하고 생성한다.
		boolean possible = true;
			
	    // 새로 놓을 블럭의 위치에 겹치는 물건이 있는지 확인
		for(int i=0; i<_Size.x; i++)
			for(int j=0; j <_Size.y; j++)
				for(int k=0; k<_Size.z; k++)
					if ( lattice[_Pos.x+i][_Pos.y+j][_Pos.z+k] == Lattice.lat_exist )
						possible = false;
		
		// 새로 놓을 블럭 밑에 무엇인가 지탱해줄 것이 있는지 확인
		boolean bottom = false;
		for(int i=0; i<_Size.x; i++)
			for(int j=0; j<_Size.y; j++)
				if ( lattice[_Pos.x+i][_Pos.y+j][_Pos.z-1] == Lattice.lat_exist ){
					bottom = true; // 밑에 무엇인가 지탱해줄 것이 있다면 true
					break;
				}
		
		possible = bottom;
		
		// 생성가능한 위치인 경우
		if ( possible ){
			// 객체를 만들고
			ObjectVector.add(new Object(new CRD_int(_Pos.x, _Pos.y, _Pos.z), new CRD_int(_Size.x, _Size.y, _Size.z), new Color(_Color)));
			// 만들어진 자리에 상태를 표시한다.
			for(int i=0; i<_Size.x; i++)
				for(int j=0; j<_Size.y; j++)
					for(int k=0; k<_Size.z; k++)
						lattice[_Pos.x+i][_Pos.y+j][_Pos.z+k] = Lattice.lat_exist;
		}
	}

	void draw(GL10 gl){
		for ( int i=0; i<ObjectVector.size(); i++){
			ObjectVector.get(i).draw(gl);
		}
	}
	
}
