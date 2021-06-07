package geometries;

import java.util.List;
import java.util.stream.Collectors;

import primitives.*;

/**
 * Intersectable interface represents geometric that can have intersection with
 * a ray.
 * 
 * @author shmulik
 */
public interface Intersectable {

	/**
	 * class for save point and geometry together for the color in intersection
	 * point
	 * 
	 * @author shmulik
	 */
	public static class GeoPoint {
		/**
		 * the geometry
		 */
		public Geometry geometry;
		/**
		 * the point
		 */
		public Point3D point;

		/**
		 * constructor for the files
		 * 
		 * @param geometry - the geometry
		 * @param point    - the point
		 */
		public GeoPoint(Geometry geometry, Point3D point) {
			this.geometry = geometry;
			this.point = point;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (!(obj instanceof GeoPoint))
				return false;
			GeoPoint other = (GeoPoint) obj;
			return this.geometry.equals(other.geometry) && this.point.equals(other.point);
		}
	}

	/**
	 * find all intersection points between ray and shape.
	 * 
	 * @param ray - the ray you want its points of intersection with the shape.
	 * @return list of intersection points
	 */
	default List<Point3D> findIntersections(Ray ray) {
		var geoList = findGeoIntersections(ray);
		return geoList == null ? null : geoList.stream().map(gp -> gp.point).collect(Collectors.toList());
	}

	/**
	 * find all intersection points between ray and shape.
	 * 
	 * @param ray - the ray you want its points of intersection with the shape.
	 * @return list of intersection goePoints
	 */
	default List<GeoPoint> findGeoIntersections(Ray ray) {
		return findGeoIntersections(ray, Double.POSITIVE_INFINITY);
	}

	/**
	 * this method calculate all intersection points between ray and shape in a
	 * specific scope
	 * 
	 * @param ray         - the ray that we want to calculate his intersections
	 * @param maxDistance - the scope
	 * @return list of GeoPoint
	 */
	List<GeoPoint> findGeoIntersections(Ray ray, double maxDistance);

	/**
	 * האם יש חיתוך של קרן עם קופסא
	 * 
	 * @param min
	 * @param max
	 * @param ray
	 * @return
	 */
	static boolean checkRayCbrIntersection(Point3D min, Point3D max, Ray ray) {
		Point3D p0 = ray.getP0();
		Vector dir = ray.getDir();
		Point3D rD = dir.getHead();

		if (dir.equals(Vector.X)) {
			if (p0.getX() > max.getX() || p0.getX() < min.getX())
				return false;
		}
		if (dir.equals(Vector.Y)) {
			if (p0.getY() > max.getY() || p0.getY() < min.getY())
				return false;
		}
		if (dir.equals(Vector.Z)) {
			if (p0.getZ() > max.getZ() || p0.getZ() < min.getZ())
				return false;
		}

		// now calculate the interval for x axis
		double tx1 = (min.getX() - p0.getX()) / rD.getX();
		double tx2 = (max.getX() - p0.getX()) / rD.getX();
		if (tx1 > tx2) {
			double temp = tx1;
			tx1 = tx2;
			tx2 = temp;
		}

		double tStart = tx1;
		double tEnd = tx2;

		// now calculate the interval for y axis
		double ty1 = (min.getY() - p0.getY()) / rD.getY();
		double ty2 = (max.getY() - p0.getY()) / rD.getY();
		if (ty1 > ty2) {
			double temp = ty1;
			ty1 = ty2;
			ty2 = temp;
		}

		if (ty1 > tStart)
			tStart = ty1;
		if (ty2 < tEnd)
			tEnd = ty2;
		
		// now calculate the interval for z axis
		double tz1 = (min.getZ() - p0.getZ()) / rD.getZ();
		double tz2 = (max.getZ() - p0.getZ()) / rD.getZ();
		if (tz1 > tz2) {
			double temp = tz1;
			tz1 = tz2;
			tz2 = temp;
		}

		if (tz1 > tStart)
			tStart = tz1;
		if (tz2 < tEnd)
			tEnd = tz2;
		
		// box is missed
		if (tStart > tEnd)
			return false;
		
		// box is behind
//		if (tEnd < p0.getX() || tEnd < p0.getY() || tEnd < p0.getZ())
		if (tEnd < 0)
			return false;
		
		return true;
	}

	/**
	 * האם הקרן חותכת קופסא של גוף גיאומטרי במימוש בכל אחד מהגופים משתמש בפונקציה
	 * שלמעלה
	 * 
	 * @param ray
	 * @return
	 */
	boolean checkCbrIntersection(Ray ray);
}
