package unittests;

import static org.junit.Assert.*;

import org.junit.Test;

import primitives.*;

/**
 * Testing Point3D
 * 
 * @author user1
 *
 */
public class Point3DTests {
	/**
	 * Test method for {@link primitives.Point3D#subtract(primitives.Point3D)}.
	 */
	@Test
	public void testSubtract() {
		// ============ Equivalence Partitions Tests ==============
		// TC01: Point + Vector
		Point3D p1 = new Point3D(1, 2, 3);
		assertTrue("Point + Vector does not work correctly", Point3D.ZERO.equals(p1.add(new Vector(-1, -2, -3))));
	}

	/**
	 * Test method for {@link primitives.Point3D#add(primitives.Vector)}.
	 */
	@Test
	public void testAdd() {
		// ============ Equivalence Partitions Tests ==============
		// TC02: Point - Vector
		Point3D p1 = new Point3D(1, 2, 3);
		assertTrue("Point - Point does not work correctly",
				new Vector(1, 1, 1).equals(new Point3D(2, 3, 4).subtract(p1)));
	}
}
