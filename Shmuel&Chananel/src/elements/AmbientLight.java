package elements;

import primitives.*;

/**
 * AmbientLight class represents the ambient light that affects all the scene
 * 
 * @author Shmulik & Chananel
 */
public class AmbientLight extends Light  {

	/**
	 * this method calculate the intensity of ambient light
	 * @param intensity
	 */
	public AmbientLight(Color intensity, double kA) {
		super(intensity.scale(kA));
	}

}
