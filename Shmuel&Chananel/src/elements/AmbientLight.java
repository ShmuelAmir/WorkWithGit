/**
 * 
 */
package elements;

import primitives.*;

/**
 * @author Shmulik & Chananel
 *
 */
public class AmbientLight {
	
	private Color intensity ;
	
	/**
	 * @param intensity
	 */
	public AmbientLight(Color intensity , double kA) {
		
		this.intensity = intensity.scale(kA);
	}

	
	/**
	 * @return the intensity
	 */
	public Color getIntensity() {
		return intensity;
	}

	
	

}
