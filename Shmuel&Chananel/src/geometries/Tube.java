package geometries;

import java.util.List;

import static primitives.Util.*;
import primitives.*;

/**
 * Tube class represents Tube in the geometric space (a long, hollow cylinder)
 * 
 * @author shmulik
 */
public class Tube extends Geometry {
	protected Ray axisRay; // ray that defines the axis of the tube
	protected double radius; // radius of the ray

	/**
	 * Constructor that build tube from ray and radius.
	 * 
	 * @param ray
	 * @param radius
	 */
	public Tube(Ray ray, double radius) {
		this.axisRay = ray;
		this.radius = radius;
	}

	/**
	 * @return the axisRay
	 */
	public Ray getAxisRay() {
		return axisRay;
	}

	/**
	 * @return the radius
	 */
	public double getRadius() {
		return radius;
	}

	@Override
	public Vector getNormal(Point3D point) {
		Point3D p0 = axisRay.getP0();
		Vector dir = axisRay.getDir();

		double t = dir.dotProduct(point.subtract(p0)); // t = v * (P - P0)

		if (isZero(t)) // P0 and the point are on the same plane
			return point.subtract(p0).normalize();

		Point3D center = axisRay.getPoint(t); // O = P0 + t*v

		return point.subtract(center).normalize();
	}

	@Override
	public List<GeoPoint> findGeoIntersections(Ray ray) {
		double[] abc = findABC(ray);
		if (abc == null)
			return null;

		double a = abc[0];
		double b = abc[1];
		double c = abc[2];

		double[] rootArray = quadratic(a, b, c);
		if (rootArray == null)
			return null;

		if (rootArray.length == 2) {
			double t1 = rootArray[0];
			double t2 = rootArray[1];

			Point3D p1 = ray.getPoint(t1);
			Point3D p2 = ray.getPoint(t2);

			return List.of(new GeoPoint(this, p1), new GeoPoint(this, p2));
		}

		if (rootArray.length == 1) {
			double t = rootArray[0];

			Point3D p1 = ray.getPoint(t);

			return List.of(new GeoPoint(this, p1));
		}

		return null;
	}
	
	@Override
	public String toString() {
		return axisRay.toString() + " " + radius;
	}

	// ---------- help functions for find intersection

	/**
	 * Calculates the coefficients of the quadratic equation that need to be solved
	 * in order to find the points of intersection between a ray and a tube.
	 * 
	 * @param ray - the intersection ray
	 * @return - array of A, B and C
	 */
	private double[] findABC(Ray ray) {
		Vector vA = axisRay.getDir();
		Point3D pA = axisRay.getP0();
		Vector v = ray.getDir();
		Point3D p = ray.getP0();

		Vector xA, yA, xB, yB;
		double[] abc = new double[3];
		double rSqueredMinus = -radius * radius;

		double vDotVa = v.dotProduct(vA);
		if (isZero(vDotVa))
			yA = v;
		else {
			xA = vA.scale(vDotVa);
			if (v.equals(xA))
				return null;
			yA = v.subtract(xA);
		}
		abc[0] = yA.dotProduct(yA);

		if (p.equals(pA)) {
			abc[1] = 0;
			abc[2] = rSqueredMinus;
			return abc;
		}

		Vector deltaP = p.subtract(pA);
		double deltaPDotVa = deltaP.dotProduct(vA);
		if (isZero(deltaPDotVa))
			yB = deltaP;
		else {
			xB = vA.scale(deltaPDotVa);
			if (deltaP.equals(xB)) {
				abc[1] = 0;
				abc[2] = rSqueredMinus;
				return abc;
			}

			yB = deltaP.subtract(xB);
		}

		abc[1] = yA.dotProduct(yB) * 2;
		abc[2] = yB.dotProduct(yB) + rSqueredMinus;

		return abc;
	}

	/**
	 * solve a quadratic equation.
	 * 
	 * @param a - the first coefficient
	 * @param b - the second coefficient
	 * @param c - the third coefficient
	 * @return if 0 or 1 roots - null, otherwise array with roots bigger than 0.
	 */
	private double[] quadratic(double a, double b, double c) {
		double t1, t2;
		double[] rootArray = null;

		double d = (b * b) - (4 * a * c);
		if (d > 0) {
			t1 = (-b + Math.sqrt(d)) / (2.0 * a);
			t2 = (-b - Math.sqrt(d)) / (2.0 * a);
			if (t1 > 0 && t2 > 0) {
				rootArray = new double[2];
				rootArray[0] = t1;
				rootArray[1] = t2;
			} else if (t1 > 0) {
				rootArray = new double[1];
				rootArray[0] = t1;
			} else if (t2 > 0) {
				rootArray = new double[1];
				rootArray[0] = t2;
			}
		}

		return rootArray;
	}

}
