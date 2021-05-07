package renderer;

import java.util.List;

import elements.LightSource;
import geometries.Intersectable.GeoPoint;
import primitives.*;
import static primitives.Util.*;
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
		List<GeoPoint> intersections = scene.geometries.findGeoIntersections(ray);

		// if there is no intersection - return the background color
		if (intersections == null)
			return scene.background;

		// if there is intersections - the color determine by the close point
		GeoPoint closestPoint = ray.getClosestGeoPoint(intersections);
		return calcColor(closestPoint, ray);
	}

	/**
	 * Calculate the color in a point
	 * 
	 * @param point - the point
	 * @return the color
	 */
	private Color calcColor(GeoPoint intersection, Ray ray) {
		return scene.ambientLight.getIntensity().add(intersection.geometry.getEmission())
				.add(calcLocalEffects(intersection, ray));
	}

	private Color calcLocalEffects(GeoPoint intersection, Ray ray) {
		Vector v = ray.getDir(); 
		Vector n = intersection.geometry.getNormal(intersection.point);
		
		double nv = alignZero(n.dotProduct(v)); 
		if (nv == 0) 
			return Color.BLACK;
		
		Material material = intersection.geometry.getMaterial();
		int nShininess = material.nShininess;
		double kd = material.kD, ks = material.kS;
		
		Color color = Color.BLACK;
		for (LightSource lightSource : scene.lights) {
			Vector l = lightSource.getL(intersection.point);
			double nl = alignZero(n.dotProduct(l));
			if (nl * nv > 0) { // sign(nl) == sing(nv)
				Color lightIntensity = lightSource.getIntensity(intersection.point);
				color = color.add(calcDiffusive(kd, l, n, lightIntensity),
				calcSpecular(ks, l, n, v, nShininess, lightIntensity));
			}
		}
		
		return color;
	}

	private Color calcDiffusive(double kd, Vector l, Vector n, Color lightIntensity) {
		double lDotN = Math.abs(l.dotProduct(n));
		
		return lightIntensity.scale(kd * lDotN);
	}

	private Color calcSpecular(double ks, Vector l, Vector n, Vector v, int nShininess, Color lightIntensity) {
		double lDotN2 = l.dotProduct(n) * 2;
		Vector r = l.subtract(n.scale(lDotN2));
		
		double minusVDotR = max(0, v.scale(-1).dotProduct(r));
		return lightIntensity.scale(Math.pow(minusVDotR, nShininess) * ks);
	}

}
