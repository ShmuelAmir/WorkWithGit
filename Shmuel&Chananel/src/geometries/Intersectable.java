/**
 * 
 */
package geometries;

import java.util.List;

import primitives.*;

/**
 * @author shmulik
 *
 */
public interface Intersectable {
	/**
	 * 
	 * @param ray
	 * @return
	 */
	List<Point3D> findIntersections(Ray ray);
}
