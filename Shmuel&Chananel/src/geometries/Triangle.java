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

		// this vector help us to find the intersections according to the formula that
		// we learn in class
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
		return this.vertices.get(0) + " " + this.vertices.get(1) + " " + this.vertices.get(2) + " " + this.plane;
	}

//	@Override
//	public double[] getMinMax() {
//		double minX = Double.POSITIVE_INFINITY;
//		double minY = Double.POSITIVE_INFINITY;
//		double minZ = Double.POSITIVE_INFINITY;
//		double maxX = Double.NEGATIVE_INFINITY;
//		double maxY = Double.NEGATIVE_INFINITY;
//		double maxZ = Double.NEGATIVE_INFINITY;
//
//		Point3D point1 = vertices.get(0);
//		double x1 = point1.getX();
//		double y1 = point1.getY();
//		double z1 = point1.getZ();
//
//		Point3D point2 = vertices.get(1);
//		double x2 = point2.getX();
//		double y2 = point2.getY();
//		double z2 = point2.getZ();
//
//		Point3D point3 = vertices.get(2);
//		double x3 = point3.getX();
//		double y3 = point3.getY();
//		double z3 = point3.getZ();
//
//		if (x1 > x2) {
//			if (x1> x3) {
//				maxX = x1;
//				if (x2 > x3) {
//					minX = x3;
//				} else {
//					minX = x2;
//				}
//			} else {
//				if (x2 > x3) {
//					maxX = x2;
//					minX = x3;
//				} else {
//					maxX = x3;
//					minX = x2;
//				}
//			}
//		} else if (x1 > x3) {
//			if (x2 > x3) {
//				maxX = x2;
//				minX = x3;
//			} else {
//				maxX = x3;
//				minX = x2;
//			}
//		} else {
//			minX = x1;
//			if (x2 > x3) {
//				maxX = x2;
//			} else {
//				maxX = x3;
//			}
//		}
//
//		if (y1 > y2) {
//			if (y1> y3) {
//				maxY = y1;
//				if (y2 > y3) {
//					minY = y3;
//				} else {
//					minY = y2;
//				}
//			} else {
//				if (y2 > y3) {
//					maxY = y2;
//					minY = y3;
//				} else {
//					maxY = y3;
//					minY = y2;
//				}
//			}
//		} else if (y1 > y3) {
//			if (y2 > y3) {
//				maxY = y2;
//				minY = y3;
//			} else {
//				maxY = y3;
//				minY = y2;
//			}
//		} else {
//			minY = y1;
//			if (y2 > y3) {
//				maxY = y2;
//			} else {
//				maxY = y3;
//			}
//		}
//		
//		if (z1 > z2) {
//			if (z1> z3) {
//				maxZ = z1;
//				if (z2 > z3) {
//					minZ = z3;
//				} else {
//					minZ = z2;
//				}
//			} else {
//				if (z2 > z3) {
//					maxZ = z2;
//					minZ = z3;
//				} else {
//					maxZ = z3;
//					minZ = z2;
//				}
//			}
//		} else if (z1 > z3) {
//			if (z2 > z3) {
//				maxZ = z2;
//				minZ = z3;
//			} else {
//				maxZ = z3;
//				minZ = z2;
//			}
//		} else {
//			minZ = z1;
//			if (z2 > z3) {
//				maxZ = z2;
//			} else {
//				maxZ = z3;
//			}
//		}
//		double minMax[] = { minX, minY, minZ, maxX, maxY, maxZ };
//		return minMax;
//	}
}
