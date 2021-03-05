package geometries;

import primitives.*;

/**
 * Plane class represents plain in the geometric space
 * 
 * @author shmulik
 *
 */
public class Plane implements Geometry {
	private Point3D p0; // point in the plane
	private Vector normal; // vector normal to the plane

	/**
	 * Constructor that calculate the normal and build a plain object
	 * 
	 * @param p1 - first point
	 * @param p2 - second point
	 * @param p3 - third point
	 */
	public Plane(Point3D p1, Point3D p2, Point3D p3) {
		p0 = p1;
		normal = null;
	}

	/**
	 * Constructor that build a plain object
	 * 
	 * @param point - point on the plane
	 * @param vector - The normal vector
	 */
	public Plane(Point3D point, Vector vector) {
		p0 = point;
		normal = vector;
	}

	/**
	 * @return the p0
	 */
	public Point3D getP0() {
		return p0;
	}

	/**
	 * @return the normal
	 */
	public Vector getNormal() {
		return normal;
	}

	@Override
	public Vector getNormal(Point3D point) {
		return null;
	}

	@Override
	public String toString() {
		return p0.toString() + " " + normal.toString();
	}
}
