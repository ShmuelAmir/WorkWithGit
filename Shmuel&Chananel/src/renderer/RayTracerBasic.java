package renderer;

import java.util.List;

import elements.LightSource;
import geometries.Intersectable.GeoPoint;
import primitives.*;
import static primitives.Util.*;
import scene.Scene;

/**
 * RayTracerBasic class extends RayTracerBase, this class determine the color of
 * the image in a particular pixel - according to the ray that come from the
 * camera
 * 
 * @author shmulik
 */
public class RayTracerBasic extends RayTracerBase {
	private static final double DELTA = 0.1;
	private static final int MAX_CALC_COLOR_LEVEL = 10;
	private static final double MIN_CALC_COLOR_K = 0.001;

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
		GeoPoint closestPoint = findClosestIntersection(ray);
		return closestPoint == null ? Color.BLACK : calcColor(closestPoint, ray);
	}

	/**
	 * this method get intersection point (GeoPoint) and ray and calculate the color
	 * in this point according to the Phong Reflectance Mode
	 * 
	 * @param point - the intersection point
	 * @return the color
	 */
	private Color calcColor(GeoPoint intersection, Ray ray, int level, double k) {
		Color color = intersection.geometry.getEmission();
		color = color.add(calcLocalEffects(intersection, ray));
		return 1 == level ? color : color.add(calcGlobalEffects(intersection, ray, level, k));
	}

	/**
	 * Help method - this method calculate the color of a point considering all the
	 * light sources
	 * 
	 * @param intersection - the intersection point
	 * @param ray          - the ray from the light
	 * @return - the color
	 */
	private Color calcLocalEffects(GeoPoint intersection, Ray ray) {
		Vector v = ray.getDir();
		Vector n = intersection.geometry.getNormal(intersection.point);

		double nv = alignZero(n.dotProduct(v));
		if (nv == 0)
			return Color.BLACK;

		Material material = intersection.geometry.getMaterial();

		Color color = Color.BLACK;
		for (LightSource lightSource : scene.lights) {
			Vector l = lightSource.getL(intersection.point);
			double nl = alignZero(n.dotProduct(l));
			if (nl * nv > 0) { // sign(nl) == sing(nv)
				if (unshaded(l, n, intersection, lightSource)) {
					Color lightIntensity = lightSource.getIntensity(intersection.point);
					color = color //
							.add(lightIntensity.scale(calcDiffusive(material.kD, nl) + //
									calcSpecular(material.kS, l, n, v, material.nShininess, nl)));
				}
			}
		}

		return color;
	}

	/**
	 * Help method - this method calculate the diffusive component
	 * 
	 * @param kd - coefficient in the formula
	 * @param nl - dot product between l and the normal When l and n are normalized,
	 *           l � n is the cosine of the angle between them
	 * @return double that represent the diffusive part
	 */
	private double calcDiffusive(double kd, double nl) {
		return kd * Math.abs(nl);
	}

	/**
	 * Help method - this method calculate the Specular component
	 * 
	 * @param ks         - coefficient in the formula
	 * @param l          - vector between the light and the intersection point
	 * @param n          - normal of the body
	 * @param v          - the direction of the camera
	 * @param nShininess - coefficient in the formula
	 * @param nl         - dot product between l and the normal
	 * @return double that represent the Specular part
	 */
	private double calcSpecular(double ks, Vector l, Vector n, Vector v, int nShininess, double nl) {
		Vector r = l.subtract(n.scale(nl * 2));

		double minusVDotR = alignZero(-v.dotProduct(r));
		return minusVDotR <= 0 ? 0 : Math.pow(minusVDotR, nShininess) * ks;
	}

	private boolean unshaded(Vector l, Vector n, GeoPoint geopoint, LightSource light) {
		Vector lightDirection = l.scale(-1); // from point to light source

		Vector delta = n.scale(n.dotProduct(lightDirection) > 0 ? DELTA : -DELTA);
		Point3D point = geopoint.point.add(delta);

		Ray lightRay = new Ray(point, lightDirection);
		List<GeoPoint> intersections = scene.geometries.findGeoIntersections(lightRay, light.getDistance(point));
		return intersections == null;
	}

	private Color calcGlobalEffects(GeoPoint geopoint, Ray ray, int level, double k) {
		Color color = Color.BLACK;
		Material material = geopoint.geometry.getMaterial();
		double kr = material.kR, kkr = k * kr;
		if (kkr > MIN_CALC_COLOR_K) {
			Vector normal = geopoint.geometry.getNormal(geopoint.point);
			double nl = normal.dotProduct(ray.getDir());
		    Vector r = ray.getDir().subtract(normal.scale(nl * 2));
		    Ray reflectedRay = new Ray(geopoint.point,r ,geopoint.geometry.getNormal(geopoint.point));
		    GeoPoint reflectedPoint = findClosestIntersection(reflectedRay);
		    color = color.add(calcColor(reflectedPoint, reflectedRay, level � 1, kkr).scale(kr));
		}
		double kt = material.kt, kkt = k * kt;
		if (kkt > MIN_CALC_COLOR_K) {
		Ray refractedRay = constructRefractedRay(n, geopoint.point, inRay);
		GeoPoint refractedPoint = findClosestIntersection(refractedRay);
		color = color.add(calcColor(refractedPoint, refractedRay, level � 1, kkt).scale(kt));
		}
		return color;
		}

	/**
	 * 
	 * @param ray
	 * @return
	 */
	private GeoPoint findClosestIntersection(Ray ray) { // refactor with line 34?? RED 777
		List<GeoPoint> intersections = scene.geometries.findGeoIntersections(ray);

		// if there is no intersection - return null
		if (intersections == null)
			return null;

		// if there is intersections - the color determine by the close point
		return ray.getClosestGeoPoint(intersections);

	}

	private Color calcColor(GeoPoint geopoint, Ray ray) {
		return calcColor(geopoint, ray, MAX_CALC_COLOR_LEVEL, MIN_CALC_COLOR_K).add(scene.ambientLight.getIntensity());
	}

	constructRefractedRay

}
