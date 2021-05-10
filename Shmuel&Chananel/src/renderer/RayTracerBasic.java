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
	 * this method get intersection point (GeoPoint) and ray and calculate the color 
	 * in this point according to the Phong Reflectance Mode
	 * 
	 * @param point - the intersection point
	 * @return the color
	 */
	private Color calcColor(GeoPoint intersection, Ray ray) {
		return scene.ambientLight.getIntensity().add(intersection.geometry.getEmission())
				.add(calcLocalEffects(intersection, ray));
	}

	/**
	 * Help method - this method calculate the color of a point 
	 * considering all the light sources
	 * @param intersection - the intersection point
	 * @param ray - the ray from the light
	 * @return - the color
	 */
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
				color = color //
						.add(lightIntensity.scale(calcDiffusive(kd, nl) + //
								calcSpecular(ks, l, n, v, nShininess, nl)));

			}
		}

		return color;
	}

	/**
	 * Help method - this method calculate the diffusive component 
	 * @param kd - coefficient in the formula
	 * @param nl - dot product between l and the normal
	 * When l and n are normalized, l · n is the cosine of the angle between them
	 * @return double that represent the diffusive 
	 */
	private double calcDiffusive(double kd, double nl) {
		double lDotN = Math.abs(nl);

		return kd * lDotN;
	}

	/**
	 *  Help method - this method calculate the Specular component 
	 *  
	 * @param ks - coefficient in the formula
	 * @param l - vector between the light and the intersection point
	 * @param n - normal of the body
	 * @param v - the dirction of the camera
	 * @param nShininess - coefficient in the formula
	 * @param nl - dot product between l and the normal
	 * @return
	 */
	private double calcSpecular(double ks, Vector l, Vector n, Vector v, int nShininess, double nl) {
		double lDotN2 = nl * 2;
		Vector r = l.subtract(n.scale(lDotN2));

		double minusVDotR = alignZero(-v.dotProduct(r));
		return minusVDotR <= 0 ? 0 : Math.pow(minusVDotR, nShininess) * ks;
	}

}
