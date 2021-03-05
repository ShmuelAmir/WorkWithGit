package geometries;

import primitives.*;

/**
 * 
 * Sphere class represents  in ball 3D Cartesian coordinate
 *
 */
public class Sphere implements Geometry {
	private Point3D center;
	private double radius;
	
	public Sphere(Point3D point, double radius) {
		this.center = point;
		this.radius = radius;
	}

	/**
	 * @return the center
	 */
	public Point3D getCenter() {
		return center;
	}

	/**
	 * @return the radius
	 */
	public double getRadius() {
		return radius;
	}

	@Override
	public Vector getNormal(Point3D point) {
		return null;
	}

	@Override
	public String toString() {
		return center.toString() + " " + radius;
	}
}
