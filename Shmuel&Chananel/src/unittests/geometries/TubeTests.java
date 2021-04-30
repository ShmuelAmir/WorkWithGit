package unittests.geometries;

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
//		Ray ray = new Ray(new Point3D(-0.8, 0, 0), new Vector(-1, 0, 0));
//		Tube tube = new Tube(ray, 1);
//		List<Point3D> result;
//		// **** Group:(2 point)
//		// ============ Equivalence Partitions Tests ==============
//		// TC01: There is a simple single test here
//		result = tube.findIntersections(new Ray(new Point3D(-5.5, 5.97, 0), new Vector(3.5, -6.97, 0)));
//		assertEquals("Wrong number of points", 2, result.size());
//		if (result.get(0).getX() > result.get(1).getX())
//			result = List.of(result.get(1), result.get(0));
//		assertEquals("Ray intersect the Tube", List.of(new Point3D(-3, 1, 0), new Point3D(-2, -1, 0)), result);
//
//		// =============== Boundary Values Tests ==================
//		// TC26: Ray orthogonal to the vector of the tube (2 points)
//		result = tube.findIntersections(new Ray(new Point3D(-3, -3, 0.5), new Vector(0, 1, 0)));
//		assertEquals("Wrong number of points", 2, result.size());
//		if (result.get(0).getX() > result.get(1).getX())
//			result = List.of(result.get(1), result.get(0));
//		assertEquals("Ray intersect the Tube", List.of(new Point3D(-3, 0.87, 0.5), new Point3D(-3, -0.87, 0.5)),
//				result);
//
//		// TC27: Ray pass throw the center (2 points)
//		result = tube.findIntersections(new Ray(new Point3D(-3, -3, 0), new Vector(0, 1, 0)));
//		assertEquals("Wrong number of points", 2, result.size());
//		if (result.get(0).getX() > result.get(1).getX())
//			result = List.of(result.get(1), result.get(0));
//		assertEquals("Ray intersect the Tube", List.of(new Point3D(-3, 1, 0), new Point3D(-3, -1, 0)), result);
//
//		// TC28: Ray pass throw the starting point (2 points)
//		result = tube.findIntersections(new Ray(new Point3D(-0.8, -3, 0), new Vector(0, 1, 0)));
//		assertEquals("Wrong number of points", 2, result.size());
//		if (result.get(0).getX() > result.get(1).getX())
//			result = List.of(result.get(1), result.get(0));
//		assertEquals("Ray intersect the Tube", List.of(new Point3D(-0.8, 1, 0), new Point3D(-0.8, -1, 0)), result);
//
//		// TC29: Ray in the same plane of the starting point (2 points)
//		result = tube.findIntersections(new Ray(new Point3D(-0.8, -3, 0.5), new Vector(0, 1, 0)));
//		assertEquals("Wrong number of points", 2, result.size());
//		if (result.get(0).getX() > result.get(1).getX())
//			result = List.of(result.get(1), result.get(0));
//		assertEquals("Ray intersect the Tube", List.of(new Point3D(-0.8, 0.87, 0.5), new Point3D(-0.8, -0.87, 0.5)),
//				result);
//
//		
//		
//		
//		
//		
//		
//		// **** Group:(1 point)
//		// ============ Equivalence Partitions Tests =============
//
//		// TC22:Ray's line is begin in the tube and no vertical to the tube ray
//		result = tube.findIntersections(new Ray(new Point3D(-2, 0.5, 0), new Vector(-1, 1, 0)));
//		assertEquals("Ray's line is begin in the tube and no vertical to the tube ray", List.of(new Point3D(-2.5, 1, 0)),
//				result);
//
//		// ============ Boundary Partitions Tests =============
//		
//		// TC23: Ray's line is begin in the tube and vertical to the tube ray
//		result = tube.findIntersections(new Ray(new Point3D(-2, 0.5, 0.25), new Vector(0, 1, 0)));
//		assertEquals("Ray's line is begin in the tube and vertical to the tube ray", List.of(new Point3D(-2, 0.97, 0.25)), result);
//
//		
//		// **** Group: Ray's line pass throw the tube ray
//		// TC24: Ray's line is begin in the tube ray and vertical to the tube ray
//		result = tube.findIntersections(new Ray(new Point3D(-2, 0, 0), new Vector(0, 1, 0)));
//		assertEquals("Ray's line is begin in the tube ray and vertical to the tube ray", List.of(new Point3D(-2, 1, 0)), result);
//
//		// TC25: Ray's line is begin in the tube and no vertical to the tube ray
//		result = tube.findIntersections(new Ray(new Point3D(-2, 0, 0), new Vector(0.45, 0.8, 0.59)));
//		assertEquals("Ray's line is begin in the tube and no vertical to the tube ray", List.of(new Point3D(-1.55, 0.8, 0.59)), result);
//
//		// TC26: Ray's line is begin before it
//		result = tube.findIntersections(new Ray(new Point3D(-2, -0.5, 0), new Vector(0, 1, 0)));
//		assertEquals("Ray's line pass throw the tube ray and begin before it", List.of(new Point3D(-2, 1, 0)), result);
//
//		// TC27: Ray's line is begin on the casing
//		result = tube.findIntersections(new Ray(new Point3D(-2, -1, 0), new Vector(0, 1, 0)));
//		assertEquals("Ray's line pass throw the tube ray and begin on the casing", List.of(new Point3D(-2, 1, 0)), result);
//
//		// TC28: Ray's line is begin after it
//		result = tube.findIntersections(new Ray(new Point3D(-2, 0.5, 0), new Vector(0, 1, 0)));
//		assertEquals("Ray's line pass throw the tube ray and begin after it", List.of(new Point3D(-2, 1, 0)), result);
//
//		
////		// TC29: Ray's line is on the casing
////		result = tube.findIntersections(new Ray(new Point3D(-2, -1, 0), new Vector(3.5, -6.97, -3)));
////		assertEquals("Ray's line is on the casing", List.of(new Point3D(-2.85, 0.69, 0.73)), result);
//
//		
//		// **** Group: Ray's line pass throw the starting point
//		// TC30: Ray's line is begin before it
//		result = tube.findIntersections(new Ray(new Point3D(-0.8, -0.5, 0), new Vector(0, 1, 0)));
//		assertEquals("Ray's line pass throw the starting point and begin before it", List.of(new Point3D(-0.8, 1, 0)), result);
//
//		// TC31: Ray's line is begin in the starting point
//		result = tube.findIntersections(new Ray(new Point3D(-0.8, 0, 0), new Vector(0, 1, 0)));
//		assertEquals("Ray's line pass throw the starting point and begin before it", List.of(new Point3D(-0.8, 1, 0)), result);
//
//		// TC32: Ray's line is begin after it
//		result = tube.findIntersections(new Ray(new Point3D(-0.8, 0.5, 0), new Vector(0, 1, 0)));
//		assertEquals("Ray's line pass throw the starting point and begin after it", List.of(new Point3D(-0.8, 1, 0)), result);
//		
//		// TC34: Ray's line is begin on the casing
//		result = tube.findIntersections(new Ray(new Point3D(-0.8, -1, 0), new Vector(0, 1, 0)));
//		assertEquals("Ray's line pass throw the starting point and begin on the casing", List.of(new Point3D(-0.8, 1, 0)), result);
//
//		
//		// TC33: Ray's line pass throw the plane of the starting point
//		result = tube.findIntersections(new Ray(new Point3D(-0.8, 0 , 0.25), new Vector(0 ,1 ,-0.25)));
//		assertEquals("Ray's line pass throw the plane of the starting point", List.of(new Point3D(-0.8, 1, 0)), result);
//
//
//
//		
//
//
//		
//		
//		
//		
//		// ============ Equivalence Partitions Tests =============
//		// **** Group: Ray's line parallel to the tube (0 point)
//		// TC04: Ray's line is Outside against down
//		result = tube.findIntersections(new Ray(new Point3D(0, 0, -2), new Vector(-1, 0, 0)));
//		assertNull("Ray's line is Outside against down", result);
//
//		// **** Group:(0 point)
//		// TC08: Ray's line is Outside standing down
//		result = tube.findIntersections(new Ray(new Point3D(-2, 0, -2), new Vector(0, 1, 0)));
//		assertNull("Ray's line is Outside against down", result);
//		// TC09: Ray's line is Outside with no intersection
//		result = tube.findIntersections(new Ray(new Point3D(-2, 3, 0), new Vector(1, 1, 1)));
//		assertNull("Ray's line is Outside against down", result);
//
//		// ============ Boundary Partitions Tests =============
//		// TC10: Ray's line is inside parallel to tube ray
//		result = tube.findIntersections(new Ray(new Point3D(-0.8, 0, 0), new Vector(-1, 0, 0)));
//		assertNull("Ray's line is Outside against down", result);
//		// TC11: Ray's line is inside merge to tube ray
//		result = tube.findIntersections(new Ray(new Point3D(-0.8, 0.5, 0), new Vector(-1, 0, 0)));
//		assertNull("Ray's line is Outside against down", result);
//
//		// TC12: Ray's line is unite with the casing
//		result = tube.findIntersections(new Ray(new Point3D(0, 0, 1), new Vector(1, 0, 0)));
//		assertNull("Ray's line is Outside against down", result);
//		// TC13: Ray's line is touch the casing
//		result = tube.findIntersections(new Ray(new Point3D(-1, 1, -2), new Vector(0, 0, 1)));
//		assertNull("Ray's line is Outside against down", result);
//
//		// TC14: Ray's line is begin in the casing
//		result = tube.findIntersections(new Ray(new Point3D(-1, 1, 0), new Vector(0, 0, 1)));
//		assertNull("Ray's line is Outside against down", result);
//		
//		
//		
//		
//		
		
		
		Tube tube1 = new Tube(new Ray(new Point3D(1, 0, 0), new Vector(0, 1, 0)), 1d);
		Vector vAxis = new Vector(0, 0, 1);
		Tube tube2 = new Tube(new Ray(new Point3D(1, 1, 1), vAxis), 1d);
		Ray ray;

		// ============ Equivalence Partitions Tests ==============
		// TC01: Ray's line is outside the tube (0 points)
		ray = new Ray(new Point3D(1, 1, 2), new Vector(1, 1, 0));
		assertNull("Must not be intersections", tube1.findIntersections(ray));

		// TC02: Ray's crosses the tube (2 points)
		ray = new Ray(new Point3D(0, 0, 0), new Vector(2, 1, 1));
		List<Point3D> result = tube2.findIntersections(ray);
		assertNotNull("must be intersections", result);
		assertEquals("must be 2 intersections", 2, result.size());
		if (result.get(0).getY() > result.get(1).getY())
			result = List.of(result.get(1), result.get(0));
		assertEquals("Bad intersections", List.of(new Point3D(0.4, 0.2, 0.2), new Point3D(2, 1, 1)), result);

		// TC03: Ray's starts within tube and crosses the tube (1 point)
		ray = new Ray(new Point3D(1, 0.5, 0.5), new Vector(2, 1, 1));
		result = tube2.findIntersections(ray);
		assertNotNull("must be intersections", result);
		assertEquals("must be 1 intersections", 1, result.size());
		assertEquals("Bad intersections", List.of(new Point3D(2, 1, 1)), result);

		// =============== Boundary Values Tests ==================

		// **** Group: Ray's line is parallel to the axis (0 points)
		// TC11: Ray is inside the tube (0 points)
		ray = new Ray(new Point3D(0.5, 0.5, 0.5), vAxis);
		assertNull("must not be intersections", tube2.findIntersections(ray));
		// TC12: Ray is outside the tube
		ray = new Ray(new Point3D(0.5, -0.5, 0.5), vAxis);
		assertNull("must not be intersections", tube2.findIntersections(ray));
		// TC13: Ray is at the tube surface
		ray = new Ray(new Point3D(2, 1, 0.5), vAxis);
		assertNull("must not be intersections", tube2.findIntersections(ray));
		// TC14: Ray is inside the tube and starts against axis head
		ray = new Ray(new Point3D(0.5, 0.5, 1), vAxis);
		assertNull("must not be intersections", tube2.findIntersections(ray));
		// TC15: Ray is outside the tube and starts against axis head
		ray = new Ray(new Point3D(0.5, -0.5, 1), vAxis);
		assertNull("must not be intersections", tube2.findIntersections(ray));
		// TC16: Ray is at the tube surface and starts against axis head
		ray = new Ray(new Point3D(2, 1, 1), vAxis);
		assertNull("must not be intersections", tube2.findIntersections(ray));
		// TC17: Ray is inside the tube and starts at axis head
		ray = new Ray(new Point3D(1, 1, 1), vAxis);
		assertNull("must not be intersections", tube2.findIntersections(ray));

		// **** Group: Ray is orthogonal but does not begin against the axis head
		// TC21: Ray starts outside and the line is outside (0 points)
		ray = new Ray(new Point3D(0, 2, 2), new Vector(1, 1, 0));
		assertNull("must not be intersections", tube2.findIntersections(ray));
		// TC22: The line is tangent and the ray starts before the tube (0 points)
		ray = new Ray(new Point3D(0, 2, 2), new Vector(1, 0, 0));
		assertNull("must not be intersections", tube2.findIntersections(ray));
		// TC23: The line is tangent and the ray starts at the tube (0 points)
		ray = new Ray(new Point3D(1, 2, 2), new Vector(1, 0, 0));
		assertNull("must not be intersections", tube2.findIntersections(ray));
		// TC24: The line is tangent and the ray starts after the tube (0 points)
		ray = new Ray(new Point3D(2, 2, 2), new Vector(1, 0, 0));
		assertNull("must not be intersections", tube2.findIntersections(ray));
		// TC25: Ray starts before (2 points)
		ray = new Ray(new Point3D(0, 0, 2), new Vector(2, 1, 0));
		result = tube2.findIntersections(ray);
		assertNotNull("must be intersections", result);
		assertEquals("must be 2 intersections", 2, result.size());
		if (result.get(0).getY() > result.get(1).getY())
			result = List.of(result.get(1), result.get(0));
		assertEquals("Bad intersections", List.of(new Point3D(0.4, 0.2, 2), new Point3D(2, 1, 2)), result);
		// TC26: Ray starts at the surface and goes inside (1 point)
		ray = new Ray(new Point3D(0.4, 0.2, 2), new Vector(2, 1, 0));
		result = tube2.findIntersections(ray);
		assertNotNull("must be intersections", result);
		assertEquals("must be 1 intersections", 1, result.size());
		assertEquals("Bad intersections", List.of(new Point3D(2, 1, 2)), result);
		// TC27: Ray starts inside (1 point)
		ray = new Ray(new Point3D(1, 0.5, 2), new Vector(2, 1, 0));
		result = tube2.findIntersections(ray);
		assertNotNull("must be intersections", result);
		assertEquals("must be 1 intersections", 1, result.size());
		assertEquals("Bad intersections", List.of(new Point3D(2, 1, 2)), result);
		// TC28: Ray starts at the surface and goes outside (0 points)
		ray = new Ray(new Point3D(2, 1, 2), new Vector(2, 1, 0));
		result = tube2.findIntersections(ray);
		assertNull("Bad intersections", result);
		// TC29: Ray starts after
		ray = new Ray(new Point3D(4, 2, 2), new Vector(2, 1, 0));
		result = tube2.findIntersections(ray);
		assertNull("Bad intersections", result);
		// TC30: Ray starts before and crosses the axis (2 points)
		ray = new Ray(new Point3D(1, -1, 2), new Vector(0, 1, 0));
		result = tube2.findIntersections(ray);
		assertNotNull("must be intersections", result);
		assertEquals("must be 2 intersections", 2, result.size());
		if (result.get(0).getY() > result.get(1).getY())
			result = List.of(result.get(1), result.get(0));
		assertEquals("Bad intersections", List.of(new Point3D(1, 0, 2), new Point3D(1, 2, 2)), result);
		// TC31: Ray starts at the surface and goes inside and crosses the axis
		ray = new Ray(new Point3D(1, 0, 2), new Vector(0, 1, 0));
		result = tube2.findIntersections(ray);
		assertNotNull("must be intersections", result);
		assertEquals("must be 1 intersections", 1, result.size());
		assertEquals("Bad intersections", List.of(new Point3D(1, 2, 2)), result);
		// TC32: Ray starts inside and the line crosses the axis (1 point)
		ray = new Ray(new Point3D(1, 0.5, 2), new Vector(0, 1, 0));
		result = tube2.findIntersections(ray);
		assertNotNull("must be intersections", result);
		assertEquals("must be 1 intersections", 1, result.size());
		assertEquals("Bad intersections", List.of(new Point3D(1, 2, 2)), result);
		// TC33: Ray starts at the surface and goes outside and the line crosses the
		// axis (0 points)
		ray = new Ray(new Point3D(1, 2, 2), new Vector(0, 1, 0));
		result = tube2.findIntersections(ray);
		assertNull("Bad intersections", result);
		// TC34: Ray starts after and crosses the axis (0 points)
		ray = new Ray(new Point3D(1, 3, 2), new Vector(0, 1, 0));
		result = tube2.findIntersections(ray);
		assertNull("Bad intersections", result);
		// TC35: Ray start at the axis
		ray = new Ray(new Point3D(1, 1, 2), new Vector(0, 1, 0));
		result = tube2.findIntersections(ray);
		assertNotNull("must be intersections", result);
		assertEquals("must be 1 intersections", 1, result.size());
		assertEquals("Bad intersections", List.of(new Point3D(1, 2, 2)), result);

		// **** Group: Ray is orthogonal to axis and begins against the axis head
		// TC41: Ray starts outside and the line is outside (
		ray = new Ray(new Point3D(0, 2, 1), new Vector(1, 1, 0));
		assertNull("must not be intersections", tube2.findIntersections(ray));
		// TC42: The line is tangent and the ray starts before the tube
		ray = new Ray(new Point3D(0, 2, 1), new Vector(1, 0, 0));
		assertNull("must not be intersections", tube2.findIntersections(ray));
		// TC43: The line is tangent and the ray starts at the tube
		ray = new Ray(new Point3D(1, 2, 1), new Vector(1, 0, 0));
		assertNull("must not be intersections", tube2.findIntersections(ray));
		// TC44: The line is tangent and the ray starts after the tube
		ray = new Ray(new Point3D(2, 2, 2), new Vector(1, 0, 0));
		assertNull("must not be intersections", tube2.findIntersections(ray));
		// TC45: Ray starts before
		ray = new Ray(new Point3D(0, 0, 1), new Vector(2, 1, 0));
		result = tube2.findIntersections(ray);
		assertNotNull("must be intersections", result);
		assertEquals("must be 2 intersections", 2, result.size());
		if (result.get(0).getY() > result.get(1).getY())
			result = List.of(result.get(1), result.get(0));
		assertEquals("Bad intersections", List.of(new Point3D(0.4, 0.2, 1), new Point3D(2, 1, 1)), result);
		// TC46: Ray starts at the surface and goes inside
		ray = new Ray(new Point3D(0.4, 0.2, 1), new Vector(2, 1, 0));
		result = tube2.findIntersections(ray);
		assertNotNull("must be intersections", result);
		assertEquals("must be 1 intersections", 1, result.size());
		assertEquals("Bad intersections", List.of(new Point3D(2, 1, 1)), result);
		// TC47: Ray starts inside
		ray = new Ray(new Point3D(1, 0.5, 1), new Vector(2, 1, 0));
		result = tube2.findIntersections(ray);
		assertNotNull("must be intersections", result);
		assertEquals("must be 1 intersections", 1, result.size());
		assertEquals("Bad intersections", List.of(new Point3D(2, 1, 1)), result);
		// TC48: Ray starts at the surface and goes outside
		ray = new Ray(new Point3D(2, 1, 1), new Vector(2, 1, 0));
		result = tube2.findIntersections(ray);
		assertNull("Bad intersections", result);
		// TC49: Ray starts after
		ray = new Ray(new Point3D(4, 2, 1), new Vector(2, 1, 0));
		result = tube2.findIntersections(ray);
		assertNull("Bad intersections", result);
		// TC50: Ray starts before and goes through the axis head
		ray = new Ray(new Point3D(1, -1, 1), new Vector(0, 1, 0));
		result = tube2.findIntersections(ray);
		assertNotNull("must be intersections", result);
		assertEquals("must be 2 intersections", 2, result.size());
		if (result.get(0).getY() > result.get(1).getY())
			result = List.of(result.get(1), result.get(0));
		assertEquals("Bad intersections", List.of(new Point3D(1, 0, 1), new Point3D(1, 2, 1)), result);
		// TC51: Ray starts at the surface and goes inside and goes through the axis
		// head
		ray = new Ray(new Point3D(1, 0, 1), new Vector(0, 1, 0));
		result = tube2.findIntersections(ray);
		assertNotNull("must be intersections", result);
		assertEquals("must be 1 intersections", 1, result.size());
		assertEquals("Bad intersections", List.of(new Point3D(1, 2, 1)), result);
		// TC52: Ray starts inside and the line goes through the axis head
		ray = new Ray(new Point3D(1, 0.5, 1), new Vector(0, 1, 0));
		result = tube2.findIntersections(ray);
		assertNotNull("must be intersections", result);
		assertEquals("must be 1 intersections", 1, result.size());
		assertEquals("Bad intersections", List.of(new Point3D(1, 2, 1)), result);
		// TC53: Ray starts at the surface and the line goes outside and goes through
		// the axis head
		ray = new Ray(new Point3D(1, 2, 1), new Vector(0, 1, 0));
		result = tube2.findIntersections(ray);
		assertNull("Bad intersections", result);
		// TC54: Ray starts after and the line goes through the axis head
		ray = new Ray(new Point3D(1, 3, 1), new Vector(0, 1, 0));
		result = tube2.findIntersections(ray);
		assertNull("Bad intersections", result);
		// TC55: Ray start at the axis head
		ray = new Ray(new Point3D(1, 1, 1), new Vector(0, 1, 0));
		result = tube2.findIntersections(ray);
		assertNotNull("must be intersections", result);
		assertEquals("must be 1 intersections", 1, result.size());
		assertEquals("Bad intersections", List.of(new Point3D(1, 2, 1)), result);

		// **** Group: Ray's line is neither parallel nor orthogonal to the axis and
		// begins against axis head
		Point3D p0 = new Point3D(0, 2, 1);
		// TC61: Ray's line is outside the tube
		ray = new Ray(p0, new Vector(1, 1, 1));
		result = tube2.findIntersections(ray);
		assertNull("Bad intersections", result);
		// TC62: Ray's line crosses the tube and begins before
		ray = new Ray(p0, new Vector(2, -1, 1));
		result = tube2.findIntersections(ray);
		assertNotNull("must be intersections", result);
		assertEquals("must be 2 intersections", 2, result.size());
		if (result.get(0).getY() > result.get(1).getY())
			result = List.of(result.get(1), result.get(0));
		assertEquals("Bad intersections", List.of(new Point3D(2, 1, 2), new Point3D(0.4, 1.8, 1.2)), result);
		// TC63: Ray's line crosses the tube and begins at surface and goes inside
		ray = new Ray(new Point3D(0.4, 1.8, 1), new Vector(2, -1, 1));
		result = tube2.findIntersections(ray);
		assertNotNull("must be intersections", result);
		assertEquals("must be 1 intersections", 1, result.size());
		assertEquals("Bad intersections", List.of(new Point3D(2, 1, 1.8)), result);
		// TC64: Ray's line crosses the tube and begins inside
		ray = new Ray(new Point3D(1, 1.5, 1), new Vector(2, -1, 1));
		result = tube2.findIntersections(ray);
		assertNotNull("must be intersections", result);
		assertEquals("must be 1 intersections", 1, result.size());
		assertEquals("Bad intersections", List.of(new Point3D(2, 1, 1.5)), result);
		// TC65: Ray's line crosses the tube and begins at the axis head
		ray = new Ray(new Point3D(1, 1, 1), new Vector(0, 1, 1));
		result = tube2.findIntersections(ray);
		assertNotNull("must be intersections", result);
		assertEquals("must be 1 intersections", 1, result.size());
		assertEquals("Bad intersections", List.of(new Point3D(1, 2, 2)), result);
		// TC66: Ray's line crosses the tube and begins at surface and goes outside
		ray = new Ray(new Point3D(2, 1, 1), new Vector(2, -1, 1));
		result = tube2.findIntersections(ray);
		assertNull("Bad intersections", result);
		// TC67: Ray's line is tangent and begins before
		ray = new Ray(p0, new Vector(0, 2, 1));
		result = tube2.findIntersections(ray);
		assertNull("Bad intersections", result);
		// TC68: Ray's line is tangent and begins at the tube surface
		ray = new Ray(new Point3D(1, 2, 1), new Vector(1, 0, 1));
		result = tube2.findIntersections(ray);
		assertNull("Bad intersections", result);
		// TC69: Ray's line is tangent and begins after
		ray = new Ray(new Point3D(2, 2, 1), new Vector(1, 0, 1));
		result = tube2.findIntersections(ray);
		assertNull("Bad intersections", result);

//		// **** Group: Ray's line is neither parallel nor orthogonal to the axis and
//		// does not begin against axis head
//		double sqrt2 = Math.sqrt(2);
//		double denomSqrt2 = 1 / sqrt2;
//		double value1 = 1 - denomSqrt2;
//		double value2 = 1 + denomSqrt2;
//		// TC71: Ray's crosses the tube and the axis
//		ray = new Ray(new Point3D(0, 0, 2), new Vector(1, 1, 1));
//		result = tube2.findIntersections(ray);
//		assertNotNull("must be intersections", result);
//		assertEquals("must be 2 intersections", 2, result.size());
//		if (result.get(0).getY() > result.get(1).getY())
//			result = List.of(result.get(1), result.get(0));
//		assertEquals("Bad intersections",
//				List.of(new Point3D(value1, value1, 2 + value1), new Point3D(value2, value2, 2 + value2)), result);
//		// TC72: Ray's crosses the tube and the axis head
//		ray = new Ray(new Point3D(0, 0, 0), new Vector(1, 1, 1));
//		result = tube2.findIntersections(ray);
//		assertNotNull("must be intersections", result);
//		assertEquals("must be 2 intersections", 2, result.size());
//		if (result.get(0).getY() > result.get(1).getY())
//			result = List.of(result.get(1), result.get(0));
//		assertEquals("Bad intersections",
//				List.of(new Point3D(value1, value1, value1), new Point3D(value2, value2, value2)), result);
//		// TC73: Ray's begins at the surface and goes inside
//		// TC74: Ray's begins at the surface and goes inside crossing the axis
//		ray = new Ray(new Point3D(value1, value1, 2 + value1), new Vector(1, 1, 1));
//		result = tube2.findIntersections(ray);
//		assertNotNull("must be intersections", result);
//		assertEquals("must be 1 intersections", 1, result.size());
//		assertEquals("Bad intersections", List.of(new Point3D(value2, value2, 2 + value2)), result);
//		// TC75: Ray's begins at the surface and goes inside crossing the axis head
//		ray = new Ray(new Point3D(value1, value1, value1), new Vector(1, 1, 1));
//		result = tube2.findIntersections(ray);
//		assertNotNull("must be intersections", result);
//		assertEquals("must be 1 intersections", 1, result.size());
//		assertEquals("Bad intersections", List.of(new Point3D(value2, value2, value2)), result);
//		// TC76: Ray's begins inside and the line crosses the axis
//		ray = new Ray(new Point3D(0.5, 0.5, 2.5), new Vector(1, 1, 1));
//		result = tube2.findIntersections(ray);
//		assertNotNull("must be intersections", result);
//		assertEquals("must be 1 intersections", 1, result.size());
//		assertEquals("Bad intersections", List.of(new Point3D(value2, value2, 2 + value2)), result);
//		// TC77: Ray's begins inside and the line crosses the axis head
//		ray = new Ray(new Point3D(0.5, 0.5, 0.5), new Vector(1, 1, 1));
//		result = tube2.findIntersections(ray);
//		assertNotNull("must be intersections", result);
//		assertEquals("must be 1 intersections", 1, result.size());
//		assertEquals("Bad intersections", List.of(new Point3D(value2, value2, value2)), result);
//		// TC78: Ray's begins at the axis
//		ray = new Ray(new Point3D(1, 1, 3), new Vector(1, 1, 1));
//		result = tube2.findIntersections(ray);
//		assertNotNull("must be intersections", result);
//		assertEquals("must be 1 intersections", 1, result.size());
//		assertEquals("Bad intersections", List.of(new Point3D(value2, value2, 2 + value2)), result);
//		// TC79: Ray's begins at the surface and goes outside
//		ray = new Ray(new Point3D(2, 1, 2), new Vector(2, 1, 1));
//		result = tube2.findIntersections(ray);
//		assertNull("Bad intersections", result);
//		// TC80: Ray's begins at the surface and goes outside and the line crosses the
//		// axis
//		ray = new Ray(new Point3D(value2, value2, 2 + value2), new Vector(1, 1, 1));
//		result = tube2.findIntersections(ray);
//		assertNull("Bad intersections", result);
//		// TC81: Ray's begins at the surface and goes outside and the line crosses the
//		// axis head
//		ray = new Ray(new Point3D(value2, value2, value2), new Vector(1, 1, 1));
//		result = tube2.findIntersections(ray);
//		assertNull("Bad intersections", result);
	}
}
