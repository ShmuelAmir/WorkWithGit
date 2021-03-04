package geometries;

import primitives.Ray;

public class Cylinder extends Tube {
	private double height;
	
	public Cylinder(Ray ray, double radius, double height) {
		super(ray, radius);
		this.height = height;
	}

	/**
	 * @return the height
	 */
	public double getHeight() {
		return height;
	}

	@Override
	public String toString() {
		return super.toString() + " " + height;
	}
}
