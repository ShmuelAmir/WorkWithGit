package renderer;

import java.util.List;

import primitives.Color;
import primitives.Point3D;
import primitives.Ray;
import scene.Scene;

/**
 * RayTracerBasic class extends RayTracerBase, this class determine 
 * the color of the image in a particular pixel - according to the ray that come frome the camera
 * @author shmulik
 */
public class RayTracerBasic extends RayTracerBase {
	/**
	 * initialize scene
	 * @param scene - get scene to initialize this scene
	 */
	public RayTracerBasic(Scene scene) {
		super(scene);
	}

	/**
	 * this method  get ray and determine the color according to the intersection of the ray with the object in the scene
	 */
	@Override
	public Color traceRay(Ray ray) {
		List<Point3D> intersections = scene.geometries.findIntersections(ray);
		//if there is no intersection - return the background
		if (intersections == null)
			return scene.background;
		//if there is intersections - the color determine by the close point
		Point3D closestPoint = ray.getClosestPoint(intersections);
		return calcColor(closestPoint);
	}

	/**
	 * get the intensity of the ambient light
	 * @param point
	 * @return
	 */
	private Color calcColor(Point3D point) {
		return scene.ambientLight.getIntensity();
	}
}
