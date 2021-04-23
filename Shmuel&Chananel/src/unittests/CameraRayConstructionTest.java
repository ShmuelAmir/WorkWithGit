/**
 * 
 */
package unittests;

import static org.junit.Assert.*;

import org.junit.Test;

import elements.Camera;
import geometries.*;
import primitives.*;


/**
 * @author user1
 *
 */
public class CameraRayConstructionTest {

	@Test
	public void test() {
		
		// TC01: 3X3 Sphere 
		Camera camera = new Camera(Point3D.ZERO, new Vector(0, 1, 0), new Vector(0, 0 , -1));
		camera.setDistance(1).setViewPlaneSize(3, 3);
		Sphere sphere = new Sphere(new Point3D(0, 0, -3), 1);

		
		assertEquals("ray sphere 2 point intersections",2,countIntersections(camera, sphere));
		
		// TC02: 3X3 Sphere
		camera = new Camera(new Point3D(0, 0, 0.5), new Vector(0, 1, 0), new Vector(0, 0 , -1));
		camera.setDistance(1).setViewPlaneSize(3, 3);
		sphere = new Sphere(new Point3D(0, 0, -2.5), 2.5);

		assertEquals("ray sphere 2 point intersections",2,countIntersections(camera, sphere));
		
		
		// TC03: 3X3 Sphere
		// TC04: 3X3 Sphere
		// TC05: 3X3 Sphere
		assertEquals("Bad ray", new Ray(Point3D.ZERO, new Vector(-2, -2, 10)),
				camera.setViewPlaneSize(6, 6).constructRayThroughPixel(3, 3, 0, 0));
	}

	/**
	 * @param camera
	 * @param sphere
	 * @param sum
	 * @return
	 */
	public int countIntersections(Camera camera, Intersectable shape) {
		int sum = 0;
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j <3 ; j++) {
				var intersection = shape.findIntersections(camera.constructRayThroughPixel(3, 3, j, i));  ///???  באמת צריך?
				if (intersection != null) {
					sum += intersection.size();
				}	
			}
		}
		
		return sum;
	}
	
	
	

	
	
	
}
