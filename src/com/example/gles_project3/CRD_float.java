package com.example.gles_project3;

public class CRD_float {

	public float x;
	public float y;
	public float z;
	
	CRD_float(float _X, float _Y, float _Z){
		this.x = _X;
		this.y = _Y;
		this.z = _Z;
	}
	
	CRD_float(CRD_float _CRD){
		this.x = _CRD.x;
		this.y = _CRD.y;
		this.z = _CRD.z;		
	}
}