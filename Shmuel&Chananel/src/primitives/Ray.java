package primitives;

import java.util.List;

/**
 * Ray - any of a set of straight lines passing through one point
 * 
 * @author shmulik
 */
public class Ray {
	private Point3D p0; // the starting point
	private Vector dir; // the vector from the Starting point
	
	/**
	 * Constructor that get point and vector and create ray
	 * @param point - starting point
	 * @param vector - direction vector
	 */
	public Ray(Point3D point, Vector vector) {
		p0 = point;
		dir = vector.normalized();
	}

	/**
	 * @return the p0
	 */
	public Point3D getP0() {
		return p0;
	}

	/**
	 * @return the dir
	 */
	public Vector getDir() {
		return dir;
	}

	public Point3D getPoint(double t) {
		return p0.add(dir.scale(t));
	}
	
	public Point3D getClosestPoint (List<Point3D> points) {
		if(points == null)
			return null;
		Point3D minPoint = points.get(0);
		double  minDistance = minPoint.distance(p0);
		for (Point3D point : points) {
			if (point.distance(p0) < minDistance) {
				minPoint = point;
				minDistance = point.distance(p0);
			}
		}
		
		return minPoint;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Ray))
			return false;
		Ray other = (Ray) obj;
		return this.p0.equals(other.p0) && this.dir.equals(other.dir);
	}
	
	@Override
	public String toString() {
		return p0.toString() + " " + dir.toString();
	}
}
