package unittests;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import geometries.*;
import primitives.*;

/**
 * Testing Tube
 * 
 * @author user1
 *
 */
public class TubeTests {
	/**
	 * Test method for {@link geometries.Tube#getNormal(primitives.Point3D)}.
	 */
	@Test
	public void testGetNormal() {
		Ray r = new Ray(new Point3D(0, 0, 0), new Vector(0, 0, 1));
		Tube t = new Tube(r, 1);

		// ============ Equivalence Partitions Tests ==============
		// TC01: Test normal of tube
		assertEquals("Bad normal to tube", new Vector(1, 0, 0), t.getNormal(new Point3D(1, 0, 1)));

		// =============== Boundary Values Tests ==================
		// TC10: Test when P0 and the point are on the same plane
		assertEquals("Bad normal to tube", new Vector(1, 0, 0), t.getNormal(new Point3D(1, 0, 0)));
	}

	/**
	 * Test method for {@link geometries.Tube#findIntersections(primitives.Ray)}.
	 */
	@Test
	public void testFindIntersections() {
		Ray ray = new Ray(new Point3D(-0.8, 0, 0), new Vector(-1, 0, 0));
		Tube tube = new Tube(ray, 1);
		List<Point3D> result;
		// **** Group:(2 point)
		// ============ Equivalence Partitions Tests ==============
		// TC01: There is a simple single test here
		result = tube.findIntersections(new Ray(new Point3D(-5.5, 5.97, 0), new Vector(3.5, -6.97, 0)));
		assertEquals("Wrong number of points", 2, result.size());
		if (result.get(0).getX() > result.get(1).getX())
			result = List.of(result.get(1), result.get(0));
		assertEquals("Ray intersect the Tube", List.of(new Point3D(-3, 1, 0), new Point3D(-2, -1, 0)), result);

		// =============== Boundary Values Tests ==================
		// TC26: Ray orthogonal to the vector of the tube (2 points)
		result = tube.findIntersections(new Ray(new Point3D(-3, -3, 0.5), new Vector(0, 1, 0)));
		assertEquals("Wrong number of points", 2, result.size());
		if (result.get(0).getX() > result.get(1).getX())
			result = List.of(result.get(1), result.get(0));
		assertEquals("Ray intersect the Tube", List.of(new Point3D(-3, 0.87, 0.5), new Point3D(-3, -0.87, 0.5)),
				result);

		// TC27: Ray pass throw the center (2 points)
		result = tube.findIntersections(new Ray(new Point3D(-3, -3, 0), new Vector(0, 1, 0)));
		assertEquals("Wrong number of points", 2, result.size());
		if (result.get(0).getX() > result.get(1).getX())
			result = List.of(result.get(1), result.get(0));
		assertEquals("Ray intersect the Tube", List.of(new Point3D(-3, 1, 0), new Point3D(-3, -1, 0)), result);

		// TC28: Ray pass throw the starting point (2 points)
		result = tube.findIntersections(new Ray(new Point3D(-0.8, -3, 0), new Vector(0, 1, 0)));
		assertEquals("Wrong number of points", 2, result.size());
		if (result.get(0).getX() > result.get(1).getX())
			result = List.of(result.get(1), result.get(0));
		assertEquals("Ray intersect the Tube", List.of(new Point3D(-0.8, 1, 0), new Point3D(-0.8, -1, 0)), result);

		// TC29: Ray in the same plane of the starting point (2 points)
		result = tube.findIntersections(new Ray(new Point3D(-0.8, -3, 0.5), new Vector(0, 1, 0)));
		assertEquals("Wrong number of points", 2, result.size());
		if (result.get(0).getX() > result.get(1).getX())
			result = List.of(result.get(1), result.get(0));
		assertEquals("Ray intersect the Tube", List.of(new Point3D(-0.8, 0.87, 0.5), new Point3D(-0.8, -0.87, 0.5)),
				result);

		
		
		
		
		
		
		// **** Group:(1 point)
		// ============ Equivalence Partitions Tests =============

		// TC22:Ray's line is begin in the tube and no vertical to the tube ray
		result = tube.findIntersections(new Ray(new Point3D(-2, 0.5, 0), new Vector(-1, 1, 0)));
		assertEquals("Ray's line is begin in the tube and no vertical to the tube ray", List.of(new Point3D(-2.5, 1, 0)),
				result);

		// ============ Boundary Partitions Tests =============
		
		// TC23: Ray's line is begin in the tube and vertical to the tube ray
		result = tube.findIntersections(new Ray(new Point3D(-1, 1, 0), new Vector(0, 0, 1)));
		assertEquals("Ray's line is begin in the tube and vertical to the tube ray", List.of(new Point3D(0.5, 0.25, 1)), result);

		
		// **** Group: Ray's line pass throw the tube ray
		// TC24: Ray's line is begin in the tube ray and no vertical to the tube ray
		result = tube.findIntersections(new Ray(new Point3D(-1, 1, 0), new Vector(0, 0, 1)));
		assertEquals("Ray's line is begin in the tube ray and no vertical to the tube ray", List.of(new Point3D(0.5, 0.25, 1)), result);

		// TC25: Ray's line is begin in the tube and no vertical to the tube ray
		result = tube.findIntersections(new Ray(new Point3D(-1, 1, 0), new Vector(0, 0, 1)));
		assertEquals("Ray's line is begin in the tube and no vertical to the tube ray", List.of(new Point3D(0.5, 0.25, 1)), result);

		// TC26: Ray's line is begin before it
		result = tube.findIntersections(new Ray(new Point3D(-1, 1, 0), new Vector(0, 0, 1)));
		assertEquals("Ray's line pass throw the tube ray and begin before it", List.of(new Point3D(0.5, 0.25, 1)), result);

		// TC27: Ray's line is begin on the casing
		result = tube.findIntersections(new Ray(new Point3D(-1, 1, 0), new Vector(0, 0, 1)));
		assertEquals("Ray's line pass throw the tube ray and begin on the casing", List.of(new Point3D(0.5, 0.25, 1)), result);

		// TC28: Ray's line is begin after it
		result = tube.findIntersections(new Ray(new Point3D(-1, 1, 0), new Vector(0, 0, 1)));
		assertEquals("Ray's line pass throw the tube ray and begin after it", List.of(new Point3D(0.5, 0.25, 1)), result);

		
		// TC29: Ray's line is on the casing
		result = tube.findIntersections(new Ray(new Point3D(-1, 1, 0), new Vector(0, 0, 1)));
		assertEquals("Ray's line is on the casing", List.of(new Point3D(0.5, 0.25, 1)), result);

		
		// **** Group: Ray's line pass throw the starting point
		// TC30: Ray's line is begin before it
		result = tube.findIntersections(new Ray(new Point3D(-1, 1, 0), new Vector(0, 0, 1)));
		assertEquals("Ray's line pass throw the starting point and begin before it", List.of(new Point3D(0.5, 0.25, 1)), result);

		// TC31: Ray's line is begin in the starting point
		result = tube.findIntersections(new Ray(new Point3D(-1, 1, 0), new Vector(0, 0, 1)));
		assertEquals("Ray's line pass throw the starting point and begin before it", List.of(new Point3D(0.5, 0.25, 1)), result);

		// TC32: Ray's line is begin after it
		result = tube.findIntersections(new Ray(new Point3D(-1, 1, 0), new Vector(0, 0, 1)));
		assertEquals("Ray's line pass throw the starting point and begin after it", List.of(new Point3D(0.5, 0.25, 1)), result);
		
		// TC34: Ray's line is begin on the casing
		result = tube.findIntersections(new Ray(new Point3D(-1, 1, 0), new Vector(0, 0, 1)));
		assertEquals("Ray's line pass throw the starting point and begin on the casing", List.of(new Point3D(0.5, 0.25, 1)), result);

		
		// TC33: Ray's line pass throw the plane of the starting point
		result = tube.findIntersections(new Ray(new Point3D(-1, 1, 0), new Vector(0, 0, 1)));
		assertEquals("Ray's line pass throw the plane of the starting point", List.of(new Point3D(0.5, 0.25, 1)), result);



		


		
		
		
		
		// ============ Equivalence Partitions Tests =============
		// **** Group: Ray's line parallel to the tube (0 point)
		// TC04: Ray's line is Outside against down
		result = tube.findIntersections(new Ray(new Point3D(0, 0, -2), new Vector(-1, 0, 0)));
		assertNull("Ray's line is Outside against down", result);

		// **** Group:(0 point)
		// TC08: Ray's line is Outside standing down
		result = tube.findIntersections(new Ray(new Point3D(-2, 0, -2), new Vector(0, 1, 0)));
		assertNull("Ray's line is Outside against down", result);
		// TC09: Ray's line is Outside with no intersection
		result = tube.findIntersections(new Ray(new Point3D(-2, 3, 0), new Vector(1, 1, 1)));
		assertNull("Ray's line is Outside against down", result);

		// ============ Boundary Partitions Tests =============
		// TC10: Ray's line is inside parallel to tube ray
		result = tube.findIntersections(new Ray(new Point3D(-0.8, 0, 0), new Vector(-1, 0, 0)));
		assertNull("Ray's line is Outside against down", result);
		// TC11: Ray's line is inside merge to tube ray
		result = tube.findIntersections(new Ray(new Point3D(-0.8, 0.5, 0), new Vector(-1, 0, 0)));
		assertNull("Ray's line is Outside against down", result);

		// TC12: Ray's line is unite with the casing
		result = tube.findIntersections(new Ray(new Point3D(0, 0, 1), new Vector(1, 0, 0)));
		assertNull("Ray's line is Outside against down", result);
		// TC13: Ray's line is touch the casing
		result = tube.findIntersections(new Ray(new Point3D(-1, 1, -2), new Vector(0, 0, 1)));
		assertNull("Ray's line is Outside against down", result);

		// TC14: Ray's line is begin in the casing
		result = tube.findIntersections(new Ray(new Point3D(-1, 1, 0), new Vector(0, 0, 1)));
		assertNull("Ray's line is Outside against down", result);
	}
}
