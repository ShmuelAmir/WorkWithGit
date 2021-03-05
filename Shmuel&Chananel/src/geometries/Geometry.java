package geometries;
import primitives.*;

/**
 * @author shmulik
 *
 */
public interface Geometry {
	/**
	 * calculate the normal vector to the body at specific point
	 * @param point
	 * @return normal vector
	 */
	public Vector getNormal(Point3D point);
}
