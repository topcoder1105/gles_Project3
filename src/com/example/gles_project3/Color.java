package com.example.gles_project3;

public class Color {

	public float r = 0.0f;
	public float g = 0.0f;
	public float b = 0.0f;
	public float a = 0.0f;
	
	Color(int _R, int _G, int _B, int _A)
	{	
		this.r = _R/255.0f;
		this.g = _G/255.0f;
		this.b = _B/255.0f;
		this.a = _A/255.0f;
	}
	
	Color(Color _CLR)
	{
		this.r = _CLR.r;
		this.g = _CLR.g;
		this.b = _CLR.b;
		this.a = _CLR.a;
	}	

	public static final Color red = new Color(100, 0, 0, 255);
	public static final Color green = new Color(0, 100, 0, 255);
	public static final Color blue = new Color(0, 0, 100, 255);
	public static final Color yellow = new Color(100, 100, 0, 255);
	public static final Color skyblue = new Color(0, 100, 100, 255);
	public static final Color purple = new Color(100, 0, 100, 255);	

	// 16진수 int를 ColorData 클래스로 변경 
		static Color intToColor(int i) {
			int b = (i)&0xFF;
			int g = (i>>8)&0xFF;
			int r = (i>>16)&0xFF;
			int a = (i>>24)&0xFF;
			
			return new Color(r,g,b,a);
		}
		
		// ColorData 클래스를 16진수 int로 변경
		static int colorToInt(Color c) {
			if (c == null) {
				c = new Color(255, 255, 255, 255);
			}
			int rgb = Math.round(c.a * 255);
			rgb = (rgb << 8) + Math.round(c.r * 255);
			rgb = (rgb << 8) + Math.round(c.g * 255);
			rgb = (rgb << 8) + Math.round(c.b * 255);
			
			return rgb;
		}
}
