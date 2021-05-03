package renderer;

import java.util.List;

import primitives.Color;
import primitives.Point3D;
import primitives.Ray;
import scene.Scene;

/**
 * RayTracerBasic class extends RayTracerBase, this class determine the color of
 * the image in a particular pixel - according to the ray that come frome the
 * camera
 * 
 * @author shmulik
 */
public class RayTracerBasic extends RayTracerBase {
	/**
	 * initialize scene
	 * 
	 * @param scene - the scene
	 */
	public RayTracerBasic(Scene scene) {
		super(scene);
	}

	@Override
	public Color traceRay(Ray ray) {
		List<Point3D> intersections = scene.geometries.findIntersections(ray);

		// if there is no intersection - return the background color
		if (intersections == null)
			return scene.background;

		// if there is intersections - the color determine by the close point
		Point3D closestPoint = ray.getClosestPoint(intersections);
		return calcColor(closestPoint);
	}

	/**
	 * Calculate the color in a point
	 * 
	 * @param point - the point
	 * @return the color
	 */
	private Color calcColor(Point3D point) {
		return scene.ambientLight.getIntensity();
	}
}
