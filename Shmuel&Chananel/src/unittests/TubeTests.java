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
public class TubeTests {

	/**
	 * Test method for {@link geometries.Tube#Tube(primitives.Ray, double)}.
	 */
	@Test
	public void testTube() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link geometries.Tube#getNormal(primitives.Point3D)}.
	 */
	@Test
	public void testGetNormal() {
		Ray r = new Ray(new Point3D(0, 0, 0), new Vector(0, 0, 1));
		Tube t = new Tube(r, 1);
		
		assertEquals("Bad normal to tube", new Vector(1, 0, 0), t.getNormal(new Point3D(1, 0, 0)));
	}

}
