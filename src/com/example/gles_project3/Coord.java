package com.example.gles_project3;

public class Coord {

	private float x;
	private float y;

	Coord(float _X, float _Y)
	{
		x = _X;
		y = _Y;
	}
	
	Coord(Coord _CRD)
	{
		x = _CRD.x;
		y = _CRD.y;
	}
	
	
	public void setCoord(float _X, float _Y)
	{
		x = _X;
		y = _Y;
	}
	
	public void move(float _dX, float _dY)
	{
		x += _dX;
		y += _dY;
	}
	
	public Coord getCoord()
	{
		return new Coord(x, y);
	}

}
