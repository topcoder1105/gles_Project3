package com.example.gles_project3;

public class MathUtil {
	public static double degreeToRadian(double angle) {
		return angle * Math.PI / 180;
	}
	
	public static double radianToDegree(double theta) {
		return theta * 180 / Math.PI;
	}
	
	public static double calculate_distance(Dot p1, Dot p2) {
		return Math.sqrt(Math.pow(p1.x - p2.x, 2) + Math.pow(p1.y - p2.y, 2) + Math.pow(p1.z - p2.z, 2));
	}
	
	public static double inner_product(Dot p1, Dot p2) {
		return p1.x * p2.x + p1.y * p2.y + p1.z * p2.z;
	}
	
	public static double calculate_distance_with_line(Dot p1, Dot direct_vector, Dot one_pt) {
		double t = - (one_pt.x * p1.x + one_pt.y * p1.y + one_pt.z * p1.z) 
				/ (direct_vector.x * p1.x + direct_vector.y * p1.y + direct_vector.z * p1.z);
		Dot p2 = new Dot(t * direct_vector.x + one_pt.x, t * direct_vector.y + one_pt.y, t * direct_vector.z + one_pt.z);
		return calculate_distance(p1, p2);
	}
}
