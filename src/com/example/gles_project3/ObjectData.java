package com.example.gles_project3;

public class ObjectData {

	public CRD_float center;
	public CRD_int size;
	public CRD_int pos;
	public Color color;
	
	ObjectData(CRD_float _Center, CRD_int _Pos, CRD_int _Size, Color _Color)
	{
		this.center = _Center;
		this.pos = _Pos;
		this.size = _Size;
		this.color = _Color;
	}
}
