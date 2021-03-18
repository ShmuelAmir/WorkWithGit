package geometries;

import java.util.List;

import primitives.*;
import static primitives.Util.*;

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
		
		// Calculate two vectors of the plane (point subtraction) 
		// and the vector orthogonal to them (cross product).
		Vector v1 = p2.subtract(p1);
		Vector v2 = p3.subtract(p1);
		normal = v1.crossProduct(v2).normalize();
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
		return normal;
	}

	@Override
	public List<Point3D> findIntersections(Ray ray)
	{
		Point3D result = null;
		// Check if ray is contained within the plane
		double nv = normal.dotProduct(ray.getDir());
		if (isZero(nv))
			return null;
		double numerator = normal.dotProduct(p0.subtract(ray.getP0()));
		double t = alignZero(numerator / nv);
		if (t > 0)
		{
			result = ray.getP0().add(normal.scale(t));
			return List.of(result);
		}
		return null;
		
	}
	
	@Override
	public String toString() {
		return p0.toString() + " " + normal.toString();
	}
}
