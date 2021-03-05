package geometries;

import primitives.Ray;

/**
 * Class Cylinder represents a cylinder (Tube with height)
 * 
 * @author shmulik
 */
public class Cylinder extends Tube {
	private double height;
	
	/**
	 * constructor with ray, radius and height
	 * 
	 * @param ray
	 * @param radius
	 * @param height
	 */
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
