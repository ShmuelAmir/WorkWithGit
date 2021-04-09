package geometries;

import java.util.List;

import static primitives.Util.*;
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
		Point3D p0 = axisRay.getP0();
		Vector dir = axisRay.getDir();
		
		if (point.equals(p0)) {
			return dir.scale(-1);
		}
		
		Vector tsetVector = point.subtract(p0);
		// The point is in the base where the begging of the ray
		if (isZero(tsetVector.dotProduct(dir))) {
			return dir.scale(-1);
		}

		if (isZero(tsetVector.dotProduct(dir) - height)) {
			return dir;
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
