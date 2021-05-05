/**
 * 
 */
package elements;

import primitives.*;

/**
 * @author user1
 *
 */
public interface LightSource {
	public Color getIntensity(Point3D p);
	public Vector getL(Point3D p);


}
