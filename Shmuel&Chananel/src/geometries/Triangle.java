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
	public List<GeoPoint> findGeoIntersections(Ray ray)
	{
		List<GeoPoint> result = plane.findGeoIntersections(ray);
		if (result == null)
			return null;
		
		Vector v1 = vertices.get(0).subtract(ray.getP0());
		Vector v2 = vertices.get(1).subtract(ray.getP0());
		Vector v3 = vertices.get(2).subtract(ray.getP0());
		
		Vector n1 = v1.crossProduct(v2).normalize();
		Vector n2 = v2.crossProduct(v3).normalize();
		Vector n3 = v3.crossProduct(v1).normalize();
		
		double vDotN1 = alignZero(ray.getDir().dotProduct(n1));
		double vDotN2 = alignZero(ray.getDir().dotProduct(n2));
		double vDotN3 = alignZero(ray.getDir().dotProduct(n3));
		
		if (checkSign(vDotN1, vDotN2) && checkSign(vDotN1, vDotN3)) {
			result.get(0).geometry = this;
			return result;
		}

		return null;
	}
	
	@Override
	public String toString() {
		return this.vertices.get(0).toString() + " " + this.vertices.get(1).toString() + " "
				+ this.vertices.get(2).toString() + " " + this.plane.toString();
	}
}
