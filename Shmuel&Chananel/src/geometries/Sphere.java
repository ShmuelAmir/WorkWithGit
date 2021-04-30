package geometries;

import java.util.List;

import static primitives.Util.*;
import primitives.*;

/**
 * Sphere class represents in ball 3D Cartesian coordinate
 * 
 * @author shmulik
 */
public class Sphere implements Geometry {
	private final Point3D center; // the center of the sphere
	private final double radius; // the radius of the sphere
	private final double rSquared;

	/**
	 * constructor that build sphere object
	 * 
	 * @param point  - center point
	 * @param radius - the radius
	 */
	public Sphere(Point3D point, double radius) {
		this.center = point;
		this.radius = radius;
		this.rSquared = radius * radius;
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
	public List<Point3D> findIntersections(Ray ray) {
		if (center.equals(ray.getP0()))
			return List.of(ray.getPoint(radius));

		Vector u = center.subtract(ray.getP0());
		double tm = ray.getDir().dotProduct(u); // tm is never greater than d
		// d^2 = u^2 - tm^2
		double dSquared = u.lengthSquared() - tm * tm;
		double thSquared = alignZero(rSquared - dSquared);

		if (thSquared <= 0)
			return null;

		double th = Math.sqrt(thSquared);
		double t1 = alignZero(tm + th);
		double t2 = alignZero(tm - th);

		if (t1 > 0)
			return (t2 > 0) ? List.of(ray.getPoint(t1), ray.getPoint(t2)) : List.of(ray.getPoint(t1));

		return null;
	}

	@Override
	public String toString() {
		return center.toString() + " " + radius;
	}
}
