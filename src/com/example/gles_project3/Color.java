package com.example.gles_project3;

public class Color {
	float r;
	float g;
	float b;
	float a;
	
	Color(int _R, int _G, int _B, int _A)
	{	
		r = _R;
		g = _G;
		b = _B;
		a = _A;
	}
	
	Color(Color _CLR)
	{
		r = _CLR.r;
		g = _CLR.g;
		b = _CLR.b;
		a = _CLR.a;
	}	
}
