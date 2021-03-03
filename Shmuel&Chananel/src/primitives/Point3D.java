package primitives;

/**
 * point with 3 d
 * @author user1
 *
 */
public class Point3D {
	Coordinate x;
	Coordinate y;
	Coordinate z;
	
	private static final Point3D ZERO = new Point3D(0, 0, 0);
	
	public Point3D(Coordinate x, Coordinate y, Coordinate z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public Point3D(double x, double y, double z) {
		this.x = new Coordinate(x);
		this.y = new Coordinate(y);
		this.z = new Coordinate(z);
	}

	/**
	 * 
	 * @param point
	 * @return
	 */
	public Vector subtract(Point3D point) {
		return new Vector(this.x.coord - point.x.coord, this.y.coord - point.y.coord, this.z.coord - point.z.coord);
	}
	
	public Point3D add(Vector vector) {
		return Point3D(this.x.coord + vector.head.x.coord, this.y.coord + vector.head.y.coord, this.z.coord + vector.head.z.coord);
	}
	
	public double distanceSquared(Point3D point) {
		double xSquared = (this.x.coord - point.x.coord) * (this.x.coord - point.x.coord);
		double ySquared = (this.y.coord - point.y.coord) * (this.y.coord - point.y.coord);
		double zSquared = (this.z.coord - point.z.coord) * (this.z.coord - point.z.coord);
		return xSquared + ySquared + zSquared;
	}
	
	public double distance(Point3D point) {
		return Math.sqrt(distanceSquared(point));
	}
}
