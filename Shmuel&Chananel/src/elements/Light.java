/**
 * 
 */
package elements;

import primitives.Color;

/**
 *  light abstract class represents a light  with intensity
 * @author Shmulik & Chananel
 *
 */
abstract class Light {
	// intensity - the intensity of the light
	protected Color intensity;

	// constructor to Light
	protected Light(Color intensity) {
		this.intensity = intensity;
	}

	/**
	 * get the intensity of the light
	 * @return the intensity
	 */
	public Color getIntensity() {
		return intensity;
	}

}
