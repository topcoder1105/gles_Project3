package com.example.gles_project3;

public class ObjectData {

	public Coord center;
	public Coord size;
	public Coord pos;
	public Color color;
	
	ObjectData(Coord _Center, Coord _Size, Coord _Pos, Color _Color)
	{
		this.center = new Coord(_Center);
		this.size = new Coord(_Size);
		this.pos = new Coord(_Pos);
		this.color = new Color(_Color);
	}
}
