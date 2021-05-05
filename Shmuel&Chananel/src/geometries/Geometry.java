package geometries;

import primitives.*;

/**
 * Geometry interface represents Some geometric body
 * 
 * @author shmulik
 */
public abstract class Geometry implements Intersectable {
	protected Color emission = Color.BLACK;
	private Material material = new Material();
	
	/**
	 * @param material the material to set
	 */
	public Geometry setMaterial(Material material) {
		this.material = material;
		return this;
	}

	/**
	 * @return the material
	 */
	public Material getMaterial() {
		return material;
	}

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
