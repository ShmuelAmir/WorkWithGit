/**
 * 
 */
package renderer;

import primitives.Color;
import primitives.Ray;
import scene.*;

/**
 * @author shmulik
 *
 */
public abstract class RayTracerBase {
	
	protected Scene scene;
	
	/**
	 * 
	 */
	public RayTracerBase(Scene scene) {
		this.scene = scene;
	}

	/**
	 * 
	 */
	public abstract Color traceRay(Ray ray);
}
