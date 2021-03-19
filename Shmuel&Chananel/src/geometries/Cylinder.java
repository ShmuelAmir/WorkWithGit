package geometries;

import java.util.List;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

/**
 * Class Cylinder represents a cylinder (Tube with height)
 * 
 * @author shmulik
 */
public class Cylinder extends Tube {
	private double height; // save the height of the cylinder

	/**
	 * constructor with ray, radius and height
	 * 
	 * @param ray
	 * @param radius
	 * @param height
	 */
	public Cylinder(Ray ray, double radius, double height) {
		super(ray, radius);
		this.height = height;
	}

	/**
	 * @return the height
	 */
	public double getHeight() {
		return height;
	}

	@Override
	public Vector getNormal(Point3D point) {
		if (point.equals(axisRay.getP0())) {
			return axisRay.getDir().scale(-1);
		}
		
		Vector tsetVector = point.subtract(axisRay.getP0());
		// The point is in the base where the begging of the ray
		if (tsetVector.dotProduct(axisRay.getDir()) == 0) {
			return axisRay.getDir().scale(-1);
		}

//		if (tsetVector.dotProduct(axisRay.getDir()) == height) {
//			return axisRay.getDir();
//		}
		
		Vector helpVector = axisRay.getDir().scale(height);
		Point3D helpPoint = axisRay.getP0().add(helpVector); // to find point on the second base
		if (point.equals(helpPoint)) {
			return axisRay.getDir();
		}
		
		tsetVector = point.subtract(helpPoint);
		// The point is in the other base
		if (tsetVector.dotProduct(axisRay.getDir()) == 0) {
			return axisRay.getDir();
		}

		// The point is in the casing
		return super.getNormal(point);
	}

	@Override
	public List<Point3D> findIntersections(Ray ray)
	{
		return null;
	}
	
	@Override
	public String toString() {
		return super.toString() + " " + height;
	}
}
