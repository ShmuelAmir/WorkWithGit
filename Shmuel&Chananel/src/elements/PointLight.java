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
	private double kC, kL ,kQ;
	/**
	 * @param intensity
	 */
	public PointLight(Color intensity , Point3D position , double kC, double kL , double kQ) {
		super(intensity);
		this.kC = kC;
		this.kL = kL;
		this.kQ = kQ;
		this.position = position;
	}

	@Override
	public Color getIntensity(Point3D p) {
		double distanceSquared = p.distanceSquared(position);
		double denominator;     //save the mechne 
		denominator = kC + kL*Math.sqrt(distanceSquared) + kQ*distanceSquared;
				
		return intensity.reduce(denominator);
	}

	@Override
	public Vector getL(Point3D p) {
		return p.subtract(position).normalize();
	}

}
