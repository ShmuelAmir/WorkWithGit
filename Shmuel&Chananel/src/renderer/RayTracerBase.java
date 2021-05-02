/**
 * 
 */
package renderer;

import primitives.Color;
import primitives.Ray;
import scene.*;

/**
 * RayTracerBase class is a abstract class that have tow methods which help determine 
 * the color of the image in a particular pixel
 * 
 * @author shmulik
 *
 */
public abstract class RayTracerBase {
	protected Scene scene;
	
	/**
	 * initialize scene
	 * @param scene - get scene to initialize this scene
	 */
	public RayTracerBase(Scene scene) {
		this.scene = scene;
	}

	/**
	 * get ray and determine the color
	 * @param ray
	 * @return - the color in this point
	 */
	public abstract Color traceRay(Ray ray);
}
