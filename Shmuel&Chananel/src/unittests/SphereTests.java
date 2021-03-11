/**
 * 
 */
package unittests;

import static org.junit.Assert.*;

import org.junit.Test;

import geometries.Sphere;
import primitives.Point3D;
import primitives.Vector;

/**
 * @author user1
 *
 */
public class SphereTests {


	/**
	 * Test method for {@link geometries.Sphere#getNormal(primitives.Point3D)}.
	 */
	@Test
	public void testGetNormal() {
		Sphere s = new Sphere(new Point3D(0, 0, 0), 1);
		
		assertEquals("Bad normal to sphere", new Vector(0, 0, 1), s.getNormal(new Point3D(0, 0, 1)));
	}

}
