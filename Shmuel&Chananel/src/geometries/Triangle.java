package geometries;

import primitives.Point3D;

/**
 * Triangle class represents two-dimensional Triangle in 3D Cartesian coordinate
 * 
 * @author shmulik
 */
public class Triangle extends Polygon {
	/**
	 * constructor of Triangle with 3 Point3D
	 * 
	 * @param p1 - first point
	 * @param p2 - second point
	 * @param p3 - third point
	 */
	public Triangle(Point3D p1, Point3D p2, Point3D p3) {
		super(p1, p2, p3);
	}
}
