/**
 * 
 */
package geometries;

import java.util.LinkedList;
import java.util.List;

import primitives.Point3D;
import primitives.Ray;

/**
 * @author shmulik
 *
 */
public class Geometries implements Intersectable {
	private List<Intersectable> geometriesList;
	
	/**
	 * 
	 */
	public Geometries() {
		geometriesList = new LinkedList<>();
	}
	
	public Geometries(Intersectable... geometries) {
		geometriesList = List.of(geometries);
	}

	public void add(Intersectable... geometries) {
		geometriesList.addAll(List.of(geometries));
	}
	
	@Override
	public List<Point3D> findIntersections(Ray ray) {
		List<Point3D> list = new LinkedList<>();
		for (Intersectable intersectable : geometriesList) {
			var pointsOrNull = intersectable.findIntersections(ray);
			if (pointsOrNull == null)
				continue;
			list.addAll(pointsOrNull);
		}
		
		if (list.size() == 0)
			return null;
		
		return list;
	}
}
