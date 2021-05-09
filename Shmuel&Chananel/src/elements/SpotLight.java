/**
 * 
 */
package elements;

import primitives.*;
import static primitives.Util.*;


/**
 * @author user1
 *
 */
public class SpotLight extends PointLight {
	private Vector direction;
	int narrowBeam = 1; 
	
	/**
	 * @param intensity
	 */
	public SpotLight(Color intensity, Point3D position, Vector direction) {
		super(intensity, position);
		this.direction = direction.normalize();
	}

	
//	@Override
//	public Color getIntensity(Point3D p) {
//		Color iL = super.getIntensity(p);
//		double numerator = max(0, alignZero(direction.dotProduct(super.getL(p))));
//		return iL.scale(numerator);
//	}
	
	/**
	 * @param narrowBeam the narrowBeam to set
	 */
	public SpotLight setNarrowBeam(int narrowBeam) {
		this.narrowBeam = narrowBeam;
		return this;
	}


	@Override
	public Color getIntensity(Point3D p) {
		Color iL = super.getIntensity(p);
		double numerator = max(0, alignZero(direction.dotProduct(super.getL(p))));
		return iL.scale(Math.pow(numerator, narrowBeam));
	}
}
