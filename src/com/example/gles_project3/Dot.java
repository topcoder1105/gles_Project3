package com.example.gles_project3;

public class Dot {
	public double x, y, z;

	Dot(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public Dot normalize() {
		double l = length();
		this.x /= l;
		this.y /= l;
		this.z /= l;
		return this;
	}

	public double length() {
		return MathUtil.calculate_distance(this, new Dot(0,0,0));
	}
	
	public Dot rotate(double theta, Dot normalVector) {
		normalVector.normalize();

		double wx = normalVector.x;
		double wy = normalVector.y;
		double wz = normalVector.z;
		double px = this.x;
		double py = this.y;
		double pz = this.z;

		this.x = ((Math.cos(theta) - (Math.cos(theta) - 1) * wx * wx) * px
				+ ((1 - Math.cos(theta)) * wx * wy - Math.sin(theta) * wz) * py + (Math
				.sin(theta) * wy - (Math.cos(theta) - 1) * wx * wz)
				* pz);
		this.y = (((1 - Math.cos(theta)) * wx * wy + Math.sin(theta) * wz) * px
				+ (Math.cos(theta) - (Math.cos(theta) - 1) * wy * wy) * py + (-Math
				.sin(theta) * wx - (Math.cos(theta) - 1) * wy * wz)
				* pz);
		this.z = ((-Math.sin(theta) * wy - (Math.cos(theta) - 1) * wx * wz)
				* px + (Math.sin(theta) * wx - (Math.cos(theta) - 1) * wy * wz)
				* py + (Math.cos(theta) - (Math.cos(theta) - 1) * wz * wz) * pz);

		return this;
	}

	public Dot rotate(double theta, double x, double y, double z) {
		return rotate(theta, new Dot(x, y, z));
	}

	public double angle(Dot pt) {
		double radian = Math.acos(inner_product(pt) / (this.length() * pt.length()));
		return MathUtil.radianToDegree(radian);
	}

	public double inner_product(Dot pt) {
		return MathUtil.inner_product(this, pt);
	}
}
