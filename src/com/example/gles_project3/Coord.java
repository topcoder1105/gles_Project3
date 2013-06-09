package com.example.gles_project3;

public class Coord {

	public float x;
	public float y;
	public float z;
	
	Coord(float _X, float _Y, float _Z){
		this.x = _X;
		this.y = _Y;
		this.z = _Z;
	}
	
	Coord(Coord _CRD){
		this.x = _CRD.x;
		this.y = _CRD.y;
		this.z = _CRD.z;		
	}
}