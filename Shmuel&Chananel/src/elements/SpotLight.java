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

	/**
	 * @param narrowBeam the narrowBeam to set
	 */
	public SpotLight setNarrowBeam(int narrowBeam) {
		this.narrowBeam = narrowBeam;
		return this;
	}

	@Override
	public Color getIntensity(Point3D p) {
		double numerator = alignZero(direction.dotProduct(super.getL(p)));
		if (numerator <= 0)
			return Color.BLACK;
		if (narrowBeam != 1)
			numerator = Math.pow(numerator, narrowBeam);
		return super.getIntensity(p).scale(numerator);
	}
}
