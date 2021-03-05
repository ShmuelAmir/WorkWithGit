package primitives;

/**
 * Ray - any of a set of straight lines passing through one point
 * 
 *
 */
public class Ray {
	private Point3D p0;
	private Vector dir;
	
	/**
	 * Constructor that get point and vector and creat ray
	 * @param point
	 * @param vector
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
