/**
 * 
 */
package unittests;

import static org.junit.Assert.*;

import org.junit.Test;

import geometries.Geometries;
import geometries.Plane;
import geometries.Sphere;
import geometries.Triangle;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

/**
 * @author shmulik
 *
 */
public class GeometriesTests {

	/**
	 * Test method for {@link geometries.Geometries#findIntersections(primitives.Ray)}.
	 */
	@Test
	public void testFindIntersections() {
		Plane plane = new Plane(new Point3D(0, 0, 1), new Point3D(1, 0, 1), new Point3D(0, 1, 1));
		Triangle triangle = new Triangle(new Point3D(0, 0, 2), new Point3D(5, 0, 2), new Point3D(0, 5, 2));
		Sphere sphere = new Sphere(new Point3D(1, 0, 0), 1d);
		Geometries geometries = new Geometries(plane, triangle, sphere);
		Ray ray = new Ray(new Point3D(2, 1, 0), new Vector(0, 0, 1));
		
		// ============ Equivalence Partitions Tests ==============
		// TC01: Some shapes (but not all) are intersection
		assertEquals("Some shapes (but not all) are intersection", 2, geometries.findIntersections(ray).size());
		
		// =============== Boundary Values Tests ==================

		// TC10: Empty collection
		Geometries geometries1 = new Geometries();
		assertEquals("Empty collection", null, geometries1.findIntersections(ray));
		
		// TC11: No intersection shape
		ray = new Ray(new Point3D(2, 1, 5), new Vector(0, 0, 1));
		assertEquals("No intersection shape", null, geometries.findIntersections(ray));
		
		// TC12: Only one shape is intersection 
		ray = new Ray(new Point3D(20, 20, 0), new Vector(0, 0, 1));
		assertEquals("Only one shape is intersection", 1, geometries.findIntersections(ray).size());
		
		// TC13: All shapes are intersection
		ray = new Ray(new Point3D(0.5, 0.5, 0), new Vector(0, 0, 1));
		assertEquals("All shapes are intersection", 3, geometries.findIntersections(ray).size());
	}
}
