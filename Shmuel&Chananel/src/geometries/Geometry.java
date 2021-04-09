package geometries;

import primitives.*;

/**
 * Geometry interface represents Some geometric body
 * 
 * @author shmulik
 */
public interface Geometry extends Intersectable {
	/**
	 * calculate the normal vector to the body at specific point.
	 * 
	 * @param point - A point on the geometric shape (the method don't check it)
	 * @return The normal vector
	 */
	public Vector getNormal(Point3D point);
}
