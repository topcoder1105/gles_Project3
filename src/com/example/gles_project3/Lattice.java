package com.example.gles_project3;

import java.util.Vector;

import javax.microedition.khronos.opengles.GL10;

public class Lattice implements OnTouchInterface{
	

	public static final int lat_empty = 101;
	public static final int lat_exist = 102;
	public static final int lat_width = 20;
	public static final int lat_height = 20;
	public static final int lat_depth = 30;
	
	static int lattice[][][] = new int[lat_width][lat_height][lat_depth];

	public static final CRD_float OriginPoint = new CRD_float(0.0f,0.0f,0.0f);
	
	
	static Vector<Object> ObjectVector = new Vector<Object>();
	static Object tmpObject;
	
	Lattice()
	{
		for(int i=0; i<Lattice.lat_width; i++)
			for(int j=0; j<Lattice.lat_height; j++)
				for(int k=0; k<30; k++)
					lattice[i][j][k] = Lattice.lat_empty;
	
		ObjectVector.add(new Object(new CRD_int(0, 0, 0), new CRD_int(20,20, 1), new Color(Color.green) ));
		for(int i=0; i<Lattice.lat_width; i++)
			for(int j=0; j<Lattice.lat_height; j++)
				for(int k=0; k<1; k++)
					lattice[i][j][k] = Lattice.lat_exist;
		
		TouchManager.getInstance().setOnTouchInterface(this);
	}
		
	void addBlock(CRD_int _Pos, CRD_int _Size, Color _Color)
	{
		boolean possible = true;
			
	    for(int i=0; i<_Size.x; i++)
			for(int j=0; j <_Size.y; j++)
				for(int k=0; k<_Size.z; k++){
					int x = (_Pos.x+i)%Lattice.lat_width;
					int y = (_Pos.y+j)%Lattice.lat_height;
					int z = (_Pos.z+k)%Lattice.lat_depth; 
						
					if ( lattice[x][y][z] == Lattice.lat_exist ){
						possible = false;
						return ;
					}
				}
		
	    boolean bottom = false;
		for(int i=0; i<_Size.x; i++)
			for(int j=0; j<_Size.y; j++){
				int x = (_Pos.x+i)%Lattice.lat_width;
				int y = (_Pos.y+j)%Lattice.lat_height;
				int z = (_Pos.z-1)%(Lattice.lat_depth-1); 
				
				if ( lattice[x][y][z] == Lattice.lat_exist ){
					bottom = true;
					break;
				}
			}
		
		possible = bottom;
		
		if ( possible ){
		
			ObjectVector.add(new Object(new CRD_int(_Pos.x, _Pos.y, _Pos.z), new CRD_int(_Size.x, _Size.y, _Size.z), new Color(_Color)));
			
			for(int i=0; i<_Size.x; i++)
				for(int j=0; j<_Size.y; j++)
					for(int k=0; k<_Size.z; k++)
					{
						int x = (_Pos.x+i)%Lattice.lat_width;
						int y = (_Pos.y+j)%Lattice.lat_height;
						int z = (_Pos.z+k)%Lattice.lat_depth; 
					
						lattice[x][y][z] = Lattice.lat_exist;
					}
		}
	}

	void draw(GL10 gl){
		for ( int i=0; i<ObjectVector.size(); i++){
			ObjectVector.get(i).draw(gl);
		}
		if ( tmpObject != null )
			tmpObject.draw(gl);
	}

	public static void Undo()
	{
		if ( ObjectVector.size() < 2 )
			return ;
		CRD_int _Pos = ObjectVector.get(ObjectVector.size()-1).objectData.pos;
		CRD_int _Size = ObjectVector.get(ObjectVector.size()-1).objectData.size;
		
		for(int i=0; i<_Size.x; i++)
			for(int j=0; j<_Size.y; j++)
				for(int k=0; k<_Size.z; k++)
				{
					int x = (_Pos.x+i)%Lattice.lat_width;
					int y = (_Pos.y+j)%Lattice.lat_height;
					int z = (_Pos.z+k)%Lattice.lat_depth; 
				
					lattice[x][y][z] = Lattice.lat_empty;
				}
				
		ObjectVector.remove(ObjectVector.size()-1);
	}
	
	
	
	
	@Override
	public void onInputTouchDown(Dot startPoint, Dot directVector) {
//		CRD_float SP = new CRD_float((float) startPoint.x, (float) startPoint.y,(float) startPoint.z);
//		CRD_float DV = new CRD_float((float) directVector.x,(float) directVector.y,(float) directVector.z); 
		tmpObject = null;
		float T = (float) (startPoint.z/directVector.z);	
		float BottomX = (float) (startPoint.x - directVector.x*T);
		float BottomY = (float) (startPoint.y - directVector.y*T);
		
		int LatticeX = (int) (BottomX/UnitObject.unit_size);
		int LatticeY = (int) (BottomY/UnitObject.unit_size);
		int LatticeZ = 1;
		
		if ( LatticeX > 19 ) LatticeX = 19;
		if ( LatticeX < 0 ) LatticeX = 0;
		if ( LatticeY > 19 ) LatticeY = 19;
		if ( LatticeY < 0 ) LatticeY = 0;
		
		while ( lattice[LatticeX][LatticeY][LatticeZ] != Lattice.lat_empty )
		{
			LatticeZ++;
			if ( LatticeZ == 30 )
			{	LatticeZ = 29;
				break;
			}
		}
		
		Color drawingColor = new Color(Object.drawingColor);
		drawingColor.a = 0.4f;
		drawingColor.r *= 2.55f;
		drawingColor.g *= 2.55f;
		drawingColor.b *= 2.55f;
		
		tmpObject = new Object(new CRD_int(LatticeX,LatticeY,LatticeZ), new CRD_int(Object.drawingSize), new Color(drawingColor) );
		
	}
	
	
	@Override
	public void onInputTouchUp(Dot startPoint, Dot directVector) {
		this.addBlock(tmpObject.objectData.pos, tmpObject.objectData.size, Object.drawingColor);
		tmpObject = null;
	}

	@Override
	public void onInputTouchCancel(Dot startPoint, Dot directVector) {
		tmpObject = null;
	}

	@Override
	public void onDeleteTouchDown(Dot startPoint, Dot directVector) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onDeleteTouchUp(Dot startPoint, Dot directVector) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onDeleteTouchCancel(Dot startPoint, Dot directVector) {
		// TODO Auto-generated method stub
		
	}
}
