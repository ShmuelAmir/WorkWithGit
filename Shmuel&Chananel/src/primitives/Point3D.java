package primitives;

/**
 * point with 3 d
 * 
 * @author user1
 *
 */
public class Point3D {
	Coordinate x;
	Coordinate y;
	Coordinate z;
	/**
	 * save zero number
	 */
	public static final Point3D ZERO = new Point3D(0, 0, 0);

	/**
	 * constructor that get three Coordinate and build Point3D
	 * 
	 * @param x For the position on the axis x
	 * @param y For the position on the axis y
	 * @param z For the position on the axis z
	 */
	public Point3D(Coordinate x, Coordinate y, Coordinate z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	/**
	 * constructor that get three double and build Point3D
	 * 
	 * @param x For the position on the axis x
	 * @param y For the position on the axis y
	 * @param z For the position on the axis z
	 */

	public Point3D(double x, double y, double z) {
		this.x = new Coordinate(x);
		this.y = new Coordinate(y);
		this.z = new Coordinate(z);
	}

	/**
	 * subtract between tow vector
	 * 
	 * @param point
	 * @return result
	 */
	public Vector subtract(Point3D point) {
		return new Vector(this.x.coord - point.x.coord, this.y.coord - point.y.coord, this.z.coord - point.z.coord);
	}

	/**
	 * Adding a vector to a point
	 * 
	 * @param vector
	 * @returna new point
	 */
	public Point3D add(Vector vector) {
		return new Point3D(this.x.coord + vector.getHead().x.coord, this.y.coord + vector.getHead().y.coord,
				this.z.coord + vector.getHead().z.coord);
	}

	/**
	 * calculate the distance between two points squared
	 * 
	 * @param point
	 * @return distance squared
	 */
	public double distanceSquared(Point3D point) {
		double xSquared = (this.x.coord - point.x.coord) * (this.x.coord - point.x.coord);
		double ySquared = (this.y.coord - point.y.coord) * (this.y.coord - point.y.coord);
		double zSquared = (this.z.coord - point.z.coord) * (this.z.coord - point.z.coord);
		return xSquared + ySquared + zSquared;
	}

	/**
	 * As the function above only with the root of the result
	 * 
	 * @param point
	 * @return
	 */
	public double distance(Point3D point) {
		return Math.sqrt(distanceSquared(point));
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Point3D))
			return false;
		Point3D other = (Point3D) obj;
		return this.x.equals(other.x) && this.y.equals(other.y) && this.z.equals(other.z);
	}

	@Override
	public String toString() {
		return "(" + x.toString() + ", " + y.toString() + ", " + z.toString() + ")";
	}
}
