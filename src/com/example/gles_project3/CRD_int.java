package com.example.gles_project3;

public class CRD_int {

	public int x;
	public int y;
	public int z;
	
	CRD_int(int _X, int _Y, int _Z){
		this.x = _X;
		this.y = _Y;
		this.z = _Z;
	}
	
	CRD_int(CRD_int _CRD){
		this.x = _CRD.x;
		this.y = _CRD.y;
		this.z = _CRD.z;		
	}
}