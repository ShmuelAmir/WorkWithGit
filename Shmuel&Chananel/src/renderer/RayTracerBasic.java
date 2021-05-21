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
	
	private static final int MAX_CALC_COLOR_LEVEL = 10;
	private static final double MIN_CALC_COLOR_K = 0.001;
	private static final double INITIAL_K = 1.0;
	

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
		color = color.add(calcLocalEffects(intersection, ray,k));
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
	private Color calcLocalEffects(GeoPoint intersection, Ray ray,double k) {
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
				double ktr = transparency(lightSource, l, n, intersection);
				if (ktr * k > MIN_CALC_COLOR_K) {
//				if (unshaded(l, n, intersection, lightSource)) {
					Color lightIntensity = lightSource.getIntensity(intersection.point).scale(ktr);
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
	 *           l · n is the cosine of the angle between them
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
		
		Ray lightRay = new Ray(geopoint.point, lightDirection, n); // refactored ray head move
		
		List<GeoPoint> intersections = scene.geometries.findGeoIntersections(lightRay, light.getDistance(geopoint.point));
		if (intersections == null) return true;
		
		for (GeoPoint gp : intersections) {
			if (gp.geometry.getMaterial().kT == 0)
			return false;
		}
		return true;
	}

	/*
	 * private Color calcGlobalEffects(GeoPoint geopoint, Ray ray, int level, double
	 * k) { Color color = Color.BLACK; Material material =
	 * geopoint.geometry.getMaterial(); double kr = material.kR, kkr = k * kr; if
	 * (kkr > MIN_CALC_COLOR_K) { Vector normal =
	 * geopoint.geometry.getNormal(geopoint.point); double nl =
	 * normal.dotProduct(ray.getDir()); Vector r =
	 * ray.getDir().subtract(normal.scale(nl * 2)).normalized(); Ray reflectedRay =
	 * new Ray(geopoint.point,r ,geopoint.geometry.getNormal(geopoint.point));
	 * GeoPoint reflectedPoint = findClosestIntersection(reflectedRay); color =
	 * color.add(calcColor(reflectedPoint, reflectedRay, level - 1, kkr).scale(kr));
	 * } double kt = material.kT, kkt = k * kt; if (kkt > MIN_CALC_COLOR_K) { Ray
	 * refractedRay = new Ray(geopoint.point,
	 * ray.getDir(),geopoint.geometry.getNormal(geopoint.point)); GeoPoint
	 * refractedPoint = findClosestIntersection(refractedRay); color =
	 * color.add(calcColor(refractedPoint, refractedRay, level - 1, kkt).scale(kt));
	 * } return color; }
	 */
	
	
	
    /**
     * Calculate global effect of the light on the color
     *
     * @param geopoint the point
     * @param inRay    the ray
     * @param level    level
     * @param k
     * @return the color
     */
    private Color calcGlobalEffects(GeoPoint geopoint, Ray inRay, int level, double k) {
        Color color = Color.BLACK;
        Material material = geopoint.geometry.getMaterial();
        double kr = material.kR;
        double kkr = k * kr;
        Vector n = geopoint.geometry.getNormal(geopoint.point);
        if (kkr > MIN_CALC_COLOR_K) {
            Ray reflectedRay = constructReflectedRay(n, geopoint.point, inRay);
            GeoPoint reflectedPoint = findClosestIntersection(reflectedRay);
            if (reflectedPoint != null){
                color = color.add(calcColor(reflectedPoint, reflectedRay, level - 1, kkr).scale(kr));
            }
        }
        double kt = material.kT;
        double kkt = k * kt;
        if (kkt > MIN_CALC_COLOR_K) {
            Ray refractedRay = constructRefractedRay(n, geopoint.point, inRay);
            GeoPoint refractedPoint = findClosestIntersection(refractedRay);
            if (refractedPoint != null){
                color = color.add(calcColor(refractedPoint, refractedRay, level - 1, kkt).scale(kt));
            }
        }
        return color;
    }

    /**
     * Construct the ray getting refracted on a point
     * @param n normal to the point
     * @param point the point
     * @param inRay the ray entering
     * @return the refracted ray
     */
    private Ray constructRefractedRay(Vector n, Point3D point, Ray inRay) {
        return new Ray(point, inRay.getDir(), n);
    }

    /**
     * Construct the ray getting reflected on a point
     * @param n normal to the point
     * @param point the point
     * @param inRay the ray entering
     * @return the reflected ray
     */
    private Ray constructReflectedRay(Vector n, Point3D point, Ray inRay) {
        //𝒓 = 𝒗 − 𝟐 ∙ (𝒗 ∙ 𝒏) ∙ 𝒏
        Vector v = inRay.getDir();
        Vector r = null;
        try {
            r = v.subtract(n.scale(v.dotProduct(n)).scale(2)).normalized();
        } catch (Exception e) {
            return null;
        }
        return new Ray(point, r, n);
    }

    private GeoPoint findClosestIntersection(Ray ray) {
        List<GeoPoint> geoPoints = scene.geometries.findGeoIntersections(ray);
        return ray.getClosestGeoPoint(geoPoints);
    }
	
	
    private double transparency(LightSource light, Vector l, Vector n, GeoPoint geopoint) {
    	Vector lightDirection = l.scale(-1); // from point to light source
    	Ray lightRay = new Ray(geopoint.point, lightDirection, n);
    	var intersections = scene.geometries.findGeoIntersections(lightRay,light.getDistance(geopoint.point));
    	if (intersections == null) return 1.0;
    	double ktr = 1.0;
    	for (GeoPoint gp : intersections) {
    	
    	ktr *= gp.geometry.getMaterial().kT;
    	if (ktr < MIN_CALC_COLOR_K) return 0.0;
    	}
    	
    	return ktr;
    	}

	
//    private double transparency(LightSource light, Vector l, Vector n, GeoPoint geopoint) {
//        Vector lightDirection = l.scale(-1); // from point to light source
//        Ray lightRay = new Ray(geopoint.point, lightDirection, n);
//        double lightDistance = light.getDistance(geopoint.point);
//        var intersections = scene.geometries.findGeoIntersections(lightRay);
//        if (intersections == null) return 1.0;
//        double ktr = 1.0;
//        for (GeoPoint gp : intersections) {
//            if (alignZero(gp.point.distance(geopoint.point) - lightDistance) <= 0) {
//                ktr *= gp.geometry.getMaterial().kT;
//                if (ktr < MIN_CALC_COLOR_K) return 0.0;
//            }
//        }
//        return ktr;
//    }
	
	
	
	
	

//	/**
//	 * 
//	 * @param ray
//	 * @return
//	 */
//	private GeoPoint findClosestIntersection(Ray ray) { // refactor with line 34?? RED 777
//		List<GeoPoint> intersections = scene.geometries.findGeoIntersections(ray);
//
//		// if there is no intersection - return null
//		if (intersections == null)
//			return null;
//
//		// if there is intersections - the color determine by the close point
//		return ray.getClosestGeoPoint(intersections);
//
//	}

	private Color calcColor(GeoPoint geopoint, Ray ray) {
		return calcColor(geopoint, ray, MAX_CALC_COLOR_LEVEL, INITIAL_K).add(scene.ambientLight.getIntensity());
	}



}
