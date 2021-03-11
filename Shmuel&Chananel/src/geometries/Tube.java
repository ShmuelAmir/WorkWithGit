package geometries;

import primitives.*;

/**
 * Tube class represents Tube in the geometric space (a long, hollow cylinder)
 * 
 * @author shmulik
 */
public class Tube implements Geometry {
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
		double t = axisRay.getDir().dotProduct(point.subtract(axisRay.getP0()));
		Vector v = axisRay.getDir().scale(t);
		Point3D center = axisRay.getP0().add(v);
		
		return point.subtract(center).normalize();
	}

	@Override
	public String toString() {
		return axisRay.toString() + " " + radius;
	}
}
