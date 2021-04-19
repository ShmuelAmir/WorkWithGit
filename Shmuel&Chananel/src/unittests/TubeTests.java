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
		Ray ray = new Ray(new Point3D(-0.8, 0, 0), new Vector(-1,0,0));
		Tube tube= new Tube(ray, 1);
		
		
		// ============ Equivalence Partitions Tests ==============
		// TC01: There is a simple single test here
		assertEquals("Ray intersect the Tube", List.of(new Point3D(-3, 1, 0), new Point3D(-2, -1, 0)),
				tube.findIntersections(new Ray(new Point3D(-5.5, 5.97, 0), new Vector(3.5, -6.97, 0))));
		
	}
}
