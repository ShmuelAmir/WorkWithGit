/**
 * 
 */
package elements;

import primitives.Color;
import primitives.Point3D;
import primitives.Vector;

/**
 * @author user1
 *
 */
public class PointLight extends Light implements LightSource {
	private Point3D position;
	private double kC = 1, kL = 0, kQ = 0;
	
	/**
	 * @param kC the kC to set
	 */
	public PointLight setkC(double kC) {
		this.kC = kC;
		return this;
	}

	/**
	 * @param kL the kL to set
	 */
	public PointLight setkL(double kL) {
		this.kL = kL;
		return this;
	}

	/**
	 * @param kQ the kQ to set
	 */
	public PointLight setkQ(double kQ) {
		this.kQ = kQ;
		return this;
	}

	/**
	 * @param intensity
	 */
	public PointLight(Color intensity, Point3D position) {
		super(intensity);
		this.position = position;
	}

	@Override
	public Color getIntensity(Point3D p) {
		double distanceSquared = p.distanceSquared(position);
		double denominator; // save the mechne
		denominator = kC + kL * Math.sqrt(distanceSquared) + kQ * distanceSquared;

		return intensity.reduce(denominator);
	}

	@Override
	public Vector getL(Point3D p) {
		return p.subtract(position).normalize();
	}

}