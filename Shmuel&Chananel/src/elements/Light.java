/**
 * 
 */
package elements;

import primitives.Color;

/**
 * @author user1
 *
 */
abstract class Light {
	protected Color intensity;

	protected Light(Color intensity) {
		this.intensity = intensity;
	}

	/**
	 * @return the intensity
	 */
	public Color getIntensity() {
		return intensity;
	}

}
