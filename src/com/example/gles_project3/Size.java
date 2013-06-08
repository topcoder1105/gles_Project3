package com.example.gles_project3;

public class Size {

	private int x;
	private int y;
	private int z;
	
	Size(int _X, int _Y, int _Z)
	{
		this.x = _X;
		this.y = _Y;
		this.z = _Z;
	}
	
	Size(Size _SIZE)
	{
		this.x = _SIZE.x;
		this.y = _SIZE.y;
		this.z = _SIZE.z;
	}
	
	public int getX(){	return x;	}
	public int getY(){	return y;	}
	public int getZ(){	return z;	}
		
	public Size getCoord()
	{
		return new Size(this);
	}

}
