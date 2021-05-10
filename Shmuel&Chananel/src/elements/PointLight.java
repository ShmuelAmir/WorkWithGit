/**
 * 
 */
package elements;

import primitives.Color;
import primitives.Point3D;
import primitives.Vector;

/**
 * PointLight is a class that represents PointLight - sours of light that light in 360 deg  
 * this class  extends Light class and implements LightSource interface
 * @author Shmulik & Chananel
 */
public class PointLight extends Light implements LightSource {
	// the posion of the light
	private Point3D position;
	
	// kC kL kQ - is the params that represent the attenuation with distance 
	private double kC = 1, kL = 0, kQ = 0;
	
	/**
	 * set kC
	 * @param kC the kC to set
	 */
	public PointLight setkC(double kC) {
		this.kC = kC;
		return this;
	}

	/**
	 * set kL
	 * @param kL the kL to set
	 */
	public PointLight setkL(double kL) {
		this.kL = kL;
		return this;
	}

	/**
	 * set kQ
	 * @param kQ the kQ to set
	 */
	public PointLight setkQ(double kQ) {
		this.kQ = kQ;
		return this;
	}

	/**
	 * Constructor of point light
	 * @param intensity
	 * @param position
	 */
	public PointLight(Color intensity, Point3D position) {
		super(intensity);
		this.position = position;
	}

	/**
	 * get Intensity - get point and return the intensity in this point
	 * @param p  -  intensity in this point
	 */
	@Override
	public Color getIntensity(Point3D p) {
		double distanceSquared = p.distanceSquared(position);
		double denominator = kC + kL * Math.sqrt(distanceSquared) + kQ * distanceSquared;
		return intensity.reduce(denominator);
	}

	/**
	 * L vector - represents the direction of the light - to certain point
	 * @param p - the certain point
	 */
	@Override
	public Vector getL(Point3D p) {
		return p.subtract(position).normalize();
	}

}
