/**
 * 
 */
package unittests;

import static org.junit.Assert.*;

import org.junit.Test;

import geometries.*;
import primitives.*;

/**
 * @author user1
 *
 */
public class CylinderTests {
	
	/**
	 * Test method for {@link geometries.Tube#getNormal(primitives.Point3D)}.
	 */
	@Test
	public void testGetNormal() {
		Ray r = new Ray(new Point3D(0, 0, 0), new Vector(0, 0, 1));
		Cylinder c = new Cylinder(r, 1, 2);
		
		// ============ Equivalence Partitions Tests ==============
		assertEquals("Bad normal to cylinder", new Vector(1, 0, 0), c.getNormal(new Point3D(1, 0, 1)));
		assertEquals("Bad normal to cylinder", new Vector(0, 0, -1), c.getNormal(new Point3D(0, 0, 0)));
		assertEquals("Bad normal to cylinder", new Vector(0, 0, 1), c.getNormal(new Point3D(0, 0, 2)));
		
		// =============== Boundary Values Tests ==================
		// TC10: Suture point (normal vector according to the base of the tube)
		assertEquals("Bad normal to cylinder", new Vector(0, 0, -1), c.getNormal(new Point3D(1, 0, 0)));
		// TC11: 
		assertEquals("Bad normal to cylinder", new Vector(0, 0, 1), c.getNormal(new Point3D(1, 0, 2)));
	}

}
