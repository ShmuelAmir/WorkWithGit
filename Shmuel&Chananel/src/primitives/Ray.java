package primitives;

import java.util.List;
import java.util.stream.Collectors;

import geometries.Intersectable.GeoPoint;

/**
 * Ray - any of a set of straight lines passing through one point
 * 
 * @author shmulik
 */
public class Ray {
	private Point3D p0; // the starting point
	private Vector dir; // the direction vector from the Starting point

	/**
	 * Constructor that get point and vector and create ray
	 * 
	 * @param point  - starting point
	 * @param vector - direction vector
	 */
	public Ray(Point3D point, Vector vector) {
		p0 = point;
		dir = vector.normalized();
	}

	/**
	 * @return the p0
	 */
	public Point3D getP0() {
		return p0;
	}

	/**
	 * @return the dir
	 */
	public Vector getDir() {
		return dir;
	}

	/**
	 * calculate point on the direction vector in distance t from p0
	 * 
	 * @param t - distance
	 * @return the point
	 */
	public Point3D getPoint(double t) {
		return p0.add(dir.scale(t));
	}

	/**
	 * find the closes point to p0 from a list
	 * 
	 * @param points - list of points
	 * @return - the closes point to p0 - Point3D
	 */
	public Point3D getClosestPoint(List<Point3D> points) {
		return (points == null) ? null
				: getClosestGeoPoint(
						points.stream().map(p -> new GeoPoint(null, p)).collect(Collectors.toList())).point;
	}

	/**
	 * find the closes point to p0 from a list
	 * 
	 * @param points - list of GeoPoints
	 * @return - the closes point to p0 - GeoPoint
	 */
	public GeoPoint getClosestGeoPoint(List<GeoPoint> points) {
		if (points == null)
			return null;

		GeoPoint closesPoint = points.get(0);
		double minDistance = closesPoint.point.distance(p0);

		for (GeoPoint goePoint : points) {
			if (goePoint.point.distance(p0) < minDistance) {
				closesPoint = goePoint;
				minDistance = goePoint.point.distance(p0);
			}
		}

		return closesPoint;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Ray))
			return false;
		Ray other = (Ray) obj;
		return this.p0.equals(other.p0) && this.dir.equals(other.dir);
	}

	@Override
	public String toString() {
		return p0.toString() + " " + dir.toString();
	}
}
