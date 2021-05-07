/**
 * 
 */
package elements;

import primitives.*;

/**
 * @author user1
 *
 */
public class DirectionalLight extends Light implements LightSource {

	private Vector direction;

	/**
	 * @param intensity
	 */
	public DirectionalLight(Color intensity, Vector direction) {
		super(intensity);
		this.direction = direction.normalize();
	}

	@Override
	public Color getIntensity(Point3D p) {
		return intensity;
	}

	@Override
	public Vector getL(Point3D p) {
		return direction.normalize(); ///  777
	}

}
