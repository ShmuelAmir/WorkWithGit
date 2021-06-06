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
	 * ��� �� ����� �� ��� �� �����
	 * @param min
	 * @param max
	 * @param ray
	 * @return
	 */
	static boolean checkRayCbrIntersection( Point3D min, Point3D max, Ray ray) {
		Point3D center = ray.getP0();
		Vector dir = ray.getDir();
		if(dir.equals(Vector.X)) {
			if(center.getX() > max.getX() || center.getX() < min.getX())
				return false;
		}
		if(dir.equals(Vector.Y)) {
			if(center.getY() > max.getY() || center.getY() < min.getY())
				return false;
		}
		if(dir.equals(Vector.Z)) {
			if(center.getZ() > max.getZ() || center.getZ() < min.getZ())
				return false;
		}
		
		
		//now calculate the interval for x axis
		
		
		
		return true;	
	}
	
	/**
	 * ��� ���� ����� ����� �� ��� ��������
	 * ������ ��� ��� ������� ����� �������� ������
	 * @param ray
	 * @return
	 */
	boolean checkCbrIntersection(Ray ray); 
}
