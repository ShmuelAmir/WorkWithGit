package geometries;

import java.util.List;
import static primitives.Util.*;
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
		double t = axisRay.getDir().dotProduct(point.subtract(axisRay.getP0()));	// t = v * (P - P0)
		
		if (isZero(t)) {	// P0 and the point are on the same plane 
			return point.subtract(axisRay.getP0()).normalize();
		}
		
		Point3D center = axisRay.getPoint(t);			// O = P0 + t*v
		
		return point.subtract(center).normalize();
	}

	@Override
	public List<Point3D> findIntersections(Ray ray)
	{
		return null;
	}
	
	@Override
	public String toString() {
		return axisRay.toString() + " " + radius;
	}
}
