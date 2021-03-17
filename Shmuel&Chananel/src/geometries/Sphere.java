package geometries;

import java.util.List;

import primitives.*;

/**
 * Sphere class represents in ball 3D Cartesian coordinate
 * 
 * @author shmulik
 */
public class Sphere implements Geometry {
	private Point3D center; // the center of the sphere
	private double radius; // the radius of the sphere

	/**
	 * constructor that build sphere object
	 * 
	 * @param point - center point
	 * @param radius - the radius
	 */
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
		return point.subtract(center).normalize();
	}

	@Override
	public List<Point3D> findIntersections(Ray ray)
	{
		return null;
	}
	
	@Override
	public String toString() {
		return center.toString() + " " + radius;
	}
}
