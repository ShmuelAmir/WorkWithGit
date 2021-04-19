package geometries;

import java.util.List;
import static primitives.Util.*;
import primitives.*;

/**
 * Tube class represents Tube in the geometric space (a long, hollow cylinder)
 * 
 * @author shmulik
 */
public class Tube implements Geometry {
	protected Ray axisRay; // ray that defines the axis of the tube
	protected double radius; // radius of the ray

	/**
	 * Constructor that build tube from ray and radius.
	 * 
	 * @param ray
	 * @param radius
	 */
	public Tube(Ray ray, double radius) {
		this.axisRay = ray;
		this.radius = radius;
	}

	/**
	 * @return the axisRay
	 */
	public Ray getAxisRay() {
		return axisRay;
	}

	/**
	 * @return the radius
	 */
	public double getRadius() {
		return radius;
	}

	@Override
	public Vector getNormal(Point3D point) {
		Point3D p0 = axisRay.getP0();
		Vector dir = axisRay.getDir();

		double t = dir.dotProduct(point.subtract(p0)); // t = v * (P - P0)

		if (isZero(t)) { // P0 and the point are on the same plane
			return point.subtract(p0).normalize();
		}

		Point3D center = axisRay.getPoint(t); // O = P0 + t*v

		return point.subtract(center).normalize();
	}

	@Override
	public List<Point3D> findIntersections(Ray ray) {
		Vector vA = axisRay.getDir();
		Point3D pA = axisRay.getP0();
		Vector v = ray.getDir();
		Point3D p = ray.getP0();
		double A, B, C;
		
		Vector yA = null;
		boolean vOrthogonalVa = true;
		try {
			Vector xA = vA.scale(v.dotProduct(vA));
			vOrthogonalVa = false;
			
			yA = v.subtract(xA);
		} catch (IllegalArgumentException e) {
			if (vOrthogonalVa) {
				yA = vA;
			}
			
			return null;
		}
		finally {
			A = yA.dotProduct(yA);
		}
		
		
		if (p.equals(pA)) {
			B = 0;
			C = - radius * radius;
		}
		
	
		else {
			boolean vAOrthogonalDeltaP = true;
			boolean deltaPSubtractXB = true;
			Vector deltaP = p.subtract(pA);
			Vector yB = null;
			
			
			try {
				Vector xB = vA.scale(deltaP.dotProduct(vA));
				vAOrthogonalDeltaP = false;
				yB = deltaP.subtract(xB);
				
			} catch (IllegalArgumentException e) {
				if ( vAOrthogonalDeltaP) {
					yB = deltaP;
				}
				
				else {
					B = 0;
					C = - radius * radius;
					
					double d = (B*B - 4*C*C) / 2*A;
					double t1, t2;
					if (d > 0) {
						t1 = (-B+Math.sqrt(d)) / 2*A;
						t2 = (-B-Math.sqrt(d)) / 2*A;
						return List.of(ray.getPoint(t1), ray.getPoint(t2));
					}
					else if (d == 0) {
						t1 = (-B+Math.sqrt(d)) / 2*A;
						return List.of(ray.getPoint(t1));	
					}
					
					return null;
					
				}
				
			}
			
			try {
				B = yA.dotProduct(yB) * 2;
				
			} catch (IllegalArgumentException e) {
				B = 0;
			}
			
			C = yB.dotProduct(yB) - radius * radius;
		}
		
		
		double d = (B*B - 4*A*C);
		double t1, t2;
		if (d > 0) {
			t1 = Math.sqrt(d);
			t1 = -B+Math.sqrt(d);
			t1 = (-B+Math.sqrt(d)) / (2*A);
			t2 = (-B-Math.sqrt(d)) / (2*A);
			return List.of(ray.getPoint(t1), ray.getPoint(t2));
		}
		else if (d == 0) {
			t1 = (-B+Math.sqrt(d)) / 2*A;
			return List.of(ray.getPoint(t1));	
		}
		
		return null;
	}

	@Override
	public String toString() {
		return axisRay.toString() + " " + radius;
	}
}
