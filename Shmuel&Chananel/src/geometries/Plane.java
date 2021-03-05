package geometries;

import primitives.*;

/**
 * 
 * Class Plane is the basic class representing a Plane
 */
public class Plane implements Geometry{
	// The components of a plane - point and vector at this point
	private Point3D p0;
	private Vector normal;
	
	/**
	 * constructor of plane with 3 Point3D 
	 * @param p1 - first point
	 * @param p2 - second point
	 * @param p3 - third point
	 */
	public Plane(Point3D p1, Point3D p2, Point3D p3) {
		normal = null;
		p0 = p1;
	}
	
	/**
	 * constructor with point and vector
	 * @param point
	 * @param vector
	 */
	public Plane(Point3D point, Vector vector) {
		normal = vector;
		p0 = point;
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
