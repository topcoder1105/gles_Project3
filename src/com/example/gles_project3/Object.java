package com.example.gles_project3;

import javax.microedition.khronos.opengles.GL10;

public class Object {	
	
	UnitObject block[][][];
	ObjectData objectData;
	
	public static CRD_int drawingSize = new CRD_int(2,3,4);
	public static Color drawingColor = new Color(Color.red);
	
	
	Color red = new Color(100,0,0,255);
	Color green = new Color(0,100,0,255);
	Color blue = new Color(0,0,100,255);
	Color yellow = new Color(100,100,0,255);
	Color skyblue = new Color(0,100,100,255);
	Color purple = new Color(100,0,100,255);
	
	
	Object(CRD_int _Pos, CRD_int _Size, Color _Color)
	{
		float x = (2*_Pos.x + _Size.x)*UnitObject.half_unit_size;
		float y = (2*_Pos.y + _Size.y)*UnitObject.half_unit_size;
		float z = (2*_Pos.z + _Size.z)*UnitObject.half_unit_height;
			
		this.objectData = new ObjectData(new CRD_float(x,y,z) , new CRD_int(_Pos), new CRD_int(_Size), new Color(_Color));
	
		block = new UnitObject[this.objectData.size.x][this.objectData.size.y][this.objectData.size.z];		
		
		for(int i=0; i<_Size.x; i++)
			for(int j=0; j<_Size.y; j++)
				for(int k=0; k<_Size.z; k++)
					block[i][j][k] = new UnitObject(new Color(_Color));
	}
	
	
	

	public void draw(GL10 gl){
		for(int i=0; i<this.objectData.size.x; i++)
			for(int j=0; j<this.objectData.size.y; j++)
				for(int k=0 ; k<this.objectData.size.z; k++){
					gl.glPushMatrix();
					gl.glTranslatef((this.objectData.pos.x + i)*UnitObject.unit_size, (this.objectData.pos.y + j)*UnitObject.unit_size, (this.objectData.pos.z + k)*UnitObject.unit_height);
					block[i][j][k].draw(gl);
					gl.glPopMatrix();
				}
	}
}
