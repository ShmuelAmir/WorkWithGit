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
	/**
	 * @param intensity
	 */
	public SpotLight(Color intensity , Point3D position , double kC, double kL , double kQ , Vector direction) {
		super(intensity, position, kQ, kQ, kQ);
		this.direction = direction.normalize();
		
	}
	
	@Override
	public Color getIntensity(Point3D p) {
		Color iL = super.getIntensity(p);
		double numerator = max(0,alignZero( direction.dotProduct(super.getL(p))));
		return iL.scale(numerator);
	}
	
	public double max (double x, double y ) {
		return x>y?x:y;
	}
	
	

}
