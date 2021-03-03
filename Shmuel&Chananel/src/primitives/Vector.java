package primitives;

public class Vector {
	Point3D head;

	/**
	 *  constructor with point
	 * @param head
	 */
	public Vector(Point3D head) {
		this.head = head;
	}
	
	/**
	 * constructor with  3 double number
	 * @param head
	 */
	public Vector(double x,double y,double z ) {
		if(x == 0 && y == 0 && z == 0) {
			throw new IllegalArgumentException ("No vector 0")
		}
		this.head = new Point3D(x, y, z);
	}
	
	/**
	 * constructor with  3 double number
	 * @param head
	 */
	public Vector(Coordinate x,Coordinate y,Coordinate z) {
		Coordinate zero = new Coordinate(0);
		if(x.equals(zero)) {
			throw new IllegalArgumentException ("No vector 0")
		}
		this.head =  new Point3D(x, y, z);
	}
	
	
	

}
