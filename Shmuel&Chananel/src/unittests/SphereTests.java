/**
 * 
 */
package unittests;

import static org.junit.Assert.*;

import org.junit.Test;

import geometries.Sphere;

/**
 * @author user1
 *
 */
public class SphereTests {

	/**
	 * Test method for {@link geometries.Sphere#Sphere(primitives.Point3D, double)}.
	 */
	@Test
	public void testSphere() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link geometries.Sphere#getNormal(primitives.Point3D)}.
	 */
	@Test
	public void testGetNormal() {
		Sphere s = new Sphere(null, 0);
		
		assertEquals("", , s.getNormal(null));
	}

}
