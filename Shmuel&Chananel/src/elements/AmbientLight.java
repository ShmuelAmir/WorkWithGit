package elements;

import primitives.*;

/**
 * AmbientLight class represents the ambient light that affects all the scene
 * 
 * @author Shmulik & Chananel
 */
public class AmbientLight {
	// save the intensity of this light 
	private Color intensity;

	/**
	 * this method calculate the intensity of ambient light
	 * @param intensity
	 */
	public AmbientLight(Color intensity, double kA) {
		this.intensity = intensity.scale(kA);
	}

	/**
	 * 
	 *  get the value of the intensity
	 * @return the intensity
	 */
	public Color getIntensity() {
		return intensity;
	}
}
