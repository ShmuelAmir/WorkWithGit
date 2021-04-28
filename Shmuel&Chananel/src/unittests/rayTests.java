/**
 * 
 */
package unittests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import primitives.*;

/**
 * @author user1
 *
 */
public class rayTests {

	/**
	 * Test method for {@link primitives.Ray#getClosestPoint(java.util.List)}.
	 */
	@Test
	public void testGetClosestPoint() {
		// ============ Equivalence Partitions Tests ==============
		
		// TC01: the close point is in the middle
		List<Point3D> points = new ArrayList<Point3D>();
		Point3D firstPoint =new Point3D(1, 0, 0);
		Point3D secondPoint =new Point3D(2, 0, 0);
		Point3D thirdPoint =new Point3D(6, 0, 0);
		Ray ray = new Ray(Point3D.ZERO, new Vector(1,0,0));
		points.add(0,secondPoint);
		points.add(1, firstPoint);
		points.add(2,thirdPoint);
		assertEquals("point in the middel",firstPoint , ray.getClosestPoint(points));
		
		// ============ Boundary Values Tests ==============
		
		// TC01: the list is null
		points = null;
		assertNull("list is null",ray.getClosestPoint(points));
		// TC01: the close point is in the beginning
		points = new ArrayList<Point3D>();
		points.add(0, firstPoint);
		points.add(1,secondPoint);
		points.add(2,thirdPoint);
		
		assertEquals("point in the middel",firstPoint , ray.getClosestPoint(points));
		// TC01: the list is end
		points.clear();
		points.add(0,thirdPoint);
		points.add(1,secondPoint);
		points.add(2, firstPoint);
		assertEquals("min point in the middel",firstPoint , ray.getClosestPoint(points));
		
		

	}

}
