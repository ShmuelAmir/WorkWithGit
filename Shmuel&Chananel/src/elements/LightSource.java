/**
 * 
 */
package elements;

import primitives.*;

/**
 * LightSource is interface that represents behavior of light - intensity and L (the direction)
 * 
 * @author Shmulik & Chananel
 */
public interface LightSource {
	/**
	 * get the intensity
	 * @param p - intensity in this point
	 */
	public Color getIntensity(Point3D p);

	/**
	 *  L vector - represents the direction of the light - to certain point
	 * @param p -certain point
	 * @return
	 */
	public Vector getL(Point3D p);

}
