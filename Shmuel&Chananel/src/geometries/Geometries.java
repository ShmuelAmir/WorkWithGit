package geometries;

import java.util.LinkedList;
import java.util.List;

import primitives.Ray;

/**
 * Geometries class represents a collection of geometric bodies
 * 
 * @author shmulik
 */
public class Geometries implements Intersectable {
	private List<Intersectable> geometriesList = new LinkedList<>();

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
}
