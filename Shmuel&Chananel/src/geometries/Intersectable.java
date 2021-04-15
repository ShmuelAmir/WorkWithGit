package geometries;

import java.util.List;

import primitives.*;

/**
 * Intersectable interface represents geometric that can have intersection with
 * a ray.
 * 
 * @author shmulik
 */
public interface Intersectable {
	/**
	 * find all intersection points between ray and shape.
	 * 
	 * @param ray - the ray you want its points of intersection with the shape.
	 * @return list of intersection points
	 */
	List<Point3D> findIntersections(Ray ray);
}
