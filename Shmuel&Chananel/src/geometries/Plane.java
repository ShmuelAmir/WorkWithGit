package geometries;

import primitives.*;

public class Plane implements Geometry{
	private Point3D p0;
	private Vector normal;
	
	public Plane(Point3D p1, Point3D p2, Point3D p3) {
		normal = null;
		p0 = p1;
	}
	
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
