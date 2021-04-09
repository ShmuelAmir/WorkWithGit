package unittests;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import geometries.*;
import primitives.*;

/**
 * Testing Triangle
 * 
 * @author user1
 *
 */
public class TriangleTests {
	/**
	 * Test method for {@link geometries.Polygon#getNormal(primitives.Point3D)}.
	 */
	@Test
	public void testGetNormal() {
		// ============ Equivalence Partitions Tests ==============
		// TC01: Text normal for triangle
		Triangle pl = new Triangle(new Point3D(0, 0, 1), new Point3D(1, 0, 0), new Point3D(0, 1, 0));
		double sqrt3 = Math.sqrt(1d / 3);
		assertEquals("Bad normal to triangle", new Vector(sqrt3, sqrt3, sqrt3), pl.getNormal(new Point3D(0, 0, 1)));
		
		/*
		 * Vector vec = new Vector(sqrt3, sqrt3, sqrt3); Vector normal =
		 * pl.getNormal(new Point3D(0, 0, 1)); assertTrue("Bad normal to triangle",
		 * vec.equals(normal) || vec.scale(-1).equals(normal));
		 */
	}

	/**
	 * Test method for
	 * {@link geometries.Triangle#findIntersections(primitives.Ray)}.
	 */
	@Test
	public void testFindIntersections() {
		List<Point3D> result;
		Triangle triangle = new Triangle(new Point3D(0, 0, 1), new Point3D(1, 0, 1), new Point3D(0, 1, 1));
		
		// ============ Equivalence Partitions Tests ==============

		// TC01: Ray's line is inside triangle (1 points)
		result = triangle.findIntersections(new Ray(new Point3D(0.5, 0.25, 0), new Vector(0, 0, 1)));
		assertEquals("Ray intersection inside the triangle", List.of(new Point3D(0.5, 0.25, 1)), result);

		// TC02: Ray's line is Outside against edge (0 points)
		result = triangle.findIntersections(new Ray(new Point3D(-1,0.5,0), new Vector(0, 0, 1)));
		assertNull("Ray's line is Outside against edge", result);

		// TC03: Ray's line is Outside against vertex (0 points)
		result = triangle.findIntersections(new Ray(new Point3D(-1,-1,0), new Vector(0, 0, 1)));
		assertNull("Ray's line is Outside against vertex", result);
		
		// =============== Boundary Values Tests =================
		
		// TC10: Ray's line is on edge (0 points)
		result = triangle.findIntersections(new Ray(new Point3D(0.5,0.5,0), new Vector(0, 0, 1)));
		assertNull("Ray's line is on edge", result);
		
		// TC11: Ray's line is in vertex (0 points)
		result = triangle.findIntersections(new Ray(new Point3D(1,0,0), new Vector(0, 0, 1)));
		assertNull("Ray's line is in vertex", result);
		
		// TC12: Ray's line is On edge's continuation (0 points)
		result = triangle.findIntersections(new Ray(new Point3D(2,0,0), new Vector(0, 0, 1)));
		assertNull("Ray's line is On edge's continuation", result);
	}
}
