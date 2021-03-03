package primitives;

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

	public Vector subtract(Point3D point) {
		
	}
}
