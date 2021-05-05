package geometries;

import primitives.*;

/**
 * Geometry interface represents Some geometric body
 * 
 * @author shmulik
 */
public abstract class Geometry implements Intersectable {
	protected Color emission = Color.BLACK;
	
	/**
	 * @return the emission
	 */
	public Color getEmission() {
		return emission;
	}
	
	/**
	 * set emission
	 * 
	 * @param emission
	 * @return this
	 */
	public Geometry setEmission(Color emmission) {
		this.emission = emmission;
		return this;
	}
	
	/**
	 * calculate the normal vector to the body at specific point.
	 * 
	 * @param point - A point on the geometric shape (the method don't check it)
	 * @return The normal vector
	 */
	public abstract Vector getNormal(Point3D point);
}
