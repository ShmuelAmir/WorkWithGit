package geometries;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

/**
 * Class Cylinder represents a cylinder (Tube with height)
 * 
 * @author shmulik
 */
public class Cylinder extends Tube {
	private double height;
	
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
	public String toString() {
		return super.toString() + " " + height;
	}

	@Override
	public Vector getNormal(Point3D point) {
		Vector tsetVector  = point.subtract(axisRay.getP0());
		
		if (tsetVector.dotProduct(axisRay.getDir()) == 0) { // this condition check if point is in the base where the begging of the ray
			
			return axisRay.getDir().scale(-1);
		}
		
		Vector helpVector = axisRay.getDir().scale(height);
		Point3D helpPoint = axisRay.getP0().add(helpVector);
		tsetVector = point.subtract(helpPoint);
        if (tsetVector.dotProduct(axisRay.getDir()) == 0) { // this condition check if point is in the other base 
        	return axisRay.getDir();
		}
        
         // The point is in the casing
        
        return super.getNormal(point);

	}
}
