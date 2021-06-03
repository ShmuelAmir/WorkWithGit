package geometries;

import java.util.List;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;
import static primitives.Util.*;

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

	@Override
	public List<GeoPoint> findGeoIntersections(Ray ray, double maxDistance) {
		List<GeoPoint> result = plane.findGeoIntersections(ray, maxDistance);
		if (result == null)
			return null;

		Vector v = ray.getDir();
		Point3D p0 = ray.getP0();

		// this vector help us to find the intersections according to the formula that we learn in class
		Vector v1 = vertices.get(0).subtract(p0);
		Vector v2 = vertices.get(1).subtract(p0);
		Vector n1 = v1.crossProduct(v2).normalize();
		double vDotN1 = alignZero(v.dotProduct(n1));
		if (vDotN1 == 0)
			return null;

		Vector v3 = vertices.get(2).subtract(p0);
		Vector n2 = v2.crossProduct(v3).normalize();
		double vDotN2 = alignZero(v.dotProduct(n2));
		if (vDotN1 * vDotN2 <= 0)
			return null;

		Vector n3 = v3.crossProduct(v1).normalize();
		double vDotN3 = alignZero(v.dotProduct(n3));
		if (vDotN1 * vDotN3 <= 0)
			return null;

		result.get(0).geometry = this;
		return result;
	}

	@Override
	public String toString() {
		return this.vertices.get(0) + " " + this.vertices.get(1) + " "
				+ this.vertices.get(2) + " " + this.plane;
	}
	
	@Override
	boolean checkCbrIntersection(Ray ray) {
		Point3D min = new Point3D(minX, minY, minZ);
		Point3D max = new Point3D(maxX, maxY, maxZ);
		
		return checkRayCbrIntersection(min, max, ray);
		
	}
}
