package geometries;

import java.util.LinkedList;
import java.util.List;

import elements.AxisAlignedBox;
import primitives.Ray;

/**
 * Geometries class represents a collection of geometric bodies
 * 
 * @author shmulik
 */
public class Geometries implements Intersectable {
	private List<Intersectable> geometriesList = new LinkedList<>();

	/**
	 * @return the geometriesList
	 */
	public List<Intersectable> getGeometriesList() {
		return geometriesList;
	}

	/**
	 * Default constructor that initializes an empty list.
	 */
	public Geometries() {
	}

	/**
	 * Constructor that initializes list with geometric shapes.
	 * 
	 * @param geometries - Some geometric shapes
	 */
	public Geometries(Intersectable... geometries) {
		add(geometries);
	}

	/**
	 * add some geometric shapes to the list.
	 * 
	 * @param geometries - Some geometric shapes
	 */
	public void add(Intersectable... geometries) {
		geometriesList.addAll(List.of(geometries));
	}

	@Override
	public List<GeoPoint> findGeoIntersections(Ray ray, double maxDistance) {
		List<GeoPoint> list = null;

		for (Intersectable intersectable : geometriesList) {
			var pointsOrNull = intersectable.findGeoIntersections(ray, maxDistance);
			if (pointsOrNull == null)
				continue;

			if (list == null)
				list = new LinkedList<>(pointsOrNull);
			else
				list.addAll(pointsOrNull);
		}

		return list;
	}

	// ���� ���� ���
//	@Override
//	public List<GeoPoint> findCbrGeoIntersections(Ray ray, double maxDistance) {
//		List<GeoPoint> list = null;
//
//		for (Intersectable intersectable : geometriesList) {
//
//			if (intersectable instanceof Geometries) {
//				AxisAlignedBox box = new AxisAlignedBox(getMinMax());
//				if (! box.checkIntersection(ray))
//					continue;
//			}
//
//			var pointsOrNull = intersectable.findCbrGeoIntersections(ray, maxDistance);
//			if (pointsOrNull == null)
//				continue;
//
//			if (list == null)
//				list = new LinkedList<>(pointsOrNull);
//			else
//				list.addAll(pointsOrNull);
//		}
//
//		return list;
//	}

	@Override
	public List<GeoPoint> findCbrGeoIntersections(Ray ray, double maxDistance) {
		List<GeoPoint> list = null;

		//��� ��� ������ ���� �����
//		AxisAlignedBox box = new AxisAlignedBox(getMinMax());
//		if (!box.checkIntersection(ray))
//			return null;
			
		for (Intersectable intersectable : geometriesList) {
//			if (!intersectable.checkCbrIntersection(ray))
//				continue;

//			var pointsOrNull = intersectable.findGeoIntersections(ray, maxDistance);
			var pointsOrNull = intersectable.findCbrGeoIntersections(ray, maxDistance);
			if (pointsOrNull == null)
				continue;

			if (list == null)
				list = new LinkedList<>(pointsOrNull);
			else
				list.addAll(pointsOrNull);
			
			
		}

		return list;
	}

	// ����� ���� �������
//	public boolean checkCbrIntersection(Ray ray) {
////		for (Intersectable intersectable : geometriesList) {
////			AxisAlignedBox box = new AxisAlignedBox(intersectable.getMinMax());
////
////			if (box.checkIntersection(ray))
////				return true;
////		}
////
////		return false;
//		for (Intersectable intersectable : geometriesList) {
//			if (intersectable.checkCbrIntersection(ray))
//				return true;
//		}
//		return false;
//	}

	@Override
	public double[] getMinMax() {
		double minX = Double.POSITIVE_INFINITY;
		double minY = Double.POSITIVE_INFINITY;
		double minZ = Double.POSITIVE_INFINITY;
		double maxX = Double.NEGATIVE_INFINITY;
		double maxY = Double.NEGATIVE_INFINITY;
		double maxZ = Double.NEGATIVE_INFINITY;

		for (Intersectable intersectable : geometriesList) {
//			if (intersectable instanceof Plane || intersectable instanceof Tube)
//				continue;
			
			double minMax[] = intersectable.getMinMax();
			double currentMinX = minMax[0];
			double currentMaxX = minMax[3];
			if (currentMaxX > maxX)
				maxX = currentMaxX;
			if (currentMinX < minX)
				minX = currentMinX;

			double currentMinY = minMax[1];
			double currentMaxY = minMax[4];
			if (currentMaxY > maxY)
				maxY = currentMaxY;
			if (currentMinY < minY)
				minY = currentMinY;

			double currentMinZ = minMax[2];
			double currentMaxZ = minMax[5];
			if (currentMaxZ > maxZ)
				maxZ = currentMaxZ;
			if (currentMinZ < minZ)
				minZ = currentMinZ;
			
//			if (minMax[3] > maxX)
//				maxX = minMax[3];
//			if (minMax[0] < minX)
//				minX = minMax[0];
//
//			if (minMax[4] > maxY)
//				maxY = minMax[4];
//			if (minMax[1] < minY)
//				minY = minMax[1];
//
//			if (minMax[5] > maxZ)
//				maxZ = minMax[5];
//			if (minMax[2] < minZ)
//				minZ = minMax[2];
		}

		double minMax[] = { minX, minY, minZ, maxX, maxY, maxZ };
		return minMax;
	}
}
